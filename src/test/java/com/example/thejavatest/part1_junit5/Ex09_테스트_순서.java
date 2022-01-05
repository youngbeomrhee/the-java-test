package com.example.thejavatest.part1_junit5;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;

/*
// 순서 적용 전
applyBusiness()
signup()
modifyBusiness()
login()
deleteBusiness()
withdrawalMembership()

// @TestMethodOrder(MethodOrderer.OrderAnnotation.class) 적용 후
signup()
login()
applyBusiness()
modifyBusiness()
deleteBusiness()
withdrawalMembership()
 */
@TestInstance(Lifecycle.PER_CLASS)  // 함께 사용하면 인스턴스를 공유할 수 있다
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Ex09_테스트_순서 {
    int value = 1;

    @Order(1)
    @Test
    void sign_up() {
        System.out.println("seq : " + 1);
        System.out.println("value : " + value++);
    }

    @Order(2)
    @Test
    void login() {
        System.out.println("seq : " + 2);
        System.out.println("value : " + value++);
    }

    @Order(3)
    @Test
    void apply_business() {
        System.out.println("seq : " + 3);
        System.out.println("value : " + value++);
    }

    @Test
    void intermediate() {
        System.out.println("seq : " + -1);
        System.out.println("value : " + value++);
    }

    @Order(4)
    @Test
    void modify_business() {
        System.out.println("seq : " + 4);
        System.out.println("value : " + value++);
    }

    @Order(5)
    @Test
    void delete_business() {
        System.out.println("seq : " + 5);
        System.out.println("value : " + value++);
    }

    @Order(6)
    @Test
    void withdrawal_membership() {
        System.out.println("seq : " + 6);
        System.out.println("value : " + value++);
    }
}
