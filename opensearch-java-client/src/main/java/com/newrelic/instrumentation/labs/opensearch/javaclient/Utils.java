package com.newrelic.instrumentation.labs.opensearch.javaclient;

import java.util.List;

import org.opensearch.client.opensearch._types.query_dsl.Query;
import org.opensearch.client.opensearch.core.CountRequest;
import org.opensearch.client.opensearch.core.CreateRequest;
import org.opensearch.client.opensearch.core.DeleteByQueryRequest;
import org.opensearch.client.opensearch.core.DeleteRequest;
import org.opensearch.client.opensearch.core.ExistsRequest;
import org.opensearch.client.opensearch.core.ExistsSourceRequest;
import org.opensearch.client.opensearch.core.GetRequest;
import org.opensearch.client.opensearch.core.GetScriptRequest;
import org.opensearch.client.opensearch.core.GetSourceRequest;
import org.opensearch.client.opensearch.core.IndexRequest;
import org.opensearch.client.opensearch.core.InfoRequest;
import org.opensearch.client.opensearch.core.MsearchRequest;
import org.opensearch.client.opensearch.core.MtermvectorsRequest;
import org.opensearch.client.opensearch.core.PutScriptRequest;
import org.opensearch.client.opensearch.core.RankEvalRequest;
import org.opensearch.client.opensearch.core.ReindexRequest;
import org.opensearch.client.opensearch.core.RenderSearchTemplateRequest;
import org.opensearch.client.opensearch.core.ScrollRequest;
import org.opensearch.client.opensearch.core.SearchRequest;
import org.opensearch.client.opensearch.core.SearchShardsRequest;
import org.opensearch.client.opensearch.core.SearchTemplateRequest;
import org.opensearch.client.opensearch.core.TermsEnumRequest;
import org.opensearch.client.opensearch.core.UpdateByQueryRequest;
import org.opensearch.client.opensearch.core.UpdateRequest;
import org.opensearch.client.opensearch.core.reindex.Destination;
import org.opensearch.client.opensearch.indices.AddBlockRequest;
import org.opensearch.client.opensearch.indices.AnalyzeRequest;
import org.opensearch.client.opensearch.indices.CreateIndexRequest;
import org.opensearch.client.opensearch.indices.DeleteIndexRequest;

import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;

public class Utils {

	public static DatastoreParameters getParamsFromGet(GetRequest request, String type) {
		return getParamsFromGetRequest(request, type);
	}
	
	public static DatastoreParameters getParamsFromIndicesRequest(Object requestObj) {
		
		if(requestObj instanceof AddBlockRequest) {
			return getParamsFromAddBlockRequest((AddBlockRequest)requestObj);
		}
		
		if(requestObj instanceof AnalyzeRequest) {
			return getParamsFromAnalyzeRequest((AnalyzeRequest)requestObj);
		}
		
		if(requestObj instanceof CreateIndexRequest) {
			return getParamsFromCreateIndexRequest((CreateIndexRequest)requestObj);
		}
		
		if(requestObj instanceof DeleteIndexRequest) {
			return getParamsFromDeleteIndexRequest((DeleteIndexRequest)requestObj);
		}
		
		if(requestObj instanceof org.opensearch.client.opensearch.indices.ExistsRequest) {
			return getParamsFromExistsRequest((org.opensearch.client.opensearch.indices.ExistsRequest)requestObj);
		}
		
		return null;
	}
	
	public static DatastoreParameters getParamsFromRequest(Object requestObj) {
		Class<?> clazz = requestObj.getClass();
		Package pkg = clazz.getPackage();
		String pkgName = pkg.getName();
		
		if(pkgName.startsWith("org.opensearch.client.opensearch.core")) {
			return getParamsFromCoreRequest(requestObj);
		}
		
		if(pkgName.startsWith("org.opensearch.client.opensearch.indices")) {
			return getParamsFromIndicesRequest(requestObj);
		}
		
		
		return null;
	}

