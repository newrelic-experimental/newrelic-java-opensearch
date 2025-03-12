package com.newrelic.instrumentation.labs.opensearch.highlevelrest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.opensearch.action.admin.cluster.storedscripts.DeleteStoredScriptRequest;
import org.opensearch.action.admin.cluster.storedscripts.GetStoredScriptRequest;
import org.opensearch.action.admin.cluster.storedscripts.PutStoredScriptRequest;
import org.opensearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.opensearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.opensearch.action.admin.indices.cache.clear.ClearIndicesCacheRequest;
import org.opensearch.action.admin.indices.delete.DeleteIndexRequest;
import org.opensearch.action.admin.indices.flush.FlushRequest;
import org.opensearch.action.admin.indices.forcemerge.ForceMergeRequest;
import org.opensearch.action.admin.indices.open.OpenIndexRequest;
import org.opensearch.action.admin.indices.refresh.RefreshRequest;
import org.opensearch.action.admin.indices.settings.get.GetSettingsRequest;
import org.opensearch.action.admin.indices.template.delete.DeleteIndexTemplateRequest;
import org.opensearch.action.admin.indices.validate.query.ValidateQueryRequest;
import org.opensearch.action.delete.DeleteRequest;
import org.opensearch.action.get.GetRequest;
import org.opensearch.action.get.MultiGetRequest;
import org.opensearch.action.get.MultiGetRequest.Item;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.action.search.MultiSearchRequest;
import org.opensearch.action.search.SearchRequest;
import org.opensearch.action.search.SearchScrollRequest;
import org.opensearch.action.update.UpdateRequest;
import org.opensearch.client.core.CountRequest;
import org.opensearch.client.core.GetSourceRequest;
import org.opensearch.client.core.TermVectorsRequest;
import org.opensearch.client.indices.AnalyzeRequest;
import org.opensearch.client.indices.CloseIndexRequest;
import org.opensearch.client.indices.ComposableIndexTemplateExistRequest;
import org.opensearch.client.indices.CreateDataStreamRequest;
import org.opensearch.client.indices.CreateIndexRequest;
import org.opensearch.client.indices.DataStreamsStatsRequest;
import org.opensearch.client.indices.DeleteAliasRequest;
import org.opensearch.client.indices.DeleteComposableIndexTemplateRequest;
import org.opensearch.client.indices.DeleteDataStreamRequest;
import org.opensearch.client.indices.GetComposableIndexTemplateRequest;
import org.opensearch.client.indices.GetDataStreamRequest;
import org.opensearch.client.indices.GetFieldMappingsRequest;
import org.opensearch.client.indices.GetIndexRequest;
import org.opensearch.client.indices.GetIndexTemplatesRequest;
import org.opensearch.client.indices.GetMappingsRequest;
import org.opensearch.client.indices.IndexTemplatesExistRequest;
import org.opensearch.client.indices.PutComposableIndexTemplateRequest;
import org.opensearch.client.indices.PutIndexTemplateRequest;
import org.opensearch.client.indices.PutMappingRequest;
import org.opensearch.client.indices.ResizeRequest;
import org.opensearch.client.indices.SimulateIndexTemplateRequest;
import org.opensearch.client.indices.rollover.RolloverRequest;
import org.opensearch.core.action.ActionListener;
import org.opensearch.core.common.transport.TransportAddress;
import org.opensearch.extensions.UpdateSettingsRequest;
import org.opensearch.index.rankeval.RankEvalRequest;
import org.opensearch.index.reindex.DeleteByQueryRequest;
import org.opensearch.index.reindex.ReindexRequest;
import org.opensearch.index.reindex.UpdateByQueryRequest;
import org.opensearch.script.mustache.MultiSearchTemplateRequest;
import org.opensearch.script.mustache.SearchTemplateRequest;

import com.newrelic.api.agent.DatastoreParameters;

public class Utils {

	public static <T> NRActionListener<T> getWrapper(ActionListener<T> listener, DatastoreParameters params) {
		if(!(listener instanceof NRActionListener)) {
			return new NRActionListener<T>(listener, params);
		}
		return null;
	}
	
	public static DatastoreParameters getParamsFromGet(GetRequest request, String type) {
		return getParamsFromGetRequest(request, type);
	}
	
