package org.opensearch.client;

import org.opensearch.action.ActionListener;
import org.opensearch.action.admin.cluster.storedscripts.DeleteStoredScriptRequest;
import org.opensearch.action.admin.cluster.storedscripts.GetStoredScriptRequest;
import org.opensearch.action.admin.cluster.storedscripts.GetStoredScriptResponse;
import org.opensearch.action.admin.cluster.storedscripts.PutStoredScriptRequest;
import org.opensearch.action.delete.DeleteRequest;
import org.opensearch.action.delete.DeleteResponse;
import org.opensearch.action.get.GetRequest;
import org.opensearch.action.get.GetResponse;
import org.opensearch.action.get.MultiGetRequest;
import org.opensearch.action.get.MultiGetResponse;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.action.index.IndexResponse;
import org.opensearch.action.search.MultiSearchRequest;
import org.opensearch.action.search.MultiSearchResponse;
import org.opensearch.action.search.SearchRequest;
import org.opensearch.action.search.SearchResponse;
import org.opensearch.action.search.SearchScrollRequest;
import org.opensearch.action.update.UpdateRequest;
import org.opensearch.action.update.UpdateResponse;
import org.opensearch.action.support.master.AcknowledgedResponse;
import org.opensearch.client.core.CountRequest;
import org.opensearch.client.core.CountResponse;
import org.opensearch.client.core.GetSourceRequest;
import org.opensearch.client.core.GetSourceResponse;
import org.opensearch.client.core.MultiTermVectorsRequest;
import org.opensearch.client.core.MultiTermVectorsResponse;
import org.opensearch.client.core.TermVectorsRequest;
import org.opensearch.client.core.TermVectorsResponse;
import org.opensearch.client.tasks.TaskSubmissionResponse;
import org.opensearch.index.rankeval.RankEvalRequest;
import org.opensearch.index.rankeval.RankEvalResponse;
import org.opensearch.index.reindex.BulkByScrollResponse;
import org.opensearch.index.reindex.DeleteByQueryRequest;
import org.opensearch.index.reindex.ReindexRequest;
import org.opensearch.index.reindex.UpdateByQueryRequest;
import org.opensearch.script.mustache.MultiSearchTemplateRequest;
import org.opensearch.script.mustache.MultiSearchTemplateResponse;
import org.opensearch.script.mustache.SearchTemplateRequest;
import org.opensearch.script.mustache.SearchTemplateResponse;

import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.opensearch.highlevelrest.NRActionListener;
import com.newrelic.instrumentation.labs.opensearch.highlevelrest.Utils;

@Weave
public abstract class RestHighLevelClient {

