package org.opensearch.client.opensearch;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.opensearch.client.ApiClient;
import org.opensearch.client.opensearch._types.ErrorResponse;
import org.opensearch.client.opensearch._types.InlineScript;
import org.opensearch.client.opensearch._types.query_dsl.Query;
import org.opensearch.client.opensearch.core.BulkRequest;
import org.opensearch.client.opensearch.core.BulkResponse;
import org.opensearch.client.opensearch.core.ClearScrollRequest;
import org.opensearch.client.opensearch.core.ClearScrollResponse;
import org.opensearch.client.opensearch.core.CountRequest;
import org.opensearch.client.opensearch.core.CountResponse;
import org.opensearch.client.opensearch.core.CreateRequest;
import org.opensearch.client.opensearch.core.CreateResponse;
import org.opensearch.client.opensearch.core.DeleteByQueryRequest;
import org.opensearch.client.opensearch.core.DeleteByQueryResponse;
import org.opensearch.client.opensearch.core.DeleteByQueryRethrottleRequest;
import org.opensearch.client.opensearch.core.DeleteByQueryRethrottleResponse;
import org.opensearch.client.opensearch.core.DeleteRequest;
import org.opensearch.client.opensearch.core.DeleteResponse;
import org.opensearch.client.opensearch.core.DeleteScriptRequest;
import org.opensearch.client.opensearch.core.DeleteScriptResponse;
import org.opensearch.client.opensearch.core.ExistsRequest;
import org.opensearch.client.opensearch.core.ExistsSourceRequest;
import org.opensearch.client.opensearch.core.ExplainRequest;
import org.opensearch.client.opensearch.core.ExplainResponse;
import org.opensearch.client.opensearch.core.FieldCapsRequest;
import org.opensearch.client.opensearch.core.FieldCapsResponse;
import org.opensearch.client.opensearch.core.GetRequest;
import org.opensearch.client.opensearch.core.GetResponse;
import org.opensearch.client.opensearch.core.GetScriptContextResponse;
import org.opensearch.client.opensearch.core.GetScriptLanguagesResponse;
import org.opensearch.client.opensearch.core.GetScriptRequest;
import org.opensearch.client.opensearch.core.GetScriptResponse;
import org.opensearch.client.opensearch.core.GetSourceRequest;
import org.opensearch.client.opensearch.core.GetSourceResponse;
import org.opensearch.client.opensearch.core.IndexRequest;
import org.opensearch.client.opensearch.core.IndexResponse;
import org.opensearch.client.opensearch.core.InfoResponse;
import org.opensearch.client.opensearch.core.MgetRequest;
import org.opensearch.client.opensearch.core.MgetResponse;
import org.opensearch.client.opensearch.core.MsearchRequest;
import org.opensearch.client.opensearch.core.MsearchResponse;
import org.opensearch.client.opensearch.core.MsearchTemplateRequest;
import org.opensearch.client.opensearch.core.MsearchTemplateResponse;
import org.opensearch.client.opensearch.core.MtermvectorsRequest;
import org.opensearch.client.opensearch.core.MtermvectorsResponse;
import org.opensearch.client.opensearch.core.PutScriptRequest;
import org.opensearch.client.opensearch.core.PutScriptResponse;
import org.opensearch.client.opensearch.core.RankEvalRequest;
import org.opensearch.client.opensearch.core.RankEvalResponse;
import org.opensearch.client.opensearch.core.ReindexRequest;
import org.opensearch.client.opensearch.core.ReindexResponse;
import org.opensearch.client.opensearch.core.ReindexRethrottleRequest;
import org.opensearch.client.opensearch.core.ReindexRethrottleResponse;
import org.opensearch.client.opensearch.core.ScriptsPainlessExecuteRequest;
import org.opensearch.client.opensearch.core.ScriptsPainlessExecuteResponse;
import org.opensearch.client.opensearch.core.SearchRequest;
import org.opensearch.client.opensearch.core.SearchResponse;
import org.opensearch.client.opensearch.core.SearchShardsRequest;
import org.opensearch.client.opensearch.core.SearchShardsResponse;
import org.opensearch.client.opensearch.core.SearchTemplateRequest;
import org.opensearch.client.opensearch.core.SearchTemplateResponse;
import org.opensearch.client.opensearch.core.TermvectorsRequest;
import org.opensearch.client.opensearch.core.TermvectorsResponse;
import org.opensearch.client.opensearch.core.UpdateByQueryRequest;
import org.opensearch.client.opensearch.core.UpdateByQueryResponse;
import org.opensearch.client.opensearch.core.UpdateByQueryRethrottleRequest;
import org.opensearch.client.opensearch.core.UpdateByQueryRethrottleResponse;
import org.opensearch.client.opensearch.core.UpdateRequest;
import org.opensearch.client.opensearch.core.UpdateResponse;
import org.opensearch.client.transport.Endpoint;
import org.opensearch.client.transport.OpenSearchTransport;
import org.opensearch.client.transport.TransportOptions;
import org.opensearch.client.transport.endpoints.BooleanResponse;
import org.opensearch.client.transport.endpoints.SimpleEndpoint;

