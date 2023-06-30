package zerobase.weather.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@RestController
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @ApiOperation(value = "일기 텍스트와 날씨를 이용해서 DB에 일기 저장",
            notes = "이것은 노트")
    @PostMapping("/create/diary")
    public void createDiary(
            @RequestParam @DateTimeFormat(iso = DATE)
            @ApiParam(value = "일기를 작성할 날짜", example = "2023-02-02")
            LocalDate date,
            @RequestBody
            @ApiParam(value = "일기의 내용", example = "일기 작성 내용")
            String text
    ) {
        diaryService.createDiary(date, text);
    }

    @ApiOperation("선택한 날짜의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/read/diary")
    public List<Diary> readDiary(
            @RequestParam @DateTimeFormat(iso = DATE)
            @ApiParam(value = "가져올 일기의 날짜", example = "2023-02-02")
            LocalDate date
    ) {
        return diaryService.readDiary(date);
    }

    @ApiOperation("선택한 기간중의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/read/diaries")
    public List<Diary> readDiaries(
            @RequestParam @DateTimeFormat(iso = DATE)
            @ApiParam(value = "조회할 기간의 첫번째 날짜", example = "2023-02-02")
            LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DATE)
            @ApiParam(value = "조회할 기간의 마지막 날짜", example = "2023-02-02")
            LocalDate endDate
    ) {
        return diaryService.readDiaries(startDate, endDate);
    }

    @ApiOperation("선택한 날짜의 모든 데이터를 삭제합니다.")
    @PutMapping("/update/diary")
    public void updateDiary(
            @RequestParam @DateTimeFormat(iso = DATE)
            @ApiParam(value = "일기를 삭제할 날짜", example = "2023-02-02")
            LocalDate date,
            @RequestBody String text
    ) {
        diaryService.updateDiary(date, text);
    }

    @ApiOperation("선택한 날짜의 첫번째 데이터를 수정합니다.")
    @DeleteMapping("/delete/diary")
    public void deleteDiary(
            @RequestParam @DateTimeFormat(iso = DATE)
            @ApiParam(value = "일기를 수정할 날짜", example = "2023-02-02")
            LocalDate date
    ) {
        diaryService.deleteDiary(date);
    }
}
