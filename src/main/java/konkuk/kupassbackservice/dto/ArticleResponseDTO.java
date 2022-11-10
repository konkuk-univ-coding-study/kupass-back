package konkuk.kupassbackservice.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ArticleResponseDTO {
    private String success;
    private String message;

    private List<ArticleDTO> articles;

    private boolean last;
}
