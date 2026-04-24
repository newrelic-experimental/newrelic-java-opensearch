package com.newrelic.instrumentation.labs.opensearch;

public class QueryDetails {
	
	private String rawQuery = null;
	private String collection = null;
	private String operation = null;
	
	public QueryDetails(String rQuery, String col, String op) {
		rawQuery = rQuery;
		collection = col;
		operation = op;
	}

	public String getRawQuery() {
		return rawQuery;
	}

	public String getCollection() {
		return collection;
	}

	public String getOperation() {
		return operation;
	}

	
}
