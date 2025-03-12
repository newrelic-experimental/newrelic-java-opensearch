package org.opensearch.client.transport.rest_client;

import java.util.concurrent.CompletableFuture;

import org.opensearch.client.transport.Endpoint;
import org.opensearch.client.transport.TransportOptions;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
public abstract class RestClientTransport {

	@Trace(leaf = true)
	public <RequestT, ResponseT, ErrorT> ResponseT performRequest(RequestT request, Endpoint<RequestT, ResponseT, ErrorT> endpoint, TransportOptions options) {
		return Weaver.callOriginal();
	}

	
	@Trace(leaf = true)
	public <RequestT, ResponseT, ErrorT> CompletableFuture<ResponseT> performRequestAsync(RequestT request, Endpoint<RequestT, ResponseT, ErrorT> endpoint,TransportOptions options) {
		return Weaver.callOriginal();
	}


}
