package com.example.kameleoonproject.repository;

import com.example.kameleoonproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @Test
    void itShouldFindByEmail() {
        //given
        User givenUser = new User(
                "Andrew", "andrew@gmail.com", "123"
        );
        underTest.save(givenUser);

        //when
        User savedUser = underTest.findByEmail("andrew@gmail.com").get();

        //then
        assertThat(savedUser).isEqualTo(givenUser);
    }


}