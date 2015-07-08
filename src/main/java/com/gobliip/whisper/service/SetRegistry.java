package com.gobliip.whisper.service;

import org.springframework.beans.factory.DisposableBean;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by lsamayoa on 7/8/15.
 */
public class SetRegistry<T> implements DisposableBean{
    private Set<T> registryStore = new ConcurrentSkipListSet<>();

    public void add(T register){
        registryStore.add(register);
    }

    public void flush(){
        synchronized (this.registryStore) {
            registryStore.clear();
        }
    }

    public int size(){
        return registryStore.size();
    }

    public Set<T> getData(){
        return new LinkedHashSet<>(registryStore);
    }

    @Override
    public void destroy() throws Exception {
        flush();
    }
}