	public static DatastoreParameters getParamsFromIndicesRequest(Object requestObj) {
		Class<?> clazz = requestObj.getClass();
		String classname = clazz.getName();
		String simpleName = clazz.getSimpleName();
		
		if(simpleName.equals("DeleteIndexRequest")) {
			return getParamsFromDeleteIndexRequest((DeleteIndexRequest)requestObj);
		}
		if(simpleName.equals("CreateIndexRequest")) {
			return getParamsFromCreateIndexRequest((CreateIndexRequest)requestObj);
		}
		if(simpleName.equals("CreateDataStreamRequest")) {
			return getParamsFromCreateDataStreamRequest((CreateDataStreamRequest)requestObj);
		}		
		if(simpleName.equals("DeleteDataStreamRequest")) {
			return getParamsFromDeleteDataStreamRequest((DeleteDataStreamRequest)requestObj);
		}
		if(simpleName.equals("GetDataStreamRequest")) {
			return getParamsFromGetDataStreamRequest((GetDataStreamRequest)requestObj);
		}
		if(simpleName.equals("DataStreamsStatsRequest")) {
			return getParamsFromDataStreamsStatsRequest((DataStreamsStatsRequest)requestObj);
		}
		if(simpleName.equals("PutMappingRequest")) {
			return getParamsFromPutMappingRequest((PutMappingRequest)requestObj);
		}
		if(simpleName.equals("GetMappingsRequest")) {
			return getParamsFromGetMappingsRequest((GetMappingsRequest)requestObj);
		}
		if(simpleName.equals("GetFieldMappingsRequest")) {
			return getParamsFromGetFieldMappingsRequest((GetFieldMappingsRequest)requestObj);
		}
		if(simpleName.equals("IndicesAliasesRequest")) {
			return getParamsFromIndicesAliasesRequest((IndicesAliasesRequest)requestObj);
		}
		if(simpleName.equals("OpenIndexRequest")) {
			return getParamsFromOpenIndexRequest((OpenIndexRequest)requestObj);
		}
		if(simpleName.equals("CloseIndexRequest")) {
			return getParamsFromCloseIndexRequest((CloseIndexRequest)requestObj);
		}
		if(simpleName.equals("GetAliasesRequest")) {
			return getParamsFromGetAliasesRequest((GetAliasesRequest)requestObj);
		}
		if(simpleName.equals("RefreshRequest")) {
			return getParamsFromRefreshRequest((RefreshRequest)requestObj);
		}
		if(simpleName.equals("FlushRequest")) {
			return getParamsFromFlushRequest((FlushRequest)requestObj);
		}
		if(simpleName.equals("GetSettingsRequest")) {
			return getParamsFromGetSettingsRequest((GetSettingsRequest)requestObj);
		}
		if(simpleName.equals("GetIndexRequest")) {
			return getParamsFromGetIndexRequest((GetIndexRequest)requestObj);
		}
		if(simpleName.equals("ForceMergeRequest")) {
			return getParamsFromForceMergeRequest((ForceMergeRequest)requestObj);
		}
		if(simpleName.equals("ClearIndicesCacheRequest")) {
			return getParamsFromClearIndicesCacheRequest((ClearIndicesCacheRequest)requestObj);
		}
		if(simpleName.equals("ResizeRequest")) {
			if(classname.equals("org.opensearch.action.admin.indices.shrink.ResizeRequest")) {
				return getParamsFromResizeRequest((org.opensearch.action.admin.indices.shrink.ResizeRequest)requestObj);
			}
			return getParamsFromResizeRequest((ResizeRequest)requestObj);
		}
		if(simpleName.equals("RolloverRequest")) {
			return getParamsFromRolloverRequest((RolloverRequest)requestObj);
		}
		if(simpleName.equals("GetAliasesRequest")) {
			return getParamsFromGetAliasesRequest((GetAliasesRequest)requestObj);
		}
		if(simpleName.equals("UpdateSettingsRequest")) {
			return getParamsFromUpdateSettingsRequest((UpdateSettingsRequest)requestObj);
		}
		if(simpleName.equals("PutIndexTemplateRequest")) {
			return getParamsFromPutIndexTemplateRequest((PutIndexTemplateRequest)requestObj);
		}
		if(simpleName.equals("PutComposableIndexTemplateRequest")) {
			return getParamsFromPutComposableIndexTemplateRequest((PutComposableIndexTemplateRequest)requestObj);
		}
		if(simpleName.equals("SimulateIndexTemplateRequest")) {
			return getParamsFromSimulateIndexTemplateRequest((SimulateIndexTemplateRequest)requestObj);
		}
		if(simpleName.equals("ValidateQueryRequest")) {
			return getParamsFromValidateQueryRequest((ValidateQueryRequest)requestObj);
		}
		if(simpleName.equals("GetComposableIndexTemplateRequest")) {
			return getParamsFromGetComposableIndexTemplateRequest((GetComposableIndexTemplateRequest)requestObj);
		}
		if(simpleName.equals("GetIndexTemplatesRequest")) {
			return getParamsFromGetIndexTemplatesRequest((GetIndexTemplatesRequest)requestObj);
		}
		if(simpleName.equals("IndexTemplatesExistRequest")) {
			return getParamsFromIndexTemplatesExistRequest((IndexTemplatesExistRequest)requestObj);
		}
		if(simpleName.equals("ComposableIndexTemplateExistRequest")) {
			return getParamsFromComposableIndexTemplateExistRequest((ComposableIndexTemplateExistRequest)requestObj);
		}
		if(simpleName.equals("AnalyzeRequest")) {
			return getParamsFromAnalyzeRequest((AnalyzeRequest)requestObj);
		}
		if(simpleName.equals("DeleteIndexTemplateRequest")) {
			return getParamsFromDeleteIndexTemplateRequest((DeleteIndexTemplateRequest)requestObj);
		}
		if(simpleName.equals("DeleteComposableIndexTemplateRequest")) {
			return getParamsFromDeleteComposableIndexTemplateRequest((DeleteComposableIndexTemplateRequest)requestObj);
		}
		if(simpleName.equals("DeleteAliasRequest")) {
			return getParamsFromDeleteAliasRequest((DeleteAliasRequest)requestObj);
		}
		
		return null;
	}
	
