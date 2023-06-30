package zerobase.weather.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Diary;
import zerobase.weather.exception.DiaryException;
import zerobase.weather.repository.DateWeatherRepository;
import zerobase.weather.repository.DiaryRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static zerobase.weather.type.ErrorCode.*;

@SpringBootTest
@Transactional
class DiaryServiceTest {
    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    DateWeatherRepository dateWeatherRepository;

    @Autowired
    DiaryService diaryService;

    @Test
    @DisplayName("생성 성공")
    void createDiarySuccess() {
        //given
        diaryService.createDiary(LocalDate.now(), "test");

        //when
        List<Diary> diaries =
                diaryRepository.findAllByDate(LocalDate.now());

        //then
        assertEquals(LocalDate.now(), diaries.get(diaries.size() - 1).getDate());
        assertEquals("test", diaries.get(diaries.size() - 1).getText());
    }

    @Test
    @DisplayName("읽기 성공")
    void readDiarySuccess() {
        //given
        diaryService.createDiary(LocalDate.now(), "test");
        List<Diary> diaries =
                diaryService.readDiary(LocalDate.now());

        //when

        //then
        assertNotNull(diaries);
        assertEquals(LocalDate.now(), diaries.get(diaries.size() - 1).getDate());
        assertEquals("test", diaries.get(diaries.size() - 1).getText());
    }

    @Test
    @DisplayName("범위 읽기 성공")
    void readDiariesSuccess() {
        //given
        diaryService.createDiary(LocalDate.now(), "test");
        diaryService.createDiary(LocalDate.now().minusDays(1),
                "test");
        List<Diary> diaries =
                diaryService.readDiaries(LocalDate.now().minusDays(1),
                        LocalDate.now());

        //when

        //then
        assertNotNull(diaries);
        assertEquals(LocalDate.now(), diaries.get(diaries.size() - 2).getDate());
        assertEquals("test", diaries.get(diaries.size() - 2).getText());
        assertEquals(LocalDate.now().minusDays(1),
                diaries.get(diaries.size() - 1).getDate());
        assertEquals("test", diaries.get(diaries.size() - 1).getText());
    }

    @Test
    @DisplayName("수정 성공")
    void updateDiarySuccess() {
        //given
        diaryService.createDiary(LocalDate.now(), "test");
        diaryService.updateDiary(LocalDate.now(), "test2");
        List<Diary> diaries =
                diaryRepository.findAllByDate(LocalDate.now());

        //when
        //then
        assertEquals("test2", diaries.get(0).getText());
    }

    @Test
    @DisplayName("삭제 성공")
    void deleteDiarySuccess() {
        //given
        diaryService.createDiary(LocalDate.now(), "test");
        diaryService.deleteDiary(LocalDate.now());
        List<Diary> diaries = diaryRepository.findAllByDate(LocalDate.now());
        //when
        //then
        assertTrue(diaries.isEmpty());
    }

    @Test
    @DisplayName("생성 실패 - 생성할 날짜가 과거인 경우")
    void createDiary_PastDateGet() {
        //given

        //when
        DiaryException exception = assertThrows(DiaryException.class,
                () -> diaryService.createDiary(LocalDate.now()
                        .minusDays(2), "test"));

        //then
        assertEquals(PAST_DATE_GET, exception.getErrorCode());
    }

    @Test
    @DisplayName("읽기 실패 - 읽을 날짜의 일기가 없는 경우")
    void readDiary_DiaryNotFound() {
        //given

        //when
        DiaryException exception = assertThrows(DiaryException.class,
                () -> diaryService.readDiary(LocalDate.now()
                        .minusDays(3)));
        //then
        assertEquals(DIARY_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    @DisplayName("읽기 실패 - 읽을 날짜가 너무 과거이거나 미래인 경우")
    void readDiary_TooFarOrPastDate() {
        //given

        //when
        DiaryException exception1 = assertThrows(DiaryException.class,
                () -> diaryService.readDiary(LocalDate.now()
                        .minusYears(100)
                        .minusDays(1)));
        DiaryException exception2 = assertThrows(DiaryException.class,
                () -> diaryService.readDiary(LocalDate.now()
                        .plusYears(100)
                        .plusDays(1)));

        //then
        assertEquals(TOO_FAR_OR_PAST_DATE, exception1.getErrorCode());
        assertEquals(TOO_FAR_OR_PAST_DATE, exception2.getErrorCode());
    }

    @Test
    @DisplayName("범위 읽기 실패 - 읽을 날짜의 범위에 일기가 없는 경우")
    void readDiaries_DiaryNotFound() {
        //given

        //when
        DiaryException exception = assertThrows(DiaryException.class,
                () -> diaryService.readDiaries(LocalDate.now()
                        .minusDays(4), LocalDate.now()
                        .minusDays(3)));
        //then
        assertEquals(DIARY_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    @DisplayName("수정 실패 - 수정할 날짜의 일기가 없는 경우")
    void updateDiary_DiaryNotFound() {
        //given

        //when
        DiaryException exception = assertThrows(DiaryException.class,
                () -> diaryService.updateDiary(LocalDate.now()
                        .minusDays(3), "test"));
        //then
        assertEquals(DIARY_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    @DisplayName("삭제 실패 - 삭제할 날짜의 일기가 없는 경우")
    void deleteDiary_DiaryNotFound() {
        //given

        //when
        DiaryException exception = assertThrows(DiaryException.class,
                () -> diaryService.deleteDiary(LocalDate.now()
                        .minusDays(3)));
        //then
        assertEquals(DIARY_NOT_FOUND, exception.getErrorCode());
    }
}