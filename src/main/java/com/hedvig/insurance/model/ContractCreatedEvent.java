package com.hedvig.insurance.model;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
public class ContractCreatedEvent extends Event {
    private int premium;
    private Date startDate;
    
    @Override
    public String toString() {
        return "ContractCreatedEvent{" +
                       "contractId=" + getContractId() +
                       ", premium=" + premium +
                       ", startDate=" + startDate +
                       '}';
    }
}
