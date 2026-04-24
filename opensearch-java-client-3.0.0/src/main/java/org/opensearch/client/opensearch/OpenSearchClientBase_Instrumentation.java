package org.opensearch.client.opensearch;

import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
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

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Weave(originalName = "org.opensearch.client.opensearch.OpenSearchClientBase", type = MatchType.BaseClass)
public abstract class OpenSearchClientBase_Instrumentation<Self extends OpenSearchClientBase_Instrumentation<Self>> extends ApiClient<OpenSearchTransport, Self> {

    public OpenSearchClientBase_Instrumentation(OpenSearchTransport transport, @Nullable TransportOptions transportOptions) {
        super(transport, transportOptions);
    }

    @Trace(leaf = true)
    public ClearScrollResponse clearScroll()  {
        ClearScrollRequest request = new ClearScrollRequest.Builder().build();
        TracedMethod traced = NewRelic.getAgent().getTracedMethod();
        Map<String, String> collectionAttr = new HashMap<String, String>();
        String scrollId = Utils.getObjectString(request.scrollId());
        if(scrollId != null && !scrollId.isEmpty()) {
            collectionAttr.put("scrollId", scrollId);
        }
        Endpoint<ClearScrollRequest, ClearScrollResponse, ErrorResponse> endPoint = ClearScrollRequest._ENDPOINT;

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
    public CountResponse count() {
        CountRequest request = new CountRequest.Builder().build();
        TracedMethod traced = NewRelic.getAgent().getTracedMethod();
        Map<String, String> collectionAttr = new HashMap<String, String>();
        String index = Utils.getObjectString(request.index());
        if(index != null) {
            collectionAttr.put("index", index);
        }
        Query query = request.query();
        if(query != null) {
            collectionAttr.put("query",Utils.getCollectionFromQuery(query, null));
        }
        Endpoint<CountRequest, CountResponse, ErrorResponse> endPoint = CountRequest._ENDPOINT;

        DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), query);
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
    public InfoResponse info() {
        TracedMethod traced = NewRelic.getAgent().getTracedMethod();
        Map<String, String> collectionAttr = new HashMap<String, String>();
        collectionAttr.put("", "");
        DatastoreParameters params = Utils.getParams("InfoRequest", collectionAttr, Collections.emptyMap(), null);
        traced.reportAsExternal(params);
        return Weaver.callOriginal();
    }

