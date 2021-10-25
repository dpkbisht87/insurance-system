package com.hedvig.insurance;

import com.hedvig.insurance.model.*;
import com.hedvig.insurance.report.AllEventReport;
import com.hedvig.insurance.report.CreateDeleteEventReport;
import com.hedvig.insurance.report.ReportContext;
import com.hedvig.insurance.service.MetricsService;
import com.hedvig.insurance.utils.FileResourcesUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class InsuranceMain {
    public static void main(String[] args) {
        List<Event> eventList;
        String absolutePath;
        FileResourcesUtils fileUtils = new FileResourcesUtils();
        MetricsService metricsService = new MetricsService(new ArrayList<>());
        try {
            absolutePath = fileUtils.getFileFromResource("stest-data.txt");
            eventList = fileUtils.getRecords(absolutePath);

        System.out.println("Create-Delete Report");         
        ReportContext reportContext = new ReportContext(new CreateDeleteEventReport(metricsService, fileUtils));
        List<Report> createDeleteEventReportList = reportContext.generateReport(eventList);
        System.out.println(createDeleteEventReportList);  
        
        System.out.println("All Event Report");
        MetricsService metricsService2 = new MetricsService(new ArrayList<>());
        reportContext = new ReportContext(new AllEventReport(metricsService2, fileUtils));
        List<Report> allEventReportList = reportContext.generateReport(eventList);
        System.out.println(allEventReportList);
            
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
