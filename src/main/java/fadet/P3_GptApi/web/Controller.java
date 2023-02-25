package fadet.P3_GptApi.web;


import fadet.P3_GptApi.service.GptService;
import fadet.P3_GptApi.service.PapagoService;
import fadet.P3_GptApi.service.RecomQService;
import fadet.P3_GptApi.web.dto.requestDto.ForTransEtoKRequestDto;
import fadet.P3_GptApi.web.dto.requestDto.ForTransKtoERequestDto;
import fadet.P3_GptApi.web.dto.requestDto.RQ2RequestDto;
import fadet.P3_GptApi.web.dto.requestDto.QuestionRequestDto;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.Duration;


@RequiredArgsConstructor
@RestController
public class Controller {

    private final PapagoService papagoService;
    private final RecomQService recomQService;
    private final GptService gptService;
    //서버용 rate limit
    Bucket bucket2 = Bucket.builder()
            .addLimit(Bandwidth.simple(1,Duration.ofSeconds(10)))
            .build();

    @PostMapping("/api/requestTransKE")
    public String  requestTransKtoE(@RequestBody @Valid ForTransKtoERequestDto dto){
        return papagoService.transKtoE(dto);
    }

    @GetMapping("/api/requestRQ")
    public String[] requestRecomQ(){
        return recomQService.getRecomQ();
    }

    //수정요
    @PostMapping("/api/requestRQ2")
    public String[] requestRecomQ2(@RequestBody @Valid RQ2RequestDto dto){
        recomQService.setSmallCateArr(dto);
        return recomQService.getRecomQ2();
    }

    @PostMapping("/api/requestQuestion")
    public String requestQuestion(@RequestBody @Valid QuestionRequestDto dto){
        if(bucket2.tryConsume(1)){
            gptService.saveQuestion(dto);
            return gptService.getAnswerContent();
        }
        return "Error:TOO MANY REQUEST";
    }

    @PostMapping(value="/api/requestTransEK", produces="text/plain;charset=UTF-8")
    public String requestTransEK(@RequestBody @Valid ForTransEtoKRequestDto dto){
        return papagoService.transEtoK(dto);
    }

}