import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.opensearch.javaclient.NRCompletion;
import com.newrelic.instrumentation.labs.opensearch.javaclient.Utils;

@Weave(originalName = "org.opensearch.client.opensearch.OpenSearchAsyncClient")
public abstract class OpenSearchAsyncClient_Instrumentation extends OpenSearchAsyncClientBase_Instrumentation<OpenSearchAsyncClient_Instrumentation> {

	public OpenSearchAsyncClient_Instrumentation(OpenSearchTransport transport) {
		super(transport, null);
	}

	public OpenSearchAsyncClient_Instrumentation(OpenSearchTransport transport, TransportOptions transportOptions) {
		super(transport, transportOptions);
	}


	public CompletableFuture<BulkResponse> bulk(BulkRequest request) {
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String index = Utils.getObjectString(request.index());
		if(index != null && !index.isEmpty()) {
			collectionAttr.put("index", index);
		}
		Endpoint<BulkRequest, BulkResponse, ErrorResponse> endPoint = BulkRequest._ENDPOINT;
		
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		CompletableFuture<BulkResponse> future = Weaver.callOriginal();
		NRCompletion<BulkResponse> completionListener = new NRCompletion<BulkResponse>(Utils.getOperationFromRequest(request), params, attributes);
		return future.whenComplete(completionListener);
	}

	public <TDocument> CompletableFuture<CreateResponse> create(CreateRequest<TDocument> request) {
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
			collectionAttr.put("documentType", doc.getClass().getSimpleName());
		}
		
