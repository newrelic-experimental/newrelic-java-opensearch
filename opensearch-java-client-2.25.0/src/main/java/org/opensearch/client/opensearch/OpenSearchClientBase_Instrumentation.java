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
import org.opensearch.client.opensearch.core.SearchShardsRequest;
import org.opensearch.client.opensearch.core.SearchShardsResponse;
import org.opensearch.client.transport.Endpoint;
import org.opensearch.client.transport.OpenSearchTransport;
import org.opensearch.client.transport.TransportOptions;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Weave(originalName = "org.opensearch.client.opensearch.OpenSearchClientBase", type = MatchType.BaseClass)
public abstract class OpenSearchClientBase_Instrumentation<Self extends OpenSearchClientBase_Instrumentation<Self>> extends ApiClient<OpenSearchTransport, Self> {

    public OpenSearchClientBase_Instrumentation(OpenSearchTransport transport, @Nullable TransportOptions transportOptions) {
        super(transport, transportOptions);
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


}
