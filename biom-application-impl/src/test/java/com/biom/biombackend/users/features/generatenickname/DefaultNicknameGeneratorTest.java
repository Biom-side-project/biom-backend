package com.biom.biombackend.users.features.generatenickname;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

class DefaultNicknameGeneratorTest {
    private Integer howMany;
    private DefaultNicknameGenerator generator = new DefaultNicknameGenerator();
    
    @RepeatedTest(1)
    @DisplayName("test01")
    void test01() {
        // given
        Faker faker = new Faker(new Locale("ko"));
        HashSet<String> names = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            String hex = faker.random().hex();
            String name = faker.dragonBall().character();
            names.add(name);
            System.out.println("hex = " + hex);
            System.out.println("name = " + name);
        }
    
        howMany = names.size();
        assertThat(names.size()).isEqualTo(1000);
    }
    
    @Test
    @DisplayName("randomNickname")
    void randomNickname() {
        HashSet<String> names = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            String name = generator.randomNickname();
            names.add(name);
            System.out.println("name = " + name);
        }
        int size = names.size();
        howMany = size;
        assertThat(size).isEqualTo(1000);
    }
    
    @AfterEach
    void printSize(){
        System.out.println(howMany);
    }
}