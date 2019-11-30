package com.qugenx.storage.storage;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionStorage implements Serializable {

    private static final long serialVersionUID = 1L;

    private HashMap<Object, Object> storage = new HashMap<>();

    private UUID id;

    public SessionStorage() {
        setId(UUID.randomUUID());
    }

    public <T> void put(Class<T> clazz, final T object) {
        storage.put(clazz, object);
    }

    public <T> void put(Property property, final T object) {
        storage.put(property.getName(), object);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(final Class<T> clazz) {
        return (T) storage.get(clazz);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(final Property property) {
        return (T) storage.get(property.getName());
    }

    public <T> void remove(final Class<T> clazz) {
        storage.remove(clazz);
    }

    public void remove(final Property property) {
        storage.remove(property.getName());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
