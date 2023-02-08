package ForCloud.backend.exception;

import ForCloud.backend.config.BaseResponse;
import ForCloud.backend.config.BaseResponseStatus;
import lombok.Builder;
import lombok.Getter;

public class BusinessException extends RuntimeException{

    @Getter
    private BaseResponseStatus ErrorCode;

    @Builder
    public BusinessException (String message, BaseResponseStatus ErrorCode){
        super(message);
        this.ErrorCode = ErrorCode;
    }
    public BusinessException (BaseResponseStatus ErrorCode){
        super(ErrorCode.getMessage());
        this.ErrorCode = ErrorCode;
    }
}
