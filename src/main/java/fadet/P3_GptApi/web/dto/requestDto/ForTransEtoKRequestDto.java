package fadet.P3_GptApi.web.dto.requestDto;

import fadet.P3_GptApi.domain.forTrans.ForTrans;
import lombok.Getter;

@Getter
public class ForTransEtoKRequestDto {
    private String sentence;
    private int transType;

    public ForTransEtoKRequestDto(String sentence) {
        this.sentence = sentence;
        this.transType = 2;
    }

    public ForTrans toDomain(){
        return new ForTrans(sentence, transType);
    }
}
