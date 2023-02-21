package fadet.P3_GptApi.domain.recomQ;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter @Setter
public class RecomQ {
    String[] lData = {"java","spring", "spring module","js", "react", "DOM" ,"RDBMS", "Python"};

    private List<String> largeCategory = new ArrayList<>(Arrays.asList(lData));

}
