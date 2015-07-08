package com.gobliip.whisper.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.time.Instant;

/**
 * Created by lsamayoa on 7/8/15.
 */
public class WorkMetric implements Comparable<WorkMetric>{

    final private Instant start;
    final private Instant end;
    final private int keyboardStrokes;
    final private int mouseActions;

    public WorkMetric(Instant start, Instant end, int keyboardStrokes, int mouseActions) {
        this.start = start;
        this.end = end;
        this.keyboardStrokes = keyboardStrokes;
        this.mouseActions = mouseActions;
    }

    public Instant getStart() {
        return start;
    }

    public Instant getEnd() {
        return end;
    }

    public int getKeyboardStrokes() {
        return keyboardStrokes;
    }

    public int getMouseActions() {
        return mouseActions;
    }

    @Override
    public int compareTo(WorkMetric metric) {
        long difference = this.start.toEpochMilli() - metric.getStart().toEpochMilli();
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

        WorkMetric metric = (WorkMetric) o;

        if (keyboardStrokes != metric.keyboardStrokes) {
            return false;
        }
        if (mouseActions != metric.mouseActions) {
            return false;
        }
        if (!start.equals(metric.start)) {
            return false;
        }
        return end.equals(metric.end);

    }

    @Override
    public int hashCode() {
        int result = start.hashCode();
        result = 31 * result + end.hashCode();
        result = 31 * result + keyboardStrokes;
        result = 31 * result + mouseActions;
        return result;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
