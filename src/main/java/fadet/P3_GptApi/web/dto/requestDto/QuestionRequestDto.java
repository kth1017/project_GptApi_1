package fadet.P3_GptApi.web.dto.requestDto;

import fadet.P3_GptApi.domain.entity.question.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class QuestionRequestDto {
    @NotBlank(message = "빈 값이나 공백만 존재해서는 안됩니다.")
    @Max(value = 30000, message = "질문은 30,000글자까지만 허용합니다.")
    String questionContent;

    public QuestionRequestDto(String questionContent) {
        this.questionContent = questionContent;
    }

    public Question toEntity(){
        return new Question(questionContent);
    }
}
