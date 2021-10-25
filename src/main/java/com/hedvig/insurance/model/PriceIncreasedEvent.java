package com.hedvig.insurance.model;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class PriceIncreasedEvent extends Event {
    private int premiumIncrease;
    private Date atDate;
}
