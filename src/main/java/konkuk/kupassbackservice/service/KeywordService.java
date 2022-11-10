package konkuk.kupassbackservice.service;

import konkuk.kupassbackservice.domain.InterestingKeyword;
import konkuk.kupassbackservice.domain.Keyword;
import konkuk.kupassbackservice.domain.User;
import konkuk.kupassbackservice.exceptions.KeywordExistsException;
import konkuk.kupassbackservice.exceptions.KeywordNotExistsException;
import konkuk.kupassbackservice.repository.InterestingKeywordRepository;
import konkuk.kupassbackservice.repository.KeywordRepository;
import konkuk.kupassbackservice.specification.InterestingKeywordSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class KeywordService {

    private final InterestingKeywordRepository interestingKeywordRepository;
    private final KeywordRepository keywordRepository;

    public List<String> getKeywords(User user) {
        return interestingKeywordRepository.findAllByUserEquals(user)
                .stream()
                .map(InterestingKeyword::getKeyword)
                .map(Keyword::getKeyword)
                .collect(Collectors.toList());
    }

    public void saveKeyword(User user, String keywordName) {
        if (checkAlreadyHas(user, keywordName)) {
            throw new KeywordExistsException();
        }

        saveIfNotExit(keywordName);
        keywordRepository.findKeywordByKeyword(keywordName)
                .ifPresent(keyword -> {
                    InterestingKeyword interestingKeyword = new InterestingKeyword();
                    interestingKeyword.setUser(user);
                    interestingKeyword.setKeyword(keyword);
                    interestingKeywordRepository.save(interestingKeyword);
                });

    }

    private boolean checkAlreadyHas(User user, String keywordName) {
        return interestingKeywordRepository.exists(InterestingKeywordSpecification
                .existsInterestingKeyword(user, keywordName));
    }

    private void saveIfNotExit(String keywordName) {
        keywordRepository.findKeywordByKeyword(keywordName)
                .orElseGet(() -> {
                    Keyword keyword = new Keyword();
                    keyword.setKeyword(keywordName);
                    return keywordRepository.save(keyword);
                });
    }

    public void deleteKeyword(User user, String keyword) {
        if (!checkAlreadyHas(user, keyword)) {
            throw new KeywordNotExistsException();
        }
        Keyword deleteKeyword = keywordRepository.findKeywordByKeyword(keyword)
                .orElseThrow(KeywordNotExistsException::new);

        interestingKeywordRepository.deleteByKeyword(deleteKeyword);
    }
}
