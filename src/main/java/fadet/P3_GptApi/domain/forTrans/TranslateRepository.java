package fadet.P3_GptApi.domain.forTrans;

public interface TranslateRepository {

    public void clear();
    public Translate saveKtoE(Translate newOne);
    public Translate saveEtoK(Translate newOne);
    public Translate findLastOne();
    public String getTranslatedText();
}
