package com.hedvig.insurance.factory;

import com.hedvig.insurance.model.*;

public class EventFactory {
    public static Event getEvent(String eventType){
        if(eventType == null){
            return null;
        }
        if(eventType.contains("ContractCreatedEvent")){
            return new ContractCreatedEvent();
        } else if(eventType.contains("ContractTerminatedEvent")){
            return new ContractTerminatedEvent();
        } else if (eventType.contains("PriceIncreasedEvent")){
            return new PriceIncreasedEvent();
        } else if (eventType.contains("PriceDecreasedEvent")){
            return new PriceDecreasedEvent();
        }
        return null;
    }
}
