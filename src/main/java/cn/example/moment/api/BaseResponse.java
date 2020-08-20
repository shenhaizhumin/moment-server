package cn.example.moment.api;

public class BaseResponse<T> {
    private String message;
    private int code;
    private T data;

    public BaseResponse(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public BaseResponse(T data) {
        this.code = 200;
        this.message = "ok";
        this.data = data;
    }
    public BaseResponse(){
        this.code = 200;
        this.message = "ok";
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
