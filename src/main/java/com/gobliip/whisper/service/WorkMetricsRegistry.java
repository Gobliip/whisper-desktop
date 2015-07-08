package com.gobliip.whisper.service;

import com.gobliip.whisper.model.WorkMetric;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by lsamayoa on 7/8/15.
 */
@Service
public class WorkMetricsRegistry extends SetRegistry<WorkMetric>{

}
