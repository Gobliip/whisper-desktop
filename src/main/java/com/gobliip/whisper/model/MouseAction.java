package com.gobliip.whisper.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.time.Instant;

/**
 * Created by lsamayoa on 7/8/15.
 */
public class MouseAction implements Comparable<MouseAction> {
    final private Instant when;
    final private int x;
    final private int y;

    public MouseAction(Instant when, int x, int y) {
        this.when = when;
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(MouseAction action) {
        long difference = this.when.toEpochMilli() - action.getWhen().toEpochMilli();
        return (int)difference;
    }

    public Instant getWhen() {
        return when;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MouseAction that = (MouseAction) o;

        if (x != that.x) {
            return false;
        }
        if (y != that.y) {
            return false;
        }
        return when.equals(that.when);

    }

    @Override
    public int hashCode() {
        int result = when.hashCode();
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
