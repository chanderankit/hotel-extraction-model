package com.extraction.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ContentDto {
    String star;
    String area;
    String city;
    String stateCode;
    String country;
    String latitude;
    String longitude;
    List<LandmarkDto> landmarks;
    List<AmenitiesDto> amenities;
    Map<String ,RoomDto> rooms;
    String propertyType;
    boolean instantBook;
    List<ReviewRatingDto> reviewRating;
    GoogleReviewInfoDto googleReviewInfo;
}
