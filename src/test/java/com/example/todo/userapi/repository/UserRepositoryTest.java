package com.example.todo.userapi.repository;

import com.example.todo.userapi.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("회원 가입 테스트")
    void saveText() {
        //given
        User newUser = User.builder()
                .email("abc1234@abc.com")
                .password("1234")
                .userName("춘식이")
                .build();
        //when
        User saved = userRepository.save(newUser);

        //then
        assertNotNull(saved);
    }

    @Test
    @DisplayName("이메일로 회원 조회하기")
    void findEmailTest() {
        //given
        String email = "abc1234@abc.com";
        //when
        Optional<User> userOptional = userRepository.findByEmail(email);
        //then
        assertTrue(userOptional.isPresent());
        User user = userOptional.get();
        assertEquals("춘식이", user.getUserName());

        System.out.println("\n\n\n");
        System.out.println("user = " + user);
        System.out.println("\n\n\n");
    }

    @Test
    @DisplayName("이메일 중복체크를 하면 중복값이 false여야 한다.")
    void emailFalse() {
        //given
        String email = "db1234@abc.com";
        //when
        boolean flag = userRepository.existsByEmail(email);
        //then
        assertFalse(flag);
    }

}