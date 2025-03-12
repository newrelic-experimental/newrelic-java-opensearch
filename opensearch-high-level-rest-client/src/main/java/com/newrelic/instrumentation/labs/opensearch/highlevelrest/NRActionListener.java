package com.newrelic.instrumentation.labs.opensearch.highlevelrest;

import org.opensearch.action.ActionListener;

import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;

public class NRActionListener<Response> implements ActionListener<Response> {
	
	private ActionListener<Response> delegate = null;
	private Segment segment = null;
	private DatastoreParameters params = null;
	
	public NRActionListener(ActionListener<Response> d, DatastoreParameters p) {
		delegate = d;
		params = p;
		segment = NewRelic.getAgent().getTransaction().startSegment("OpenSearch-Call");
	}

	@Override
	public void onResponse(Response response) {
		if(segment != null) {
			if(params != null) {
				segment.reportAsExternal(params);
			}
			segment.end();
			segment = null;
		}
		if(delegate != null) {
			delegate.onResponse(response);
		}
	}

	@Override
	public void onFailure(Exception e) {
		NewRelic.noticeError(e);
		if(segment != null) {
			segment.ignore();
			segment = null;
		}
		if(delegate != null) {
			delegate.onFailure(e);
		}
	}

}
