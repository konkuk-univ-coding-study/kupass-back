package konkuk.kupassbackservice.controller;

import konkuk.kupassbackservice.dto.ArticleDTO;
import konkuk.kupassbackservice.dto.ArticleResponseDTO;
import konkuk.kupassbackservice.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<ArticleResponseDTO>search(
            @RequestParam(value = "publisher", required = false, defaultValue = "") String publisher,
            @RequestParam(value = "category", required = false, defaultValue = "") String category,
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page
            ) {
        PageRequest pageRequest = PageRequest.of(page, 20);
        List<ArticleDTO> articles = articleService.searchArticle(publisher, keyword, category, pageRequest);

        return new ResponseEntity<>(new ArticleResponseDTO("success", "ok", articles,
                articles.size() < 20), HttpStatus.OK);
    }
}
