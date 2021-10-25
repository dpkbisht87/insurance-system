package com.hedvig.insurance.report;

import com.hedvig.insurance.model.Event;
import com.hedvig.insurance.model.Report;

import java.util.List;

public interface ReportType {
    public List<Report> generateReport(List<Event> eventList);
}
