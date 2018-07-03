package com.swd.j8;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambda {


    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }

    public static class FirstCharacterConverter {
        String startsWith(String s) {
            return String.valueOf(s.charAt(0));
        }
    }

    public static class Person {
        String firstName;
        String lastName;

        Person() {
        }

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }
    }

    @FunctionalInterface
    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }

    //***************************************************
    //
    //      ---  MAIN ---
    //
    //***************************************************

    public static void main(String[] args) {
        Lambda.functionalInterfaceExamples();
        Lambda.scopeExamples();
        Lambda.predicateExamples();
        Lambda.functionCompositionExamples();
        Lambda.supplierConsumerExamples();
        Lambda.streamExamples();
        Lambda.mapExamples();
    }

    public static void functionalInterfaceExamples() {

        // Inline
        {
            Converter<String, Integer> converter = (from) -> {
                return Integer.valueOf(from);
            };
            System.out.println(converter.convert("7775"));
        }

        // Verbose
        {
            Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
            System.out.println(converter.convert("7775"));
        }

        // Using static method references
        {
            Converter<String, Integer> converter = Integer::valueOf;
            System.out.println(converter.convert("7775"));
        }

        // Using object method references
        {
            FirstCharacterConverter fcc = new FirstCharacterConverter();
            Converter<String, String> converter = fcc::startsWith;
            System.out.println(converter.convert("7775"));
        }

        // Using class constructor
        {
            PersonFactory<Person> factory = Person::new;
            System.out.println(factory.create("Joe", "Smith"));
        }
    }

    public static void scopeExamples() {

        {
            // Local variable must be implicitly final to be used w/ Lambda
            final int localValue = 155;
            Converter<Integer, Integer> converter = (from) -> Integer.valueOf(from + localValue);
            System.out.println(converter.convert(45));

        }
    }

    public static void predicateExamples() {

        {
            System.out.println(" ----- predicate examples ----- ");
            Predicate<String> predicate = (s) -> s.length() > 0;

            System.out.println(predicate.test("foo"));              // true
            System.out.println(predicate.negate().test("foo"));     // false

            Predicate<Boolean> nonNull = Objects::nonNull;
            Predicate<Boolean> isNull = Objects::isNull;

            Predicate<String> isEmpty = String::isEmpty;
            Predicate<String> isNotEmpty = isEmpty.negate();
        }
    }

    public static void functionCompositionExamples() {

        {
            System.out.println(" ----- function examples ----- ");

            Function<Integer,Integer> times2 = (a) -> { return a * 2; };
            Function<Integer,Integer> square = (a) -> a * a;
            System.out.println("8 * 2 = " + times2.apply(8));
            System.out.println("8 squared = " + square.apply(8));
            System.out.println("square.compose(times2(8)) = " + square.compose(times2).apply(8));
            System.out.println("square.andThen(times2(8)) = " + square.andThen(times2).apply(8));
        }
    }

    public static void supplierConsumerExamples() {
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();   // new Person

        Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
        greeter.accept(new Person("Luke", "Skywalker"));
    }

    public static void streamExamples(){
        System.out.println(" ----- stream examples ----- ");

        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        // Filter
        stringCollection
                .stream()
                .filter((s) -> s.startsWith("b"))
                .forEach(System.out::println);
        System.out.println(" \t\t ----- ");

        // Sorted
        stringCollection
                .stream()
                .sorted()
                .filter((s) -> s.startsWith("b"))
                .forEach(System.out::println);
        System.out.println(" \t\t ----- ");

        // Map
        stringCollection
                .stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))
                .forEach(System.out::println);
        System.out.println(" \t\t ----- ");

        // Match
        boolean anyStartsWithA =
                stringCollection
                        .stream()
                        .anyMatch((s) -> s.startsWith("a"));

        System.out.println(anyStartsWithA);      // true

        boolean allStartsWithA =
                stringCollection
                        .stream()
                        .allMatch((s) -> s.startsWith("a"));

        System.out.println(allStartsWithA);      // false

        boolean noneStartsWithZ =
                stringCollection
                        .stream()
                        .noneMatch((s) -> s.startsWith("z"));

        System.out.println(noneStartsWithZ);      // true
        System.out.println(" \t\t ----- ");

        // Count
        long startsWithB =
                stringCollection
                        .stream()
                        .filter((s) -> s.startsWith("b"))
                        .count();

        System.out.println(startsWithB);    // 3
        System.out.println(" \t\t ----- ");

        // Reduce
        Optional<String> reduced =
                stringCollection
                        .stream()
                        .sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);

        reduced.ifPresent(System.out::println); // "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"
        System.out.println(" \t\t ----- ");


    }

    public static void mapExamples() {
        System.out.println(" ----- map examples ----- ");
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach((id, val) -> System.out.println(val));
        System.out.println(" \t\t ----- ");

        map.computeIfPresent(3, (num, val) -> val + num);
        System.out.println(map.get(3));             // val33

        map.computeIfPresent(9, (num, val) -> null);
        System.out.println(map.containsKey(9));     // false

        map.computeIfAbsent(23, num -> "val" + num);
        System.out.println(map.containsKey(23));    // true

        map.computeIfAbsent(3, num -> "bam");
        System.out.println(map.get(3));             // val33
        System.out.println(" \t\t ----- ");


        map.forEach((id, val) -> System.out.println(val));
        System.out.println(" \t\t ----- ");

    }


}