		Endpoint<CreateRequest<?>, CreateResponse, ErrorResponse> endPoint = CreateRequest._ENDPOINT;
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		CompletableFuture<CreateResponse> future = Weaver.callOriginal();
		NRCompletion<CreateResponse> completionListener = new NRCompletion<CreateResponse>(Utils.getOperationFromRequest(request), params, attributes);
		return future.whenComplete(completionListener);	
	}

	public <TDocument> CompletableFuture<ExplainResponse<TDocument>> explain(ExplainRequest request, Class<TDocument> tDocumentClass) {
		DatastoreParameters params = null;
		CompletableFuture<ExplainResponse<TDocument>> future = Weaver.callOriginal();
		NRCompletion<ExplainResponse<TDocument>> completionListener = new NRCompletion<ExplainResponse<TDocument>>(Utils.getOperationFromRequest(request), params, null);
		return future.whenComplete(completionListener);	
	}

	public <TDocument> CompletableFuture<GetResponse<TDocument>> get(GetRequest request, Class<TDocument> tDocumentClass) {
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
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		CompletableFuture<GetResponse<TDocument>> future = Weaver.callOriginal();
		NRCompletion<GetResponse<TDocument>> completionListener = new NRCompletion<GetResponse<TDocument>>(Utils.getOperationFromRequest(request), params, attributes);
		return future.whenComplete(completionListener);	
	}

	public <TDocument> CompletableFuture<GetSourceResponse<TDocument>> getSource(GetSourceRequest request, Class<TDocument> tDocumentClass) {
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
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		CompletableFuture<GetSourceResponse<TDocument>> future = Weaver.callOriginal();
		NRCompletion<GetSourceResponse<TDocument>> completionListener = new NRCompletion<GetSourceResponse<TDocument>>(Utils.getOperationFromRequest(request), params, attributes);
		return future.whenComplete(completionListener);	
	}

	public <TDocument> CompletableFuture<IndexResponse> index(IndexRequest<TDocument> request) {
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
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		CompletableFuture<IndexResponse> future = Weaver.callOriginal();
		NRCompletion<IndexResponse> completionListener = new NRCompletion<IndexResponse>(Utils.getOperationFromRequest(request), params, attributes);
		return future.whenComplete(completionListener);	
	}

	public <TDocument> CompletableFuture<MgetResponse<TDocument>> mget(MgetRequest request, Class<TDocument> tDocumentClass) {
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
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		CompletableFuture<MgetResponse<TDocument>> future = Weaver.callOriginal();
		NRCompletion<MgetResponse<TDocument>> completionListener = new NRCompletion<MgetResponse<TDocument>>(Utils.getOperationFromRequest(request), params, attributes);
		return future.whenComplete(completionListener);	
	}

	public <TDocument> CompletableFuture<MsearchResponse<TDocument>> msearch(MsearchRequest request, Class<TDocument> tDocumentClass) {
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String index =  Utils.getObjectString(request.index());
		if(index != null && !index.isEmpty()) {
			collectionAttr.put("index", index);
		}
		if(tDocumentClass != null) {
			collectionAttr.put("document-type", tDocumentClass.getSimpleName());
		}
		SimpleEndpoint<MsearchRequest, ?> endPoint = MsearchRequest._ENDPOINT;
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		CompletableFuture<MsearchResponse<TDocument>> future = Weaver.callOriginal();
		NRCompletion<MsearchResponse<TDocument>> completionListener = new NRCompletion<MsearchResponse<TDocument>>(Utils.getOperationFromRequest(request), params, attributes);
		return future.whenComplete(completionListener);	
	}

	public <TDocument> CompletableFuture<MsearchTemplateResponse<TDocument>> msearchTemplate(MsearchTemplateRequest request, Class<TDocument> tDocumentClass) {
		DatastoreParameters params = null;
		CompletableFuture<MsearchTemplateResponse<TDocument>> future = Weaver.callOriginal();
		NRCompletion<MsearchTemplateResponse<TDocument>> completionListener = new NRCompletion<MsearchTemplateResponse<TDocument>>(Utils.getOperationFromRequest(request), params, null);
		return future.whenComplete(completionListener);	
	}

	public <TDocument> CompletableFuture<SearchResponse<TDocument>> search(SearchRequest request, Class<TDocument> tDocumentClass) {
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String index = Utils.getObjectString(request.index());
		if(index != null && !index.isEmpty()) {
			collectionAttr.put("index",index);
		}
		Query query = request.query();
		if(query != null) {
			collectionAttr.put("query", Utils.getObjectString(query));
		}
		if(tDocumentClass != null) {
			collectionAttr.put("document-type", tDocumentClass.getSimpleName());
		}
		SimpleEndpoint<SearchRequest, ?> endPoint = SearchRequest._ENDPOINT;
		DatastoreParameters params = Utils.getParams(request,collectionAttr, endPoint.queryParameters(request), query);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		CompletableFuture<SearchResponse<TDocument>> future = Weaver.callOriginal();
		NRCompletion<SearchResponse<TDocument>> completionListener = new NRCompletion<SearchResponse<TDocument>>(Utils.getOperationFromRequest(request), params, attributes);
		return future.whenComplete(completionListener);	
	}

	public <TDocument> CompletableFuture<SearchTemplateResponse<TDocument>> searchTemplate(SearchTemplateRequest request, Class<TDocument> tDocumentClass) {
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String index = Utils.getObjectString(request.index());
		if(index != null && !index.isEmpty()) {
			collectionAttr.put("index", index);
		}		
		String id = request.id();
		if(id != null) {
			collectionAttr.put("id", id);
		}
		SimpleEndpoint<SearchTemplateRequest, ?> endPoint = SearchTemplateRequest._ENDPOINT;
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		CompletableFuture<SearchTemplateResponse<TDocument>> future = Weaver.callOriginal();
		NRCompletion<SearchTemplateResponse<TDocument>> completionListener = new NRCompletion<SearchTemplateResponse<TDocument>>(Utils.getOperationFromRequest(request), params, attributes);
		return future.whenComplete(completionListener);	
	}

	public <TDocument> CompletableFuture<TermvectorsResponse> termvectors(TermvectorsRequest<TDocument> request) {
		DatastoreParameters params = null;
		CompletableFuture<TermvectorsResponse> future = Weaver.callOriginal();
		NRCompletion<TermvectorsResponse> completionListener = new NRCompletion<TermvectorsResponse>(Utils.getOperationFromRequest(request), params, null);
		return future.whenComplete(completionListener);	
	}

	public <TDocument, TPartialDocument> CompletableFuture<UpdateResponse<TDocument>> update(UpdateRequest<TDocument, TPartialDocument> request, Class<TDocument> tDocumentClass) {
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
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		CompletableFuture<UpdateResponse<TDocument>> future = Weaver.callOriginal();
		NRCompletion<UpdateResponse<TDocument>> completionListener = new NRCompletion<UpdateResponse<TDocument>>(Utils.getOperationFromRequest(request), params, attributes);
		return future.whenComplete(completionListener);	
	}

}
