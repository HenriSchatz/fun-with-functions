package org.example.functionalstuff.shared.list;

import lombok.RequiredArgsConstructor;
import org.example.functionalstuff.shared.option.Just;
import org.example.functionalstuff.shared.option.Option;

import java.util.Objects;
import java.util.function.Function;

@RequiredArgsConstructor
public final class Cons<A> implements List<A> {

    private final A head;
    private final List<A> tail;

    @Override
    public int size() {
        return 1 + tail.size();
    }

    @Override
    public Option<A> get(int index) {
        if (index == 0) return new Just<>(head);
        return tail.get(index - 1);
    }

    @Override
    public <B> List<B> fmap(Function<A, B> f) {
        return new Cons<>(
                f.apply(head),
                tail.fmap(f)
        );
    }

    public A head() {
        return head;
    }

    public List<A> tail() {
        return tail;
    }

    @Override
    public <B> List<B> applyTo(List<Function<A, B>> f) {
        return switch (f) {
            case Empty<Function<A, B>> ignored -> new Empty<>();
            case Cons<Function<A, B>> f_ -> fmap(f_.head).concatWith(applyTo(f_.tail));
        };
    }

    @Override
    public <B> List<B> bind(Function<A, List<B>> f) {
        return f.apply(head).concatWith(tail.bind(f));
    }

    @Override
    public List<A> concatWith(List<A> other) {
        return new Cons<>(head, tail.concatWith(other));
    }

    @Override
    public String toString() {
        return "Cons[%s, %s]".formatted(head, tail);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cons<?> cons = (Cons<?>) o;

        if (!Objects.equals(head, cons.head)) return false;
        return Objects.equals(tail, cons.tail);
    }

    @Override
    public int hashCode() {
        int result = head != null ? head.hashCode() : 0;
        result = 31 * result + (tail != null ? tail.hashCode() : 0);
        return result;
    }
}
