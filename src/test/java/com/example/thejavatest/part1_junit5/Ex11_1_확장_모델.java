package com.example.thejavatest.part1_junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(FindSlowTestExtension.class)
public class Ex11_1_확장_모델 {

    @Test
//    @SlowTest   // 해당 영역이 없으면 추가하라는 메시지 출력
    void slow_test() throws InterruptedException {
        Thread.sleep(2000L);
    }

    @Test
    void fast_test() {
        System.out.println("others");
    }
}
