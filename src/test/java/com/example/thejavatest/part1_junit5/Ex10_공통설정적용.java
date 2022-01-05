package com.example.thejavatest.part1_junit5;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// junit-platform.properties 에 적용된 설정 확인
// 주석 해제 전 실행결과와 주석 해제 후 실행결과 비교
public class Ex10_공통설정적용 {
    int value = 1;

    @Test
    void test_테스트_인스턴스_라이프사이클_설정1() {
        assertEquals(value++, 1);
    }

    @Test
    void test_테스트_인스턴스_라이프사이클_설정2() {
        assertEquals(value++, 2);
    }

    @Test
    @Disabled
    void test_ignore_disabled() {
        System.out.println("Disabled method");
    }

    @Test
    void test_replace_underscore() {
        System.out.println("Check out method name");
    }
}
