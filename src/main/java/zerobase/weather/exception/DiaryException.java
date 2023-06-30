package zerobase.weather.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import zerobase.weather.type.ErrorCode;

@Getter
@Setter
@AllArgsConstructor
public class DiaryException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String errorMessage;

    public DiaryException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
