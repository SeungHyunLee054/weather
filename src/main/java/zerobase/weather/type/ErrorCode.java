package zerobase.weather.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    TOO_FAR_OR_PAST_DATE("너무 과거 혹은 미래의 날짜입니다."),
    WRONG_DATE_FORMAT("날짜의 형식이 잘못되었습니다."),
    DIARY_NOT_FOUND("해당 날짜의 일기가 없습니다."),
    PAST_DATE_GET("과거의 데이터를 가져올 수 없습니다."),
    INTERNAL_SERVER_ERROR("내부 서버 오류가 발생했습니다.")
    ;

    private final String description;
}
