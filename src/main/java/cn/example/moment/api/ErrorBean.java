package cn.example.moment.api;

public class ErrorBean {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public ErrorBean(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
