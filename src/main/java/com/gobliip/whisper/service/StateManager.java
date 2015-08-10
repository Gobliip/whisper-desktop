package com.gobliip.whisper.service;

import com.gobliip.chronos.client.model.WorkSession;
import com.gobliip.chronos.client.retrofit.WorkTrackerResource;
import com.gobliip.retrofit.cloud.mime.SHATypedByteArray;
import com.gobliip.retrofit.cloud.oauth2.jwt.JWTTokenStore;
import com.gobliip.whisper.jnative.GlobalScreenFacade;
import com.gobliip.whisper.model.WorkMetric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedString;

import java.awt.*;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by lsamayoa on 8/08/15.
 */
@Service
@ManagedResource
public class StateManager implements DisposableBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(StateManager.class);

    @Autowired
    private JWTTokenStore tokenStore;

    @Autowired
    private WorkTrackerResource workTrackerResource;

    @Autowired
    private GlobalScreenFacade screenFacade;

    @Autowired
    private WorkMetricCollector metricCollector;

    private AtomicReference<WorkSession> currentWorksession = new AtomicReference<>(null);
    private AtomicBoolean trackingActivity = new AtomicBoolean(false);

    @ManagedAttribute
    public boolean isTrackingActivity() {
        return trackingActivity.get();
    }

    @ManagedOperation
    public void startTracking() throws IOException, AWTException {
        final Optional<String> token = tokenStore.getToken();
        if (!token.isPresent()) {
            LOGGER.error("Error trying to start tracking workSession: Authentication token missing");
            // throw new NotAuthenticatedException("It is needed a user to start tracking computer activity");
        }
        final WorkSession workSession = workTrackerResource.start(new SHATypedByteArray("image/jpeg", screenFacade.takeScreenShot()), new TypedString("Started from whisper"));
        currentWorksession.set(workSession);
        trackingActivity.set(true);
    }

    @ManagedOperation
    public void stopTracking() throws IOException, AWTException {
        final Optional<String> token = tokenStore.getToken();
        if (!token.isPresent()) {
            LOGGER.error("Error trying to stop tracking workSession: Authentication token missing");
            // throw new NotAuthenticatedException("It is needed a user to start tracking computer activity");
        }
        final SHATypedByteArray image = new SHATypedByteArray("image/jpeg", screenFacade.takeScreenShot());
        final WorkMetric metric = metricCollector.collectMetric();
        workTrackerResource.stop(image, metric.getMouseActions(), metric.getKeyboardStrokes(), new TypedString("Stoped from whisper"));
        currentWorksession.set(null);
        trackingActivity.set(false);
        metricCollector.flushRegistries();
    }


    @ManagedOperation
    @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    public void logTracking() throws IOException, AWTException {
        if(isTrackingActivity()){
            metricCollector.registerMetric();
        }
    }


    public WorkSession getWorkSession() {
        return currentWorksession.get();
    }

    @Override
    public void destroy() throws Exception {
        if(isTrackingActivity()){
            stopTracking();
        }
        currentWorksession = null;
        trackingActivity = null;
    }
}
