package com.extraction.model;

import lombok.Data;

@Data
public class PricingDto {
    PerNightPriceDto perNightPrice;
    String price;
}
