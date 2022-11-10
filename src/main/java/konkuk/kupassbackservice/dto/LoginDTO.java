package konkuk.kupassbackservice.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginDTO {

    @NotNull
    private String nickname;

    @NotNull
    private String password;

}
