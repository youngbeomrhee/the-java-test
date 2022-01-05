package com.example.thejavatest.part1_junit5;

import static org.junit.jupiter.api.Assertions.*;

import com.example.thejavatest.domain.Study;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

/*
https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests
 */
public class Ex07_테스트_반복하기2 {
    @DisplayName("Parameterized test - 공백과 null을 source에 추가")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요."})
//    @EmptySource  // 공백을 parameter로 추가
//    @NullSource   // null을 parameter로 추가
    @NullAndEmptySource // composed of @EmptySource and @NullSource
    void advancedParameterizedTest(String message) {
        System.out.println(message);
    }

    @DisplayName("Parameterized test - int값 받기")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(ints = {10, 20, 40})
    void valueSourceIntegerTest(Integer limit) {
        System.out.println(limit);
    }

    @DisplayName("Parameterized test - converter 활용")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(ints = {10, 20, 40})
    void converterTest(@ConvertWith(StudyConverter.class) Study study) {
        System.out.println(study.getLimitCount());
    }

    @DisplayName("Parameterized test - CSV source 활용")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, '자바스터디'", "20, '스프링'"})
    void sourceCSVTest(Integer limit, String name) {
        Study study = new Study(limit, name);
        System.out.println(study);
    }

    @DisplayName("Parameterized test - ArgumentsAccessor 활용")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, '자바스터디'", "20, '스프링'"})
    void argumentsAccessorCSVTest(ArgumentsAccessor aa) {
        Study study = new Study(aa.getInteger(0), aa.getString(1));
        System.out.println(study);
    }

    @DisplayName("Parameterized test - ArgumentsAggregator 활용")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, '자바스터디'", "20, '스프링'"})
    void argumentsAggregatorCSVTest(@AggregateWith(StudyAggregator.class) Study study) {
        System.out.println(study);
    }

    static class StudyConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType)  throws ArgumentConversionException {
            assertEquals(Study.class, targetType, "Can only convert to Study");
            return new Study(Integer.parseInt(source.toString()));
        }
    }

    static class StudyAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            Study study = new Study(accessor.getInteger(0), accessor.getString(1));
            return study;
        }
    }
}
