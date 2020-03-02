package co.m1ke.basic;

import co.m1ke.basic.utils.container.Key;

public enum BasicSettings {

    AUTHOR(Key.of("Mike M")),
    VERSION(Key.of(0.4)),
    WEBSITE(Key.of("https://www.m1ke.co"));

    BasicSettings(Key value) {
        this.value = value;
    }

    private Key value;

    public Key getValue() {
        return value;
    }

}
