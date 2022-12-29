package api;

import base.BaseModel;
import io.restassured.http.Method;
import io.restassured.response.Response;
import utilities.Logs;
import utilities.RequestManager;

public abstract class BaseApi {

    private final RequestManager requestManger;
    protected final Logs logs=new Logs();

    public BaseApi(boolean isAuth){
        requestManger=new RequestManager(isAuth);
    }

    protected Response apiCallManager(Method method){
        return requestManger.callAPi(method);
    }

    protected BaseApi setResourcePath(String path){
        requestManger.setResourcePath(path);
        return this;
    }

    protected BaseApi setPathParameter(String key,String value){
        requestManger.setPathParameter(key,value);
        return this;
    }

    protected BaseApi setRequestBody(BaseModel model){
        requestManger.setRequestBody(model);
        return this;
    }
}
