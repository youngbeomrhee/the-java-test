package com.example.thejavatest.part1_junit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class Ex05_커스텀_태그 {

    @DisplayName("fast 태그가 붙은 경우")
//    @Test
//    @Tag("fast")
    @FastTest
    void testFast() {
        System.out.println("running in fast environments");
    }

    @DisplayName("slow 태그가 붙은 경우")
//    @Test
//    @Tag("slow")
    @SlowTest
    void testSlow() {
        System.out.println("running in slow environments");
    }
}
