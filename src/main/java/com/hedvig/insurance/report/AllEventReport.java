package com.hedvig.insurance.report;

import com.hedvig.insurance.model.*;
import com.hedvig.insurance.service.MetricsService;
import com.hedvig.insurance.utils.FileResourcesUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllEventReport implements  ReportType{
    MetricsService metricsService;
    FileResourcesUtils fileUtils;
    
    public AllEventReport(MetricsService metricsService, FileResourcesUtils fileResourcesUtils){
        this.metricsService =  metricsService;
        this.fileUtils = fileResourcesUtils;
    }
    
    @Override
    public List<Report> generateReport(List<Event> eventList) {
        Map<String, Integer> activeContractsMap = new HashMap<>();
        int month = 0;
        String contractId = null;
        int premium = 0;
        int lastPremium = 0;
        List<Report> reportList = null;
    
        for(Event event: eventList) {
            contractId = event.getContractId();
        
            if (event instanceof ContractCreatedEvent) {
                ContractCreatedEvent curEvent = (ContractCreatedEvent) event.get();
                premium = curEvent.getPremium();
                month = fileUtils.getMonth(curEvent.getStartDate());
                activeContractsMap.put(contractId, premium);
            
            } else if (event instanceof PriceIncreasedEvent) {
                PriceIncreasedEvent currEvent = (PriceIncreasedEvent) event.get();
                int newPremium = currEvent.getPremiumIncrease();
                month = fileUtils.getMonth(currEvent.getAtDate());
                //Update Map
                int currentPremium = activeContractsMap.get(contractId);
                activeContractsMap.put(contractId, currentPremium + newPremium);

            } else if (event instanceof PriceDecreasedEvent) {
                PriceDecreasedEvent currEvent = (PriceDecreasedEvent) event.get();
                int newPremium = currEvent.getPremiumReduction();
                month = fileUtils.getMonth(currEvent.getAtDate());
                //Update Map
                int currentPremium = activeContractsMap.get(contractId);
                activeContractsMap.put(contractId, currentPremium - newPremium);

            } else if (event instanceof ContractTerminatedEvent) {
                ContractTerminatedEvent currEvent = (ContractTerminatedEvent) event.get();
                lastPremium = activeContractsMap.get(contractId);
                month = fileUtils.getMonth(currEvent.getTerminationDate());
                //Update Map
                activeContractsMap.remove(contractId);
            }
        
            reportList = metricsService.updateReport(activeContractsMap, month, lastPremium);
        }
        metricsService = null;
        return reportList;
    }
}
