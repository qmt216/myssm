import com.yoosal.zqmh.GlobalVariable;
import com.yoosal.zqmh.pojo.Information;
import com.yoosal.zqmh.util.search.ElasticSearchHandler;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by qmt on 2016/8/7.
 * Desc
 */
public class Test {
    public static void main(String[] args) {
        SearchResponse response = ElasticSearchHandler.getInstance(true).prepareSearch(GlobalVariable.INDEX_NAME)//可以同时搜索多个索引prepareSearch("index","index2")
                .setTypes(Information.class.getSimpleName())//可以同时搜索多个类型
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.queryStringQuery("建设"))// Query
                .setFrom(0).setSize(20).setExplain(true)
                .execute()
                .actionGet();
        ElasticSearchHandler.close();
        forSearchResponse(response);
        System.out.println("总共查询到有：" + response.getHits().getTotalHits());

    }

    public static void forSearchResponse(SearchResponse response) {
        for (SearchHit hit1 : response.getHits()) {
            Map<String, Object> source1 = hit1.getSource();
            if (!source1.isEmpty()) {
                for (Iterator<Map.Entry<String, Object>> it = source1.entrySet().iterator(); it.hasNext(); ) {
                    Map.Entry<String, Object> entry = it.next();
                    System.out.println(entry.getKey() + "=======" + entry.getValue());
                }
            }
        }
    }
}
