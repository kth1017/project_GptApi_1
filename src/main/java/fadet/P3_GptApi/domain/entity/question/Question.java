package fadet.P3_GptApi.domain.entity.question;

import fadet.P3_GptApi.domain.entity.answer.Answer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;
    @Column(nullable = false)
    private String questionContent;

    @OneToOne(mappedBy = "question")
    private Answer answer;


    public Question(String questionContent) {
        this.questionContent = questionContent;
    }
}


