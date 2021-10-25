package com.hedvig.insurance.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Month;
import java.util.Calendar;
import java.util.Map;

@Getter
@Setter
@ToString
public class Report {
    
    private int month;
    private int numberOfContracts;
    private int actualGrossPremium;
    private int expectedGrossPremium;
    
    public Report(int month){
        this.month = month;
    }
    
    @Override
    public String toString() {
        return "Report {" +
                       " month = " + Month.of(month).name() +
                       ", #Contracts = " + numberOfContracts +
                       ", AGWP = " + actualGrossPremium +
                       ", EGWP = " + expectedGrossPremium + 
                       '}' + "\n";
    }
}
