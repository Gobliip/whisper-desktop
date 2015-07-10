package com.gobliip.whisper.service;

import org.springframework.stereotype.Service;

import com.gobliip.whisper.model.KeyboardStroke;

@Service
public class KeyboardStrokesRegistryConcurrentManaged extends ConcurrentManagedSetRegistry<KeyboardStroke> {

}
