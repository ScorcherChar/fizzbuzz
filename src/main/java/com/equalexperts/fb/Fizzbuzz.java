package com.equalexperts.fb;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Fizzbuzz {

    /**
     * Creates a string counting from one to the given max replacing every third number with fiz, 5th with buzz and
     * fifteenth with fizzbuzz. Any number containing 3 will return lucky
     * @param max the max number to count to. Expected to be one or more.
     * @return the output string.
     * @exception IllegalArgumentException On input error.
     * @see IllegalArgumentException
     */
    public static String createOutput(final Integer max) throws IllegalArgumentException{
        if(max == null || max < 1){
            throw new IllegalArgumentException("Invalid max number given to fizzbuzz. Expecting above one");
        }

        final Stream<Integer> numbers = IntStream.rangeClosed(1, max).boxed();
        return numbers.map(x -> toFizzbuzzWord(x)).reduce((s1, s2) -> s1 + " " + s2).get();
    }

    private static String toFizzbuzzWord(final Integer inputNumber){
        if(containsAThree(inputNumber)){
            return "lucky";
        }else if(isExactMultipleOf(inputNumber, 15)){
            return "fizzbuzz";
        }else if(isExactMultipleOf(inputNumber, 5)){
            return "buzz";
        }else if(isExactMultipleOf(inputNumber, 3)){
            return "fizz";
        }else {
            return inputNumber.toString();
        }
    }

    private static boolean containsAThree(Integer inputNumber) {
        return inputNumber.toString().contains("3");
    }

    private static boolean isExactMultipleOf(final int inputNumber, final int multiple) {
        return inputNumber % multiple == 0;
    }
}
