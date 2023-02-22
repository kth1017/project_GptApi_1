package fadet.P3_GptApi.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
@SpringBootTest
class RecomQServiceTest {

    @Autowired
    RecomQService service;

    @Test
    void 추천질문반환(){
        //given
        //when
        String[] list = service.getRecomQ();
        //then
        assertThat(list[0]).isEqualTo("java");
    }


}