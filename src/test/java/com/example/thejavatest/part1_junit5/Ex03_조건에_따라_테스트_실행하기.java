package com.example.thejavatest.part1_junit5;

import com.example.thejavatest.domain.Study;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperties;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

public class Ex03_조건에_따라_테스트_실행하기 {

    @DisplayName("조건에 따른 테스트")
    @Test
    void testAssumingThat() {
        String testEnv = System.getenv("TEST_ENV");
        System.out.println("TEST_ENV=" + testEnv);

        Assumptions.assumingThat("LOCAL".equalsIgnoreCase(testEnv), () -> {
            System.out.println("local case");
            Study actual = new Study(100);

            Assertions.assertThat(actual.getLimitCount()).isGreaterThan(0);
        });

        Assumptions.assumingThat(testEnv == null, () -> {
            System.out.println("null case");
            Study actual = new Study(10);

            Assertions.assertThat(actual.getLimitCount()).isGreaterThan(0);
        });
    }

    /* 조건을 정의하는 Annotation */
    @DisplayName("OS가 일치하는 경우 테스트 수행")
    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void testEnabledOnOs() {
        System.out.println(System.getProperty("os.name"));
    }

    @DisplayName("OS가 일치하지 않는 경우 테스트 수행안함")
    @Test
    @EnabledOnOs({OS.WINDOWS})
    void testNotEnabledOnOs() {
        System.out.println(System.getProperty("os.name"));
    }

    @DisplayName("JRE가 일치하는 경우 테스트 수행")
    @Test
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_11})
    void testEnabledOnJre() {
        System.out.println(System.getProperty("java.specification.version"));
    }

    @DisplayName("JRE가 일치하지 않는 경우 테스트 수행")
    @Test
    @DisabledOnJre({JRE.JAVA_9})
    void testDisabledOnJre() {
        System.out.println(System.getProperty("java.specification.version"));
    }

    @DisplayName("JRE가 범위안에 들어있는 경우 테스트 수행")
    @Test
    @EnabledForJreRange(min=JRE.JAVA_8, max=JRE.JAVA_11)
    void testEnabledForJreRange() {
        System.out.println(System.getProperty("java.specification.version"));
    }

    @DisplayName("JRE가 일치하지 않는 경우 테스트 수행안함")
    @Test
    @EnabledOnJre({JRE.OTHER})
    void testNotEnabledOnJre() {
        System.out.println(System.getProperty("java.specification.version"));
    }

    @DisplayName("시스템변수가 정규식에 매치되는 경우 테스트")
    @Test
    @EnabledIfSystemProperty(named = "java.vendor", matches = "^(Amazon).*(Inc.)$")
    void testEnabledIfSystemProperty() {
        System.out.println(System.getProperty("java.vendor"));
    }

    @DisplayName("시스템변수가 정규식에 매치되는 경우 테스트")
    @Test
    @EnabledIfSystemProperties({
        @EnabledIfSystemProperty(named = "java.vendor", matches = "Amazon.*"),
        @EnabledIfSystemProperty(named = "java.specification.version", matches = "11")
    })
    void testEnabledIfSystemProperties() {
        System.out.println(System.getProperty("java.vendor"));
        System.out.println(System.getProperty("java.specification.version"));
    }

    @DisplayName("환경변수가 정규식에 매치되는 경우 테스트")
    @Test
    @EnabledIfEnvironmentVariable(named = "USER", matches = "iyeongbeom")
    void testEnabledIfEnvironmentVariable() {
        System.out.println(System.getenv("USER"));
    }

}