	public static DatastoreParameters getParamsFromHighLevelRequest(Object requestObj) {
		if(requestObj instanceof GetRequest) {
			return getParamsFromGetRequest((GetRequest)requestObj, "Get");
		}
		
		if(requestObj instanceof SearchRequest) {
			return getParamsFromSearchRequest((SearchRequest)requestObj);
		}
		
		if(requestObj instanceof UpdateRequest) {
			return getParamsFromUpdateRequest((UpdateRequest)requestObj);
		}
		
		if(requestObj instanceof DeleteRequest) {
			return getParamsFromDeleteRequest((DeleteRequest)requestObj);
		}
		
		if(requestObj instanceof CountRequest) {
			return getParamsFromCountRequest((CountRequest)requestObj);
		}
		
		if(requestObj instanceof IndexRequest) {
			return getParamsFromIndexRequest((IndexRequest)requestObj);
		}
		
		if(requestObj instanceof DeleteByQueryRequest) {
			return getParamsFromDeleteByQueryRequest((DeleteByQueryRequest)requestObj);
		}
		
		if(requestObj instanceof DeleteStoredScriptRequest) {
			return getParamsFromDeleteStoredScriptRequest((DeleteStoredScriptRequest)requestObj);
		}
		
		if(requestObj instanceof GetSourceRequest) {
			return getParamsFromGetSourceRequest((GetSourceRequest)requestObj);
		}
		
		if(requestObj instanceof GetStoredScriptRequest) {
			return getParamsFromGetStoredScriptRequest((GetStoredScriptRequest)requestObj);
		}
		
		if(requestObj instanceof MultiGetRequest) {
			return getParamsFromMultiGetRequest((MultiGetRequest)requestObj);
		}
		
		if(requestObj instanceof MultiSearchRequest) {
			return getParamsFromMultiSearchRequest((MultiSearchRequest)requestObj);
		}
		
		if(requestObj instanceof MultiSearchTemplateRequest) {
			return getParamsFromMultiSearchTemplateRequest((MultiSearchTemplateRequest)requestObj);
		}
		
		if(requestObj instanceof PutStoredScriptRequest) {
			return getParamsFromPutStoredScriptRequest((PutStoredScriptRequest)requestObj);
		}
		
		if(requestObj instanceof RankEvalRequest) {
			return getParamsFromRankEvalRequest((RankEvalRequest)requestObj);
		}
		
		if(requestObj instanceof ReindexRequest) {
			return getParamsFromReindexRequest((ReindexRequest)requestObj);
		}
		
		if(requestObj instanceof SearchScrollRequest) {
			return getParamsFromSearchScrollRequest((SearchScrollRequest)requestObj);
		}
		
		if(requestObj instanceof SearchTemplateRequest) {
			return getParamsFromSearchTemplateRequest((SearchTemplateRequest)requestObj);
		}
		
		if(requestObj instanceof TermVectorsRequest) {
			return getParamsFromTermVectorsRequest((TermVectorsRequest)requestObj);
		}
		
		if(requestObj instanceof UpdateByQueryRequest) {
			return getParamsFromUpdateByQueryRequest((UpdateByQueryRequest)requestObj);
		}
		
		
		return null;
	}
	
