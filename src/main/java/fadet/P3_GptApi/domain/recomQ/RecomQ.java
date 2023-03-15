package fadet.P3_GptApi.domain.recomQ;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
    로그인 기능 추가시 엔티티 승격 에정이라 enum 사용 안함
 */
@Getter
@NoArgsConstructor
public class RecomQ {
    private String[] lData = {"java","spring", "js", "react", "DOM" ,"DBMS", "OOP"};
    private String category = "";
    private String[] javaSData = {"jdk", "jvm", "interface", "java8", "java11", "stream", "servlet"};
    private String[] springSData = {"spring boot", "modules", "spring mvc","DI", "IoC", "SPA", "AOP", "dependency"};
    private String[] jsSData = {"sync", "single-thread", "EC6", "object"};
    private String[] reactSData = {"component", "state", "props", "context API", "react-redux"};
    private String[] DOMSData = {"virtual DOM", "rendering", "paint", "DOM tree"};
    private String[] DBMSSData = {"query", "SQL", "No-SQL", "Transaction","Domain", "schema", "integrity", "consistency"};
    private String[] OOPSData = {"object", "encapsulation", "inheritance", "polymorphism","mariaDB", "PostgreSQL", "oracle"};

    private String[] dummy = {" "};


    public RecomQ(String category) {
        this.category = category;
    }

    public String[] getSList(){
        if (category.equals("java")) {
            return javaSData;
        } if (category.equals("spring")) {
            return springSData;
        } if (category.equals("js")) {
            return jsSData;
        } if (category.equals("react")) {
            return reactSData;
        } if (category.equals("DOM")) {
            return DOMSData;
        } if (category.equals("DBMS")) {
            return DBMSSData;
        } if (category.equals("OOP")) {
            return OOPSData;
        } else {
            return dummy;
        }
    }



}
