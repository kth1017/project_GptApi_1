package fadet.P3_GptApi.domain.recomQ;

import org.springframework.stereotype.Repository;

import lombok.Getter;



@Repository
public class RecomQRepository {
    private static String[] lDataList = {};
    private static String[] sDataList = {};

    private static String saveCate = "";

    

    public String[] getList(){
        RecomQ recomQ = new RecomQ();
        lDataList = recomQ.getLData();
        return lDataList;

    }

    public void saveCategory(String c){
        saveCate = c;
        System.out.println("saveCate = "+ saveCate);
        
    }

    public String[] getSList(){
        RecomQ recomQ = new RecomQ();
        recomQ.setCategory(saveCate);
        sDataList = recomQ.getSList();
        System.out.println("cate = " + recomQ.getCategory());
        System.out.println(sDataList[0]);
        return sDataList;
    }

}
