package com.newrelic.instrumentation.labs.opensearch.javaclient;

import java.util.Map;

import org.opensearch.client.transport.Endpoint;

import com.newrelic.api.agent.QueryConverter;

public class OpenSearchQueryConverter<RequestT, ResponseT, ErrorT>  implements QueryConverter<QueryHolder<RequestT, ResponseT, ErrorT> > {

	@Override
	public String toRawQueryString(QueryHolder<RequestT, ResponseT, ErrorT>  rawQuery) {
		Endpoint<RequestT, ResponseT, ErrorT>  endpoint = rawQuery.getEndpoint();
		RequestT reqObj = rawQuery.getRequestObj();
		
		Map<String, String> params = endpoint.queryParameters(reqObj);
		
		return null;
	}

	@Override
	public String toObfuscatedQueryString(QueryHolder<RequestT, ResponseT, ErrorT> rawQuery) {
		// TODO Auto-generated method stub
		return null;
	}

}
