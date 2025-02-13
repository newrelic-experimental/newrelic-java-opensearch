package org.opensearch.common.action;

import java.util.concurrent.TimeUnit;

import org.opensearch.common.unit.TimeValue;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.Interface)
public abstract class ActionFuture<T> {

	@NewField
	public Token token = null;
	
	@Trace(async = true)
	public T actionGet() {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","OpenSearch","ActionFuture",getClass().getSimpleName(),"actionGet");
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		return Weaver.callOriginal();
	}
	
	@Trace(async = true)
	public T actionGet(String timeout) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","OpenSearch","ActionFuture",getClass().getSimpleName(),"actionGet");
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		return Weaver.callOriginal();
	}
	
	@Trace(async = true)
	public T actionGet(long timeoutMillis) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","OpenSearch","ActionFuture",getClass().getSimpleName(),"actionGet");
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		return Weaver.callOriginal();
	}
	
	@Trace(async = true)
	public T actionGet(long timeout, TimeUnit unit) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","OpenSearch","ActionFuture",getClass().getSimpleName(),"actionGet");
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		return Weaver.callOriginal();
	}
	
	@Trace(async = true)
	public T actionGet(TimeValue timeout) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","OpenSearch","ActionFuture",getClass().getSimpleName(),"actionGet");
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		return Weaver.callOriginal();
	}
}
