package fadet.P3_GptApi.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestRQ2Dto {
    private String category;

    public RequestRQ2Dto(String category) {
        this.category = category;
    }
    
}
