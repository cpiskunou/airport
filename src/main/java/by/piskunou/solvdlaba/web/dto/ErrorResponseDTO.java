package by.piskunou.solvdlaba.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fieldError;

    private String message;

    public ErrorResponseDTO(String message) {
        this.message = message;
    }

}
