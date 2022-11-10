package konkuk.kupassbackservice.specification;

import konkuk.kupassbackservice.domain.Article;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleSpecification {

    public static Specification<Article> searchArticle(Map<String, String> searchKeys) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, String> keyVal : searchKeys.entrySet()) {
                predicates.add(criteriaBuilder.equal(root.get(keyVal.getKey()), keyVal.getValue()));
            }
            query.orderBy(criteriaBuilder.desc(root.get("createDate")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
