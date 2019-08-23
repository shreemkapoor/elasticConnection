package es.operations;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.SearchHits;

import es.configuration.ESRequestBuilder;

public class ESOperation extends ESService {


		public void searchshreemindex(){
		SearchRequestBuilder arg1 =	 ESRequestBuilder.getAllIndexSearchRequestBuilder("marketing_budget");

		BoolQueryBuilder b1 	=	QueryBuilders.boolQuery();
		/*MatchPhraseQueryBuilder mp1= QueryBuilders.matchPhraseQuery("fullname",translated).boost(5.0f);
		MatchPhraseQueryBuilder mp12= QueryBuilders.matchPhraseQuery("fullname",nontranslated).boost(5.0f);*/
		MatchQueryBuilder mp1	= QueryBuilders.matchQuery("budget", "100000");
		//MatchQueryBuilder mp12 	= QueryBuilders.matchQuery("warranty", "1 month");
		System.out.println(b1.should(mp1 ));

		arg1.setQuery(b1.should(mp1));
		SearchResponse var16=(SearchResponse)arg1.execute().actionGet();
		System.out.println(var16);
		
		SearchHits  sh=var16.getHits();
		}	
		
		public void searchmarketingbudget(){
		SearchRequestBuilder arg1 =	 ESRequestBuilder.getAllIndexSearchRequestBuilder("marketing_budget");

		BoolQueryBuilder b1 	=	QueryBuilders.boolQuery();
		MatchPhraseQueryBuilder mp3= QueryBuilders.matchPhraseQuery("sub_activity","Gap analysis").boost(5.0f);
		//MatchPhraseQueryBuilder mp4= QueryBuilders.matchPhraseQuery("fullname",nontranslated).boost(5.0f);
		MatchQueryBuilder mp1	= QueryBuilders.matchQuery("channel", "Services");
		QueryBuilder mp2 	= QueryBuilders.matchQuery("outlet", "DELHI NCR");
		WildcardQueryBuilder  q1 = QueryBuilders.wildcardQuery("outlet.keyword", "Su*");
		//System.out.println(b1.must(mp1).must(mp12).should(mp13));
		System.out.println(q1);

		arg1.setQuery(b1.must(mp1).must(mp2).should(mp3));
		SearchResponse var16=(SearchResponse)arg1.execute().actionGet();
		System.out.println(var16);
		
		SearchHits  sh=var16.getHits();
		}	
		
		public boolean insert() {
			IndexRequestBuilder irb	=	ESRequestBuilder.getindexRequestBuilder("shreemindex");
			
			Map<String, Object> sourceObect= new HashMap<String,Object>();
			sourceObect.put("name","hhhhh");
			sourceObect.put("lname","bbbbbb");
			sourceObect.put("class","eee");
			
			irb.setSource(sourceObect).setId("4");
			irb.get();
			return true;
		}
		
		
		public boolean delete() {
			DeleteRequestBuilder drb = ESRequestBuilder.getDeleteRequestBuilder("shreemindex", "5");
			drb.get();
			DeleteResponse delete = (DeleteResponse)drb.execute().actionGet();
			return true;
		}
		
		public boolean update() {
			UpdateRequestBuilder urb = ESRequestBuilder.getUpdateRequestBuilder("shreemindex", "4");
			Map<String, Object> sourceObect= new HashMap<String,Object>();
			sourceObect.put("class", "wwf");
			urb.setDoc(sourceObect).get();
			System.out.println(sourceObect);
			return true;
		}
	}
