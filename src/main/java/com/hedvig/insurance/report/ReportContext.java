package com.hedvig.insurance.report;

import com.hedvig.insurance.model.Event;
import com.hedvig.insurance.model.Report;

import java.util.List;

public class ReportContext {
    private final ReportType reportType;
    
    public ReportContext(ReportType reportType){
        this.reportType = reportType;
    }
    
    public List<Report> generateReport(List<Event> eventList){
        return reportType.generateReport(eventList);
    }
}
