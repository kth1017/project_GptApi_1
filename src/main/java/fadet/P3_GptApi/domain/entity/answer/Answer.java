package fadet.P3_GptApi.domain.entity.answer;

import fadet.P3_GptApi.ApiKey;
import fadet.P3_GptApi.domain.entity.question.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Getter
@Entity
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;
    @Column(nullable = false, length = 1000)
    private String answerContent;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    public Answer(String answerContent) {
        this.answerContent = answerContent;
    }

    /* 연관관계
            메서드
         */
    public void setQuestion(Question question) {
        this.question = question;
        question.setAnswer(this);
    }

    public static Answer createAnswer(Question question, String answerContent){
        Answer answer = new Answer(answerContent);
        answer.setQuestion(question);
        return answer;

    }

}


