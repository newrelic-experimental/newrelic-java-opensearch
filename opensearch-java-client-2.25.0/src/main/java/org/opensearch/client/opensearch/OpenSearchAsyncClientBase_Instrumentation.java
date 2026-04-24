package org.opensearch.client.opensearch;

import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.opensearch.javaclient.NRCompletion;
import com.newrelic.instrumentation.labs.opensearch.javaclient.Utils;
import org.opensearch.client.ApiClient;
import org.opensearch.client.opensearch._types.ErrorResponse;
import org.opensearch.client.opensearch.core.SearchShardsRequest;
import org.opensearch.client.opensearch.core.SearchShardsResponse;
import org.opensearch.client.transport.Endpoint;
import org.opensearch.client.transport.OpenSearchTransport;
import org.opensearch.client.transport.TransportOptions;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Weave(type = MatchType.BaseClass, originalName = "org.opensearch.client.opensearch.OpenSearchAsyncClientBase")
public abstract class OpenSearchAsyncClientBase_Instrumentation<Self extends OpenSearchAsyncClientBase_Instrumentation<Self>> extends ApiClient<OpenSearchTransport, Self> {

    public OpenSearchAsyncClientBase_Instrumentation(OpenSearchTransport transport, @Nullable TransportOptions transportOptions) {
        super(transport, transportOptions);
    }

    @Trace(leaf = true)
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


}
