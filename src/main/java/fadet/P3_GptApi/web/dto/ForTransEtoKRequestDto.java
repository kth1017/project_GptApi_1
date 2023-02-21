package fadet.P3_GptApi.web.dto;

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

    public ForTrans toDao(String sentence){
        return new ForTrans(sentence, transType);
    }
}
