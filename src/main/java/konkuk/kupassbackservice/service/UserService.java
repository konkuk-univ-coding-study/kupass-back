package konkuk.kupassbackservice.service;

import konkuk.kupassbackservice.domain.User;
import konkuk.kupassbackservice.exceptions.NicknameConflictException;
import konkuk.kupassbackservice.exceptions.NicknameFormatException;
import konkuk.kupassbackservice.exceptions.PasswordFormatException;
import konkuk.kupassbackservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.regex.Pattern;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void checkDuplicate(String nickname) {
        userRepository.findByNicknameEquals(nickname)
                .ifPresent(user -> {
                    throw new NicknameConflictException("입력된 사용자 아이디가 중복됩니다.");
                });
    }

    public void checkValidNickname(String nickname) {
        String pattern = "^[a-zA-Z0-9!@#$%^&*(){}<>?]{5,20}$";
        if (!Pattern.matches(pattern, nickname)) {
            throw new NicknameFormatException("입력된 사용자 아이디가 형식에 맞지 않습니다.");
        }
    }

    public void checkValidPassword(String password) {
        String pattern = "^[a-zA-Z0-9!@#$%^&*(){}<>?]{8,20}$";
        if (!Pattern.matches(pattern, password)) {
            throw new PasswordFormatException("입력된 비밀번호가 형식에 맞지 않습니다.");
        }
    }

    @Transactional
    public void signup(User user) {
        checkDuplicate(user.getNickname());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        userRepository.save(user);
    }

    public User findUser(String nickname) {
        return userRepository.findByNicknameEquals(nickname)
                .orElseThrow(() -> new UsernameNotFoundException(nickname + " -> 존재하지 않는 유저"));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        return userRepository.findByNicknameEquals(nickname)
                .map(user -> createUser(nickname, user))
                .orElseThrow(() -> new UsernameNotFoundException(nickname + " -> 존재하지 않는 유저"));
    }

    private org.springframework.security.core.userdetails.User createUser(String nickname, User user) {
        if (!user.isActive()) {
            throw new RuntimeException(nickname + " -> 활성화되지 않은 유저!!");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getNickname(),
                user.getPassword(),
                new ArrayList<>());
    }
}
