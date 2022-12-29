package base;

import java.io.File;

public class BaseTest {

    protected final String baseURL="https://restful-booker.herokuapp.com";
    private final String schemaFolder="src/test/resources/schemas";

    protected File getSchema(String json){
        var path =String.format("%s/%s",schemaFolder,json);
        return new File(path);
    }
}
