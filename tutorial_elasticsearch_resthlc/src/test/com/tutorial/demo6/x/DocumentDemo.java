package com.tutorial.demo6.x;

import com.alibaba.fastjson.JSON;
import com.elasticsearch.ElasticsearchStart;
import com.elasticsearch.domain.City;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jimmy
 * @date 2019-03-2516:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchStart.class)
@Slf4j
public class DocumentDemo {
    @Autowired
    RestHighLevelClient restHighLevelClient;

    public static final String INDEX_NAME   = "city_index";
    public static final String MAPPING_NAME = "tutorial_mapping";
    @Test
    public void test(){
        System.out.println(restHighLevelClient.cluster());
    }

    /**
     * 索引单个文档
     * DocWriteRequest.OpType.CREATE 如果索引中已存在该id的文档,则索引操作将失败
     * DocWriteRequest.OpType.INDEX  如果索引中已存在该id的文档,则更新文档(默认)
     * @throws IOException
     */
    @Test
    public void saveDoc() throws IOException {
        City city = new City(1L,"nanjing1");

        IndexRequest indexRequest = new IndexRequest(INDEX_NAME);
        indexRequest.type(MAPPING_NAME);
        indexRequest.id("1");
        indexRequest.source(JSON.toJSONString(city), XContentType.JSON);
        //indexRequest.opType(DocWriteRequest.OpType.INDEX);
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(indexResponse);


    }

    /**
     * 判断文档是否存在
     * @throws IOException
     */
    @Test
    public void docExist() throws IOException {
        GetRequest getRequest = new GetRequest(INDEX_NAME, MAPPING_NAME, "1");
        //表示不返回 _source
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        boolean exists = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    /**
     * 获取文档
     * @throws IOException
     */
    @Test
    public void getDoc() throws IOException {
        GetRequest getRequest = new GetRequest(INDEX_NAME, MAPPING_NAME, "1");
        String[] includes = new String[]{"id","name","_source"};//返回包含字段
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true,includes,excludes);
        getRequest.fetchSourceContext(fetchSourceContext);
        getRequest.storedFields("_source");

        GetResponse response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println("version="+response.getVersion());
        Map<String, DocumentField> map = response.getFields();
        System.out.println("map=="+map);
        map.forEach((key,value)-> System.out.println(key+"==="+value));
       // System.out.println(response.getSourceAsString());
       // System.out.println((char[]) response.getField("_source").getValue());
        City city = JSON.parseObject(response.getSourceAsString(), City.class);
        System.out.println(city);

    }

    /**
     * 批量保存文档
     * @throws IOException
     */
    @Test
    public void saveBulkDoc() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        City city1 = new City(1L,"beijing");
        City city2 = new City(2L,"taiwan");
        City city3 = new City(3L,"taiwan");
        List<IndexRequest> indexRequestList = new ArrayList<>();
        indexRequestList.add(getIndexRequest(city1));
        indexRequestList.add(getIndexRequest(city2));
        indexRequestList.add(getIndexRequest(city3));
        indexRequestList.stream().forEach(s->bulkRequest.add(s));
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulkResponse);
    }

    public IndexRequest getIndexRequest(City city){
        IndexRequest indexRequest = new IndexRequest(INDEX_NAME);
        indexRequest.type(MAPPING_NAME);
        indexRequest.id(String.valueOf(city.getId()));
        indexRequest.source(JSON.toJSONString(city), XContentType.JSON);
        return indexRequest;
    }

    /**
     * 删除文档
     */
    @Test
    public void delDoc() throws IOException {
        DeleteRequest request = new DeleteRequest(INDEX_NAME, MAPPING_NAME, "1");
        request.timeout(TimeValue.timeValueMinutes(2));
        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        DeleteResponse deleteResponse = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(deleteResponse.status());
        //文档不存在
        if (deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND) {
            System.out.println("文档不存在");
        }
    }

    /**
     * 批量删除
     * @throws IOException
     */
    @Test
    public void delBulkDoc() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        DeleteRequest deleteRequest1 = new DeleteRequest(INDEX_NAME, MAPPING_NAME, "1");
        bulkRequest.add(deleteRequest1);
        DeleteRequest deleteRequest2 = new DeleteRequest(INDEX_NAME, MAPPING_NAME, "2");
        bulkRequest.add(deleteRequest2);
        DeleteRequest deleteRequest3 = new DeleteRequest(INDEX_NAME, MAPPING_NAME, "3");
        bulkRequest.add(deleteRequest3);
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulkResponse);
    }

    /**
     * 异步保存
     */
    @Test
    public void asyncSave() throws InterruptedException {
        City city = new City(1L,"宇宙");
        IndexRequest indexRequest = new IndexRequest(INDEX_NAME);
        indexRequest.type(MAPPING_NAME);
        indexRequest.id("1");
        indexRequest.source(JSON.toJSONString(city), XContentType.JSON);

        restHighLevelClient.indexAsync(indexRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                log.info("total: "+indexResponse.getShardInfo().getTotal());
                log.info("success: "+indexResponse.getShardInfo().getSuccessful());
                log.info("failed: "+indexResponse.getShardInfo().getFailed());
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });

        Thread.sleep(1000*10);
    }

    /**
     * 异步批量保存
     */
    @Test
    public void asyncBulkSave() throws InterruptedException {
        BulkRequest bulkRequest = new BulkRequest();
        City city1 = new City(1L,"北京1");
        City city2 = new City(2L,"上海2");
        City city3 = new City(3L,"天津3");
        City city4 = new City(4L,"北京4");
        City city5 = new City(5L,"上海5");
        City city6 = new City(6L,"天津6");
        List<IndexRequest> indexRequestList = new ArrayList<>();
        indexRequestList.add(getIndexRequest(city1));
        indexRequestList.add(getIndexRequest(city2));
        indexRequestList.add(getIndexRequest(city3));
        indexRequestList.add(getIndexRequest(city4));
        indexRequestList.add(getIndexRequest(city5));
        indexRequestList.add(getIndexRequest(city6));
        indexRequestList.stream().forEach(s->bulkRequest.add(s));
        System.out.println("====size==="+bulkRequest.numberOfActions());
        restHighLevelClient.bulkAsync(bulkRequest, RequestOptions.DEFAULT, new ActionListener<BulkResponse>() {
            @Override
            public void onResponse(BulkResponse bulkResponse) {
                System.out.println("msg"+bulkResponse.buildFailureMessage());
                System.out.println("msg"+bulkResponse.status().name());
                System.out.println("msg"+bulkResponse.getIngestTook());
                System.out.println("msg"+bulkResponse.hasFailures());
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("e==="+e);
            }
        });

        Thread.sleep(1000*10);
    }

    /**
     * 异步删除文档
     * @throws IOException
     */
    @Test
    public void asyncDelDoc() throws IOException, InterruptedException {
        DeleteRequest request = new DeleteRequest(INDEX_NAME, MAPPING_NAME, "6");
        request.timeout(TimeValue.timeValueMinutes(2));
        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        restHighLevelClient.deleteAsync(request, RequestOptions.DEFAULT,new ActionListener<DeleteResponse>() {
            @Override
            public void onResponse(DeleteResponse deleteResponse) {
                System.out.println(deleteResponse.status());
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("e=="+e);
            }
        });
        Thread.sleep(1000*10);
    }
}
