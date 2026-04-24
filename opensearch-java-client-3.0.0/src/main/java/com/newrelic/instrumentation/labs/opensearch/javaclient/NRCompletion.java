package com.newrelic.instrumentation.labs.opensearch.javaclient;

import java.util.Map;
import java.util.function.BiConsumer;

import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;

public class NRCompletion<T> implements BiConsumer<T, Throwable> {
	
	private Segment segment = null;
	private DatastoreParameters params = null;
	private Map<String,Object> attributes = null;
	
	public NRCompletion(String segmentName, DatastoreParameters p, Map<String, Object> attrs) {
		segment = NewRelic.getAgent().getTransaction().startSegment(segmentName);
		params = p;
		attributes = attrs;
	}

	@Override
	public void accept(T t, Throwable u) {
		if(t != null) {
			if(segment != null) {
				if(params != null) {
					segment.reportAsExternal(params);
				}
				if(attributes != null && !attributes.isEmpty()) {
					segment.addCustomAttributes(attributes);
				}
				segment.end();
				segment = null;
			}
		} else if(u != null) {
			NewRelic.noticeError(u);
			if(segment != null) {
				segment.ignore();
				segment = null;
			}
		}
	}

	public Segment getSegment() {
		return segment;
	}

	public DatastoreParameters getParams() {
		return params;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	
}
