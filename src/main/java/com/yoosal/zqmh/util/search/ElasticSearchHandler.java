package com.yoosal.zqmh.util.search;

import com.alibaba.fastjson.JSON;
import com.yoosal.zqmh.GlobalVariable;
import com.yoosal.zqmh.pojo.Information;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import java.net.InetAddress;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by qinmingtao on 2016/8/22.
 * Desc 核心搜索类
 */
public class ElasticSearchHandler {

    private static Client client;

    private ElasticSearchHandler() {

    }

    public static Client getInstance() {
        return getInstance(true);
    }

    public static Client getInstance(boolean indexEx) {
        if (client == null) {
            try {
                Settings settings = Settings.settingsBuilder()
                        .put("cluster.name", "yoosal").build();
                client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

                if (!indexEx) {
                    //设置分词
                    createCluterName(GlobalVariable.INDEX_NAME);
                    createMapping(GlobalVariable.INDEX_NAME, Information.class.getSimpleName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return client;
    }

    /**
     * 将对象通过jackson.databind转换成byte[]
     * 注意一下date类型需要格式化处理  默认是 时间戳
     *
     * @return
     */
    public byte[] convertByteArray(Object obj) {
//        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
//        try {
//            byte[] json = mapper.writeValueAsBytes(obj);
//            return json;
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    /**
     * 将对象通过JSONtoString转换成JSON字符串
     * 使用fastjson 格式化注解  在属性上加入 @JSONField(format="yyyy-MM-dd HH:mm:ss")
     *
     * @return
     */
    private static String jsonStr(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * 创建索引
     *
     * @param bean 实体类
     * @param id   ID
     * @return 成功true 失败false
     */
    public static boolean createIndex(Object bean, int id) {
        IndexResponse response = getInstance().prepareIndex(GlobalVariable.INDEX_NAME, bean.getClass().getSimpleName(), id + "")//参数说明： 索引，类型 ，_id
                .setSource(jsonStr(bean))//setSource可以传以上map string  byte[] 几种方式
                .get();
        return response.isCreated();
    }

    /**
     * 删除索引
     *
     * @param bean 实体类
     * @param id   ID
     * @return 成功true 失败false
     */
    public static boolean delIndex(Object bean, int id) {
        DeleteResponse response2 = getInstance().prepareDelete(GlobalVariable.INDEX_NAME, bean.getClass().getSimpleName(), id + "").get();
        return response2.isFound();
    }

    /**
     * 更新索引
     *
     * @param bean 实体类
     * @param id   ID
     * @return 成功true 失败false
     */
    public static boolean updateIndex(Object bean, int id) {
        UpdateResponse response = getInstance().prepareUpdate(GlobalVariable.INDEX_NAME, bean.getClass().getSimpleName(), id + "")//参数说明： 索引，类型 ，_id
                .setDoc(jsonStr(bean))
                .get();
        return response.isCreated();
    }

    public void forSearchResponse(SearchResponse response) {
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

    public static void close() {
        if (client != null) {
            client.close();
        }
    }

    /**
     * 创建索引名称
     *
     * @param indices 索引名称
     */
    public static void createCluterName(String indices) {
        client.admin().indices().prepareCreate(indices).execute().actionGet();
    }

    /**
     * 创建mapping(feid("indexAnalyzer","ik")该字段分词IK索引 ；feid("searchAnalyzer","ik")该字段分词ik查询；具体分词插件请看IK分词插件说明)
     *
     * @param indices     索引名称；
     * @param mappingType 索引类型
     * @throws Exception
     */
    public static void createMapping(String indices, String mappingType) throws Exception {
        new XContentFactory();
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .startObject(mappingType)
                .startObject("properties")
                .startObject("id").field("type", "integer").field("store", "yes").endObject()
                .startObject("name").field("type", "string").field("store", "yes").field("analyzer", "ik_max_word").field("search_analyzer", "ik_max_word").endObject()
                .startObject("content").field("type", "string").field("store", "yes").field("analyzer", "ik_max_word").field("search_analyzer", "ik_max_word").endObject()
                .endObject()
                .endObject()
                .endObject();
        PutMappingRequest mapping = Requests.putMappingRequest(indices).type(mappingType).source(builder);
        client.admin().indices().putMapping(mapping).actionGet();
    }
}
