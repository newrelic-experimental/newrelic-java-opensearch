package org.opensearch.client;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
public abstract class RestClient {

	@Trace(leaf = true, excludeFromTransactionTrace = true)
	public Response performRequest(Request request) {
		return Weaver.callOriginal();
	}
	
	@Trace(leaf = true, excludeFromTransactionTrace = true)
	public Cancellable performRequestAsync(Request request, ResponseListener responseListener) {
		return Weaver.callOriginal();
	}
}
