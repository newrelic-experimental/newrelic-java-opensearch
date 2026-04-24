package com.newrelic.instrumentation.labs.opensearch;

import java.util.List;
import java.util.Map;

import org.opensearch.action.ActionRequest;
import org.opensearch.action.delete.DeleteRequest;
import org.opensearch.action.get.GetRequest;
import org.opensearch.action.get.MultiGetRequest;
import org.opensearch.action.get.MultiGetRequest.Item;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.action.search.MultiSearchRequest;
import org.opensearch.action.search.SearchRequest;
import org.opensearch.action.update.UpdateRequest;
import org.opensearch.core.common.transport.TransportAddress;
import org.opensearch.transport.TransportChannel;

public class Utils {

	public static void addAttribute(Map<String, Object> attributes, String key, Object value) {
		if(key != null && !key.isEmpty() && value != null) {
			attributes.put(key, value);
		}
	}
	
	public static void addTransportAddress(Map<String, Object> attributes, TransportAddress address) {
		addAttribute(attributes, "Transport-Address", address.getAddress());
	}
	
	public static void addTransportChannel(Map<String,Object> attributes, TransportChannel channel) {
		
		addAttribute(attributes, "TransportChannel-ChannelType", channel.getChannelType());
		addAttribute(attributes, "TransportChannel-ProfileName", channel.getProfileName());
		addAttribute(attributes, "TransportChannel-ProfileName", channel.getVersion());

	}
	
	public static void addActionRequest(Map<String,Object> attributes, ActionRequest request) {
		addAttribute(attributes, "Request-Description", request.getDescription());
		addTransportAddress(attributes, request.remoteAddress());
		if(request instanceof GetRequest) {
			GetRequest getRequest = (GetRequest)request;
			addAttribute(attributes, "GetRequest-Id", getRequest.id());
			addAttribute(attributes, "GetRequest-Index", getRequest.index());
			addAttribute(attributes, "GetRequest-Routing", getRequest.routing());
		} else if(request instanceof UpdateRequest) {
			UpdateRequest updateRequest = (UpdateRequest)request;
			addAttribute(attributes, "UpdateRequest-Id", updateRequest.id());
			addAttribute(attributes, "UpdateRequest-ConcreateIndex", updateRequest.concreteIndex());
			addAttribute(attributes, "UpdateRequest-ShardId", updateRequest.getShardId());
			addAttribute(attributes, "UpdateRequest-Index", updateRequest.index());
			addAttribute(attributes, "UpdateRequest-Id", updateRequest.opType());
			addAttribute(attributes, "UpdateRequest-Routing", updateRequest.routing());
		} else if(request instanceof SearchRequest) {
			SearchRequest searchRequest = (SearchRequest)request;
			addAttribute(attributes, "SearchRequest-Routing", searchRequest.routing());
			addAttribute(attributes, "SearchRequest-SearchType", searchRequest.searchType());
		} else if(request instanceof IndexRequest) {
			IndexRequest indexRequest = (IndexRequest)request;
			addAttribute(attributes, "IndexRequest-ContentType", indexRequest.getContentType());
			addAttribute(attributes, "IndexRequest-Pipeline", indexRequest.getPipeline());
			addAttribute(attributes, "IndexRequest-Id", indexRequest.id());
			addAttribute(attributes, "IndexRequest-Index", indexRequest.index());
			addAttribute(attributes, "IndexRequest-OpType", indexRequest.opType());
			addAttribute(attributes, "IndexRequest-ShardId", indexRequest.shardId());
			addAttribute(attributes, "IndexRequest-Routing", indexRequest.routing());
		} else if(request instanceof DeleteRequest) {
			DeleteRequest deleteRequest = (DeleteRequest)request;
			addAttribute(attributes, "DeleteRequest-Id", deleteRequest.id());
			addAttribute(attributes, "DeleteRequest-Index", deleteRequest.index());
			addAttribute(attributes, "DeleteRequest-OpType", deleteRequest.opType());
			addAttribute(attributes, "DeleteRequest-Routing", deleteRequest.routing());
		} else if(request instanceof MultiGetRequest) {
			MultiGetRequest	multiGet = (MultiGetRequest)request;
			StringBuffer sb = new StringBuffer();
			List<Item> itemList = multiGet.getItems();
			int size = itemList.size();
			for(int i=0;i<size;i++) {
				sb.append(itemList.get(i));
				if(i < size - 1) {
					sb.append(',');
				}
			}
			if(sb.length() > 0) {
				addAttribute(attributes, "Items", sb.toString());
			}
		} else if(request instanceof MultiSearchRequest) {
			MultiSearchRequest multiSearch = (MultiSearchRequest)request;
			List<SearchRequest> requests = multiSearch.requests();
			int count = 1;
			for(SearchRequest searchRequest : requests) {
				addAttribute(attributes, "MultiSearchRequest-"+count+"-SearchType", searchRequest.searchType());
				String[] indices = searchRequest.indices();
				StringBuffer sb = new StringBuffer();
				for(int i=0;i<indices.length;i++) {
					sb.append(indices[i]);
					if(i < indices.length - 1) {
						sb.append(',');
					}
				}
				if(sb.length() > 0) {
					addAttribute(attributes, "MultiSearchRequest-"+count+"-Indices", sb.toString());
				}
			}
		}
	}
}
