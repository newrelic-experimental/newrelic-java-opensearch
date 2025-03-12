package com.newrelic.instrumentation.labs.opensearch.highlevelrest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.opensearch.action.admin.cluster.storedscripts.DeleteStoredScriptRequest;
import org.opensearch.action.admin.cluster.storedscripts.GetStoredScriptRequest;
import org.opensearch.action.admin.cluster.storedscripts.PutStoredScriptRequest;
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
import org.opensearch.common.transport.TransportAddress;
import org.opensearch.index.rankeval.RankEvalRequest;
import org.opensearch.index.reindex.DeleteByQueryRequest;
import org.opensearch.index.reindex.ReindexRequest;
import org.opensearch.index.reindex.UpdateByQueryRequest;
import org.opensearch.script.mustache.MultiSearchTemplateRequest;
import org.opensearch.script.mustache.SearchTemplateRequest;

import com.newrelic.api.agent.DatastoreParameters;

public class Utils {

	public static DatastoreParameters getParamsFromGet(GetRequest request, String type) {
		return getParamsFromGetRequest(request, type);
	}
	
	public static DatastoreParameters getParamsFromRequest(Object requestObj) {
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
		return getParams(req.index(), "Update", req.remoteAddress());
	}
	
	
}
