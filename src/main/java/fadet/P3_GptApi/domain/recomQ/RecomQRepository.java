package fadet.P3_GptApi.domain.recomQ;

import org.springframework.stereotype.Repository;

@Repository
public class RecomQRepository {
    public static String[] lDataList = {};

    public String[] getList(){
        RecomQ recomQ = new RecomQ();
        lDataList = recomQ.getLData();
        return lDataList;

    }
}
