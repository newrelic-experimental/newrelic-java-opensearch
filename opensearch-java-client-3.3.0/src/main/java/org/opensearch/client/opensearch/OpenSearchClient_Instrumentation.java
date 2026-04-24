package org.opensearch.client.opensearch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opensearch.client.opensearch._types.ErrorResponse;
import org.opensearch.client.opensearch._types.InlineScript;
import org.opensearch.client.opensearch._types.query_dsl.Query;
import org.opensearch.client.opensearch.core.BulkRequest;
import org.opensearch.client.opensearch.core.BulkResponse;
import org.opensearch.client.opensearch.core.CreateRequest;
import org.opensearch.client.opensearch.core.CreateResponse;
import org.opensearch.client.opensearch.core.GetRequest;
import org.opensearch.client.opensearch.core.GetResponse;
import org.opensearch.client.opensearch.core.GetSourceRequest;
import org.opensearch.client.opensearch.core.GetSourceResponse;
import org.opensearch.client.opensearch.core.IndexRequest;
import org.opensearch.client.opensearch.core.IndexResponse;
import org.opensearch.client.opensearch.core.MgetRequest;
import org.opensearch.client.opensearch.core.MgetResponse;
import org.opensearch.client.opensearch.core.MsearchRequest;
import org.opensearch.client.opensearch.core.MsearchResponse;
import org.opensearch.client.opensearch.core.ScriptsPainlessExecuteRequest;
import org.opensearch.client.opensearch.core.ScriptsPainlessExecuteResponse;
import org.opensearch.client.opensearch.core.SearchRequest;
import org.opensearch.client.opensearch.core.SearchResponse;
import org.opensearch.client.opensearch.core.SearchTemplateRequest;
import org.opensearch.client.opensearch.core.SearchTemplateResponse;
import org.opensearch.client.opensearch.core.UpdateRequest;
import org.opensearch.client.opensearch.core.UpdateResponse;
import org.opensearch.client.transport.Endpoint;
import org.opensearch.client.transport.OpenSearchTransport;
import org.opensearch.client.transport.TransportOptions;
import org.opensearch.client.transport.endpoints.SimpleEndpoint;

import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.opensearch.javaclient.Utils;


@Weave(originalName = "org.opensearch.client.opensearch.OpenSearchClient")
public abstract class OpenSearchClient_Instrumentation extends OpenSearchClientBase_Instrumentation<OpenSearchClient_Instrumentation> {

	public OpenSearchClient_Instrumentation(OpenSearchTransport transport) {
		super(transport, null);
	}

	public OpenSearchClient_Instrumentation(OpenSearchTransport transport, TransportOptions transportOptions) {
		super(transport, transportOptions);
	}
	
