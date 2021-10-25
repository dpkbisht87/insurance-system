package com.hedvig.insurance.utils;

import com.google.gson.Gson;
import com.hedvig.insurance.model.ContractCreatedEvent;
import com.hedvig.insurance.model.ContractTerminatedEvent;
import com.hedvig.insurance.model.Event;
import com.hedvig.insurance.factory.EventFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileResourcesUtils {
    public String getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        String absolutePath = null;
        File file = null;
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            file = new File(resource.toURI());
            absolutePath = file.getAbsolutePath();
        }
        return absolutePath;
    }
    
    public List<Event> getRecords(String fileName) throws IOException {
        List<Event> eventList = new ArrayList<>();
        Stream<String> stream = Files.lines(Paths.get(fileName));
        List<String> recordList = stream.collect(Collectors.toList());
        Gson gson = new Gson();
        for (String eventDetailsStr : recordList) {
            if (!eventDetailsStr.isEmpty()) {
                Event event = EventFactory.getEvent(eventDetailsStr);
                if (event != null) {
                    event = gson.fromJson(eventDetailsStr, event.getClass());
                    eventList.add(event);
                } else {
                    System.out.println("No valid event found in record " + eventDetailsStr);
                }
            }
        }
        return eventList;
    }
    
    public int getMonth(Date activityDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(activityDate);
        return cal.get(Calendar.MONTH) + 1;
    }
}
