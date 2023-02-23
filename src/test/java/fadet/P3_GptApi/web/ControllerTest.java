package fadet.P3_GptApi.web;

import com.fasterxml.jackson.databind.ObjectMapper;

import fadet.P3_GptApi.domain.entity.question.QuestionRepository;
import fadet.P3_GptApi.domain.forTrans.ForTransRepositoryImpl;
import fadet.P3_GptApi.domain.recomQ.RecomQRepository;
import fadet.P3_GptApi.service.GptService;
import fadet.P3_GptApi.service.PapagoService;
import fadet.P3_GptApi.web.dto.requestDto.ForTransKtoERequestDto;
import fadet.P3_GptApi.web.dto.requestDto.QuestionRequestDto;
import fadet.P3_GptApi.web.dto.requestDto.RQ2RequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;
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



    @Autowired
    GptService gptService;

    private MockMvc mvc;

    // WebApplicationContext 사용하여 .setup으로 컨트롤러 묶어서 처리 가능. 컨트롤러 한개라 stand 사용
    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    @Test
    public void post_번역할첫질문take() throws Exception {
        //given
        String sentence = "안녕하세요.";
        ForTransKtoERequestDto dto = new ForTransKtoERequestDto(sentence);
        String url = "http://localhost:8080/api/requestTransKE";
        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());
        //then
        assertThat(forTransRepository.findLastOne().getSentence()).isEqualTo("안녕하세요.");
    }

    @Test
    public void get_번역된문장give_isOk() throws Exception {
        //given
        forTransRepository.saveKtoE(new ForTransKtoERequestDto("안녕하세요."));
        String url = "http://localhost:8080/api/responseTransKE";
        //when
        //then
        mvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").exists());
    }


    @Test
    public void get_번역된문장give_url오류_실패4xx() throws Exception {
        //given
        forTransRepository.saveKtoE(new ForTransKtoERequestDto("안녕하세요."));
        String url = "http://localhost:8080/api/";
        //when
        //then
        mvc.perform(get(url))
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
    public void get_추천질문리스트give_실패4xx() throws Exception {
        //given
        String url = "http://localhost:8080/requestRQ";

        //when
        //then
        mvc.perform(get(url))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void post_카테고리take() throws Exception {
        //given
        String categoty = "java";
        RQ2RequestDto dto = new RQ2RequestDto(categoty);
        String url = "http://localhost:8080/api/requestRQ2";
        //when
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());
        //then
        assertThat(recomQRepository.getSavedCategory()).isEqualTo("java");
    }

    @Test
    public void get_소분류리스트give_성공() throws Exception {
        //given
        recomQRepository.saveCategory("js");
        String url = "http://localhost:8080/api/responseRQ2";
        //when
        //then
        mvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]", is("sync")))
                .andExpect(jsonPath("$[2]", is("EC6")));

    }

    @Test
    public void post_번역되거나추천된질문get() throws Exception {
        //given
        String sentence = "what is java?";
        QuestionRequestDto dto = new QuestionRequestDto(sentence);
        String url = "http://localhost:8080/api/requestQuestion";
        //when
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());
        //then
        assertThat(questionRepository.findLastOne().get(0)).isEqualTo("what is java?");
    }

    @Test
    public void get_gpt답변give_성공() throws Exception {
        //given
        gptService.saveQuestion(new QuestionRequestDto("What is java."));
        String url = "http://localhost:8080/api/responseAnswer";
        //when
        //then
        mvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.object").value("text_completion"));
    }
}