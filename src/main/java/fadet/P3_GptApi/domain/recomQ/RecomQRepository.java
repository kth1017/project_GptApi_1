package fadet.P3_GptApi.domain.recomQ;

public interface RecomQRepository {
    public void saveCategory(String c);
    public String getSavedCategory();
    public String[] getList();

    public String[] getSList();
}
