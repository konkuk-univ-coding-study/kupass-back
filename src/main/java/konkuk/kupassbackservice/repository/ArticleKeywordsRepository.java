package konkuk.kupassbackservice.repository;

import konkuk.kupassbackservice.domain.ArticleKeywords;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleKeywordsRepository extends PagingAndSortingRepository<ArticleKeywords, Long>,
        JpaSpecificationExecutor<ArticleKeywords> {
}
