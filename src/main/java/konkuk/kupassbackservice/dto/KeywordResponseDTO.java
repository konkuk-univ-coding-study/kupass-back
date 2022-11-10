package konkuk.kupassbackservice.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class KeywordResponseDTO {
    private String success;
    private String message;

    private List<String> keywords;
}
