package com.example.thejavatest.part1_junit5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
/*
// @TestInstance(Lifecycle.PER_CLASS) 없이 실행
com.example.thejavatest.part1_junit5.Ex8_테스트_인스턴스@6cc558c6
1
com.example.thejavatest.part1_junit5.Ex8_테스트_인스턴스@4ae9cfc1
1

// @TestInstance(Lifecycle.PER_CLASS) 추가 후 실행
com.example.thejavatest.part1_junit5.Ex8_테스트_인스턴스@26ceffa8
1
com.example.thejavatest.part1_junit5.Ex8_테스트_인스턴스@26ceffa8
2
 */
// junit-platform.properties 에서 기본 설정을 변경할 수 있다
// junit.jupiter.testinstance.lifecycle.default = per_class
@TestInstance(Lifecycle.PER_CLASS)
//@TestInstance(Lifecycle.PER_METHOD)
public class Ex08_테스트_인스턴스 {
    int value = 1;

    @Test
    void shareValueTest() {
        System.out.println(this);
        System.out.println(value++);
    }

    @Test
    void shareValueTest2() {
        System.out.println(this);
        System.out.println(value++);
    }

    @BeforeAll
//    void beforeAll() {    // if @TestInstance(Lifecycle.PER_CLASS) -> static 제외 가능
    static void beforeAll() {   // others
        System.out.println("# BeforeAll");
    }

    @AfterAll
//    void afterAll() { // if @TestInstance(Lifecycle.PER_CLASS) -> static 제외 가능
    static void afterAll() {    // others
        System.out.println("# AfterAll");
    }
}
