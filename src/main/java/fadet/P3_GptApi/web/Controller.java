package fadet.P3_GptApi.web;


import fadet.P3_GptApi.service.PapagoService;
import fadet.P3_GptApi.service.RecomQService;
import fadet.P3_GptApi.web.dto.requestDto.ForTransKtoERequestDto;
import fadet.P3_GptApi.web.dto.RequestRQ2Dto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class Controller {

    private final PapagoService papagoService;
    private final RecomQService recomQService;



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

    @PostMapping("/api/requestRQ2")
    public void requestRecomQ2(@RequestBody RequestRQ2Dto dto){
        recomQService.setSmallCateArr(dto);
    }

    @GetMapping("/api/responseRQ2")
    public String[] responseRecomQ2(){
        return recomQService.getRecomQ2();
    }




}
