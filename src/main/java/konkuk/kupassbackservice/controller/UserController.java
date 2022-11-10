package konkuk.kupassbackservice.controller;

import konkuk.kupassbackservice.domain.User;
import konkuk.kupassbackservice.dto.KeywordResponseDTO;
import konkuk.kupassbackservice.dto.UserResponseDTO;
import konkuk.kupassbackservice.exceptions.KeywordExistsException;
import konkuk.kupassbackservice.exceptions.KeywordNotExistsException;
import konkuk.kupassbackservice.jwt.TokenProvider;
import konkuk.kupassbackservice.service.KeywordService;
import konkuk.kupassbackservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static konkuk.kupassbackservice.jwt.JWTFilter.AUTHORIZATION_HEADER;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final KeywordService keywordService;

    private final TokenProvider tokenProvider;

    @GetMapping("/{nickname}/keywords")
    public ResponseEntity<KeywordResponseDTO> getKeywords(@PathVariable String nickname) {
        User user = userService.findUser(nickname);
        List<String> keywords = keywordService.getKeywords(user);

        return new ResponseEntity<>(new KeywordResponseDTO("success", "ok", keywords), HttpStatus.OK);
    }

    @PutMapping("/{nickname}/keywords/{keyword}")
    public ResponseEntity<UserResponseDTO> saveKeyword(@PathVariable String nickname,
                                                       @PathVariable String keyword,
                                                       HttpServletRequest request) {
        String subject = getSubject(request);
        if (!subject.equals(nickname)) {
            return new ResponseEntity<>(
                    new UserResponseDTO("fail", "user doesn't match"), HttpStatus.OK);
        }

        User user = userService.findUser(nickname);
        try {
            keywordService.saveKeyword(user, keyword);
        } catch (KeywordExistsException e) {
            return new ResponseEntity<>(
                    new UserResponseDTO("fail", "keyword already exists"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new UserResponseDTO("success", "ok"), HttpStatus.OK);
    }

    @DeleteMapping("/{nickname}/keywords/{keyword}")
    public ResponseEntity<UserResponseDTO> deleteKeyword(@PathVariable String nickname,
                                                         @PathVariable String keyword,
                                                         HttpServletRequest request) {
        String subject = getSubject(request);
        if (!subject.equals(nickname)) {
            return new ResponseEntity<>(
                    new UserResponseDTO("fail", "user doesn't match"), HttpStatus.OK);
        }
        User user = userService.findUser(nickname);

        try {
            keywordService.deleteKeyword(user, keyword);
        } catch (KeywordNotExistsException e) {
            return new ResponseEntity<>(
                    new UserResponseDTO("fail", "keyword not exists"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new UserResponseDTO("success", "successfully deleted " + keyword),
                HttpStatus.OK);
    }

    private String getSubject(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER);
        String token = header.substring(7);
        return tokenProvider.getSubject(token);
    }
}
