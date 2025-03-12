package org.opensearch.client;

import org.opensearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.opensearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.opensearch.action.admin.indices.cache.clear.ClearIndicesCacheRequest;
import org.opensearch.action.admin.indices.cache.clear.ClearIndicesCacheResponse;
import org.opensearch.action.admin.indices.delete.DeleteIndexRequest;
import org.opensearch.action.admin.indices.flush.FlushRequest;
import org.opensearch.action.admin.indices.flush.FlushResponse;
import org.opensearch.action.admin.indices.forcemerge.ForceMergeRequest;
import org.opensearch.action.admin.indices.forcemerge.ForceMergeResponse;
import org.opensearch.action.admin.indices.open.OpenIndexRequest;
import org.opensearch.action.admin.indices.open.OpenIndexResponse;
import org.opensearch.action.admin.indices.refresh.RefreshRequest;
import org.opensearch.action.admin.indices.refresh.RefreshResponse;
import org.opensearch.action.admin.indices.settings.get.GetSettingsRequest;
import org.opensearch.action.admin.indices.settings.get.GetSettingsResponse;
import org.opensearch.action.admin.indices.template.delete.DeleteIndexTemplateRequest;
import org.opensearch.action.admin.indices.validate.query.ValidateQueryRequest;
import org.opensearch.action.admin.indices.validate.query.ValidateQueryResponse;
import org.opensearch.action.support.master.AcknowledgedResponse;
import org.opensearch.client.indices.AnalyzeRequest;
import org.opensearch.client.indices.AnalyzeResponse;
import org.opensearch.client.indices.CloseIndexRequest;
import org.opensearch.client.indices.CloseIndexResponse;
import org.opensearch.client.indices.ComposableIndexTemplateExistRequest;
import org.opensearch.client.indices.CreateDataStreamRequest;
import org.opensearch.client.indices.CreateIndexRequest;
import org.opensearch.client.indices.CreateIndexResponse;
import org.opensearch.client.indices.DataStreamsStatsRequest;
import org.opensearch.client.indices.DataStreamsStatsResponse;
import org.opensearch.client.indices.DeleteAliasRequest;
import org.opensearch.client.indices.DeleteComposableIndexTemplateRequest;
import org.opensearch.client.indices.DeleteDataStreamRequest;
import org.opensearch.client.indices.GetComposableIndexTemplateRequest;
import org.opensearch.client.indices.GetComposableIndexTemplatesResponse;
import org.opensearch.client.indices.GetDataStreamRequest;
import org.opensearch.client.indices.GetDataStreamResponse;
import org.opensearch.client.indices.GetFieldMappingsRequest;
import org.opensearch.client.indices.GetFieldMappingsResponse;
import org.opensearch.client.indices.GetIndexRequest;
import org.opensearch.client.indices.GetIndexResponse;
import org.opensearch.client.indices.GetIndexTemplatesRequest;
import org.opensearch.client.indices.GetIndexTemplatesResponse;
import org.opensearch.client.indices.GetMappingsRequest;
import org.opensearch.client.indices.GetMappingsResponse;
import org.opensearch.client.indices.IndexTemplatesExistRequest;
import org.opensearch.client.indices.PutComposableIndexTemplateRequest;
import org.opensearch.client.indices.PutIndexTemplateRequest;
import org.opensearch.client.indices.PutMappingRequest;
import org.opensearch.client.indices.ResizeRequest;
import org.opensearch.client.indices.ResizeResponse;
import org.opensearch.client.indices.SimulateIndexTemplateRequest;
import org.opensearch.client.indices.SimulateIndexTemplateResponse;
import org.opensearch.client.indices.rollover.RolloverRequest;
import org.opensearch.client.indices.rollover.RolloverResponse;
import org.opensearch.core.action.ActionListener;
import org.opensearch.action.admin.indices.settings.put.UpdateSettingsRequest;

import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.opensearch.highlevelrest.NRActionListener;
import com.newrelic.instrumentation.labs.opensearch.highlevelrest.Utils;

@SuppressWarnings("deprecation")
@Weave
public abstract class IndicesClient {
	
