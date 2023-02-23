package fadet.P3_GptApi.domain.recomQ;

import lombok.Getter;
import org.springframework.stereotype.Repository;

@Getter
@Repository
public class RecomQRepositoryImpl implements RecomQRepository{

    private static String[] lDataList = {};
    private static String[] sDataList = {};

    private static String saveCate = "";

    @Override
    public void saveCategory(String c){
        saveCate = c;
    }
    @Override
    public String getSavedCategory() {
        return saveCate;
    }
    @Override
    public String[] getList(){
        RecomQ recomQ = new RecomQ();
        lDataList = recomQ.getLData();
        return lDataList;

    }
    @Override
    public String[] getSList(){
        RecomQ recomQ = new RecomQ();
        recomQ.setCategory(saveCate);
        sDataList = recomQ.getSList();
        return sDataList;
    }

}
