package org.opensearch.client.transport.httpclient5;

import java.util.Iterator;
import java.util.concurrent.CompletableFuture;

import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.opensearch.client.Response;
import org.opensearch.client.transport.Endpoint;
import org.opensearch.client.transport.TransportOptions;
import org.opensearch.client.transport.httpclient5.internal.Node;

import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.opensearch.javaclient.AttributeUtils;
import com.newrelic.instrumentation.labs.opensearch.javaclient.NRCompletion;
import com.newrelic.instrumentation.labs.opensearch.javaclient.Utils;

@Weave
public abstract class ApacheHttpClient5Transport {

	@Trace(leaf = true)
	public <RequestT, ResponseT, ErrorT> ResponseT performRequest(RequestT request, Endpoint<RequestT, ResponseT, ErrorT> endpoint, TransportOptions options) {
		AttributeUtils.reportRequestEndpointInfo(request, endpoint);
		DatastoreParameters params = Utils.getParamsFromRequest(request);
		if(params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
	}

	
	public <RequestT, ResponseT, ErrorT> CompletableFuture<ResponseT> performRequestAsync(RequestT request, Endpoint<RequestT, ResponseT, ErrorT> endpoint,TransportOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(request);
		Segment segment = NewRelic.getAgent().getTransaction().startSegment("OpenSearch", request.getClass().getSimpleName());
		NRCompletion<ResponseT> wrapper = new NRCompletion<ResponseT>(segment, params);
		CompletableFuture<ResponseT> future = Weaver.callOriginal();
		
		return future.whenComplete(wrapper);
	}

	@Trace(leaf = true, excludeFromTransactionTrace = true)
	private void performRequestAsync(NodeTuple<Iterator<Node>> nodeTuple, ApacheHttpClient5Options options, HttpUriRequestBase request, WarningsHandler warningsHandler, CompletableFuture<Response> listener) {
		Weaver.callOriginal();
	}
	
	@Weave
	static class NodeTuple<T> {
		
	}
}
