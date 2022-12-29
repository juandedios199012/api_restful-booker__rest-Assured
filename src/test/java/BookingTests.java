import api.BookerApi;
import base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import model.booking.BookerResponse;
import model.booking.BookingCreateResponse;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

public class BookingTests extends BaseTest {

    private Response response;

    @Test(testName="CRUDBooking")
    public void crudBookingTest() {
        final var bookerApi = new BookerApi(true);

        final var bookerRequestBody = new BookerResponse();
        response = bookerApi.createBooking(bookerRequestBody);

        var responseBody = response.then().assertThat()
                .statusCode(200)
                .time(lessThan(3000L))
                .body(JsonSchemaValidator.matchesJsonSchema(getSchema(BookingCreateResponse.schemaFile)))
                .extract().body().as(BookingCreateResponse.class);

        bookerRequestBody.isEqualsTo(responseBody.getBookerResponse());

        final var bookingID = responseBody.getBookingId();

        //GetBooking
        //GET
        response = bookerApi.getBooking(bookingID);
        response.then().assertThat()
                .statusCode(200)
                .time(lessThan(3000L));
    }
}
