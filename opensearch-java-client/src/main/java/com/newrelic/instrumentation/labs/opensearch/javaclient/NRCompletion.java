package com.newrelic.instrumentation.labs.opensearch.javaclient;

import java.util.function.BiConsumer;

import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;

public class NRCompletion<T> implements BiConsumer<T, Throwable> {
	
	private Segment segment = null;
	private DatastoreParameters params = null;
	
	public NRCompletion(Segment s, DatastoreParameters p) {
		segment = s;
		params = p;
	}

	@Override
	public void accept(T t, Throwable u) {
		if(u != null) {
			NewRelic.noticeError(u);
			if(segment != null) {
				segment.ignore();
				segment = null;
			}
		} else if(t != null) {
			if(segment != null) {
				if(params != null) {
					segment.reportAsExternal(params);
				}
				segment.end();
				segment = null;
			}
		}
	}

}
