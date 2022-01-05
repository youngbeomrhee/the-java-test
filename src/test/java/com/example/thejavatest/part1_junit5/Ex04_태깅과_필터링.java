package com.example.thejavatest.part1_junit5;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
/*
참고
https://docs.gradle.org/current/userguide/java_testing.html#test_grouping
https://junit.org/junit5/docs/current/user-guide/#running-tests-tag-expressions
 */
//
// 빌드관리도구의 설정파일(build.gradle, pom.xml) 파일에 실행할 태그 지정
// run/debug configuration에서 include/exclude 할 태그 지정
public class Ex04_태깅과_필터링 {

    @DisplayName("fast 태그가 붙은 경우")
    @Test
    @Tag("fast")
    void testFast() {
        System.out.println("running in fast environments");
    }

    @DisplayName("slow 태그가 붙은 경우")
    @Test
    @Tag("slow")
    void testSlow() {
        System.out.println("running in slow environments");
    }

    @DisplayName("dev 태그가 붙은 경우")
    @Test
    @Tag("dev")
    void testDev() {
        System.out.println("개발시에만 하는 테스트");
    }

    @DisplayName("prod 태그가 붙은 경우")
    @Test
    @Tag("prod")
    void testProd() {
        System.out.println("운영배포시에 하는 테스트");
    }

}
