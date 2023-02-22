package fadet.P3_GptApi.domain.recomQ;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class RecomQ {
    private String[] lData = {"java","spring", "spring module","js", "react", "DOM" ,"RDBMS", "Python"};

    private String category = "";
    private String[] javaSData = {"class", "interface"};
    private String[] jsSData = {"sync", "jQuery"};
    private String[] dummy = {" "};

    public String[] getSList(){
        if (category.equals("java")) {
            return javaSData;
        } if (category.equals("js")) {
            return jsSData;
        } else {
            return dummy;
        }
    }



}
