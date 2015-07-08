package com.gobliip.whisper.service;

import apple.security.KeychainStore;
import com.gobliip.whisper.model.KeyboardStroke;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Service
public class KeyboardStrokesRegistry extends SetRegistry<KeyboardStroke>{

}
