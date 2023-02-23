package fadet.P3_GptApi.vO;

import fadet.P3_GptApi.domain.entity.answer.Answer;
import lombok.Getter;

@Getter
public class ToAnswerVO {
    private String answer;

    public ToAnswerVO(String answer) {
        this.answer = answer;
    }

    public Answer toEntity(){
        return new Answer(answer);
    }
}
