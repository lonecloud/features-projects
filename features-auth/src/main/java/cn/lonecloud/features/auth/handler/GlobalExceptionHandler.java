package cn.lonecloud.features.auth.handler;

import cn.lonecloud.features.common.cts.RCode;
import cn.lonecloud.features.common.entity.Result;
import cn.lonecloud.features.common.exception.BusinessException;
import cn.lonecloud.features.common.exception.NeedLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常处理器
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/5/24 9:23
 * @since v1.0
 */
@ControllerAdvice
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 业务异常处理器
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(NeedLoginException.class)
    public Result loginException(HttpServletRequest request, NeedLoginException e){
        log.error("throw loginException",e);
        return Result.error(RCode.NEED_LOGIN.getCode(),e.getMessage());
    }

    /**
     * 业务异常处理器
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public Result bizException(HttpServletRequest request, BusinessException e){
        log.error("throw BusinessException",e);
        return Result.error(RCode.BUSINESS,e.getMessage());
    }
    /**
     * 业务异常处理器
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public Result bizException(HttpServletRequest request, BindException e){
        log.error("throw BindException",e);
        List<ObjectError> allErrors = e.getAllErrors();
        String message = allErrors.stream().map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(","));

        return Result.error(RCode.BUSINESS,message);
    }
    /**
     * 系统异常处理
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result exception(HttpServletRequest request,Exception e){
        log.error("throw Exception",e);
        return  Result.error(e.getMessage());
    }


}
