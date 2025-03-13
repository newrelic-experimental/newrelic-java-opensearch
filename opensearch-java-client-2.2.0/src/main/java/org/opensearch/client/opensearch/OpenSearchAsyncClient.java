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
import org.opensearch.client.opensearch.core.TermsEnumRequest;
import org.opensearch.client.opensearch.core.TermsEnumResponse;
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

@Weave
public abstract class OpenSearchAsyncClient extends ApiClient<OpenSearchTransport, OpenSearchAsyncClient> {

	public OpenSearchAsyncClient(OpenSearchTransport transport) {
		super(transport, null);
	}

	public OpenSearchAsyncClient(OpenSearchTransport transport, TransportOptions transportOptions) {
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

	public CompletableFuture<ClearScrollResponse> clearScroll(ClearScrollRequest request) {
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String scrollId = Utils.getObjectString(request.scrollId());
		if(scrollId != null && !scrollId.isEmpty()) {
			collectionAttr.put("scrollId", scrollId);
		}
		Endpoint<ClearScrollRequest, ClearScrollResponse, ErrorResponse> endPoint = ClearScrollRequest._ENDPOINT;
		
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		CompletableFuture<ClearScrollResponse> future = Weaver.callOriginal();
		NRCompletion<ClearScrollResponse> completionListener = new NRCompletion<ClearScrollResponse>(Utils.getOperationFromRequest(request), params, attributes);
		return future.whenComplete(completionListener);	
	}

	public CompletableFuture<CountResponse> count(CountRequest request) {
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String index = Utils.getObjectString(request.index());
		if(index != null && !index.isEmpty()) {
			collectionAttr.put("index",index);
		}
		Query query = request.query();
		if(query != null) {
			collectionAttr.put("query",Utils.getObjectString(query));
		}
		Endpoint<CountRequest, CountResponse, ErrorResponse> endPoint = CountRequest._ENDPOINT;
		
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		CompletableFuture<CountResponse> future = Weaver.callOriginal();
		NRCompletion<CountResponse> completionListener = new NRCompletion<CountResponse>(Utils.getOperationFromRequest(request), params, attributes);
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

	public CompletableFuture<DeleteResponse> delete(DeleteRequest request) {
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String index = request.index();
		if(index != null) {
			collectionAttr.put("index", index);
		}
		String id = request.id();
		if(id != null) {
			collectionAttr.put("id", id);
		}
		 Endpoint<DeleteRequest, DeleteResponse, ErrorResponse> endPoint = DeleteRequest._ENDPOINT;
		
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		CompletableFuture<DeleteResponse> future = Weaver.callOriginal();
		NRCompletion<DeleteResponse> completionListener = new NRCompletion<DeleteResponse>(Utils.getOperationFromRequest(request), params, attributes);
		return future.whenComplete(completionListener);	
	}

	public CompletableFuture<DeleteByQueryResponse> deleteByQuery(DeleteByQueryRequest request) {
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String index = Utils.getObjectString(request.index());
		if(index != null && !index.isEmpty()) {
			collectionAttr.put("index", index);
		}
		Query query = request.query();
		if(query != null) {
			collectionAttr.put("Query", Utils.getObjectString(query));
		}

		Endpoint<DeleteByQueryRequest, DeleteByQueryResponse, ErrorResponse> endPoint = DeleteByQueryRequest._ENDPOINT;
		DatastoreParameters params = Utils.getParams(request, collectionAttr, DeleteByQueryRequest._ENDPOINT.queryParameters(request), query);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		CompletableFuture<DeleteByQueryResponse> future = Weaver.callOriginal();
		NRCompletion<DeleteByQueryResponse> completionListener = new NRCompletion<DeleteByQueryResponse>(Utils.getOperationFromRequest(request), params, attributes);
		return future.whenComplete(completionListener);	
	}

	public CompletableFuture<DeleteByQueryRethrottleResponse> deleteByQueryRethrottle(DeleteByQueryRethrottleRequest request) {
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String taskId = request.taskId();
		if(taskId != null) {
			collectionAttr.put("taskid", taskId);
		}
		Endpoint<DeleteByQueryRethrottleRequest, DeleteByQueryRethrottleResponse, ErrorResponse> endPoint = DeleteByQueryRethrottleRequest._ENDPOINT;
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		CompletableFuture<DeleteByQueryRethrottleResponse> future = Weaver.callOriginal();
		NRCompletion<DeleteByQueryRethrottleResponse> completionListener = new NRCompletion<DeleteByQueryRethrottleResponse>(Utils.getOperationFromRequest(request), params, attributes);
		return future.whenComplete(completionListener);	
	}

	public CompletableFuture<DeleteScriptResponse> deleteScript(DeleteScriptRequest request) {
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String scriptId = request.id();
		collectionAttr.put("scriptid", scriptId);
		Endpoint<DeleteScriptRequest, DeleteScriptResponse, ErrorResponse> endPoint = DeleteScriptRequest._ENDPOINT;
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		CompletableFuture<DeleteScriptResponse> future = Weaver.callOriginal();
		NRCompletion<DeleteScriptResponse> completionListener = new NRCompletion<DeleteScriptResponse>(Utils.getOperationFromRequest(request), params, attributes);
		return future.whenComplete(completionListener);	
	}

	public CompletableFuture<BooleanResponse> exists(ExistsRequest request) {
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String index = request.index();
		if(index != null) {
			collectionAttr.put("index", index);
		}
		String id = request.id();
		if(id != null) {
			collectionAttr.put("id", id);
		}
		Endpoint<ExistsRequest, BooleanResponse, ErrorResponse> endPoint = ExistsRequest._ENDPOINT;
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		CompletableFuture<BooleanResponse> future = Weaver.callOriginal();
		NRCompletion<BooleanResponse> completionListener = new NRCompletion<BooleanResponse>(Utils.getOperationFromRequest(request), params, attributes);
		return future.whenComplete(completionListener);	
	}

	public CompletableFuture<BooleanResponse> existsSource(ExistsSourceRequest request) {
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String index = request.index();
		if(index != null) {
			collectionAttr.put("index", index);
		}
		String id = request.id();
		if(id != null) {
			collectionAttr.put("id", id);
		}
		Endpoint<ExistsSourceRequest, BooleanResponse, ErrorResponse> endPoint = ExistsSourceRequest._ENDPOINT;
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		CompletableFuture<BooleanResponse> future = Weaver.callOriginal();
		NRCompletion<BooleanResponse> completionListener = new NRCompletion<BooleanResponse>(Utils.getOperationFromRequest(request), params, attributes);
		return future.whenComplete(completionListener);	
	}

	public <TDocument> CompletableFuture<ExplainResponse<TDocument>> explain(ExplainRequest request, Class<TDocument> tDocumentClass) {
		DatastoreParameters params = null;
		CompletableFuture<ExplainResponse<TDocument>> future = Weaver.callOriginal();
		NRCompletion<ExplainResponse<TDocument>> completionListener = new NRCompletion<ExplainResponse<TDocument>>(Utils.getOperationFromRequest(request), params, null);
		return future.whenComplete(completionListener);	
	}

	public CompletableFuture<FieldCapsResponse> fieldCaps(FieldCapsRequest request) {
		DatastoreParameters params = null;
		CompletableFuture<FieldCapsResponse> future = Weaver.callOriginal();
		NRCompletion<FieldCapsResponse> completionListener = new NRCompletion<FieldCapsResponse>(Utils.getOperationFromRequest(request), params, null);
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

	public CompletableFuture<GetScriptResponse> getScript(GetScriptRequest request) {
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String id = request.id();
		if(id != null) {
			collectionAttr.put("scriptid", id);
		}
		Endpoint<GetScriptRequest, GetScriptResponse, ErrorResponse> endPoint = GetScriptRequest._ENDPOINT;
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		CompletableFuture<GetScriptResponse> future = Weaver.callOriginal();
		NRCompletion<GetScriptResponse> completionListener = new NRCompletion<GetScriptResponse>(Utils.getOperationFromRequest(request), params, attributes);
		return future.whenComplete(completionListener);	
	}

	public CompletableFuture<GetScriptContextResponse> getScriptContext() {
		DatastoreParameters params = null;
		CompletableFuture<GetScriptContextResponse> future = Weaver.callOriginal();
		NRCompletion<GetScriptContextResponse> completionListener = new NRCompletion<GetScriptContextResponse>("GetScriptContext", params, null);
		return future.whenComplete(completionListener);	
	}

	public CompletableFuture<GetScriptLanguagesResponse> getScriptLanguages() {
		DatastoreParameters params = null;
		CompletableFuture<GetScriptLanguagesResponse> future = Weaver.callOriginal();
		NRCompletion<GetScriptLanguagesResponse> completionListener = new NRCompletion<GetScriptLanguagesResponse>("GetScriptLanguates", params, null);
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

	public CompletableFuture<InfoResponse> info() {
		Map<String, String> collectionAttr = new HashMap<String, String>();
		collectionAttr.put("", "");
		DatastoreParameters params = Utils.getParams("InfoRequest", collectionAttr, Collections.emptyMap(), null);
		CompletableFuture<InfoResponse> future = Weaver.callOriginal();
		NRCompletion<InfoResponse> completionListener = new NRCompletion<InfoResponse>("Info", params, null);
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

	public CompletableFuture<MtermvectorsResponse> mtermvectors(MtermvectorsRequest request) {
		DatastoreParameters params = null;
		CompletableFuture<MtermvectorsResponse> future = Weaver.callOriginal();
		NRCompletion<MtermvectorsResponse> completionListener = new NRCompletion<MtermvectorsResponse>(Utils.getOperationFromRequest(request), params, null);
		return future.whenComplete(completionListener);	
	}

	public CompletableFuture<BooleanResponse> ping() {
		DatastoreParameters params = null;
		CompletableFuture<BooleanResponse> future = Weaver.callOriginal();
		NRCompletion<BooleanResponse> completionListener = new NRCompletion<BooleanResponse>("Ping", params, null);
		return future.whenComplete(completionListener);	
	}

	public CompletableFuture<PutScriptResponse> putScript(PutScriptRequest request) {
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String id = request.id();
		if(id != null) {
			collectionAttr.put("id", id);
		}
		String context = request.context();
		if(context != null) {
			collectionAttr.put("context", context);
		}
		Endpoint<PutScriptRequest, PutScriptResponse, ErrorResponse> endPoint = PutScriptRequest._ENDPOINT;
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		CompletableFuture<PutScriptResponse> future = Weaver.callOriginal();
		NRCompletion<PutScriptResponse> completionListener = new NRCompletion<PutScriptResponse>(Utils.getOperationFromRequest(request), params, attributes);
		return future.whenComplete(completionListener);	
	}

	public CompletableFuture<RankEvalResponse> rankEval(RankEvalRequest request) {
		DatastoreParameters params = null;
		CompletableFuture<RankEvalResponse> future = Weaver.callOriginal();
		NRCompletion<RankEvalResponse> completionListener = new NRCompletion<RankEvalResponse>(Utils.getOperationFromRequest(request), params, null);
		return future.whenComplete(completionListener);	
	}

	public CompletableFuture<ReindexResponse> reindex(ReindexRequest request) {
		Map<String, String> collectionAttr = new HashMap<String, String>();
		collectionAttr.put("collection", "");
		Endpoint<ReindexRequest, ReindexResponse, ErrorResponse> endPoint = ReindexRequest._ENDPOINT;
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		CompletableFuture<ReindexResponse> future = Weaver.callOriginal();
		NRCompletion<ReindexResponse> completionListener = new NRCompletion<ReindexResponse>(Utils.getOperationFromRequest(request), params, attributes);
		return future.whenComplete(completionListener);	
	}

	public CompletableFuture<ReindexRethrottleResponse> reindexRethrottle(ReindexRethrottleRequest request) {
		DatastoreParameters params = null;
		CompletableFuture<ReindexRethrottleResponse> future = Weaver.callOriginal();
		NRCompletion<ReindexRethrottleResponse> completionListener = new NRCompletion<ReindexRethrottleResponse>(Utils.getOperationFromRequest(request), params, null);
		return future.whenComplete(completionListener);	
	}

	public <TResult> CompletableFuture<ScriptsPainlessExecuteResponse<TResult>> scriptsPainlessExecute(ScriptsPainlessExecuteRequest request, Class<TResult> tResultClass) {
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String context = request.context();
		if(context != null) {
			collectionAttr.put("context", context);
		}
		InlineScript script = request.script();
		if (script != null) {
			String source = script.source();
			if(source != null) {
				collectionAttr.put("script-source", source);
			}
			
		}
		if(tResultClass != null) {
			collectionAttr.put("result-type", tResultClass.getSimpleName());
		}
		 SimpleEndpoint<ScriptsPainlessExecuteRequest, ?> endPoint = ScriptsPainlessExecuteRequest._ENDPOINT;
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		CompletableFuture<ScriptsPainlessExecuteResponse<TResult>> future = Weaver.callOriginal();
		NRCompletion<ScriptsPainlessExecuteResponse<TResult>> completionListener = new NRCompletion<ScriptsPainlessExecuteResponse<TResult>>(Utils.getOperationFromRequest(request), params, attributes);
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

	public CompletableFuture<SearchShardsResponse> searchShards(SearchShardsRequest request) {
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String index = Utils.getObjectString(request.index());
		if(index != null && !index.isEmpty()) {
			collectionAttr.put("index", index);
		} else {
			collectionAttr.put(" ", "");
		}
		Endpoint<SearchShardsRequest, SearchShardsResponse, ErrorResponse> endPoint = SearchShardsRequest._ENDPOINT;
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), null);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		CompletableFuture<SearchShardsResponse> future = Weaver.callOriginal();
		NRCompletion<SearchShardsResponse> completionListener = new NRCompletion<SearchShardsResponse>(Utils.getOperationFromRequest(request), params, attributes);
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

	public CompletableFuture<TermsEnumResponse> termsEnum(TermsEnumRequest request) {
		DatastoreParameters params = null;
		CompletableFuture<TermsEnumResponse> future = Weaver.callOriginal();
		NRCompletion<TermsEnumResponse> completionListener = new NRCompletion<TermsEnumResponse>(Utils.getOperationFromRequest(request), params, null);
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

	public CompletableFuture<UpdateByQueryResponse> updateByQuery(UpdateByQueryRequest request) {
		Map<String, String> collectionAttr = new HashMap<String, String>();
		String index = Utils.getObjectString(request.index());
		if(index != null && !index.isEmpty()) {
			collectionAttr.put("index", index);
		}
		Query query = request.query();
		if(query != null) {
			collectionAttr.put("query", Utils.getObjectString(query));
		}
		Endpoint<UpdateByQueryRequest, UpdateByQueryResponse, ErrorResponse> endPoint = UpdateByQueryRequest._ENDPOINT;
		DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), query);
		Object payloadObj = endPoint.hasRequestBody() ? Utils.getRequestBody(request, transport.jsonpMapper()) : null;;
		String requestUrl = endPoint.requestUrl(request);
		String method = endPoint.method(request);
		
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		Utils.recordRequest(attributes, payloadObj, requestUrl, method);
		CompletableFuture<UpdateByQueryResponse> future = Weaver.callOriginal();
		NRCompletion<UpdateByQueryResponse> completionListener = new NRCompletion<UpdateByQueryResponse>(Utils.getOperationFromRequest(request), params, attributes);
		return future.whenComplete(completionListener);	
	}

	public CompletableFuture<UpdateByQueryRethrottleResponse> updateByQueryRethrottle(UpdateByQueryRethrottleRequest request) {
		DatastoreParameters params = null;
		CompletableFuture<UpdateByQueryRethrottleResponse> future = Weaver.callOriginal();
		NRCompletion<UpdateByQueryRethrottleResponse> completionListener = new NRCompletion<UpdateByQueryRethrottleResponse>(Utils.getOperationFromRequest(request), params, null);
		return future.whenComplete(completionListener);	
	}

}
