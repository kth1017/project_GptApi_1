package fadet.P3_GptApi.service;

import fadet.P3_GptApi.domain.entity.answer.Answer;
import fadet.P3_GptApi.domain.entity.question.Question;
import fadet.P3_GptApi.domain.entity.question.QuestionRepository;
import fadet.P3_GptApi.domain.forAsk.AskGptRepository;
import fadet.P3_GptApi.web.dto.responseDto.AnswerResponseDto;
import fadet.P3_GptApi.web.dto.requestDto.QuestionRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class GptService {

    private final QuestionRepository questionRepository;
    private final AskGptRepository askGptRepository;

    @Transactional(timeout = 15)
    public String getAnswerContent(QuestionRequestDto dto) {
        // 1 질문을 받아 question entity로 저장
        Question newQuestion = questionRepository.save(dto.toEntity());
        // 2 요청 받은 question을 askGpt 도메인에 넘겨 answer를 불러옴 
        String answerContent = askGptRepository.getAnswer(newQuestion.getQuestionContent());
        // 3 불러온 answer을 entity로 저장, 생성 메서드를 통해 연관관계 매핑
        Answer newAnswer = Answer.createAnswer(newQuestion, answerContent);
        // 4 responseDto를 return하고 서비스 종료
        return new AnswerResponseDto(newAnswer).getAnswerContent();
    }

}
