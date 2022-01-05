package com.example.thejavatest.part1_junit5;

import com.example.thejavatest.domain.Study;
import java.time.Duration;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Ex02_Assertion {

    @DisplayName("assert equal")
    @Test
    void notNull() {
        Boolean expected = true;
        Boolean actual = false;
        String message = "초기값은 true여야 한다.";

        // 테스트 실패시 표시할 메시지
//        assertEquals(expected, actual, message);
        // supplier 사용 가능
//        assertEquals(expected, actual, () -> message);
/*
        assertEquals(expected, actual, new Supplier<String>() {
            @Override
            public String get() {
                return message;
            }
        });
*/
        // 이전의 테스트가 실패하면 이후의 테스트 확인 불가
        // -> 모든 테스트 실행 assertAll
        assertAll(
            () -> assertEquals(expected, actual, "실패하는 테스트1"),
            () -> assertEquals(expected, actual, "실패하는 테스트2"),
            () -> assertEquals(expected, actual, "실패하는 테스트3")
        );
    }

    @DisplayName("Assert Exception")
    @Test
    void testAssertThrows() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-1));
        assertEquals(exception.getMessage(), "limit은 0보다 커야 한다.");
    }

    @DisplayName("Assert Timeout - timeout 시간이 지나도 내부로직 실행종료 후에 종료")
    @Test
    void testAssertTimeout() {
        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });
    }

    @DisplayName("Assert Timeout - timeout 설정시간이 지나면 바로 종료")
    @Test
    void testAssertTimeoutPreemptively() {
        // TODO : 실행문 중에 ThreadLocal을 사용하는 경우 예상치 못한 결과가 발생할 수 있다
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });
    }
}
