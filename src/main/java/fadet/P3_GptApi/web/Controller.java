package fadet.P3_GptApi.web;

import fadet.P3_GptApi.service.PapagoService;
import fadet.P3_GptApi.service.RecomQService;
import fadet.P3_GptApi.web.dto.ForTransKtoERequestDto;
import fadet.P3_GptApi.TestRepository;
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
    private final TestRepository testRepository;

    @PostMapping("/api/requestTransKE")
    public void requestTransKtoE(@RequestBody ForTransKtoERequestDto dto){

        String s = papagoService.saveKtoE(dto);
        System.out.println(s);
        testRepository.save(s);

    }

    @GetMapping("/api/responseTransKE")
    public String responseTransKtoE(){
        return papagoService.transKtoE();
    }

    @GetMapping("/api/requestRQ")
    public String[] requestRecomQ(){
        return recomQService.getRecomQ();
    }



}
