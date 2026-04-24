package com.newrelic.instrumentation.labs.opensearch.javaclient;

import java.util.Map;

import com.newrelic.api.agent.QueryConverter;

public class OpenSearchQueryConverter  implements QueryConverter<QueryHolder > {

	@Override
	public String toRawQueryString(QueryHolder rawQuery) {
		String collString = rawQuery.getCollection();
		String operation = rawQuery.getOperation();
		Map<String, String> params = rawQuery.getParams();
		
		StringBuffer sb = new StringBuffer();
		sb.append(operation);
		sb.append(' ');
		sb.append(collString);
		sb.append(" Parameters: ");
		for(String key : params.keySet()) {
			sb.append(key + "=" + params.get(key) + ",");
		}
		String queryStr = sb.toString();
		return queryStr;
	}

	@Override
	public String toObfuscatedQueryString(QueryHolder rawQuery) {
		String collString = rawQuery.getCollection();
		String operation = rawQuery.getOperation();
		Map<String, String> params = rawQuery.getParams();
		
		StringBuffer sb = new StringBuffer();
		sb.append(operation);
		sb.append(' ');
		sb.append(collString);
		sb.append(" Parameters: ");
		for(String key : params.keySet()) {
			sb.append(key + "=?,");
		}
		String queryStr = sb.toString();
		return queryStr;
	}


}
