package com.gobliip.whisper.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * Created by lsamayoa on 7/8/15.
 */
@ManagedResource
public class ConcurrentManagedSetRegistry<T> implements DisposableBean {
	private Set<T> registryStore = new ConcurrentSkipListSet<>();

	public void add(T register) {
		registryStore.add(register);
	}

	@ManagedOperation
	public void flush() {
		synchronized (this.registryStore) {
			registryStore.clear();
		}
	}

	@ManagedAttribute
	public int getSize() {
		return registryStore.size();
	}

	@ManagedOperation
	public Set<String> getMData(){
		HashSet<String> result = new HashSet<String>();
		synchronized (this.registryStore) {
			for (Iterator<T> iterator = registryStore.iterator(); iterator.hasNext();) {
				T data = iterator.next();
				result.add(data.toString());
			}
		}
		return result;
	}

	public Set<T> getData() {
		return new LinkedHashSet<>(registryStore);
	}

	@Override
	public void destroy() throws Exception {
		flush();
	}
}
