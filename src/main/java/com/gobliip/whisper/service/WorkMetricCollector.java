package com.gobliip.whisper.service;

import com.gobliip.chronos.client.retrofit.WorkTrackerResource;
import com.gobliip.whisper.jnative.GlobalScreenFacade;
import com.gobliip.whisper.model.WorkMetric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.time.Instant;

/**
 * Created by lsamayoa on 7/8/15.
 */
@Service
@ManagedResource
public class WorkMetricCollector {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkMetricCollector.class);

    @Autowired
    private KeyboardStrokesRegistry keyboardRegistry;

    @Autowired
    private MouseActionsRegistry mouseRegistry;

    @Autowired
    private WorkMetricsRegistry workRegistry;

    @Autowired
    private GlobalScreenFacade screenFacade;

    @Autowired
    private WorkTrackerResource workTrackerResource;

    public WorkMetricCollector() {
        super();
    }

    private Instant lastCollection = Instant.now();

    @ManagedOperation
    public WorkMetric registerMetric() throws IOException, AWTException {
        LOGGER.debug("Collecting metric - last collection: {}", getLastCollection());
        final WorkMetric metric = collectMetric();
        workRegistry.add(metric);
        flushRegistries();
        LOGGER.debug("Collected metric: {}", metric);
        return metric;
    }

    @ManagedOperation
    public WorkMetric collectMetric() throws IOException, AWTException {
        final Instant collectionInstant = Instant.now();
        final WorkMetric metric = new WorkMetric(getLastCollection(), collectionInstant, getKeyboardStrokes(), getMouseActions(), screenFacade.takeScreenShot());
        setLastCollection(collectionInstant);
        return metric;
    }


    @ManagedAttribute
    private int getMouseActions() {
        return mouseRegistry.getSize();
    }

    @ManagedOperation
    public void flushRegistries(){
        keyboardRegistry.flush();
        mouseRegistry.flush();
    }

    @ManagedAttribute
    private int getKeyboardStrokes() {
        return keyboardRegistry.getSize();
    }

    protected synchronized void setLastCollection(Instant lastCollection){
        this.lastCollection = lastCollection;
    }

    @ManagedAttribute
    protected Instant getLastCollection() {
        return lastCollection;
    }

}
