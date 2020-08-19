package cn.example.moment.exception;

import cn.example.moment.api.ErrorBean;

public class BusinessInterfaceException extends Exception {
    private ErrorBean errorBean;

    public BusinessInterfaceException(int code, String msg) {
        super(msg);
        errorBean = new ErrorBean(code, msg);
    }

    public ErrorBean getError() {
        return errorBean;
    }

    public void setErrorBean(ErrorBean errorBean) {
        this.errorBean = errorBean;
    }
}
