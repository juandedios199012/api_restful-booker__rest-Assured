package utilities;

import base.BaseModel;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestManager {
    private final RequestSpecification requestSpecification;
    private final String baseURL="https://restful-booker.herokuapp.com";
    private final Logs logs = new Logs();
    private final boolean isAuth;

    public RequestManager(boolean isAuth){
        this.isAuth=isAuth;
        this.requestSpecification=buildRequestSpecification();
    }

    private RequestSpecification buildRequestSpecification(){
        final var specCustom= new RequestSpecBuilder()
                .setBaseUri(baseURL)
                .setContentType(ContentType.JSON)
                .build();
        assingAuth();
        return RestAssured.given().spec(specCustom).filter(new RequestFilter());
    }

    private void assingAuth() {
        if (isAuth){
            logs.debug("Setting credentials");
            final var autScheme= new PreemptiveBasicAuthScheme();
            autScheme.setUserName("admin");
            autScheme.setPassword("password123");

            RestAssured.authentication=autScheme;
        }else {
            logs.debug("NO credentials");
            RestAssured.authentication=RestAssured.DEFAULT_AUTH;
        }
    }

    public void setRequestBody(BaseModel model){
        logs.debug("Setting request body");
        requestSpecification.body(model);
    }

    public void setResourcePath(String value){
        logs.debug("Setting base path" + value);
        requestSpecification.basePath(value);
    }

    public void setPathParameter(String key,String value){
        logs.debug("Setting path parameter" + value);
        requestSpecification.pathParams(key,value);
    }

    public Response callAPi(Method method){
        return requestSpecification.request(method);
    }
}