	public static DatastoreParameters getParamsFromCoreRequest(Object requestObj) {
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Request-Type",requestObj.getClass().getSimpleName());
		if(requestObj instanceof GetRequest) {
			return getParamsFromGetRequest((GetRequest)requestObj, "Get");
		}
		
		if(requestObj instanceof CreateRequest) {
			return getParamsFromCreateRequest((CreateRequest<?>)requestObj);
		}

		if(requestObj instanceof SearchRequest) {
			return getParamsFromSearchRequest((SearchRequest)requestObj);
		}

		if(requestObj instanceof UpdateRequest) {
			return getParamsFromUpdateRequest((UpdateRequest<?,?>)requestObj);
		}

		if(requestObj instanceof DeleteRequest) {
			return getParamsFromDeleteRequest((DeleteRequest)requestObj);
		}

		if(requestObj instanceof CountRequest) {
			return getParamsFromCountRequest((CountRequest)requestObj);
		}

		if(requestObj instanceof IndexRequest) {
			return getParamsFromIndexRequest((IndexRequest<?>)requestObj);
		}
		
		if(requestObj instanceof ExistsRequest) {
			return getParamsFromExistsRequest((ExistsRequest)requestObj);
		}

		if(requestObj instanceof DeleteByQueryRequest) {
			return getParamsFromDeleteByQueryRequest((DeleteByQueryRequest)requestObj);
		}
		
		if(requestObj instanceof MsearchRequest) {
			return getParamsFromMsearchRequest((MsearchRequest)requestObj);
		}

		if(requestObj instanceof GetSourceRequest) {
			return getParamsFromGetSourceRequest((GetSourceRequest)requestObj);
		}

		if(requestObj instanceof RankEvalRequest) {
			return getParamsFromRankEvalRequest((RankEvalRequest)requestObj);
		}
		
		if(requestObj instanceof SearchShardsRequest) {
			return getParamsFromSearchShardsRequest((SearchShardsRequest)requestObj);
		}

		if(requestObj instanceof ReindexRequest) {
			return getParamsFromReindexRequest((ReindexRequest)requestObj);
		}
		
		if(requestObj instanceof TermsEnumRequest) {
			return getParamsFromTermsEnumRequest((TermsEnumRequest)requestObj);
		}

		if(requestObj instanceof SearchTemplateRequest) {
			return getParamsFromSearchTemplateRequest((SearchTemplateRequest)requestObj);
		}

		if(requestObj instanceof UpdateByQueryRequest) {
			return getParamsFromUpdateByQueryRequest((UpdateByQueryRequest)requestObj);
		}

		if(requestObj instanceof ExistsSourceRequest) {
			return getParamsFromExistsSourceRequest((ExistsSourceRequest)requestObj);
		}
		
		if(requestObj instanceof GetScriptRequest) {
			return getParamsFromGetScriptRequest((GetScriptRequest)requestObj);
		}
		
		if(requestObj instanceof MtermvectorsRequest) {
			return getParamsFroMtermvectorsRequest((MtermvectorsRequest)requestObj);
		}
		
		if(requestObj instanceof PutScriptRequest) {
			return getParamsFromPutScriptRequest((PutScriptRequest)requestObj);
		}
		
		if(requestObj instanceof RenderSearchTemplateRequest) {
			return getParamsFromRenderSearchTemplateRequest((RenderSearchTemplateRequest)requestObj);
		}
		
		if(requestObj instanceof ScrollRequest) {
			return getParamsFromScrollRequest((ScrollRequest)requestObj);
		}
		if(requestObj instanceof InfoRequest) {
			return getParamsFromInfoRequest((InfoRequest)requestObj);
		}
		
		
		return null;
	}
	
