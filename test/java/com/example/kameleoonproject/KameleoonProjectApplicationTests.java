package com.example.kameleoonproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
@Configuration
@ComponentScan({"com.example.*"})
class KameleoonProjectApplicationTests {

    @Test
    void contextLoads() {
    }

}
