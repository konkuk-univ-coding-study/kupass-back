package konkuk.kupassbackservice.repository;

import konkuk.kupassbackservice.domain.InterestingKeyword;
import konkuk.kupassbackservice.domain.Keyword;
import konkuk.kupassbackservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface InterestingKeywordRepository extends JpaRepository<InterestingKeyword, Long>,
        JpaSpecificationExecutor<InterestingKeyword> {

    List<InterestingKeyword> findAllByUserEquals(User user);

    void deleteByKeyword(Keyword keyword);
}
