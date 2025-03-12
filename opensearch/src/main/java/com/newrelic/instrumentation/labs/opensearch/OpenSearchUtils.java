package com.newrelic.instrumentation.labs.opensearch;

import java.util.List;

import org.opensearch.action.ActionRequest;
import org.opensearch.action.delete.DeleteRequest;
import org.opensearch.action.get.GetRequest;
import org.opensearch.action.get.MultiGetRequest;
import org.opensearch.action.get.MultiGetRequest.Item;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.action.search.MultiSearchRequest;
import org.opensearch.action.search.SearchRequest;
import org.opensearch.action.support.replication.ReplicationRequest;
import org.opensearch.action.support.single.instance.InstanceShardOperationRequest;
import org.opensearch.action.support.single.shard.SingleShardRequest;
import org.opensearch.action.termvectors.MultiTermVectorsRequest;
import org.opensearch.action.termvectors.TermVectorsRequest;
import org.opensearch.action.update.UpdateRequest;

public class OpenSearchUtils {

	public static QueryDetails getQueryDetails(ActionRequest request) {
		String operation = getOperation(request);
		String query = getQuery(operation, request);
		String collection = getCollection(request);
		return new QueryDetails(query, collection, operation);
	}

	public static String getOperation(ActionRequest request) {
		String type = "Unknown";
		String classname = request.getClass().getSimpleName();
		
		if(classname.endsWith("Request")) {
			int index = classname.indexOf("Request");
			if(index > -1) {
				type = classname.substring(0, index);
			}
		}
		return type;
	}
	
	public static String getQuery(String operation, ActionRequest request) {
		String result = "";
		
		if(operation.equalsIgnoreCase("get")) {
			GetRequest getRequest = (GetRequest)request;
			String index = getRequest.index();
			String id = getRequest.id();
			
			return  "get "+index+"/"+id;
		}
		
		if(operation.equalsIgnoreCase("update")) {
			UpdateRequest updateRequest = (UpdateRequest)request;
			String index = updateRequest.index();
			String id = updateRequest.id();
			return  "update "+index+"/"+id;
		}
		
		if(operation.equalsIgnoreCase("search")) {
			SearchRequest searchRequest = (SearchRequest)request;
			String tmp = getSearchCollection(searchRequest);
			return "search "+tmp;
		}
		
		if(operation.equalsIgnoreCase("index")) {
			IndexRequest indexRequest = (IndexRequest)request;
			String index = indexRequest.index();
			String id = indexRequest.id();
			
			return "index " +index+"/"+id;
		}
		
		if(operation.equalsIgnoreCase("delete")) {
			DeleteRequest deleteRequest = (DeleteRequest)request;
			String index = deleteRequest.index();
			String id = deleteRequest.id();
			
			return "delete " +index+"/"+id;
		}
		
		if(operation.equalsIgnoreCase("multiget")) {
			MultiGetRequest multiGetRequest = (MultiGetRequest)request;
			List<Item> items = multiGetRequest.getItems();
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<items.size();i++) {
				Item item = items.get(i);
				String index = item.index();
				String id = item.id();
				sb.append(index+"/"+id);
				if(i < items.size()-1) {
					sb.append(',');
				}
			}
			return "multiget " + sb.toString();
		}
		
		if(operation.equalsIgnoreCase("multisearch")) {
			MultiSearchRequest multiSearchRequest = (MultiSearchRequest)request;
			List<SearchRequest> searches = multiSearchRequest.requests(); 
			int size = searches.size();
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<size;i++) {
				SearchRequest searchRequest = searches.get(i);
				String s = getSearchCollection(searchRequest);
				sb.append(s);
				if(i < size -1) {
					sb.append(',');
				}
			}
			return "multisearch "+sb.toString();
		}
		
		return result;
	}
	
	private static String getSearchCollection(SearchRequest request) {
		String[] indices = request.indices();
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<indices.length;i++) {
			sb.append(indices[i]);
			if(i < indices.length-1) {
				sb.append(',');
			}
		}
		if(sb.length() != 0) {
			sb.append('-');
		}
		return sb.toString();
	}

	public static String getCollection(ActionRequest request) {
		String result = "Unknown";
		
		if(request instanceof SingleShardRequest) {
			return ((SingleShardRequest<?>)request).index();
		}
		
		if(request instanceof ReplicationRequest) {
			return ((ReplicationRequest<?>)request).index();
		}
		
		if(request instanceof InstanceShardOperationRequest) {
			return ((InstanceShardOperationRequest<?>)request).index();
		}
		
		if(request instanceof SearchRequest) {
			return getSearchCollection((SearchRequest)request);
		}
		
		if(request instanceof MultiGetRequest) {
			MultiGetRequest multiGet = (MultiGetRequest)request;
			List<Item> items = multiGet.getItems();
			int count = items.size();
			int i = 0;
			StringBuffer sb = new StringBuffer();
			for(Item item : items) {
				String index = item.index();
				sb.append(index);
				if(i < count - 1) {
					sb.append(',');
				}
				i++;
			}
			return sb.toString();
		}
		
		if(request instanceof MultiSearchRequest) {
			MultiSearchRequest multiSearch = (MultiSearchRequest)request;
			List<SearchRequest> searches = multiSearch.requests();
			int size = searches.size();
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<size;i++) {
				SearchRequest searchRequest = searches.get(i);
				String s = getSearchCollection(searchRequest);
				sb.append(s);
				if(i < size -1) {
					sb.append(',');
				}
				i++;
			}
			result = sb.toString();
		}
		
		if(request instanceof MultiTermVectorsRequest) {
			MultiTermVectorsRequest tvMultiRequest = (MultiTermVectorsRequest)request;
			List<TermVectorsRequest> tvRequests = tvMultiRequest.getRequests();
			int size = tvRequests.size();
			int i = 0;
			StringBuffer sb = new StringBuffer();
			for(TermVectorsRequest tvRequest : tvRequests) {
				sb.append(tvRequest.index());
				if(i < size - 1) {
					sb.append(',');
				}
				i++;
			}
			return sb.toString();
		}
		
		return result;
	}

}
