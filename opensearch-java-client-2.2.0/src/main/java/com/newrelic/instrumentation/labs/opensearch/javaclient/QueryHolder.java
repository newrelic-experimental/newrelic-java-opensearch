package com.newrelic.instrumentation.labs.opensearch.javaclient;

import java.util.Map;

public class QueryHolder {

	private String operation = null;
	private String collection = null;
	private Map<String, String> params = null;
	
	public QueryHolder(String op, String coll, Map<String,String> p) {
		operation = op;
		collection = coll;
		params = p;
	}

	public String getOperation() {
		return operation;
	}

	public String getCollection() {
		return collection;
	}

	@Override
	public String toString() {
		return "Operation: " + operation + ", Collection: " + collection +", Parameters: " + params.toString();
	}

	public Map<String, String> getParams() {
		return params;
	}
	
	
}
