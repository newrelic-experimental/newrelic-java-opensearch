package org.opensearch.client.support;

import java.util.HashMap;

import org.opensearch.action.ActionFuture;
import org.opensearch.action.ActionListener;
import org.opensearch.action.ActionRequest;
import org.opensearch.action.ActionResponse;
import org.opensearch.action.ActionType;
import org.opensearch.common.transport.TransportAddress;

import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.opensearch.OpenSearchQueryConverter;
import com.newrelic.instrumentation.labs.opensearch.OpenSearchUtils;
import com.newrelic.instrumentation.labs.opensearch.QueryDetails;
import com.newrelic.instrumentation.labs.opensearch.Utils;

@Weave(type = MatchType.BaseClass)
public abstract class AbstractClient {

	@Trace
	protected <Request extends ActionRequest, Response extends ActionResponse> void doExecute(ActionType<Response> action, Request request, ActionListener<Response> listener) {
		String host = null;
		Integer port = null;
		if(request != null) {
			TransportAddress remote = request.remoteAddress();
			if(remote != null) {
				host = remote.getAddress();
				port = remote.getPort();
			}
		}
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.addAttribute(attributes, "Host", host);
		Utils.addAttribute(attributes, "Port", port);
		Utils.addAttribute(attributes, "Action", action.name());
		Utils.addActionRequest(attributes, request);
		QueryDetails details = OpenSearchUtils.getQueryDetails(request);
		Utils.addAttribute(attributes, "Query-Collection", details.getCollection());
		Utils.addAttribute(attributes, "Query-Operation", details.getOperation());
		
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		DatastoreParameters params = null;
		
		if(host != null) {
			params = DatastoreParameters.product("ElasticSearch").collection(details.getCollection()).operation(details.getOperation()).instance(host, port).noDatabaseName().slowQuery(details.getRawQuery(), new OpenSearchQueryConverter()).build();
		} else {
			params = DatastoreParameters.product("ElasticSearch").collection(details.getCollection()).operation(details.getOperation()).noInstance().noDatabaseName().slowQuery(details.getRawQuery(), new OpenSearchQueryConverter()).build();

		}
		if(listener != null) {
			if(listener.params == null) {
				listener.params = params;
			}
			if(listener.segment == null) {
				listener.segment = NewRelic.getAgent().getTransaction().startSegment("OpenSearchQuery");
			}
		}
		Weaver.callOriginal();
	}
	
	public <Request extends ActionRequest, Response extends ActionResponse> ActionFuture<Response> execute(ActionType<Response> action,Request request) {
		ActionFuture<Response> response = Weaver.callOriginal();
		if(response.token == null) {
			Token t = NewRelic.getAgent().getTransaction().getToken();
			if(t != null && t.isActive()) {
				response.token = t;
			} else if(t != null) {
				t.expire();
				t = null;
			}
		}
		return response;
	}
}
