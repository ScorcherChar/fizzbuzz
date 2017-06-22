package com.equalexperts.fb;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class FizzbuzzTest {

    @Test(expected = IllegalArgumentException.class)
    public void nullInputGivesArgumentException() {
        Fizzbuzz.createOutput(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroInputGivesArgumentException() {
        Fizzbuzz.createOutput(0);
    }

    @Test
    public void twentyGivesExpectedOutput() {
        String output = Fizzbuzz.createOutput(20);
        final String expected = "1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz 16 17 fizz 19 buzz fizz: 4 buzz: 3 fizzbuzz: 1 lucky: 2 integer: 10";
        assertEquals(expected, output);
    }

    @Test
    public void thirtyNumbersGivesFourLucky() {
        //3 13 and 30
        String output = Fizzbuzz.createOutput(30);
        assertTrue(output.contains("lucky: 4"));
    }
}
