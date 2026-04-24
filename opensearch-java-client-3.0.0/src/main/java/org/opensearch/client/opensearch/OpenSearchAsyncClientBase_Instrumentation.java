package org.opensearch.client.opensearch;

import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.opensearch.javaclient.NRCompletion;
import com.newrelic.instrumentation.labs.opensearch.javaclient.Utils;
import org.opensearch.client.ApiClient;
import org.opensearch.client.opensearch._types.ErrorResponse;
import org.opensearch.client.opensearch._types.InlineScript;
import org.opensearch.client.opensearch._types.query_dsl.Query;
import org.opensearch.client.opensearch.core.*;
import org.opensearch.client.transport.Endpoint;
import org.opensearch.client.transport.OpenSearchTransport;
import org.opensearch.client.transport.TransportOptions;
import org.opensearch.client.transport.endpoints.BooleanResponse;
import org.opensearch.client.transport.endpoints.SimpleEndpoint;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Weave(originalName = "org.opensearch.client.opensearch.OpenSearchAsyncClientBase", type = MatchType.BaseClass)
public abstract class OpenSearchAsyncClientBase_Instrumentation<Self extends OpenSearchAsyncClientBase_Instrumentation<Self>> extends ApiClient<OpenSearchTransport, Self> {

	public OpenSearchAsyncClientBase_Instrumentation(OpenSearchTransport transport, TransportOptions transportOptions) {
		super(transport, transportOptions);
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

	public CompletableFuture<FieldCapsResponse> fieldCaps(FieldCapsRequest request) {
		DatastoreParameters params = null;
		CompletableFuture<FieldCapsResponse> future = Weaver.callOriginal();
		NRCompletion<FieldCapsResponse> completionListener = new NRCompletion<FieldCapsResponse>(Utils.getOperationFromRequest(request), params, null);
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

	public CompletableFuture<InfoResponse> info() {
		Map<String, String> collectionAttr = new HashMap<String, String>();
		collectionAttr.put("", "");
		DatastoreParameters params = Utils.getParams("InfoRequest", collectionAttr, Collections.emptyMap(), null);
		CompletableFuture<InfoResponse> future = Weaver.callOriginal();
		NRCompletion<InfoResponse> completionListener = new NRCompletion<InfoResponse>("Info", params, null);
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
