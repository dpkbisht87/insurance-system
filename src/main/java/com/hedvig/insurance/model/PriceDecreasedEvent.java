package com.hedvig.insurance.model;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class PriceDecreasedEvent extends Event {
    private int premiumReduction;
    private Date atDate;
}
