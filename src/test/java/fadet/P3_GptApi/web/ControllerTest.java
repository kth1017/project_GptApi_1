package fadet.P3_GptApi.web;

import com.fasterxml.jackson.databind.ObjectMapper;

import fadet.P3_GptApi.domain.entity.answer.AnswerRepository;
import fadet.P3_GptApi.domain.entity.question.QuestionRepository;
import fadet.P3_GptApi.domain.forTrans.ForTransRepositoryImpl;
import fadet.P3_GptApi.domain.recomQ.RecomQRepository;
import fadet.P3_GptApi.service.GptService;
import fadet.P3_GptApi.service.PapagoService;
import fadet.P3_GptApi.web.dto.requestDto.ForTransEtoKRequestDto;
import fadet.P3_GptApi.web.dto.requestDto.ForTransKtoERequestDto;
import fadet.P3_GptApi.web.dto.requestDto.QuestionRequestDto;
import fadet.P3_GptApi.web.dto.requestDto.RQ2RequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.util.NestedServletException;

import static org.hamcrest.CoreMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class ControllerTest {
 
    @Autowired
    Controller controller;
    @Autowired
    ForTransRepositoryImpl forTransRepository;
    @Autowired
    RecomQRepository recomQRepository;
    @Autowired
    QuestionRepository questionRepository;


    private MockMvc mvc;

    // WebApplicationContext 사용하여 .setup으로 컨트롤러 묶어서 처리 가능. 컨트롤러 한개라 stand 사용
    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerAdvice())
                .build();
    }

    @Test
    public void url잘못작성_통신실패4xx() throws Exception {
        //given
        forTransRepository.saveKtoE(new ForTransKtoERequestDto("안녕하세요."));
        String url = "http://localhost:8080/api/";
        //when
        //then
        mvc.perform(get(url))
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void 다른dto사용_실패() throws Exception {
        //given
        //dto는 번역 url은 질문
        ForTransKtoERequestDto dto = new ForTransKtoERequestDto("안녕하세요.");
        String url = "http://localhost:8080/api/requestQuestion";
        //when
        //then
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect((result -> {
                    Assertions.assertEquals(
                            result.getResolvedException().getClass().getCanonicalName(),
                            MethodArgumentNotValidException.class.getCanonicalName());
                }));
    }

    @Test
    public void post_번역할첫질문답변_성공() throws Exception {
        //given
        ForTransKtoERequestDto dto = new ForTransKtoERequestDto("안녕하세요.");
        String url = "http://localhost:8080/api/requestTransKE";
        //when
        //then
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").exists());

        assertThat(forTransRepository.findLastOne().getSentence()).isEqualTo("안녕하세요.");
    }

    @Test
    public void post_번역할첫질문답변_NULLor빈값전달_실패() throws Exception {
        //given
        ForTransKtoERequestDto dto = new ForTransKtoERequestDto(" ");
        String url = "http://localhost:8080/api/requestTransKE";
        //when
        //then
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void post_번역할첫질문답변_너무김_실패() throws Exception {
        //given
        //테스트기에 버퍼 대신 빌더 사용
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 200; i++) {
            sb.append("가");
        }
        ForTransKtoERequestDto dto = new ForTransKtoERequestDto(sb.toString());
        String url = "http://localhost:8080/api/requestTransKE";
        //when
        //then
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void get_추천질문리스트give_성공() throws Exception {
        //given
        String url = "http://localhost:8080/api/requestRQ";

        //when
        //then
        mvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]", is("java")))
                .andExpect(jsonPath("$[1]", is("spring")));
    }

    @Test
    public void post_카테고리저장후추천반환_성공() throws Exception {
        //given
        String categoty = "js";
        RQ2RequestDto dto = new RQ2RequestDto(categoty);
        String url = "http://localhost:8080/api/requestRQ2";
        //when
        //then
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]", is("sync")))
                .andExpect(jsonPath("$[2]", is("EC6")));

        assertThat(recomQRepository.getSavedCategory()).isEqualTo("js");
    }

    @Test
    public void post_카테고리저장후추천반환_빈값_실패() throws Exception {
        //given
        String categoty = "python";
        RQ2RequestDto dto = new RQ2RequestDto(categoty);
        String url = "http://localhost:8080/api/requestRQ2";
        //when
        //then
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void post_번역되거나추천된질문답변_첫요청성공_이후실패() throws Exception {
        //given
        String sentence = "what is java?";
        QuestionRequestDto dto = new QuestionRequestDto(sentence);
        String url = "http://localhost:8080/api/requestQuestion";
        //when
        //then
        // 첫번째 요청 - 성공
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.object").value("text_completion"))
                .andReturn();

        assertThat(questionRepository.findLastOne().get(0).getQuestionContent()).isEqualTo("what is java?");
        // 두번째 요청 - error string 반환
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Error:TOO MANY REQUEST"))
                .andReturn();
    }

    @Test
    public void post_번역되거나추천된질문답변_Nullor빈값_실패() throws Exception {
        //given
        String sentence = "  ";
        QuestionRequestDto dto = new QuestionRequestDto(sentence);
        String url = "http://localhost:8080/api/requestQuestion";
        //when
        //then
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void post_번역되거나추천된질문답변_너무김_실패() throws Exception {
        //given
        //테스트기에 버퍼 대신 빌더 사용
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 30001; i++) {
            sb.append("a");
        }
        QuestionRequestDto dto = new QuestionRequestDto(sb.toString());
        String url = "http://localhost:8080/api/requestQuestion";
        //when
        //then
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void post_ai답변한글로번역_성공() throws Exception {
        //given
        ForTransEtoKRequestDto dto = new ForTransEtoKRequestDto("java is good.");;
        String url = "http://localhost:8080/api/requestTransEK";
        //when
        //then
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").exists());

        assertThat(forTransRepository.findLastOne().getSentence()).isEqualTo("java is good.");
    }

    @Test
    public void post_ai답변한글로번역_Nullor빈값_실패_() throws Exception {
        //given
        ForTransEtoKRequestDto dto = new ForTransEtoKRequestDto("  ");;
        String url = "http://localhost:8080/api/requestTransEK";
        //when
        //then
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void post_ai답변한글로번역_너무김_실패() throws Exception {
        //given
        //테스트기에 버퍼 대신 빌더 사용
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 30001; i++) {
            sb.append("a");
        }
        ForTransEtoKRequestDto dto = new ForTransEtoKRequestDto(sb.toString());;
        String url = "http://localhost:8080/api/requestTransEK";
        //when
        //then
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().is4xxClientError());
    }


}