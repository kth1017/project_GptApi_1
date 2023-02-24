package fadet.P3_GptApi.web;


import fadet.P3_GptApi.service.GptService;
import fadet.P3_GptApi.service.PapagoService;
import fadet.P3_GptApi.service.RecomQService;
import fadet.P3_GptApi.web.dto.requestDto.ForTransKtoERequestDto;
import fadet.P3_GptApi.web.dto.requestDto.RQ2RequestDto;
import fadet.P3_GptApi.web.dto.requestDto.QuestionRequestDto;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;


@RequiredArgsConstructor
@RestController
public class Controller {

    private final PapagoService papagoService;
    private final RecomQService recomQService;
    private final GptService gptService;


    Bandwidth secLimit2 = Bandwidth.simple(1,Duration.ofSeconds(10));

    Bucket bucket2 = Bucket.builder()
            .addLimit(secLimit2)
            .build();



    @PostMapping("/api/requestTransKE")
    public void requestTransKtoE(@RequestBody ForTransKtoERequestDto dto){

        String s = papagoService.saveKtoE(dto);
        System.out.println(s);

    }

    @GetMapping("/api/responseTransKE")
    public String responseTransKtoE(){
        System.out.println(papagoService.transKtoE());
        return papagoService.transKtoE();
    }

    @GetMapping("/api/requestRQ")
    public String[] requestRecomQ(){
        return recomQService.getRecomQ();
    }

    //수정요
    @PostMapping("/api/requestRQ2")
    public String[] requestRecomQ2(@RequestBody RQ2RequestDto dto){
        recomQService.setSmallCateArr(dto);
        return recomQService.getRecomQ2();
    }

    @PostMapping("/api/requestQuestion")
    public String requestQuestion(@RequestBody QuestionRequestDto dto){

            gptService.saveQuestion(dto);
            return "is_ok";}


    @GetMapping("/api/responseAnswer")
    public String responseAnswer(){
        if(bucket2.tryConsume(1)){
            return gptService.getAnswerContent();
        }
        System.out.println("TOO MANY REQUEST2");
        return "Error";
    }


}