	@Trace(leaf=true)
	public CountResponse count(CountRequest countRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(countRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
	}

	public Cancellable countAsync(CountRequest countRequest, RequestOptions options, ActionListener<CountResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromRequest(countRequest);
		listener = new NRActionListener<CountResponse>(listener, params);
		return Weaver.callOriginal();
	}

	@Trace(leaf=true)
	public DeleteResponse delete(DeleteRequest deleteRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(deleteRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
	}

	public Cancellable deleteAsync(DeleteRequest deleteRequest, RequestOptions options, ActionListener<DeleteResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromRequest(deleteRequest);
		listener = new NRActionListener<DeleteResponse>(listener, params);
		return Weaver.callOriginal();
	}

	@Trace(leaf=true)
	public BulkByScrollResponse deleteByQuery(DeleteByQueryRequest deleteByQueryRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(deleteByQueryRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
	}

	public Cancellable deleteByQueryAsync(DeleteByQueryRequest deleteByQueryRequest, RequestOptions options, ActionListener<BulkByScrollResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromRequest(deleteByQueryRequest);
		listener = new NRActionListener<BulkByScrollResponse>(listener, params);
		return Weaver.callOriginal();
	}

	@Trace(leaf=true)
	public AcknowledgedResponse deleteScript(DeleteStoredScriptRequest request, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(request);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
	}

	public Cancellable deleteScriptAsync(DeleteStoredScriptRequest request, RequestOptions options, ActionListener<AcknowledgedResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromRequest(request);
		listener = new NRActionListener<AcknowledgedResponse>(listener, params);
		return Weaver.callOriginal();
	}

	@Trace(leaf=true)
	public boolean exists(GetRequest getRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromGet(getRequest, "Exists");
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
	}

	public Cancellable existsAsync(GetRequest getRequest, RequestOptions options, ActionListener<Boolean> listener) {
		DatastoreParameters params = Utils.getParamsFromGet(getRequest, "Exists");
		listener = new NRActionListener<Boolean>(listener, params);
		return Weaver.callOriginal();
	}

	@Trace(leaf=true)
	public boolean existsSource(GetRequest getRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromGet(getRequest, "ExistsSource");
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
	}

	@Trace(leaf=true)
	public boolean existsSource(GetSourceRequest getSourceRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(getSourceRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
	}

	public Cancellable existsSourceAsync(GetRequest getRequest, RequestOptions options, ActionListener<Boolean> listener) {
		DatastoreParameters params = Utils.getParamsFromGet(getRequest, "ExistsSource");
		listener = new NRActionListener<Boolean>(listener, params);
		return Weaver.callOriginal();
	}

	public Cancellable existsSourceAsync(GetSourceRequest getSourceRequest, RequestOptions options, ActionListener<Boolean> listener) {
		DatastoreParameters params = Utils.getParamsFromRequest(getSourceRequest);
		listener = new NRActionListener<Boolean>(listener, params);
		return Weaver.callOriginal();
	}

	@Trace(leaf=true)
	public GetResponse get(GetRequest getRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromGet(getRequest, "Get");
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
	}

	public Cancellable getAsync(GetRequest getRequest, RequestOptions options, ActionListener<GetResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromGet(getRequest, "Get");
		listener = new NRActionListener<GetResponse>(listener, params);
		return Weaver.callOriginal();
	}

	@Trace(leaf=true)
	public GetStoredScriptResponse getScript(GetStoredScriptRequest request, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(request);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
	}

	public Cancellable getScriptAsync(GetStoredScriptRequest request, RequestOptions options, ActionListener<GetStoredScriptResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromRequest(request);
		listener = new NRActionListener<GetStoredScriptResponse>(listener, params);
		return Weaver.callOriginal();
	}

	@Trace(leaf=true)
	public GetSourceResponse getSource(GetSourceRequest getSourceRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(getSourceRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
	}

	public Cancellable getSourceAsync(GetSourceRequest getSourceRequest, RequestOptions options, ActionListener<GetSourceResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromRequest(getSourceRequest);
		listener = new NRActionListener<GetSourceResponse>(listener, params);
		return Weaver.callOriginal();
	}

	@Trace(leaf=true)
	public IndexResponse index(IndexRequest indexRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(indexRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
	}

	public Cancellable indexAsync(IndexRequest indexRequest, RequestOptions options, ActionListener<IndexResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromRequest(indexRequest);
		listener = new NRActionListener<IndexResponse>(listener, params);
		return Weaver.callOriginal();
	}

	@Trace(leaf=true)
	public MultiGetResponse mget(MultiGetRequest multiGetRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(multiGetRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
	}
	
	public Cancellable multiGetAsync(MultiGetRequest multiGetRequest, RequestOptions options, ActionListener<MultiGetResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromRequest(multiGetRequest);
		listener = new NRActionListener<MultiGetResponse>(listener, params);
		return Weaver.callOriginal();
	}
	
	public Cancellable mgetAsync(MultiGetRequest multiGetRequest, RequestOptions options, ActionListener<MultiGetResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromRequest(multiGetRequest);
		listener = new NRActionListener<MultiGetResponse>(listener, params);
		return Weaver.callOriginal();
	}

	@Trace(leaf=true)
	public MultiSearchResponse msearch(MultiSearchRequest multiSearchRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(multiSearchRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
	}
	
	public Cancellable msearchAsync(MultiSearchRequest searchRequest, RequestOptions options, ActionListener<MultiSearchResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromRequest(searchRequest);
		listener = new NRActionListener<MultiSearchResponse>(listener, params);
		return Weaver.callOriginal();
	}
	
	@Trace(leaf=true)
	public MultiSearchTemplateResponse msearchTemplate(MultiSearchTemplateRequest multiSearchTemplateRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(multiSearchTemplateRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
	}
	
	public Cancellable msearchTemplateAsync(MultiSearchTemplateRequest multiSearchTemplateRequest, RequestOptions options, ActionListener<MultiSearchTemplateResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromRequest(multiSearchTemplateRequest);
		listener = new NRActionListener<MultiSearchTemplateResponse>(listener, params);
		return Weaver.callOriginal();
	}
	
	@Trace(leaf=true)
	public MultiTermVectorsResponse mtermvectors(MultiTermVectorsRequest request, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(request);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
	}
	
	public Cancellable mtermvectorsAsync(MultiTermVectorsRequest request, RequestOptions options, ActionListener<MultiTermVectorsResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromRequest(request);
		listener = new NRActionListener<MultiTermVectorsResponse>(listener, params);
		return Weaver.callOriginal();
	}
	
	@Trace(leaf=true)
	public MultiGetResponse multiGet(MultiGetRequest multiGetRequest, RequestOptions options)  {
		DatastoreParameters params = Utils.getParamsFromRequest(multiGetRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
	}

	@Trace(leaf=true)
    public MultiSearchResponse multiSearch(MultiSearchRequest multiSearchRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(multiSearchRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
    }


    public Cancellable multiSearchAsync(MultiSearchRequest searchRequest, RequestOptions options, ActionListener<MultiSearchResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromRequest(searchRequest);
		listener = new NRActionListener<MultiSearchResponse>(listener, params);
		return Weaver.callOriginal();
    }

    @Trace(leaf=true)
    public AcknowledgedResponse putScript(PutStoredScriptRequest putStoredScriptRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(putStoredScriptRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
    }
    
    public Cancellable putScriptAsync(PutStoredScriptRequest putStoredScriptRequest, RequestOptions options, ActionListener<AcknowledgedResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromRequest(putStoredScriptRequest);
		listener = new NRActionListener<AcknowledgedResponse>(listener, params);
		return Weaver.callOriginal();
    }
    
    @Trace(leaf=true)
    public RankEvalResponse rankEval(RankEvalRequest rankEvalRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(rankEvalRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
    }
    
    public Cancellable rankEvalAsync(RankEvalRequest rankEvalRequest, RequestOptions options, ActionListener<RankEvalResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromRequest(rankEvalRequest);
		listener = new NRActionListener<RankEvalResponse>(listener, params);
		return Weaver.callOriginal();
    }
    
    @Trace(leaf=true)
    public BulkByScrollResponse reindex(ReindexRequest reindexRequest, RequestOptions options)  {
		DatastoreParameters params = Utils.getParamsFromRequest(reindexRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
    }
    
    public Cancellable reindexAsync(ReindexRequest reindexRequest, RequestOptions options, ActionListener<BulkByScrollResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromRequest(reindexRequest);
		listener = new NRActionListener<BulkByScrollResponse>(listener, params);
		return Weaver.callOriginal();
    }
    
    @Trace(leaf=true)
    public SearchResponse scroll(SearchScrollRequest searchScrollRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(searchScrollRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
    }
    
    public Cancellable scrollAsync(SearchScrollRequest searchScrollRequest, RequestOptions options, ActionListener<SearchResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromRequest(searchScrollRequest);
		listener = new NRActionListener<SearchResponse>(listener, params);
		return Weaver.callOriginal();
    }
    
    @Trace(leaf=true)
    public SearchResponse search(SearchRequest searchRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(searchRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
    }
    
    public Cancellable searchAsync(SearchRequest searchRequest, RequestOptions options, ActionListener<SearchResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromRequest(searchRequest);
		listener = new NRActionListener<SearchResponse>(listener, params);
		return Weaver.callOriginal();
    }
    
    @Trace(leaf=true)
    public SearchResponse searchScroll(SearchScrollRequest searchScrollRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(searchScrollRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
    }
    
    public Cancellable searchScrollAsync(SearchScrollRequest searchScrollRequest, RequestOptions options, ActionListener<SearchResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromRequest(searchScrollRequest);
		listener = new NRActionListener<SearchResponse>(listener, params);
		return Weaver.callOriginal();
    }
    
    @Trace(leaf=true)
    public SearchTemplateResponse searchTemplate(SearchTemplateRequest searchTemplateRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(searchTemplateRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
    }
    
    public Cancellable searchTemplateAsync(SearchTemplateRequest searchTemplateRequest, RequestOptions options, ActionListener<SearchTemplateResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromRequest(searchTemplateRequest);
		listener = new NRActionListener<SearchTemplateResponse>(listener, params);
		return Weaver.callOriginal();
    }
      
    @Trace(leaf=true)
    public TaskSubmissionResponse submitDeleteByQueryTask(DeleteByQueryRequest deleteByQueryRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(deleteByQueryRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
    }
    
    @Trace(leaf=true)
    public TaskSubmissionResponse submitReindexTask(ReindexRequest reindexRequest, RequestOptions options)  {
		DatastoreParameters params = Utils.getParamsFromRequest(reindexRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
    }
    
    @Trace(leaf=true)
    public TaskSubmissionResponse submitUpdateByQueryTask(UpdateByQueryRequest updateByQueryRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(updateByQueryRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
    }
    
    @Trace(leaf=true)
    public TermVectorsResponse termvectors(TermVectorsRequest request, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(request);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
    }

    public Cancellable termvectorsAsync(TermVectorsRequest request, RequestOptions options, ActionListener<TermVectorsResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromRequest(request);
		listener = new NRActionListener<TermVectorsResponse>(listener, params);
		return Weaver.callOriginal();
    }
    
    @Trace(leaf=true)
    public UpdateResponse update(UpdateRequest updateRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(updateRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
    }
    
    public Cancellable updateAsync(UpdateRequest updateRequest, RequestOptions options, ActionListener<UpdateResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromRequest(updateRequest);
		listener = new NRActionListener<UpdateResponse>(listener, params);
		return Weaver.callOriginal();
    }

    @Trace(leaf=true)
    public BulkByScrollResponse updateByQuery(UpdateByQueryRequest updateByQueryRequest, RequestOptions options) {
		DatastoreParameters params = Utils.getParamsFromRequest(updateByQueryRequest);
		if (params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		return Weaver.callOriginal();
    }
    
    public Cancellable updateByQueryAsync(UpdateByQueryRequest updateByQueryRequest, RequestOptions options, ActionListener<BulkByScrollResponse> listener) {
		DatastoreParameters params = Utils.getParamsFromRequest(updateByQueryRequest);
		listener = new NRActionListener<BulkByScrollResponse>(listener, params);
		return Weaver.callOriginal();
    }
    
    
}
