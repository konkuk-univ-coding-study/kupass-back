package konkuk.kupassbackservice.specification;

import konkuk.kupassbackservice.domain.InterestingKeyword;
import konkuk.kupassbackservice.domain.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class InterestingKeywordSpecification {

    public static Specification<InterestingKeyword> existsInterestingKeyword(User user, String keyword) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<Object, Object> userJoin = root.join("user", JoinType.INNER);
            predicates.add(criteriaBuilder.equal(userJoin.get("nickname"), user.getNickname()));

            Join<Object, Object> keywordJoin = root.join("keyword", JoinType.INNER);
            predicates.add(criteriaBuilder.equal(keywordJoin.get("keyword"), keyword));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