	@Trace(leaf = true)
    public AcknowledgedResponse delete(DeleteIndexRequest deleteIndexRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(deleteIndexRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
    	
        return Weaver.callOriginal();
    }

    public Cancellable deleteAsync(DeleteIndexRequest deleteIndexRequest, RequestOptions options, ActionListener<AcknowledgedResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(deleteIndexRequest);
		NRActionListener<AcknowledgedResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public CreateIndexResponse create(CreateIndexRequest createIndexRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(createIndexRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable createAsync(CreateIndexRequest createIndexRequest, RequestOptions options, ActionListener<CreateIndexResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(createIndexRequest);
		NRActionListener<CreateIndexResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public AcknowledgedResponse createDataStream(CreateDataStreamRequest createDataStreamRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(createDataStreamRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable createDataStreamAsync(CreateDataStreamRequest createDataStreamRequest, RequestOptions options, ActionListener<AcknowledgedResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(createDataStreamRequest);
		NRActionListener<AcknowledgedResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public AcknowledgedResponse deleteDataStream(DeleteDataStreamRequest deleteDataStreamRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(deleteDataStreamRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable deleteDataStreamAsync(DeleteDataStreamRequest deleteDataStreamRequest, RequestOptions options, ActionListener<AcknowledgedResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(deleteDataStreamRequest);
		NRActionListener<AcknowledgedResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public GetDataStreamResponse getDataStream(GetDataStreamRequest dataStreamRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(dataStreamRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable getDataStreamAsync(GetDataStreamRequest dataStreamRequest, RequestOptions options, ActionListener<GetDataStreamResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(dataStreamRequest);
		NRActionListener<GetDataStreamResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public DataStreamsStatsResponse dataStreamsStats(DataStreamsStatsRequest dataStreamsStatsRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(dataStreamsStatsRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable dataStreamsStatsAsync(DataStreamsStatsRequest dataStreamsStatsRequest, RequestOptions options, ActionListener<DataStreamsStatsResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(dataStreamsStatsRequest);
		NRActionListener<DataStreamsStatsResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public AcknowledgedResponse putMapping(PutMappingRequest putMappingRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(putMappingRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable putMappingAsync(PutMappingRequest putMappingRequest, RequestOptions options, ActionListener<AcknowledgedResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(putMappingRequest);
		NRActionListener<AcknowledgedResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public GetMappingsResponse getMapping(GetMappingsRequest getMappingsRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(getMappingsRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable getMappingAsync(GetMappingsRequest getMappingsRequest, RequestOptions options, ActionListener<GetMappingsResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(getMappingsRequest);
		NRActionListener<GetMappingsResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public GetFieldMappingsResponse getFieldMapping(GetFieldMappingsRequest getFieldMappingsRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(getFieldMappingsRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable getFieldMappingAsync(GetFieldMappingsRequest getFieldMappingsRequest, RequestOptions options, ActionListener<GetFieldMappingsResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(getFieldMappingsRequest);
		NRActionListener<GetFieldMappingsResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public AcknowledgedResponse updateAliases(IndicesAliasesRequest indicesAliasesRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(indicesAliasesRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable updateAliasesAsync(IndicesAliasesRequest indicesAliasesRequest, RequestOptions options, ActionListener<AcknowledgedResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(indicesAliasesRequest);
		NRActionListener<AcknowledgedResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public OpenIndexResponse open(OpenIndexRequest openIndexRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(openIndexRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable openAsync(OpenIndexRequest openIndexRequest, RequestOptions options, ActionListener<OpenIndexResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(openIndexRequest);
		NRActionListener<OpenIndexResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public CloseIndexResponse close(CloseIndexRequest closeIndexRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(closeIndexRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable closeAsync(CloseIndexRequest closeIndexRequest, RequestOptions options, ActionListener<CloseIndexResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(closeIndexRequest);
		NRActionListener<CloseIndexResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public boolean existsAlias(GetAliasesRequest getAliasesRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(getAliasesRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable existsAliasAsync(GetAliasesRequest getAliasesRequest, RequestOptions options, ActionListener<Boolean> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(getAliasesRequest);
		NRActionListener<Boolean> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public RefreshResponse refresh(RefreshRequest refreshRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(refreshRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable refreshAsync(RefreshRequest refreshRequest, RequestOptions options, ActionListener<RefreshResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(refreshRequest);
		NRActionListener<RefreshResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public FlushResponse flush(FlushRequest flushRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(flushRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable flushAsync(FlushRequest flushRequest, RequestOptions options, ActionListener<FlushResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(flushRequest);
		NRActionListener<FlushResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public GetSettingsResponse getSettings(GetSettingsRequest getSettingsRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(getSettingsRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable getSettingsAsync(GetSettingsRequest getSettingsRequest, RequestOptions options, ActionListener<GetSettingsResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(getSettingsRequest);
		NRActionListener<GetSettingsResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public GetIndexResponse get(GetIndexRequest getIndexRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(getIndexRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable getAsync(GetIndexRequest getIndexRequest, RequestOptions options, ActionListener<GetIndexResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(getIndexRequest);
		NRActionListener<GetIndexResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public ForceMergeResponse forcemerge(ForceMergeRequest forceMergeRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(forceMergeRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable forcemergeAsync(ForceMergeRequest forceMergeRequest, RequestOptions options, ActionListener<ForceMergeResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(forceMergeRequest);
		NRActionListener<ForceMergeResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public ClearIndicesCacheResponse clearCache(ClearIndicesCacheRequest clearIndicesCacheRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(clearIndicesCacheRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable clearCacheAsync(ClearIndicesCacheRequest clearIndicesCacheRequest, RequestOptions options, ActionListener<ClearIndicesCacheResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(clearIndicesCacheRequest);
		NRActionListener<ClearIndicesCacheResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public boolean exists(GetIndexRequest getIndexRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(getIndexRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable existsAsync(GetIndexRequest getIndexRequest, RequestOptions options, ActionListener<Boolean> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(getIndexRequest);
		NRActionListener<Boolean> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public ResizeResponse shrink(ResizeRequest resizeRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(resizeRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public org.opensearch.action.admin.indices.shrink.ResizeResponse shrink(org.opensearch.action.admin.indices.shrink.ResizeRequest resizeRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(resizeRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable shrinkAsync(ResizeRequest resizeRequest, RequestOptions options, ActionListener<ResizeResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(resizeRequest);
		NRActionListener<ResizeResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

    public Cancellable shrinkAsync(org.opensearch.action.admin.indices.shrink.ResizeRequest resizeRequest, RequestOptions options, ActionListener<org.opensearch.action.admin.indices.shrink.ResizeResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(resizeRequest);
		NRActionListener<org.opensearch.action.admin.indices.shrink.ResizeResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public ResizeResponse split(ResizeRequest resizeRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(resizeRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public org.opensearch.action.admin.indices.shrink.ResizeResponse split(org.opensearch.action.admin.indices.shrink.ResizeRequest resizeRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(resizeRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable splitAsync(ResizeRequest resizeRequest, RequestOptions options, ActionListener<ResizeResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(resizeRequest);
		NRActionListener<ResizeResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

    public Cancellable splitAsync(org.opensearch.action.admin.indices.shrink.ResizeRequest resizeRequest, RequestOptions options, ActionListener<org.opensearch.action.admin.indices.shrink.ResizeResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(resizeRequest);
		NRActionListener<org.opensearch.action.admin.indices.shrink.ResizeResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public ResizeResponse clone(ResizeRequest resizeRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(resizeRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public org.opensearch.action.admin.indices.shrink.ResizeResponse clone(org.opensearch.action.admin.indices.shrink.ResizeRequest resizeRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(resizeRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable cloneAsync(ResizeRequest resizeRequest, RequestOptions options, ActionListener<ResizeResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(resizeRequest);
		NRActionListener<ResizeResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

    public Cancellable cloneAsync(org.opensearch.action.admin.indices.shrink.ResizeRequest resizeRequest, RequestOptions options, ActionListener<org.opensearch.action.admin.indices.shrink.ResizeResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(resizeRequest);
		NRActionListener<org.opensearch.action.admin.indices.shrink.ResizeResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public RolloverResponse rollover(RolloverRequest rolloverRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(rolloverRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable rolloverAsync(RolloverRequest rolloverRequest, RequestOptions options, ActionListener<RolloverResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(rolloverRequest);
		NRActionListener<RolloverResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public GetAliasesResponse getAlias(GetAliasesRequest getAliasesRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(getAliasesRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable getAliasAsync(GetAliasesRequest getAliasesRequest, RequestOptions options, ActionListener<GetAliasesResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(getAliasesRequest);
		NRActionListener<GetAliasesResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public AcknowledgedResponse putSettings(UpdateSettingsRequest updateSettingsRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(updateSettingsRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable putSettingsAsync(UpdateSettingsRequest updateSettingsRequest, RequestOptions options, ActionListener<AcknowledgedResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(updateSettingsRequest);
		NRActionListener<AcknowledgedResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public AcknowledgedResponse putTemplate(PutIndexTemplateRequest putIndexTemplateRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(putIndexTemplateRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable putTemplateAsync(PutIndexTemplateRequest putIndexTemplateRequest, RequestOptions options, ActionListener<AcknowledgedResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(putIndexTemplateRequest);
		NRActionListener<AcknowledgedResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public AcknowledgedResponse putIndexTemplate(PutComposableIndexTemplateRequest putIndexTemplateRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(putIndexTemplateRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable putIndexTemplateAsync(PutComposableIndexTemplateRequest putIndexTemplateRequest, RequestOptions options, ActionListener<AcknowledgedResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(putIndexTemplateRequest);
		NRActionListener<AcknowledgedResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public SimulateIndexTemplateResponse simulateIndexTemplate(SimulateIndexTemplateRequest simulateIndexTemplateRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(simulateIndexTemplateRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable simulateIndexTemplateAsync(SimulateIndexTemplateRequest simulateIndexTemplateRequest, RequestOptions options, ActionListener<SimulateIndexTemplateResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(simulateIndexTemplateRequest);
		NRActionListener<SimulateIndexTemplateResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public ValidateQueryResponse validateQuery(ValidateQueryRequest validateQueryRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(validateQueryRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable validateQueryAsync(ValidateQueryRequest validateQueryRequest, RequestOptions options, ActionListener<ValidateQueryResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(validateQueryRequest);
		NRActionListener<ValidateQueryResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public GetComposableIndexTemplatesResponse getIndexTemplate(GetComposableIndexTemplateRequest getIndexTemplatesRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(getIndexTemplatesRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable getIndexTemplateAsync(GetComposableIndexTemplateRequest getIndexTemplatesRequest, RequestOptions options, ActionListener<GetComposableIndexTemplatesResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(getIndexTemplatesRequest);
		NRActionListener<GetComposableIndexTemplatesResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public GetIndexTemplatesResponse getIndexTemplate(GetIndexTemplatesRequest getIndexTemplatesRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(getIndexTemplatesRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable getIndexTemplateAsync(GetIndexTemplatesRequest getIndexTemplatesRequest, RequestOptions options, ActionListener<GetIndexTemplatesResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(getIndexTemplatesRequest);
		NRActionListener<GetIndexTemplatesResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public boolean existsTemplate(IndexTemplatesExistRequest indexTemplatesRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(indexTemplatesRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable existsTemplateAsync(IndexTemplatesExistRequest indexTemplatesExistRequest, RequestOptions options, ActionListener<Boolean> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(indexTemplatesExistRequest);
		NRActionListener<Boolean> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public boolean existsIndexTemplate(ComposableIndexTemplateExistRequest indexTemplatesRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(indexTemplatesRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable existsIndexTemplateAsync(ComposableIndexTemplateExistRequest indexTemplatesExistRequest, RequestOptions options, ActionListener<Boolean> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(indexTemplatesExistRequest);
		NRActionListener<Boolean> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public AnalyzeResponse analyze(AnalyzeRequest analyzeRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(analyzeRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable analyzeAsync(AnalyzeRequest analyzeRequest, RequestOptions options, ActionListener<AnalyzeResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(analyzeRequest);
		NRActionListener<AnalyzeResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public AcknowledgedResponse deleteTemplate(DeleteIndexTemplateRequest deleteIndexTemplateRequest, RequestOptions options){
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(deleteIndexTemplateRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable deleteTemplateAsync(DeleteIndexTemplateRequest deleteIndexTemplateRequest, RequestOptions options, ActionListener<AcknowledgedResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(deleteIndexTemplateRequest);
		NRActionListener<AcknowledgedResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public AcknowledgedResponse deleteIndexTemplate(DeleteComposableIndexTemplateRequest deleteComposableIndexTemplateRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(deleteComposableIndexTemplateRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

	public Cancellable deleteIndexTemplateAsync(DeleteComposableIndexTemplateRequest deleteComposableIndexTemplateRequest, RequestOptions options, ActionListener<AcknowledgedResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(deleteComposableIndexTemplateRequest);
		NRActionListener<AcknowledgedResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

	@Trace(leaf = true)
    public org.opensearch.client.core.AcknowledgedResponse deleteAlias(DeleteAliasRequest deleteAliasRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(deleteAliasRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
        return Weaver.callOriginal();
    }

    public Cancellable deleteAliasAsync(DeleteAliasRequest deleteAliasRequest, RequestOptions options, ActionListener<org.opensearch.client.core.AcknowledgedResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromIndicesRequest(deleteAliasRequest);
		NRActionListener<org.opensearch.client.core.AcknowledgedResponse> wrapper = Utils.getWrapper(listener, params);
		if(wrapper != null) {
			listener = wrapper;
		}
        return Weaver.callOriginal();
    }

}
