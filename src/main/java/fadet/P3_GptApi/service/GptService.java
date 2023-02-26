package fadet.P3_GptApi.service;

import fadet.P3_GptApi.domain.entity.answer.Answer;
import fadet.P3_GptApi.domain.entity.answer.AnswerRepository;
import fadet.P3_GptApi.domain.entity.question.Question;
import fadet.P3_GptApi.domain.entity.question.QuestionRepository;
import fadet.P3_GptApi.domain.questionToAnswer.QuestionToAnswerRepository;
import fadet.P3_GptApi.web.dto.requestDto.QuestionRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class GptService {

    private final QuestionRepository questionRepository;
    private final QuestionToAnswerRepository questionToAnswerRepository;

    public String getAnswerContent(QuestionRequestDto dto) {
        Question question = questionRepository.findLastOne().get(0);
        String answerContent = questionToAnswerRepository.getAnswer(question.getQuestionContent());
        return Answer.createAnswer(question, answerContent).getAnswerContent();
    }
}
