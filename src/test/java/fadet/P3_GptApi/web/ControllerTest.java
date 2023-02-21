package fadet.P3_GptApi.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import fadet.P3_GptApi.TestRepository;
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
    TestRepository testRepository;

    private MockMvc mvc;

    // WebApplicationContext 사용하여 .setup으로 컨트롤러 묶어서 처리 가능. 여기선 레포땜시 stand 사용
    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(controller, testRepository)
                .build();
    }

    @Test
    public void post_번역할첫질문() throws Exception {
        //given
        String sentence = "안녕하세요.";
        ForTransKtoERequestDto dto = new ForTransKtoERequestDto(sentence);
        String url = "http://localhost:8080/requestTransKE";
        String expectedMessage = "{\"message\":{\"result\":{\"srcLangType\":\"ko\",\"tarLangType\":\"en\",\"translatedText\":\"Hello.\",\"engineType\":\"PRETRANS\",\"pivot\":null,\"dict\":null,\"tarDict\":null,\"modelVer\":\"Unknown\"},\"@type\":\"response\",\"@service\":\"naverservice.nmt.proxy\",\"@version\":\"1.0.0\"}}";

        //when
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)  //UTF8 deprecated
                        .content(new ObjectMapper().writeValueAsString(dto)))
                        .andExpect(status().isOk());

        //then
        assertThat(testRepository.get()).isEqualTo(expectedMessage);
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