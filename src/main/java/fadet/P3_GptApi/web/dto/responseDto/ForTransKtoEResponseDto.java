package fadet.P3_GptApi.web.dto.responseDto;

import lombok.Getter;

@Getter
public class ForTransKtoEResponseDto {
    private String TranslatedText;

    public ForTransKtoEResponseDto(String translatedText) {
        TranslatedText = translatedText;
    }
}
