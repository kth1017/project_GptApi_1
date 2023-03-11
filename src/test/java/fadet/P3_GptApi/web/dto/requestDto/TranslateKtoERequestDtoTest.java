package fadet.P3_GptApi.web.dto.requestDto;

import fadet.P3_GptApi.domain.forTrans.Translate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TranslateKtoERequestDtoTest {
    @Test
    void Question도메인화() {
        //given
        TranslateKtoERequestDto dto = new TranslateKtoERequestDto("안녕하세요.");
        //when
        //then
        assertThat(dto.toDomain()).isInstanceOf(Translate.class);
    }

}