package utilities;

import io.restassured.filter.FilterContext;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.internal.support.Prettifier;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.util.Objects;

public class RequestFilter extends RequestLoggingFilter {
    private final String separator = "**********************************************************";
    private final Logs log = new Logs();

    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {
        var response = ctx.next(requestSpec, responseSpec);
        printLog(requestSpec, response);
        return response;
    }

    private void printLog(FilterableRequestSpecification requestSpec, Response response) {
        var requestBody = new Prettifier().getPrettifiedBodyIfPossible(requestSpec);
        var headerList = requestSpec.getHeaders().asList();

        var newLine = System.getProperty("line.separator");

        var stringBuilder = new StringBuilder();

        stringBuilder.append(newLine);

        stringBuilder.append(newLine);
        stringBuilder.append(separator);
        stringBuilder.append(newLine);
        stringBuilder.append("Request:");
        stringBuilder.append(newLine);
        stringBuilder.append(separator);
        stringBuilder.append(newLine);

        var message = String.format("%s \t %s", requestSpec.getMethod(), requestSpec.getURI());
        stringBuilder.append(message);
        stringBuilder.append(newLine);

        stringBuilder.append("Headers:");
        stringBuilder.append(newLine);

        for (var header : headerList) {
            message = String.format("\t%s: %s", header.getName(), header.getValue());
            stringBuilder.append(message);
            stringBuilder.append(newLine);
        }

        if (requestBody != null) {
            stringBuilder.append("Request Body:");
            stringBuilder.append(newLine);
            stringBuilder.append(requestBody);
            stringBuilder.append(newLine);
        }

        stringBuilder.append(newLine);
        stringBuilder.append(separator);
        stringBuilder.append(newLine);
        stringBuilder.append("Response:");
        stringBuilder.append(newLine);
        stringBuilder.append(separator);
        stringBuilder.append(newLine);

        message = String.format("Status code: %d", response.getStatusCode());
        stringBuilder.append(message);
        stringBuilder.append(newLine);

        message = String.format("Response time: %d ms", response.getTime());
        stringBuilder.append(message);
        stringBuilder.append(newLine);

        stringBuilder.append("Response Body:");
        stringBuilder.append(newLine);
        stringBuilder.append(Objects.requireNonNullElse(response.getBody().asPrettyString(), "NONE"));

        log.debug(stringBuilder.toString());
    }
}