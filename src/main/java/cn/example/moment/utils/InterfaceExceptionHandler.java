package cn.example.moment.utils;

import cn.example.moment.exception.BusinessInterfaceException;
import cn.example.moment.api.BaseResponse;
import cn.example.moment.api.ErrorBean;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InterfaceExceptionHandler {
    private Logger log = cn.example.moment.utils.Logger.logger;

    /**
     * 接口 业务异常
     */
    @ResponseBody
    @ExceptionHandler(BusinessInterfaceException.class)
    public BaseResponse businessInterfaceException(BusinessInterfaceException e) {
        log.error(e.getMessage(), e);
        ErrorBean error = e.getError();
        BaseResponse resultBean = new BaseResponse(error.getCode(), error.getErrorMsg());
        return resultBean;
    }

    /**
     * 拦截所有运行时的全局异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public BaseResponse runtimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        // 返回 JOSN
        BaseResponse resultBean = new BaseResponse(301, e.getMessage());
        return resultBean;
    }

    /**
     * 系统异常捕获处理
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponse exception(Exception e) {
        log.error(e.getMessage(), e);
        BaseResponse resultBean = new BaseResponse(301, e.getMessage());
        // 返回 JOSN
        return resultBean;
    }
}
