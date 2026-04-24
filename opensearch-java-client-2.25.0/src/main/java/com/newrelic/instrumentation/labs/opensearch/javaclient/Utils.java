package com.newrelic.instrumentation.labs.opensearch.javaclient;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.opensearch.client.json.JsonpMapper;
import org.opensearch.client.json.NdJsonpSerializable;
import org.opensearch.client.opensearch._types.query_dsl.Query;

import com.newrelic.api.agent.DatastoreParameters;

import jakarta.json.stream.JsonGenerator;

public class Utils {

	public static <RequestT> DatastoreParameters getParams(Object request, Map<String, String> collectionAttributes, Map<String,String> params, Query query) {
		String operation = getOperationFromRequest(request);
		StringBuffer sb = new StringBuffer();
		if(params == null) {
			params = new HashMap<String, String>();
		}
		for(String key : collectionAttributes.keySet()) {
			if(key.equals("id")) {
				params.put("id", collectionAttributes.get("id"));
			} else {
				sb.append(key + ":" + collectionAttributes.get(key) + " ");
			}
		}
		if(query != null) {
			Object value = query._get();
			if(value != null) {
				params.put("Query-JSON", value.toString());
			}
		}
		String collection = sb.toString();
		QueryHolder holder = new QueryHolder(operation, collection, params);
		OpenSearchQueryConverter converter = new OpenSearchQueryConverter();
		
		return DatastoreParameters.product("ElasticSearch").collection(collection).operation(operation).noInstance().noDatabaseName().slowQuery(holder, converter).build();
	}
	
	public static String getObjectString(Object obj) {
		
		if(obj instanceof String) {
			return (String)obj;
		}
		if(obj instanceof List) {
			List<?> list = (List<?>)obj;
			int size = list.size();
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<size;i++) {
				sb.append(list.get(i).toString());
				if(i < size-1) {
					sb.append(',');
				}
			}
			return sb.toString();
		}
		if(obj instanceof Query) {
			return getCollectionFromQuery((Query)obj, null);
		}
		
		return null;
	}
	
	public static String getCollectionFromList(List<String> list) {
		int size = list.size();
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<size;i++) {
			sb.append(list.get(i));
			if(i < size-1) {
				sb.append(',');
			}
		}
		return sb.toString();
	}
	
	public static <TDocument> String getCollectionFromQuery(Query query, Class<TDocument> tDocumentClass) {
		if(query != null) {
			return tDocumentClass != null ? "QueryType-" + query._kind().name() + "-" + tDocumentClass.getSimpleName() : "QueryType-" + query._kind().name();
		}
		return null;
	}
	
	public static void recordRequest(Map<String, Object> attributes, Object payload, String requestURL, String method) {
		if(attributes != null) {
			if(payload != null) {
				attributes.put("Request-Payload", payload.toString());
			}
			if(requestURL != null) {
				attributes.put("RequestUrl", requestURL);
			}
			if(method != null) {
				attributes.put("Request-Method", method);
			}
			
		}
	}
	
	public static String getOperationFromRequest(Object obj) {
		if(obj instanceof String) {
			return ((String)obj).replace("Request", "");
		}
		return convertFirstCharToLowerCase(obj.getClass().getSimpleName().replace("Request", ""));
	}
	
	public static String convertFirstCharToLowerCase(String s) {
		if(s == null || s.isEmpty()) {
			return s;
		}
		return s.substring(0, 1).toLowerCase() + s.substring(1);
	}
	
	public static Object getRequestBody(Object request, JsonpMapper mapper) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		if (request instanceof NdJsonpSerializable) {
			writeNdJson((NdJsonpSerializable) request, baos, mapper);
		} else {
            JsonGenerator generator = mapper.jsonProvider().createGenerator(baos);
            mapper.serialize(request, generator);
            generator.close();
		}
		
		return baos;
	}

    private static void writeNdJson(NdJsonpSerializable value, ByteArrayOutputStream baos, JsonpMapper mapper) {
        Iterator<?> values = value._serializables();
        while(values.hasNext()) {
            Object item = values.next();
            if (item instanceof NdJsonpSerializable && item != value) { // do not recurse on the item itself
                writeNdJson((NdJsonpSerializable) item, baos, mapper);
            } else {
                JsonGenerator generator = mapper.jsonProvider().createGenerator(baos);
                mapper.serialize(item, generator);
                generator.close();
                baos.write('\n');
            }
        }
    }

}
