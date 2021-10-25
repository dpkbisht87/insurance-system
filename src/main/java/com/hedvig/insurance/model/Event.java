package com.hedvig.insurance.model;

import lombok.Getter;

@Getter
public abstract class Event {
    private String contractId;
    public Event get(){
        return this;
    }
}
