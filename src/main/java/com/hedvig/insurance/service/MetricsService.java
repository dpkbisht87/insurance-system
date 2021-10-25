package com.hedvig.insurance.service;

import com.hedvig.insurance.model.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MetricsService {
    int prevAGWP = 0;
    List<Report> reportList;
    public MetricsService(List<Report> reportList){
        this.reportList = reportList;
    }
    public List<Report> updateReport(Map<String, Integer> activeContractsMap, int month, int lastPremium){
        int noOfContracts = activeContractsMap.size();
        boolean newReport = false;
        Report currentReport = reportList.stream().filter(report -> month == report.getMonth()).findFirst().orElse(null);
        
        if (currentReport == null){
            currentReport = new Report(month);
            newReport = true;
        }
        if(lastPremium != 0){
            noOfContracts += 1;
        }
        currentReport.setNumberOfContracts(noOfContracts);
        
        int curAGWP = calculateAGWP(activeContractsMap);
        curAGWP += lastPremium;
        currentReport.setActualGrossPremium(curAGWP + prevAGWP);

        int egwp = calculateEGWP(activeContractsMap, month, lastPremium);
        currentReport.setExpectedGrossPremium(egwp);
        prevAGWP += curAGWP;
        
        if(newReport){
            reportList.add(currentReport);
        }
        return reportList;
    }
    
    public int calculateAGWP(Map<String, Integer> activeContractsMap){
        int agwp = 0;
        for (int premium : activeContractsMap.values()){
            agwp += premium;
        }
        return agwp;
    }
    public int calculateEGWP(Map<String, Integer> activeContractsMap, int month, int lastPremium){
        int agwp = 0;
        for (int premium : activeContractsMap.values()){
            agwp += premium;
        }
        return (agwp) * (12 - month + 1) + prevAGWP + lastPremium;
    }
}
