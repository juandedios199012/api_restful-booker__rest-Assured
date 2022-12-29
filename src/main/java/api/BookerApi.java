package api;

import io.restassured.http.Method;
import io.restassured.response.Response;
import model.booking.BookerResponse;

public class BookerApi extends BaseApi {

    private final String path = "booking"; //path sin id
    private final String keyParameter = "bookingID";
    private final String pathID = String.format("%s/{%s}", path, keyParameter); //booking/{bookerID}

    public BookerApi(boolean isAuth) {
        super(isAuth);
    }

    public Response createBooking(BookerResponse booking) {
        logs.info("Booking POST");
        setResourcePath(path);
        setRequestBody(booking);
        return apiCallManager(Method.POST);
    }

    public Response getBooking(int bookingId) {
        logs.info("Booking GET");
        return setPathParameter(keyParameter, String.valueOf(bookingId))//key,value
                .setResourcePath(pathID)
                .apiCallManager(Method.GET);
    }

    public Response updateBooking(int bookingId, BookerResponse booking) {
        logs.info("Booking PUT");
        return setPathParameter(keyParameter, String.valueOf(bookingId))//key,value
                .setResourcePath(pathID)
                .setRequestBody(booking)
                .apiCallManager(Method.PUT);
    }

    public Response deleteBooking(int bookingId) {
        logs.info("Booking DELETE");
        return setPathParameter(keyParameter, String.valueOf(bookingId))//key,value
                .setResourcePath(pathID)
                .apiCallManager(Method.DELETE);
    }
}
