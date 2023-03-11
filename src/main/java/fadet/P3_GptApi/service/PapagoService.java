package fadet.P3_GptApi.service;
import fadet.P3_GptApi.domain.forTrans.Translate;
import fadet.P3_GptApi.domain.forTrans.TranslateRepository;
import fadet.P3_GptApi.web.dto.requestDto.TranslateEtoKRequestDto;
import fadet.P3_GptApi.web.dto.requestDto.TranslateKtoERequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PapagoService {
    private final TranslateRepository repository;


    public String transKtoE(TranslateKtoERequestDto dto) {
        Translate newOneKE = dto.toDomain();
        repository.saveKtoE(newOneKE);
        return repository.getTranslatedText();
    }

    public String transEtoK(TranslateEtoKRequestDto dto) {
        Translate newOneEK = dto.toDomain();
        repository.saveEtoK(newOneEK);
        return repository.getTranslatedText();
    }
}
