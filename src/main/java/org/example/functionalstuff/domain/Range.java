package org.example.functionalstuff.domain;

public record Range(int start, int end) {
    public boolean contains(int i) {
        return i >= start && i <= end;
    }

    @Override
    public String toString() {
        return "%d..%d".formatted(start, end);
    }
}