	@Trace(leaf = true)
	public BulkResponse bulk(BulkRequest request) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String index = Utils.getObjectString(request.index());
		if(index != null && !index.isEmpty()) {
			collectionAttr.put("index", index);
		}
		Endpoint<BulkRequest, BulkResponse, ErrorResponse> endPoint = BulkRequest._ENDPOINT;
		
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		traced.reportAsExternal(params);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;
		
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		traced.addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace(leaf = true)
	public BulkResponse bulk() {
		BulkRequest request = new BulkRequest.Builder().build();
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String index = Utils.getObjectString(request.index());
		if(index != null && !index.isEmpty()) {
			collectionAttr.put("index", index);
		}
		Endpoint<BulkRequest, BulkResponse, ErrorResponse> endPoint = BulkRequest._ENDPOINT;
		
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		traced.reportAsExternal(params);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;
		
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		traced.addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	public <TDocument> CreateResponse create(CreateRequest<TDocument> request) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String index = Utils.getObjectString(request.index());
		if(index != null) {
			collectionAttr.put("index", index);
		}
		String id = Utils.getObjectString(request.id());
		if(id != null) {
			collectionAttr.put("id", id);
		}
		TDocument doc = request.document();
		if(doc != null) {
			collectionAttr.put("documentType", doc.getClass().getSimpleName());
		}
		Endpoint<CreateRequest<?>, CreateResponse, ErrorResponse> endPoint = CreateRequest._ENDPOINT;
		
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		traced.reportAsExternal(params);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		traced.addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace(leaf = true)
	public <TDocument> GetResponse<TDocument> get(GetRequest request, Class<TDocument> tDocumentClass) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String index = request.index();
		if(index != null) {
			collectionAttr.put("index", index);
		}
		String id = request.id();
		if(id != null) {
			collectionAttr.put("id", id);
		}
		if(tDocumentClass != null) {
			collectionAttr.put("document-type",tDocumentClass.getSimpleName());
		}
		SimpleEndpoint<GetRequest, ?> endPoint = GetRequest._ENDPOINT;
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		traced.reportAsExternal(params);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		traced.addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace(leaf = true)
	public <TDocument> GetSourceResponse<TDocument> getSource(GetSourceRequest request, Class<TDocument> tDocumentClass) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String index = request.index();
		if(index != null) {
			collectionAttr.put("index", index);
		}
		String id = request.id();
		if(id != null) {
			collectionAttr.put("id", id);
		}
		if(tDocumentClass != null) {
			collectionAttr.put("document-type", tDocumentClass.getSimpleName());
		}
		SimpleEndpoint<GetSourceRequest, ?> endPoint = GetSourceRequest._ENDPOINT;
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		traced.reportAsExternal(params);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		traced.addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace(leaf = true)
	public <TDocument> IndexResponse index(IndexRequest<TDocument> request) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String index = request.index();
		if(index != null) {
			collectionAttr.put("index", index);
		}
		String id = request.id();
		if(id != null) {
			collectionAttr.put("id", id);
		}
		TDocument doc = request.document();
		if(doc != null) {
			collectionAttr.put("document-type", doc.getClass().getSimpleName());
		}
		Endpoint<IndexRequest<?>, IndexResponse, ErrorResponse> endPoint = IndexRequest._ENDPOINT;
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		traced.reportAsExternal(params);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		traced.addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace(leaf = true)
	public <TDocument> MgetResponse<TDocument> mget(MgetRequest request, Class<TDocument> tDocumentClass) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String index = request.index();
		if(index != null) {
			collectionAttr.put("index", index);
		}
		if(tDocumentClass != null) {
			collectionAttr.put("document-type", tDocumentClass.getSimpleName());
		}
		SimpleEndpoint<MgetRequest, ?> endPoint = MgetRequest._ENDPOINT;
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		traced.reportAsExternal(params);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		traced.addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace(leaf = true)
	public <TDocument> MsearchResponse<TDocument> msearch(MsearchRequest request, Class<TDocument> tDocumentClass) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		Map<String, String> collectionAttr = new HashMap<String, String>();
		List<String> indices = request.index();
		if(indices != null && !indices.isEmpty()) {
			collectionAttr.put("indices", Utils.getCollectionFromList(indices));
		}
		if(tDocumentClass != null) {
			collectionAttr.put("document-type", tDocumentClass.getSimpleName());
		}
		SimpleEndpoint<MsearchRequest, ?> endPoint = MsearchRequest._ENDPOINT;
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		traced.reportAsExternal(params);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		traced.addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace(leaf = true)
	public <TDocument> SearchResponse<TDocument> search(SearchRequest request, Class<TDocument> tDocumentClass) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		Map<String, String> collectionAttr = new HashMap<String, String>();
		List<String> indices = request.index();
		if(indices != null && !indices.isEmpty()) {
			collectionAttr.put("indices", Utils.getCollectionFromList(indices));
		}
		Query query = request.query();
		if(query != null) {
			collectionAttr.put("query", Utils.getCollectionFromQuery(query,null));
		}
		if(tDocumentClass != null) {
			collectionAttr.put("document-type", tDocumentClass.getSimpleName());
		}
		SimpleEndpoint<SearchRequest, ?> endPoint = SearchRequest._ENDPOINT;
		DatastoreParameters params = Utils.getParams(request,collectionAttr, endPoint.queryParameters(request), query);
		traced.reportAsExternal(params);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		traced.addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace(leaf = true)
	public <TDocument> SearchTemplateResponse<TDocument> searchTemplate(SearchTemplateRequest request, Class<TDocument> tDocumentClass) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		Map<String, String> collectionAttr = new HashMap<String, String>();
		List<String> indices = request.index();
		if(indices != null && !indices.isEmpty()) {
			collectionAttr.put("indices", Utils.getCollectionFromList(indices));
		}		
		String id = request.id();
		if(id != null) {
			collectionAttr.put("id", id);
		}
		SimpleEndpoint<SearchTemplateRequest, ?> endPoint = SearchTemplateRequest._ENDPOINT;
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		traced.reportAsExternal(params);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		traced.addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace(leaf = true)
	public <TDocument, TPartialDocument> UpdateResponse<TDocument> update(UpdateRequest<TDocument, TPartialDocument> request, Class<TDocument> tDocumentClass) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String index = request.index();
		if(index != null) {
			collectionAttr.put("index", index);
		}
		String id = request.id();
		if(id != null) {
			collectionAttr.put("id", id);
		}
		TPartialDocument partialDoc = request.doc();
		if(partialDoc != null) {
			collectionAttr.put("partial-doc-type", partialDoc.getClass().getSimpleName());
		}
		if(tDocumentClass != null) {
			collectionAttr.put("document-type", tDocumentClass.getSimpleName());
		}
		SimpleEndpoint<UpdateRequest<?, ?>, ?> endPoint = UpdateRequest._ENDPOINT;
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request) , null);
		traced.reportAsExternal(params);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		traced.addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

}
