package fadet.P3_GptApi.web.dto.requestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RQ2RequestDto {
    private String category;

    public RQ2RequestDto(String category) {
        this.category = category;
    }


    
}
