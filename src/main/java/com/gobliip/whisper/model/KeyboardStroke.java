package com.gobliip.whisper.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.time.Instant;

/**
 * Created by lsamayoa on 7/8/15.
 */
public class KeyboardStroke implements Comparable<KeyboardStroke> {
    final private Instant when;
    final private int keyCode;
    final private char character;

    public KeyboardStroke(Instant when, int keyCode, char character) {
        this.when = when;
        this.keyCode = keyCode;
        this.character = character;
    }

    public Instant getWhen() {
        return when;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public char getCharacter() {
        return character;
    }

    @Override
    public int compareTo(KeyboardStroke stroke) {
        long difference = this.when.toEpochMilli() - stroke.getWhen().toEpochMilli();
        return (int) difference;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        KeyboardStroke stroke = (KeyboardStroke) o;

        if (keyCode != stroke.keyCode) {
            return false;
        }
        if (character != stroke.character) {
            return false;
        }
        return when.equals(stroke.when);

    }

    @Override
    public int hashCode() {
        int result = when.hashCode();
        result = 31 * result + keyCode;
        result = 31 * result + (int) character;
        return result;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
