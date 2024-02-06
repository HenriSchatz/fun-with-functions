package org.example.functionalstuff.shared.list;

import org.example.functionalstuff.shared.Unit;
import org.example.functionalstuff.shared.option.Nothing;
import org.example.functionalstuff.shared.option.Option;

import java.util.function.Function;
import java.util.function.Predicate;

public final class Empty<A> implements List<A> {
    @Override
    public int size() {
        return 0;
    }

    @Override
    public Option<A> get(int index) {
        return new Nothing<>();
    }

    @Override
    public <B> List<B> fmap(Function<A, B> f) {
        return new Empty<>();
    }

    @Override
    public <B> List<B> applyTo(List<Function<A, B>> f) {
        return new Empty<>();
    }

    @Override
    public <B> List<B> bind(Function<A, List<B>> f) {
        return new Empty<>();
    }

    @Override
    public List<A> concatWith(List<A> other) {
        return other;
    }

    @Override
    public Option<A> find(Predicate<A> predicate) {
        return new Nothing<>();
    }

    @Override
    public Unit foreach(Function<A, ?> f) {
        return Unit.get();
    }

    @Override
    public <B> B fold(Function<A, Function<B, B>> f, B start) {
        return start;
    }

    @Override
    public boolean any(Predicate<A> predicate) {
        return false;
    }

    @Override
    public List<A> filter(Predicate<A> predicate) {
        return this;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Empty;
    }

    @Override
    public String toString() {
        return "Empty";
    }
}
