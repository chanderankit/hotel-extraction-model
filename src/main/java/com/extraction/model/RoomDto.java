package com.extraction.model;

import lombok.Data;

import java.util.List;

@Data
public class RoomDto {
    String name;
    String maxGuestOccupency;
    List<BedDto> beds;
    List<AmenitiesDto> amenities;
}
