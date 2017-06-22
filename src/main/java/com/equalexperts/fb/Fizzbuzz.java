package com.equalexperts.fb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Fizzbuzz {


    private final static String FIZZ = "fizz";
    private final static String BUZZ = "buzz";
    private final static String FIZZBUZZ = "fizzbuzz";
    private final static String LUCKY = "lucky";
    private final static String INTEGER = "integer";

    /**
     * Creates a string counting from one to the given max replacing every third number with fiz, 5th with buzz and
     * fifteenth with fizzbuzz. Any number containing 3 will return lucky. Is appended with a report on word frequency
     *
     * @param max the max number to count to. Expected to be one or more.
     * @return the output string.
     * @throws IllegalArgumentException On input error.
     * @see IllegalArgumentException
     */
    public static String createOutput(final Integer max) throws IllegalArgumentException {
        if (max == null || max < 1) {
            throw new IllegalArgumentException("Invalid max number given to fizzbuzz. Expecting above one");
        }

        final Stream<Integer> numbers = IntStream.rangeClosed(1, max).boxed();
        final List<String> fizzbuzzWords = numbers.map(x -> toFizzbuzzWord(x)).collect(Collectors.toList());
        final Map<String, Long> wordsFrequency = fizzbuzzWords.stream().map(x -> toFizzBuzzWordType(x)).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        final List<String> allWords = new ArrayList<>();
        allWords.addAll(fizzbuzzWords);
        //Cant just combine lists because order of report is important
        allWords.add(getReportForWord(FIZZ, wordsFrequency));
        allWords.add(getReportForWord(BUZZ, wordsFrequency));
        allWords.add(getReportForWord(FIZZBUZZ, wordsFrequency));
        allWords.add(getReportForWord(LUCKY, wordsFrequency));
        allWords.add(getReportForWord(INTEGER, wordsFrequency));

        return allWords.stream().reduce((s1, s2) -> s1 + " " + s2).get();
    }


    private static String getReportForWord(final String word, final Map<String, Long> reportWordsFrequency) {
        Long wordFrequency = reportWordsFrequency.getOrDefault(word, new Long(0));
        return word + ": " + wordFrequency;
    }

    private static String toFizzbuzzWord(final Integer inputNumber) {
        if (containsAThree(inputNumber)) {
            return LUCKY;
        } else if (isExactMultipleOf(inputNumber, 15)) {
            return FIZZBUZZ;
        } else if (isExactMultipleOf(inputNumber, 5)) {
            return BUZZ;
        } else if (isExactMultipleOf(inputNumber, 3)) {
            return FIZZ;
        } else {
            return inputNumber.toString();
        }
    }

    private static boolean containsAThree(final Integer inputNumber) {
        return inputNumber.toString().contains("3");
    }

    private static String toFizzBuzzWordType(final String input) {
        if (input == LUCKY || input == FIZZ || input == BUZZ || input == FIZZBUZZ) {
            return input;
        } else {
            return "integer";
        }
    }

    private static boolean isExactMultipleOf(final int inputNumber, final int multiple) {
        return inputNumber % multiple == 0;
    }
}
