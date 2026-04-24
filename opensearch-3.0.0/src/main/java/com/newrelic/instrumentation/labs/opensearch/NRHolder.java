package com.newrelic.instrumentation.labs.opensearch;

import com.newrelic.api.agent.ExternalParameters;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Token;

public class NRHolder {
	
	private Token token = null;
	private Segment segment = null;
	private ExternalParameters params = null;

	public NRHolder(Token t, Segment s, ExternalParameters p) {
		token = t;
		segment = s;
		params = p;
	}
	 
	public void linkAndExpire() {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
	}
	
	public void link() {
		if(token != null) {
			token.link();
		}
	}
	
	public void expire() {
		if(token != null) {
			token.expire();
			token = null;
		}
	}
	
	public void endSegment() {
		if(segment != null) {
			if(params != null) {
				segment.reportAsExternal(params);
			}
			segment.end();
			segment = null;
		}
	}
	
	public void ignoreSegment() {
		if(segment != null) {
			segment.ignore();
			segment = null;
		}
	}
}
