package com.extraction.model;

import lombok.Data;

@Data
public class RatesDto {
    String name;
    String roomTypeId;
    PricingDto pricing;
}