	private static String getCollectionFromArray(String[] array) {
		StringBuffer sb = new StringBuffer();
		int size = array.length;
		for(int i=0;i<size;i++) {
			sb.append(array[i]);
			if(i < size - 1) {
				sb.append(',');
			}
		}
		return sb.toString();
	}
	
	private static DatastoreParameters getParams(String collection, String operation, TransportAddress address) {
		
		if(address != null) {
			return DatastoreParameters.product("OpenSearch").collection(collection).operation(operation).instance(address.getAddress(), address.getPort()).build();
		} else {
			return DatastoreParameters.product("OpenSearch").collection(collection).operation(operation).build();
		}
		
	}
	
	private static DatastoreParameters getParamsFromCountRequest(CountRequest req) {
		return getParams(getCollectionFromArray(req.indices()), "Count", req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromDeleteRequest(DeleteRequest req) {
		return getParams(req.index(), "Delete", req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromGetRequest(GetRequest req, String type) {
		return getParams(req.index(),type,req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromDeleteByQueryRequest(DeleteByQueryRequest req) {
		return getParams(getCollectionFromArray(req.indices()), "DeleteByQuery", req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromDeleteStoredScriptRequest(DeleteStoredScriptRequest req) {
		return getParams(req.id(), "DeleteStoredScript", req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromGetSourceRequest(GetSourceRequest req) {
		return getParams(req.index(), "GetSource", null);
	}
	
	private static DatastoreParameters getParamsFromGetStoredScriptRequest(GetStoredScriptRequest req) {
		return getParams(req.id(), "GetStoredScript", req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromIndexRequest(IndexRequest req) {
		return getParams(req.index(), "Index", req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromMultiGetRequest(MultiGetRequest req) {
		List<Item> items = req.getItems();
		int size = items.size();
		String[] itemIndexes = new String[size];
		
		int i = 0;
		for(Item item : items) {
			itemIndexes[i] = item.index();
			i++;
		}
		return getParams(getCollectionFromArray(itemIndexes), "MultiGet", req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromMultiSearchRequest(MultiSearchRequest req) {
		List<SearchRequest> searchRequests = req.requests();
		int size = searchRequests.size();
		Set<String> allIndices = new HashSet<String>();
		
		for(int i=0;i<size;i++) {
			SearchRequest searchReq = searchRequests.get(i);
			String[] indices = searchReq.indices();
			allIndices.addAll(Arrays.asList(indices));
		}
		String[] collections = new String[allIndices.size()];
		allIndices.toArray(collections);
		
		return getParams(getCollectionFromArray(collections), "MultiSearch", req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromMultiSearchTemplateRequest(MultiSearchTemplateRequest req) {
		List<SearchTemplateRequest> requests = req.requests();
		int size = requests.size();
		Set<String> allIndices = new HashSet<String>();
		
		for(int i=0;i<size;i++) {
			SearchTemplateRequest tRequest = requests.get(i);
			SearchRequest searchReq = tRequest.getRequest();
			String[] indices = searchReq.indices();
			allIndices.addAll(Arrays.asList(indices));
		}
		String[] collections = new String[allIndices.size()];
		allIndices.toArray(collections);
		
		return getParams(getCollectionFromArray(collections), "MultiSearchTemplate", req.remoteAddress());
	}

	private static DatastoreParameters getParamsFromRankEvalRequest(RankEvalRequest req) {
		return getParams(getCollectionFromArray(req.indices()), "RankEvalRequest", req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromReindexRequest(ReindexRequest req) {
		IndexRequest indexReq = req.getDestination();
		
		return getParams(indexReq.index(), "ReIndex", req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromSearchScrollRequest(SearchScrollRequest req) {
		return getParams(req.scrollId(), "SearchScroll", req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromSearchRequest(SearchRequest req) {
		return getParams(getCollectionFromArray(req.indices()), "Search", req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromSearchTemplateRequest(SearchTemplateRequest req) {
		SearchRequest searchRequest = req.getRequest();
		return getParams(getCollectionFromArray(searchRequest.indices()), "SearchTemplate", req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromUpdateByQueryRequest(UpdateByQueryRequest req) {
		return getParams(getCollectionFromArray(req.indices()), "UpdateByQuery", req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromPutStoredScriptRequest(PutStoredScriptRequest req) {
		return getParams(req.id(), "PutStoredScript", req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromTermVectorsRequest(TermVectorsRequest req) {
		return getParams(req.getIndex(), "TermVectors", null);
	}
	
	private static DatastoreParameters getParamsFromUpdateRequest(UpdateRequest req) {
		return getParams(req.id(), "Update", req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromDeleteIndexRequest(DeleteIndexRequest req) {
		return getParams(getCollectionFromArray(req.indices()), "DeleteIndex", req.remoteAddress());
	}

	private static DatastoreParameters getParamsFromCreateIndexRequest(CreateIndexRequest req) {
		return getParams(req.index(), "CreateIndex", null);
	}

	private static DatastoreParameters getParamsFromCreateDataStreamRequest(CreateDataStreamRequest req) {
		return getParams(req.getName(), "CreateDataStream", null);
	}

	private static DatastoreParameters getParamsFromDeleteDataStreamRequest(DeleteDataStreamRequest req) {
		return getParams(req.getName(), "DeleteDataStream", null);
	}
	
	private static DatastoreParameters getParamsFromGetDataStreamRequest(GetDataStreamRequest req) {
		return getParams(req.getName(), "GetDataStream", null);
	}

	private static DatastoreParameters getParamsFromDataStreamsStatsRequest(DataStreamsStatsRequest req) {
		return getParams(getCollectionFromArray(req.indices()), "DataStreamsStats", null);
	}

	private static DatastoreParameters getParamsFromPutMappingRequest(PutMappingRequest req) {
		return getParams(getCollectionFromArray(req.indices()), "PutMappings", null);
	}

	private static DatastoreParameters getParamsFromGetMappingsRequest(GetMappingsRequest req) {
		return getParams(getCollectionFromArray(req.indices()), "GetMappings", null);
	}

	private static DatastoreParameters getParamsFromGetFieldMappingsRequest(GetFieldMappingsRequest req) {
		return getParams(getCollectionFromArray(req.indices()), "GetFieldMappings", null);
	}

	private static DatastoreParameters getParamsFromIndicesAliasesRequest(IndicesAliasesRequest req) {
		return getParams(req.origin(), "IndicesAliases", req.remoteAddress());
	}

	private static DatastoreParameters getParamsFromOpenIndexRequest(OpenIndexRequest req) {
		return getParams(getCollectionFromArray(req.indices()), "OpenIndex", req.remoteAddress());
	}

	private static DatastoreParameters getParamsFromCloseIndexRequest(CloseIndexRequest req) {
		return getParams(getCollectionFromArray(req.indices()), "CloseIndex", null);
	}

	private static DatastoreParameters getParamsFromGetAliasesRequest(GetAliasesRequest req) {
		return getParams(getCollectionFromArray(req.indices()), "GetAliases", req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromRefreshRequest(RefreshRequest req) {
		return getParams(getCollectionFromArray(req.indices()), "Refresh", req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromFlushRequest(FlushRequest req) {
		return getParams(getCollectionFromArray(req.indices()), "Flush", req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromGetSettingsRequest(GetSettingsRequest req) {
		return getParams(getCollectionFromArray(req.indices()), "GetSettings", req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromGetIndexRequest(GetIndexRequest req) {
		return getParams(getCollectionFromArray(req.indices()), "GetIndex", null);
	}

	private static DatastoreParameters getParamsFromForceMergeRequest(ForceMergeRequest req) {
		return getParams(getCollectionFromArray(req.indices()), "ForceMerge", req.remoteAddress());
	}

	private static DatastoreParameters getParamsFromClearIndicesCacheRequest(ClearIndicesCacheRequest req) {
		return getParams(getCollectionFromArray(req.indices()), "ClearIndicesCache", req.remoteAddress());
	}

	private static DatastoreParameters getParamsFromResizeRequest(ResizeRequest req) {
		return getParams(req.getSourceIndex() + "->" + req.getTargetIndex(), "Resize", null);
	}
	
	private static DatastoreParameters getParamsFromResizeRequest(org.opensearch.action.admin.indices.shrink.ResizeRequest req) {
		return getParams(req.getSourceIndex(), "Resize", req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromRolloverRequest(RolloverRequest req) {
		return getParams(req.getNewIndexName(), "Rollover", null);
	}
	
	private static DatastoreParameters getParamsFromUpdateSettingsRequest(UpdateSettingsRequest req) {
		return getParams(req.getDescription(), "UpdateSettings", req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromPutIndexTemplateRequest(PutIndexTemplateRequest req) {
		return getParams(req.getDescription(), "PutIndexTemplate", req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromPutComposableIndexTemplateRequest(PutComposableIndexTemplateRequest req) {
		return getParams(req.name(), "PutComposableIndexTemplate", null);
	}
	
	private static DatastoreParameters getParamsFromSimulateIndexTemplateRequest(SimulateIndexTemplateRequest req) {
		return getParams(req.indexName(), "SimulateIndexTemplate", null);
	}
	
	private static DatastoreParameters getParamsFromValidateQueryRequest(ValidateQueryRequest req) {
		return getParams(getCollectionFromArray(req.indices()), "ValidateQuery", null);
	}
	
	private static DatastoreParameters getParamsFromGetComposableIndexTemplateRequest(GetComposableIndexTemplateRequest req) {
		return getParams(req.name(), "GetComposableIndexTemplate", null);
	}
	
	private static DatastoreParameters getParamsFromGetIndexTemplatesRequest(GetIndexTemplatesRequest req) {
		List<String> names = req.names();
		String[] namesArray = new String[names.size()];
		names.toArray(namesArray);
		return getParams(getCollectionFromArray(namesArray), "GetIndexTemplates", null);
	}
	
	private static DatastoreParameters getParamsFromIndexTemplatesExistRequest(IndexTemplatesExistRequest req) {
		List<String> names = req.names();
		String[] namesArray = new String[names.size()];
		names.toArray(namesArray);
		return getParams(getCollectionFromArray(namesArray), "IndexTemplatesExist", null);
	}
	
	private static DatastoreParameters getParamsFromComposableIndexTemplateExistRequest(ComposableIndexTemplateExistRequest req) {
		return getParams(req.name(), "ComposableIndexTemplateExist", null);
	}
	
	private static DatastoreParameters getParamsFromAnalyzeRequest(AnalyzeRequest req) {
		return getParams(req.index(), "Analyze", null);
	}
	
	private static DatastoreParameters getParamsFromDeleteIndexTemplateRequest(DeleteIndexTemplateRequest req) {
		return getParams(req.name(), "DeleteIndexTemplate", req.remoteAddress());
	}
	
	private static DatastoreParameters getParamsFromDeleteComposableIndexTemplateRequest(DeleteComposableIndexTemplateRequest req) {
		return getParams(req.getName(), "DeleteIndexTemplate", null);
	}
	
	private static DatastoreParameters getParamsFromDeleteAliasRequest(DeleteAliasRequest req) {
		return getParams(req.getAlias()+"-"+req.getIndex(), "DeleteAlias", null);
	}
	
	
}
