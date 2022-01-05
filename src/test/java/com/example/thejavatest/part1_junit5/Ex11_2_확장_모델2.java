package com.example.thejavatest.part1_junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

//@ExtendWith(FindSlowTestRegisterExtension.class) 제거
public class Ex11_2_확장_모델2 {

    @RegisterExtension
    static FindSlowTestRegisterExtension findSlowTestRegisterExtension =
        new FindSlowTestRegisterExtension(1000L);

    @Test
//    @SlowTest   // 해당 영역이 없으면 추가하라는 메시지 출력
    void slow_test() throws InterruptedException {
        Thread.sleep(1005L);
    }

    @Test
    void fast_test() {
        System.out.println("others");
    }
}
