package com.gobliip.whisper.service;

import com.gobliip.whisper.model.WorkMetric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * Created by lsamayoa on 7/8/15.
 */
@Service
public class WorkMetricCollector {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkMetricCollector.class);

    @Autowired
    private KeyboardStrokesRegistryConcurrentManaged keyboardRegistry;

    @Autowired
    private MouseActionsRegistryConcurrentManaged mouseRegistry;

    @Autowired
    private WorkMetricsRegistryConcurrentManaged workRegistry;

    private Instant lastCollection = Instant.now();

    @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    public void collectRegistries() {
        LOGGER.debug("Collecting metric - last collection: {}", getLastCollection());
        Instant collectionInstant = Instant.now();
        final WorkMetric metric = new WorkMetric(getLastCollection(), collectionInstant, getKeyboardStrokes(), getMouseActions());
        workRegistry.add(metric);
        setLastCollection(collectionInstant);
        flushRegistries();
        LOGGER.debug("Collected metric: {}", metric);
    }

    private int getMouseActions() {
        return mouseRegistry.getSize();
    }

    private void flushRegistries(){
        keyboardRegistry.flush();
        mouseRegistry.flush();
    }


    private int getKeyboardStrokes() {
        return keyboardRegistry.getSize();
    }

    protected void setLastCollection(Instant lastCollection){
        synchronized (this.lastCollection){
            this.lastCollection = lastCollection;
        }
    }

    protected Instant getLastCollection() {
        return lastCollection;
    }

}
