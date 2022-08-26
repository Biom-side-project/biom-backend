package com.biom.biombackend.users.features.generatenickname;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultNicknameGeneratorTest {
    private Integer howMany;
    private DefaultNicknameGenerator generator = new DefaultNicknameGenerator();
    
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