package com.newrelic.instrumentation.labs.opensearch.javaclient;

import org.opensearch.client.transport.Endpoint;

public class QueryHolder<RequestT, ResponseT, ErrorT>  {

	private Endpoint<RequestT, ResponseT, ErrorT>  endpoint;
	
	private RequestT requestObj;
	
	public QueryHolder(Endpoint<RequestT, ResponseT, ErrorT>  ep, RequestT req) {
		endpoint = ep;
		requestObj = req;
	}

	public Endpoint<RequestT, ResponseT, ErrorT>  getEndpoint() {
		return endpoint;
	}

	public RequestT getRequestObj() {
		return requestObj;
	}
	
	
}
