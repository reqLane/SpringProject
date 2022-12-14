package com.naukma.springproject.model;

public class Trio<S extends Comparable<S>, T, R> implements Comparable<Trio<S, T, R>>{
    private S first;
    private T second;
    private R third;

    public Trio(S first, T second, R third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public Trio() {
        this(null, null, null);
    }

    public S getFirst() {
        return this.first;
    }

    public T getSecond() {
        return this.second;
    }

    public R getThird() {
        return this.third;
    }

    public void setFirst(S first) {
        this.first = first;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    public void setThird(R third) {
        this.third = third;
    }

    public String toString() {
        return String.format("%s->%s", this.first, this.second);
    }

    @Override
    public int compareTo(Trio<S, T, R> o) {
        return this.first.compareTo(o.first);
    }
}
