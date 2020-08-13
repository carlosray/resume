package com.resume.event;

public abstract class UpdateEntityEvent<T> {
    private final T object;

    protected UpdateEntityEvent(T object) {
        this.object = object;
    }

    public T getObject() {
        return object;
    }
}
