package com.hedvig.insurance.model;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
public class ContractTerminatedEvent extends Event {
    private Date terminationDate;
    
    @Override
    public String toString() {
        return "ContractTerminatedEvent{" +
                       "contractId= " + getContractId() +
                       ", terminationDate=" + terminationDate +
                       '}';
    }
}
