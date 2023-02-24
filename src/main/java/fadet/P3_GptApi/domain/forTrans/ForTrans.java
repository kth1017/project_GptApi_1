package fadet.P3_GptApi.domain.forTrans;

import fadet.P3_GptApi.ApiKey;
import fadet.P3_GptApi.web.dto.requestDto.ForTransEtoKRequestDto;
import fadet.P3_GptApi.web.dto.requestDto.ForTransKtoERequestDto;
import lombok.Getter;
import lombok.Setter;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
//Id 때문에 setId 추가
@Getter
public class ForTrans {
    private Long id;

    private String sentence;
    private int transType;

    public ForTrans(String sentence, int transType) {
        this.sentence = sentence;
        this.transType = transType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String incoding(ApiKey apiKey) {

        String clientId = apiKey.getApiId();//애플리케이션 클라이언트 아이디값";
        String clientSecret = apiKey.getApiSecret();//애플리케이션 클라이언트 시크릿값";

        String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
        String text;
        try {
            text = URLEncoder.encode(sentence, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("인코딩 실패", e);
        }

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);

        String responseBody = post(apiURL, requestHeaders, text);

        return responseBody;
    }

    /*
    incoding 내장 함수
     */
    private String post(String apiUrl, Map<String, String> requestHeaders, String text) {
        HttpURLConnection con = connect(apiUrl);
        String postParams = " ";
        if (transType==1) {
            postParams = "source=ko&target=en&text=" + text;
        } else if (transType==2) {
            postParams = "source=en&target=ko&text=" + text;
        }

        try {
            con.setRequestMethod("POST");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postParams.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                return readBody(con.getInputStream());
            } else {  // 에러 응답
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
    public String getTranText(String body) {
        int beginIndex = 0;
        int endIndex = 0;

        if(body.contains("translatedText")) {
            int index = body.indexOf("translatedText");
            beginIndex = index;
        } else {
            System.out.println("번역T 없음");
        }

        if(body.contains("engineType")) {
            int index = body.indexOf("engineType");
            endIndex = index;
        } else {
            System.out.println("엔진타입 없음");
        }


        String result = body.substring(beginIndex+17, endIndex-3);

        return result;
    }


}
