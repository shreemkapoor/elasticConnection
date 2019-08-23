package es.configuration;

import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.client.Client;


public class ESRequestBuilder {
	public static SearchRequestBuilder getSearchRequestBuilder(String index, String type) {

		Client client = ESConnection.getConnection();
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type);
		return searchRequestBuilder;

	}
	
	public static SearchRequestBuilder getAllIndexSearchRequestBuilder(String index) {

		Client client = ESConnection.getConnection();
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
		return searchRequestBuilder;
	}
	
	public static IndexRequestBuilder getindexRequestBuilder(String IndexName) {
		Client client = ESConnection.getConnection();
	    try{
	        return client.prepareIndex(IndexName, "default");
	    }catch(Exception e){
	        e.printStackTrace();
	    }
		return	null;
	}
	
	public static DeleteRequestBuilder getDeleteRequestBuilder(String IndexName, String documentId) {
		Client client = ESConnection.getConnection();
		try {
			return client.prepareDelete(IndexName,"default",documentId);
		}catch(Exception e){
	        e.printStackTrace();
	    }
		return null;
	}
	
	public static UpdateRequestBuilder getUpdateRequestBuilder(String IndexName, String documentId) {
		Client client = ESConnection.getConnection();
		try {
			return client.prepareUpdate(IndexName, "default", documentId);
		}catch(Exception e){
	        e.printStackTrace();
	    }
		return null;
	}
	
	
}
