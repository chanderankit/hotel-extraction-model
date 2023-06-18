package com.extraction.util;

import com.extraction.model.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class HotelMapper {
    private static List<String[]> completeData = new ArrayList<>();
    static boolean flag = false;
    static void initial() {
        completeData.add(new String[] { "date", "location", "city", "state", "country", "property_type", "room_type",
                "Hotel_rating", "accommodates", "bathroom", "bedroom", "beds", "bed_type", "price", "security_deposit",
                "cleaning_fee", "has_availability", "number_of_reviews", "review_scores_rating", "instant_bookable",
                "essentials", "kitchen", "air_conditioning", "washer", "tv", "wifi", "free_parking_on_premises", "gym",
                "pool", "breakfast", "airport", "restaurant", "bar", "bus_station", "train_station", "shopping_mall",
                "Longitude", "Latitude"});
    }
    public static  List<String[]> getHotelData(EntityDto hotel) {
        List<String> result = new ArrayList<>();

        if (!flag) {
            initial();
            flag = true;
        }

        ContentDto content = hotel.getData().getContent();
        List<RatesDto> rates = hotel.getData().getRates();
        ExtraInfoDto extraInfo = hotel.getData().getExtraInfo();
        String landmarks = content.getLandmarks().toString();

        String roomType = "";
        String bedCount = "1";
        String bedType = "";
        String maxGuestOccupency = "1";
        boolean parking = false;
        boolean kitchen = false;
        boolean air_conditioning = false;
        boolean washer = false;
        boolean tv = false;
        boolean wifi = false;
        boolean pool = false;
        boolean breakfast = false;
        boolean airport = false;
        boolean restaurant = false;
        boolean bar = false;
        boolean bus_station = false;
        boolean gym = false;
        String price = "";
        String securityDeposit = "";

        List<AmenitiesDto> amenities = content.getAmenities();
        List<String> hotelAmenities = amenities.stream().map(amenitiesDto -> amenitiesDto.getName()).collect(Collectors.toList());
        Map<String, RoomDto> rooms = content.getRooms();

        for(String facility: hotelAmenities) {
            if (facility.contains("Parking")) parking = true;
            if (facility.contains("Bar")) bar = true;
            if (facility.contains("Pool")) pool = true;
            if (facility.toLowerCase().contains("fitness") || facility.toLowerCase().contains("gym") || facility.toLowerCase().contains("Gym")) gym = true;
            if (facility.contains("Swimming pool")) pool = true;
            if (facility.contains("Breakfast")) breakfast = true; //

        }

        for(Map.Entry<String, RoomDto> entry : rooms.entrySet()) {
            result.clear();
            String roomId = entry.getKey();

            roomType = entry.getValue().getName();
            if (roomType.toLowerCase().contains("close")) continue;

            if (entry.getValue().getBeds() != null) {
                if (entry.getValue().getBeds().get(0) != null) {
                    if (entry.getValue().getBeds().get(0).getBedCount() != null)
                        bedCount = entry.getValue().getBeds().get(0).getBedCount();

                    if (entry.getValue().getBeds().get(0).getBedType() != null)
                        bedType = entry.getValue().getBeds().get(0).getBedType();
                }
            }

            if (entry.getValue().getAmenities() != null){
                String facility = entry.getValue().getAmenities().toString();
                if (facility.contains("Air conditioning")) air_conditioning = true;
                if (facility.contains("Kitchen")) kitchen = true;
                if (facility.contains("TV")) tv=true;
                if (facility.contains("WIFI")) wifi=true;

            }

            maxGuestOccupency = entry.getValue().getMaxGuestOccupency();

            for(RatesDto ratesDto:rates) {
                if(ratesDto.getRoomTypeId().equals(roomId)) {
                    price = String.valueOf(ratesDto.getPricing().getPerNightPrice().getPrice()/82.35);
                }
            }

            result.add(extraInfo.getRateDate());
            result.add(content.getArea());
            result.add(content.getCity());
            result.add(content.getStateCode());
            result.add(content.getCountry());
            result.add(extraInfo.getPropertyType());
            result.add(roomType);
            result.add(content.getStar());
            result.add(maxGuestOccupency);
            result.add(String.valueOf(1));
            result.add(String.valueOf(1));
            result.add(bedCount);
            result.add(bedType);
            result.add(price);
            result.add(securityDeposit);
            result.add("");
            result.add("");
            if (content.getGoogleReviewInfo() != null)  result.add(String.valueOf(content.getGoogleReviewInfo().getNoOfReviews()));
            else result.add("");
            result.add("");
            result.add(String.valueOf(content.isInstantBook()));
            result.add("");
            result.add(String.valueOf(kitchen));
            result.add(String.valueOf(air_conditioning));
            result.add(String.valueOf(""));
            result.add(String.valueOf(tv));
            result.add(String.valueOf(wifi));
            result.add(String.valueOf(parking));
            result.add(String.valueOf(gym));
            result.add(String.valueOf(pool));
            result.add(String.valueOf(breakfast));
            result.add(String.valueOf(landmarks.contains("Airport")));
            result.add(String.valueOf(""));
            result.add(String.valueOf(bar));
            result.add(String.valueOf(landmarks.contains("Bus")));
            result.add(String.valueOf(landmarks.contains("Railway"))); // railway
            result.add(String.valueOf(landmarks.contains("Mall")));
            result.add(content.getLongitude());
            result.add(content.getLatitude());


            completeData.add(result.toArray(new String[0]));
        }
        return completeData;
    }

}
