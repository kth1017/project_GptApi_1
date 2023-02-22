package fadet.P3_GptApi.web;

import com.fasterxml.jackson.databind.ObjectMapper;

import fadet.P3_GptApi.domain.forTrans.ForTrans;
import fadet.P3_GptApi.domain.forTrans.ForTransRepository;
import fadet.P3_GptApi.service.PapagoService;
import fadet.P3_GptApi.web.dto.ForTransKtoERequestDto;
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
    ForTransRepository forTransRepository;
    @Autowired
    PapagoService papagoService;

    private MockMvc mvc;

    // WebApplicationContext 사용하여 .setup으로 컨트롤러 묶어서 처리 가능. 컨트롤러 한개라 stand 사용
    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    @Test
    public void post_번역할첫질문저장() throws Exception {
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
    public void get_번역된문장_isOk() throws Exception {
        //given
        forTransRepository.save(new ForTrans("안녕하세요.", 1));
        String url = "http://localhost:8080/api/responseTransKE";
        //when
        //then
        mvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").exists());
    }


    @Test
    public void get_번역된문장_url오류_실패() throws Exception {
        //given
        forTransRepository.save(new ForTrans("안녕하세요.", 1));
        String url = "http://localhost:8080/api/";
        //when
        //then
        mvc.perform(get(url))
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void get_추천질문리스트() throws Exception {
        //given
        String url = "http://localhost:8080/requestRQ";

        //when
        //then
        mvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]", is("java")))
                .andExpect(jsonPath("$[1]", is("spring")));
    }
}