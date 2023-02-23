package fadet.P3_GptApi.domain.recomQ;


import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class RecomQRepositoryTest {
    @Autowired
    RecomQRepository repository;

    @AfterEach
    void clear(){
        repository.clear();
    }
    @Test
    void 카테고리저장(){
        //given
        //when
        repository.saveCategory("java");
        //then
        assertThat(repository.getSavedCategory()).isEqualTo("java");
    }
    @Test
    void 추천카테고리배열반환(){
        //given
        //when
        String[] list = repository.getList();
        //then
        assertThat(list[0]).isEqualTo("java");
    }

    @Test
    void 카테고리지정후소분류배열반환(){
        //given
        repository.saveCategory("js");
        //when
        String[] sList = repository.getSList();
        //then
        assertThat(sList[0]).isEqualTo("sync");
        assertThat(sList[2]).isEqualTo("EC6");
    }

}