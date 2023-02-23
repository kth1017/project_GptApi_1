package fadet.P3_GptApi.service;


import fadet.P3_GptApi.domain.recomQ.RecomQRepository;
import fadet.P3_GptApi.web.dto.RequestRQ2Dto;
import org.assertj.core.api.Assertions;
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
    @Autowired
    RecomQRepository repository;

    @Test
    void 추천질문반환(){
        //given
        //when
        String[] list = service.getRecomQ();
        //then
        assertThat(list[0]).isEqualTo("java");
    }

    @Test
    void 카테고리설정(){
        //given
        RequestRQ2Dto dto = new RequestRQ2Dto("java");
        //when
        service.setSmallCateArr(dto);
        //then
        Assertions.assertThat(repository.getSavedCategory()).isEqualTo("java");

    }

    @Test
    void 추천질문소분류반환(){
        //given
        RequestRQ2Dto dto = new RequestRQ2Dto("java");
        service.setSmallCateArr(dto);
        //when
        String[] details = service.getRecomQ2();
        //then
        Assertions.assertThat(details[0]).isEqualTo("jdk");

    }


}