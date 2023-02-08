package ForCloud.backend.exception;

import ForCloud.backend.config.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity handleDuplicateApplicantException (BusinessException d){
        log.error("handleDuplicateApplicantException", d);
        BaseResponse baseResponse = new BaseResponse(d.getErrorCode());
        return ResponseEntity.ok().body(baseResponse);
    }
}
