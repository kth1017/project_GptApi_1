package fadet.P3_GptApi.domain.forAsk;

public interface AskGptRepository {

    public void clear();
    public String getAnswer(String question);

}
