package fadet.P3_GptApi.web.dto.requestDto;

import fadet.P3_GptApi.domain.forTrans.ForTrans;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ForTransKtoERequestDto {
    private String sentence;

    public ForTransKtoERequestDto(String sentence) {
        this.sentence = sentence;
    }

    public ForTrans toDomain(){
        return new ForTrans(sentence, 1);
    }
}