	private static String getCollectionFromList(List<String> list) {
		String[] array = new String[list.size()];
		list.toArray(array);
		return getCollectionFromArray(array);
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
	
	private static DatastoreParameters getParams(String collection, String operation) {
		return DatastoreParameters.product("OpenSearch").collection(collection).operation(operation).build();

	}

	private static DatastoreParameters getParamsFromExistsRequest(ExistsRequest req) {		
		return getParams(req.index(), getOpName(req));
	}
	
	private static DatastoreParameters getParamsFromExistsRequest(org.opensearch.client.opensearch.indices.ExistsRequest req) {		
		return getParams(getCollectionFromList(req.index()), getOpName(req));
	}
	
	private static DatastoreParameters getParamsFromExistsSourceRequest(ExistsSourceRequest req) {
		return getParams(req.index(), getOpName(req));
	}
	
	private static DatastoreParameters getParamsFromCreateRequest(CreateRequest<?> req) {
		return getParams(req.index(), getOpName(req));
	}
	
	private static DatastoreParameters getParamsFromCountRequest(CountRequest req) {
		
		return getParams(getCollectionFromList(req.index()), getOpName(req));
	}

	private static DatastoreParameters getParamsFromDeleteRequest(DeleteRequest req) {
		return getParams(req.index(), getOpName(req));
	}

	private static DatastoreParameters getParamsFromGetRequest(GetRequest req, String type) {
		return getParams(req.index(),type);
	}

	private static DatastoreParameters getParamsFromDeleteByQueryRequest(DeleteByQueryRequest req) {
		return getParams(getCollectionFromList(req.index()), getOpName(req));
	}

	private static DatastoreParameters getParamsFromGetSourceRequest(GetSourceRequest req) {
		return getParams(req.index(), getOpName(req));
	}

	private static  DatastoreParameters getParamsFromIndexRequest(IndexRequest<?> req) {
		return getParams(req.index(), getOpName(req));
	}


	private static DatastoreParameters getParamsFromRankEvalRequest(RankEvalRequest req) {
		return getParams(getCollectionFromList(req.index()), getOpName(req));
	}

	private static DatastoreParameters getParamsFromReindexRequest(ReindexRequest req) {
		Destination dest = req.dest();
		String collection = dest != null ? dest.index() : "Unknown";
		return getParams(collection, getOpName(req));
	}

	private static DatastoreParameters getParamsFromSearchRequest(SearchRequest req) {
		List<String> indexes = req.index();
		StringBuffer sb = new StringBuffer();
		boolean empty = true;
		if(indexes != null && !indexes.isEmpty()) {
			sb.append("Index="+ getCollectionFromList(indexes));
			empty = false;
		}
		Query query = req.query();
		if(query != null) {
			if(!empty) {
				sb.append(',');
			}
			sb.append("Query-Type="+query._kind().name());
			empty = false;
		}
		String collection = sb.toString();
		return getParams(collection, getOpName(req));
	}

	private static DatastoreParameters getParamsFromSearchTemplateRequest(SearchTemplateRequest req) {
		return getParams(getCollectionFromList(req.index()), getOpName(req));
	}

	private static DatastoreParameters getParamsFromUpdateByQueryRequest(UpdateByQueryRequest req) {
		return getParams(getCollectionFromList(req.index()), getOpName(req));
	}

	private static DatastoreParameters getParamsFromUpdateRequest(UpdateRequest<?,?> req) {
		return getParams(req.index(), getOpName(req));
	}

	private static DatastoreParameters getParamsFromGetScriptRequest(GetScriptRequest req) {
		return getParams(req.id(), getOpName(req));
	}
	
	private static DatastoreParameters getParamsFromMsearchRequest(MsearchRequest req) {
		return getParams(getCollectionFromList(req.index()), getOpName(req));
	}
	
	private static DatastoreParameters getParamsFroMtermvectorsRequest(MtermvectorsRequest req) {
		return getParams(req.index(), getOpName(req));
	}
	
	private static DatastoreParameters getParamsFromPutScriptRequest(PutScriptRequest req) {
		return getParams(req.id(), getOpName(req));
	}

	private static DatastoreParameters getParamsFromRenderSearchTemplateRequest(RenderSearchTemplateRequest req) {
		return getParams(req.id(), getOpName(req));
	}

	private static DatastoreParameters getParamsFromScrollRequest(ScrollRequest req) {
		return getParams(req.scrollId(), getOpName(req));
	}

	private static DatastoreParameters getParamsFromSearchShardsRequest(SearchShardsRequest req) {
		return getParams(getCollectionFromList(req.index()), getOpName(req));
	}

	private static DatastoreParameters getParamsFromTermsEnumRequest(TermsEnumRequest req) {
		return getParams(req.index(), getOpName(req));
	}

	private static DatastoreParameters getParamsFromAddBlockRequest(AddBlockRequest req) {
		return getParams(req.index(), getOpName(req));
	}
	
	private static DatastoreParameters getParamsFromAnalyzeRequest(AnalyzeRequest req) {
		return getParams(req.index(), getOpName(req));
	}
	
	private static DatastoreParameters getParamsFromCreateIndexRequest(CreateIndexRequest req) {
		return getParams(req.index(), getOpName(req));
	}
	
	private static DatastoreParameters getParamsFromDeleteIndexRequest(DeleteIndexRequest req) {
		return getParams(getCollectionFromList(req.index()), getOpName(req));
	}

	private static DatastoreParameters getParamsFromInfoRequest(InfoRequest req) {
		return getParams("", getOpName(req));
	}

	private static String getOpName(Object obj) {
		return obj.getClass().getSimpleName().replace("Request", "");
	}
}
