package com.example.thejavatest.part1_junit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class Ex06_테스트_반복하기 {
    @DisplayName("반복테스트")
    @RepeatedTest(value = 10, name = "> {displayName} {currentRepetition} / {totalRepetitions}")    // bug로 인해 displayName이 String의 가장 앞에 위치한 경우 출력안됨
    void repeatTest(RepetitionInfo repetitionInfo) {
        System.out.println("test " + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("Parameterized test")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요."})
    void parameterizedTest(String message) {
        System.out.println(message);
    }
}
