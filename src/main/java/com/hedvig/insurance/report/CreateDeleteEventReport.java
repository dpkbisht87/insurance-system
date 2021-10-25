package com.hedvig.insurance.report;

import com.hedvig.insurance.model.ContractCreatedEvent;
import com.hedvig.insurance.model.ContractTerminatedEvent;
import com.hedvig.insurance.model.Event;
import com.hedvig.insurance.model.Report;
import com.hedvig.insurance.service.MetricsService;
import com.hedvig.insurance.utils.FileResourcesUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateDeleteEventReport implements  ReportType{
    MetricsService metricsService;
    FileResourcesUtils fileUtils;
    
    public CreateDeleteEventReport(MetricsService metricsService, FileResourcesUtils fileResourcesUtils){
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
                
            } else if (event instanceof ContractTerminatedEvent) {
                ContractTerminatedEvent currEvent = (ContractTerminatedEvent) event.get();
                lastPremium = activeContractsMap.get(contractId);
                month = fileUtils.getMonth(currEvent.getTerminationDate());
                //Update Map
                activeContractsMap.remove(contractId);
            }
            reportList = metricsService.updateReport(activeContractsMap, month, lastPremium);
        }
        return reportList;
    }
}
