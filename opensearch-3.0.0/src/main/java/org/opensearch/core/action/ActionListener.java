package org.opensearch.core.action;

import com.newrelic.api.agent.ExternalParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.Interface)
public abstract class ActionListener<Response> {

	@NewField
	public Segment segment = null;
	
	@NewField
	public ExternalParameters params = null;
	
	@Trace
	public void onResponse(Response response) {
		if(segment != null) {
			if(params != null) {
				segment.reportAsExternal(params);
			}
			segment.end();
			segment = null;
		}
		Weaver.callOriginal();
	}
	
	@Trace
	public void onFailure(Exception e) {
		NewRelic.noticeError(e);
		if(segment != null) {
			segment.ignore();
			segment = null;
		}
		Weaver.callOriginal();
	}
}
