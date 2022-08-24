package com.biom.biombackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BiomApplication {
    /*
    * 테스트 에서의 SpringConfiguration 을 위한 메인 클래스 메서드.
    * 독립적인 실행도 가능하지만 현재 Monolith 구성을 전제로 하고 있으므로 직접 실행하지는 않는다. */
    public static void main(String[] args) {
        SpringApplication.run(BiomApplication.class, args);
    }
}
