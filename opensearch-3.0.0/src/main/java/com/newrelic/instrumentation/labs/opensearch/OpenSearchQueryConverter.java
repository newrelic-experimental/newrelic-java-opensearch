package com.newrelic.instrumentation.labs.opensearch;

import com.newrelic.api.agent.QueryConverter;

public class OpenSearchQueryConverter implements QueryConverter<String> {

	@Override
	public String toRawQueryString(String rawQuery) {
		return rawQuery;
	}

	@Override
	public String toObfuscatedQueryString(String rawQuery) {
		int index = rawQuery.lastIndexOf('/');
		if(index < 1) {
			return rawQuery;
		}
		
		return rawQuery.substring(0, index) + "/?";
	}

}
