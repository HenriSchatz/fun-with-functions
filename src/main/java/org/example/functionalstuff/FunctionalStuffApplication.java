package org.example.functionalstuff;

import org.example.functionalstuff.functional.list.Cons;
import org.example.functionalstuff.functional.list.Empty;
import org.example.functionalstuff.functional.list.List;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.function.Function;

@SpringBootApplication
public class FunctionalStuffApplication {

    public static void main(String[] args) {
        Function<Integer, List<Integer>> replicate3 = i -> replicate(i, 3);
        var result = interval(0, 5).bind(replicate3);
        System.out.println(result);
    }

    private static List<Integer> replicate(int i, int times) {
        if (times <= 0) {
            return new Empty<>();
        }

        return new Cons<>(i, replicate(i, times - 1));
    }

    private static List<Integer> interval(int start, int end) {
        if (start >= end) {
            return new Empty<>();
        }

        return new Cons<>(start, interval(start + 1, end));
    }
}
