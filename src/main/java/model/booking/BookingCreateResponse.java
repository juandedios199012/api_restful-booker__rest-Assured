package model.booking;

import base.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingCreateResponse extends BaseModel {
    public static String schemaFile = "booking/bookingResponse.json";
    @JsonProperty("bookingid")
    private int bookingId;
    @JsonProperty("booking")
    private BookerResponse bookerResponse;

    public int getBookingId() {
        return bookingId;
    }

    public BookerResponse getBookerResponse() {
        return bookerResponse;
    }
}