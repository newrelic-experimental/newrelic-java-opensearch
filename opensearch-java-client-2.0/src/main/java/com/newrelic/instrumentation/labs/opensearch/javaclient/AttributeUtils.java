package com.newrelic.instrumentation.labs.opensearch.javaclient;

import java.util.Map;

import org.opensearch.client.opensearch.core.CountRequest;
import org.opensearch.client.opensearch.core.IndexRequest;
import org.opensearch.client.opensearch.core.SearchRequest;
import org.opensearch.client.transport.Endpoint;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.TracedMethod;

public class AttributeUtils {

	public static <RequestT, ResponseT, ErrorT> void reportRequestEndpointInfo(RequestT requestObj, Endpoint<RequestT, ResponseT, ErrorT> endpoint) {
		if(requestObj != null && endpoint != null) {
			TracedMethod traced = NewRelic.getAgent().getTracedMethod();
			String method = endpoint.method(requestObj);
			traced.addCustomAttribute("Request-Method", method);
			String urlString = endpoint.requestUrl(requestObj);
			traced.addCustomAttribute("Request-URL", urlString);
		}
	}
	
	public static void reportAttribute(Map<String, Object> attributes, String key, Object value) {
		if(value != null && attributes != null && key != null && !key.isEmpty()) {
			attributes.put(key, value);
		}
	}
	
	public static void reportCountRequest(Map<String, Object> attributes, CountRequest req) {
		if(req != null) {
			reportAttribute(attributes, "CountRequest-q", req.q());
			reportAttribute(attributes, "CountRequest-query", req.query() != null ? req.query().toString() : "null");
			reportAttribute(attributes, "CountRequest-routing", req.routing());
		}
	}
	
	public static void reportSearchRequest(Map<String, Object> attributes, SearchRequest req) {
		if(req != null) {
			reportAttribute(attributes, "SearchRequest-q", req.q());
			reportAttribute(attributes, "SearchRequest-routing", req.routing());
			reportAttribute(attributes, "SearchRequest-query", req.query() != null ? req.query().toString() : "null");
			reportAttribute(attributes, "SearchRequest-searchType", req.searchType() != null ? req.searchType().name() : "null");
			
		}
	}
	
	public static void reportIndexRequest(Map<String, Object> attributes, IndexRequest<?> req) {
		if(req != null) {
			reportAttribute(attributes, "IndexRequest-id", req.id());
			reportAttribute(attributes, "IndexRequest-index", req.index());
			reportAttribute(attributes, "IndexRequest-routing", req.routing());
			reportAttribute(attributes, "IndexRequest-pipeline", req.pipeline());
			reportAttribute(attributes, "IndexRequest-opType", req.opType() != null ? req.opType().name() : "null");
			reportAttribute(attributes, "IndexRequest-document", req.document().toString());
		}
	}
}