    @Trace(leaf = true)
    public ClearScrollResponse clearScroll(ClearScrollRequest request)  {
        TracedMethod traced = NewRelic.getAgent().getTracedMethod();
        Map<String, String> collectionAttr = new HashMap<String, String>();
        String scrollId = Utils.getObjectString(request.scrollId());
        if(scrollId != null && !scrollId.isEmpty()) {
            collectionAttr.put("scrollId", scrollId);
        }
        Endpoint<ClearScrollRequest, ClearScrollResponse, ErrorResponse> endPoint = ClearScrollRequest._ENDPOINT;

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

    public DeleteResponse delete(DeleteRequest request) {
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
        Endpoint<DeleteRequest, DeleteResponse, ErrorResponse> endPoint = DeleteRequest._ENDPOINT;

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

    public DeleteByQueryResponse deleteByQuery(DeleteByQueryRequest request) {
        TracedMethod traced = NewRelic.getAgent().getTracedMethod();
        Map<String, String> collectionAttr = new HashMap<String, String>();
        String index = Utils.getObjectString(request.index());
        if(index != null) {
            collectionAttr.put("index", index);
        }
        Query query = request.query();
        if(query != null) {
            collectionAttr.put("query",Utils.getCollectionFromQuery(query, null));
        }
        Endpoint<DeleteByQueryRequest, DeleteByQueryResponse, ErrorResponse> endPoint = DeleteByQueryRequest._ENDPOINT;

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
    public DeleteByQueryRethrottleResponse deleteByQueryRethrottle(DeleteByQueryRethrottleRequest request) {
        TracedMethod traced = NewRelic.getAgent().getTracedMethod();
        Map<String, String> collectionAttr = new HashMap<String, String>();
        String taskId = request.taskId();
        if(taskId != null) {
            collectionAttr.put("taskid", taskId);
        }
        Endpoint<DeleteByQueryRethrottleRequest, DeleteByQueryRethrottleResponse, ErrorResponse> endPoint = DeleteByQueryRethrottleRequest._ENDPOINT;
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
    public DeleteScriptResponse deleteScript(DeleteScriptRequest request) {
        TracedMethod traced = NewRelic.getAgent().getTracedMethod();
        Map<String, String> collectionAttr = new HashMap<String, String>();
        String scriptId = request.id();
        collectionAttr.put("scriptid", scriptId);
        Endpoint<DeleteScriptRequest, DeleteScriptResponse, ErrorResponse> endPoint = DeleteScriptRequest._ENDPOINT;
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
    public BooleanResponse exists(ExistsRequest request) {
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
        Endpoint<ExistsRequest, BooleanResponse, ErrorResponse> endPoint = ExistsRequest._ENDPOINT;
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
    public BooleanResponse existsSource(ExistsSourceRequest request) {
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
        Endpoint<ExistsSourceRequest, BooleanResponse, ErrorResponse> endPoint = ExistsSourceRequest._ENDPOINT;
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

    public CountResponse count(CountRequest request) {
        TracedMethod traced = NewRelic.getAgent().getTracedMethod();
        Map<String, String> collectionAttr = new HashMap<String, String>();
        String index = Utils.getObjectString(request.index());
        if(index != null) {
            collectionAttr.put("index", index);
        }
        Query query = request.query();
        if(query != null) {
            collectionAttr.put("query",Utils.getCollectionFromQuery(query, null));
        }
        Endpoint<CountRequest, CountResponse, ErrorResponse> endPoint = CountRequest._ENDPOINT;

        DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), query);
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
    public GetScriptResponse getScript(GetScriptRequest request) {
        TracedMethod traced = NewRelic.getAgent().getTracedMethod();
        Map<String, String> collectionAttr = new HashMap<String, String>();
        String id = request.id();
        if(id != null) {
            collectionAttr.put("scriptid", id);
        }
        Endpoint<GetScriptRequest, GetScriptResponse, ErrorResponse> endPoint = GetScriptRequest._ENDPOINT;
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
    public PutScriptResponse putScript(PutScriptRequest request) {
        TracedMethod traced = NewRelic.getAgent().getTracedMethod();
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
    public ReindexResponse reindex(ReindexRequest request) {
        TracedMethod traced = NewRelic.getAgent().getTracedMethod();
        Map<String, String> collectionAttr = new HashMap<String, String>();
        collectionAttr.put("collection", "");
        Endpoint<ReindexRequest, ReindexResponse, ErrorResponse> endPoint = ReindexRequest._ENDPOINT;
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
    public SearchShardsResponse searchShards(SearchShardsRequest request) {
        TracedMethod traced = NewRelic.getAgent().getTracedMethod();
        Map<String, String> collectionAttr = new HashMap<String, String>();
        List<String> indices = request.index();
        if(indices != null && !indices.isEmpty()) {
            collectionAttr.put("indices", Utils.getCollectionFromList(indices));
        } else {
            collectionAttr.put(" ", "");
        }
        Endpoint<SearchShardsRequest, SearchShardsResponse, ErrorResponse> endPoint = SearchShardsRequest._ENDPOINT;
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
    public UpdateByQueryResponse updateByQuery(UpdateByQueryRequest request) {
        TracedMethod traced = NewRelic.getAgent().getTracedMethod();
        Map<String, String> collectionAttr = new HashMap<String, String>();
        List<String> indices = request.index();
        if(indices != null && !indices.isEmpty()) {
            collectionAttr.put("indices", Utils.getCollectionFromList(indices));
        }
        Query query = request.query();
        if(query != null) {
            collectionAttr.put("query", Utils.getCollectionFromQuery(query, null));
        }
        Endpoint<UpdateByQueryRequest, UpdateByQueryResponse, ErrorResponse> endPoint = UpdateByQueryRequest._ENDPOINT;
        DatastoreParameters params = Utils.getParams(request, collectionAttr, endPoint.queryParameters(request), query);
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
