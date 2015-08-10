package com.gobliip.whisper.service;

import com.gobliip.chronos.client.model.WorkSession;
import com.gobliip.chronos.client.retrofit.WorkTrackerResource;
import com.gobliip.retrofit.cloud.mime.SHATypedByteArray;
import com.gobliip.whisper.model.WorkMetric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit.mime.TypedString;

/**
 * Created by lsamayoa on 7/8/15.
 */
@Service
public class WorkMetricsRegistry {

    @Autowired
    private WorkTrackerResource workTrackerResource;

    @Autowired
    private StateManager stateManager;

    public void add(WorkMetric register) {
        final WorkSession session = stateManager.getWorkSession();
        final SHATypedByteArray image = new SHATypedByteArray("image/jpeg", register.getScreeImage());
        workTrackerResource.log(
                image,
                register.getMouseActions(),
                register.getKeyboardStrokes(),
                new TypedString("Added from work registry"));
    }
}
