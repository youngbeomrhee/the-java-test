package com.example.thejavatest.part1_junit5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class Ex01_테스트_이름_표시하기 {

    @Test
    public void underscore_to_space() {
        System.out.println("test_underscore");
    }

    @DisplayName("🤩 상세한 설명을 넣을 수 있는지 테스트 🤩")
    @Test
    public void display_name() {
        System.out.println("test_underscore2");
    }
}