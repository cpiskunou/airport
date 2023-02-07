package by.piskunou.solvdlaba.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ZTestTest {

    @Test
    public void test() {
        int x = 2;
        int y = 23;

        Assertions.assertEquals(25, x + y);
    }

    @Test
    public void test1() {
        int x = 2;
        int y = 23;

        Assertions.assertEquals(46, x * y);
    }
}
