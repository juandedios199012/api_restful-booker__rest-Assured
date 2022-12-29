package model.booking;

import base.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingDates extends BaseModel {
    @JsonProperty("checkin")
    private final String checkIn;
    @JsonProperty("checkout")
    private final String checkOut;

    public BookingDates() {
        checkIn = "2018-01-01";
        checkOut = "2018-01-01";
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }
}