package model.booking;

import base.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;
import org.testng.asserts.SoftAssert;

public class BookerResponse extends BaseModel {
    @JsonProperty("firstname")
    private final String firstName;
    @JsonProperty("lastname")
    private final String lastName;
    @JsonProperty("totalprice")
    private final int totalPrice;
    @JsonProperty("depositpaid")
    private final boolean depositPaid;
    @JsonProperty("additionalneeds")
    private final String additionalNeeds;
    @JsonProperty("bookingdates")
    private final BookingDates bookingDates;

    public BookerResponse() {
        var faker = new Faker();
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        totalPrice = faker.number().numberBetween(5, 100);
        depositPaid = faker.bool().bool();
        additionalNeeds = faker.animal().name();
        bookingDates = new BookingDates();
    }

    public void isEqualsTo(BookerResponse bookerResponse) {
        var sofAssert = new SoftAssert();
        sofAssert.assertEquals(firstName, bookerResponse.getFirstName());
        sofAssert.assertEquals(lastName, bookerResponse.getLastName());
        sofAssert.assertEquals(totalPrice, bookerResponse.getTotalPrice());
        sofAssert.assertEquals(depositPaid, bookerResponse.isDepositPaid());
        sofAssert.assertEquals(additionalNeeds, bookerResponse.getAdditionalNeeds());
        sofAssert.assertEquals(bookingDates.getCheckIn(), bookerResponse.getbookingdates().getCheckIn());
        sofAssert.assertEquals(bookingDates.getCheckOut(), bookerResponse.getbookingdates().getCheckOut());

        sofAssert.assertAll();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean isDepositPaid() {
        return depositPaid;
    }

    public String getAdditionalNeeds() {
        return additionalNeeds;
    }

    public BookingDates getbookingdates() {
        return bookingDates;
    }
}