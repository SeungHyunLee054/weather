package zerobase.weather.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DiaryController.class)
class DiaryControllerTest {
    @MockBean
    private DiaryService diaryService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void successCreateDiary() throws Exception {
        //given
        String now = String.valueOf(LocalDate.now());
        //when
        //then
        mockMvc.perform(post("/create/diary?date=" + now)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString("test")))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void successReadDiary() throws Exception {
        //given
        String now = String.valueOf(LocalDate.now());
        //when
        //then
        mockMvc.perform(get("/read/diary?date=" + now)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void successReadDiaries() throws Exception {
        //given
        String now = String.valueOf(LocalDate.now());
        String nowMinusDay1 =
                String.valueOf(LocalDate.now().minusDays(1));
        //when
        //then
        mockMvc.perform(get("/read/diaries?startDate=" + nowMinusDay1 +
                        "&endDate=" + now)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void successUpdateDiary() throws Exception {
        //given
        String now = String.valueOf(LocalDate.now());
        //when
        //then
        mockMvc.perform(put("/update/diary?date=" + now)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString("test2")))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void successDeleteDiary() throws Exception {
        //given
        String now = String.valueOf(LocalDate.now());
        //when
        //then
        mockMvc.perform(delete("/delete/diary?date=" + now)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}