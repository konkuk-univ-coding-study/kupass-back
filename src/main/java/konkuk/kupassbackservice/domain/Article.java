package konkuk.kupassbackservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    private String publisher;

    private String category;

    private String title;

    private String content;

    private String summary;

    private String source;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<ArticleKeywords> keywords = new ArrayList<>();
}
