package com.elasticsearch.demo;

import com.elasticsearch.ElasticsearchStart;
import com.elasticsearch.domain.Movie;
import com.elasticsearch.domain.Zone;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jimmy. 2018/1/29  15:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchStart.class)
public class DocumentTest {
    @Autowired
    private JestClient jestClient;

    /**
     * 索引文档
     */
    @Test
    public void indexDocument() throws IOException {
        /*Movie movie = new Movie();
        movie.setId(1L);
        movie.setName("test");
        movie.setLeadRole(new String[]{"刘德华"});
        movie.setStyle("动作");
        movie.setGrade(7.8F);
        Zone zone = new Zone();
        zone.setCountry("中国");
        zone.setCity("香港");
        movie.setZone(zone);
        movie.setDesc("这是测试");
        movie.setReleaseDate("2018-09-07");
        Index index = new Index.Builder(movie).index("twitter").type("tweet").build();
        //
        jestClient.execute(index);*/
    }


    /**
     * 索引文档-设置参数
     */
    @Test
    public void indexDocumentWithParameters() throws IOException {
       /* Movie movie = new Movie();
        movie.setId(100L);
        movie.setName("test");
        movie.setLeadRole(new String[]{"刘德华"});
        movie.setStyle("动作");
        movie.setGrade(7.8F);
        Zone zone = new Zone();
        zone.setCountry("中国");
        zone.setCity("香港");
        movie.setZone(zone);
        movie.setDesc("这是测试");
        movie.setReleaseDate("2018-09-07");
        Index index = new Index.Builder(movie).index("twitter").type("tweet")
                .setParameter(Parameters.REFRESH, true)
                .build();*/
        //
        //jestClient.execute(index);
    }


    /**
     * 批量索引文档-操作同一个索引
     */
    @Test
    public void indexBulkDocument() throws IOException {
       /* Movie movie1 = new Movie();
        movie1.setId(1L);
        movie1.setName("test");
        movie1.setLeadRole(new String[]{"刘德华"});
        movie1.setStyle("动作");
        movie1.setGrade(7.8F);
        Zone zone = new Zone();
        zone.setCountry("中国");
        zone.setCity("香港");
        movie1.setZone(zone);
        movie1.setDesc("这是测试");
        movie1.setReleaseDate("2018-09-07");

        Movie movie2 = new Movie();
        movie2.setId(2L);
        movie2.setName("test");
        movie2.setLeadRole(new String[]{"刘德华"});
        movie2.setStyle("动作");
        movie2.setGrade(7.8F);
        movie2.setZone(zone);
        movie2.setDesc("这是测试");
        movie2.setReleaseDate("2018-09-07");

        Bulk bulk = new Bulk.Builder()
                .defaultIndex("facebook")
                .defaultType("tweet")
                .addAction(Arrays.asList(
                        new Index.Builder(movie1).build(),
                        new Index.Builder(movie2).build()))
                .build();
        //
        jestClient.execute(bulk);*/
    }

    /**
     * 批量索引文档和删除文档-操作不同的索引
     * @throws IOException
     */
    @Test
    public void bulkDocument() throws IOException {
      /*  Movie movie1 = new Movie();
        movie1.setId(4L);
        movie1.setName("test");
        movie1.setLeadRole(new String[]{"刘德华","黎明"});
        movie1.setStyle("动作");
        movie1.setGrade(7.8F);
        Zone zone = new Zone();
        zone.setCountry("中国");
        zone.setCity("香港");
        movie1.setZone(zone);
        movie1.setDesc("这是测试");
        movie1.setReleaseDate("2018-09-07");

        Bulk bulk = new Bulk.Builder()
                .defaultIndex("facebook")
                .defaultType("tweet")
                .addAction(new Index.Builder(movie1).build())
              //.addAction(new Index.Builder(article2).build())
              // 可以是相同的索引也可以是不同的索引
                .addAction(new Delete.Builder("1").index("twitter").type("tweet").build())
                .build();
        //
        jestClient.execute(bulk);*/
    }

    /**
     * 检索文档
     * @throws IOException
     */
    @Test
    public void getDocument() throws IOException {
        Get get = new Get.Builder("twitter", "14").type("tweet").build();
        JestResult jestResult = jestClient.execute(get);
        System.out.println(jestResult.getSourceAsString());
    }

    /**
     * 检索文档-POJO
     * @throws IOException
     */
    @Test
    public void searchDocument() throws IOException {
        Get get = new Get.Builder("twitter", "100").type("tweet").build();
        JestResult jestResult = jestClient.execute(get);
      /*  Movie movie = jestResult.getSourceAsObject(Movie.class);
        System.out.println(movie);*/
    }

    /**
     * 更新文档
     * @throws IOException
     */
    @Test
    public void modifyDocument() throws IOException {
        Movie movie = new Movie();
        movie.setId(14L);
        movie.setName("test");
        movie.setLeadRole(new String[]{"刘德华","黎明"});
        movie.setStyle("爱情");
        movie.setGrade(7.8F);
        Zone zone = new Zone();
        zone.setCountry("中国");
        zone.setCity("台湾");
        movie.setZone(zone);
        movie.setDesc("这是测试");
        movie.setReleaseDate("2018-09-07");

        Gson gson = new GsonBuilder().create();
        String leadRoleArray = gson.toJson(new String[]{"刘德华","黎明","张学友"});
        System.out.println(leadRoleArray);

        String zoneStr = gson.toJson(zone);
        System.out.println(zoneStr);
        String script = "{" +
                "    \"doc\" : {" +
                "        \"id\" : \""+movie.getId()+"\"," +
                "        \"name\" : \""+movie.getName()+"\"," +
                "        \"leadRole\" : "+leadRoleArray+"," +
                "        \"style\" : \""+movie.getStyle()+"\"," +
                "        \"grade\" : \""+movie.getGrade()+"\"," +
                "        \"zone\" : "+zoneStr+"," +
                "        \"desc\" : \""+movie.getDesc()+"\"," +
                "        \"releaseDate\" : \""+new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date())+"\"" +
                "    }" +
                "}";
        Update update = new Update.Builder(script).index("twitter").type("tweet").id("14").build();
        JestResult result = jestClient.execute(update);
        System.out.println(result.getJsonString());
    }

    /**
     * 删除文档
     * @throws IOException
     */
    @Test
    public void deleteDocument() throws IOException {
        Delete delete = new Delete.Builder("1").index("twitter").type("tweet").build();
        jestClient.execute(delete);
      /*  jestClient.execute(new Delete.Builder("100")
                .index("twitter")
                .type("tweet")
                .build());*/
    }

    /**
     * count
     * @throws IOException
     */
    @Test
    public void getCount() throws IOException {
        String index = "rabbitmq";
        String type = "movie";
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                                   .must(QueryBuilders.termsQuery("name", "赌"));
        searchSourceBuilder.query(queryBuilder);

        Count count = new Count.Builder()
                .addIndex(index)
                .addType(type)
                .query(searchSourceBuilder.toString())
                .build();
        CountResult countResult = jestClient.execute(count);
        System.out.println(countResult.getCount());
    }

/*
    @Test
    public void test13(){
        JestClient jestClient = JestClientUtil.getJestClient();
        //未知原因
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().filter(QueryBuilders.termsQuery("id", "17089562","17089563"));
        DeleteByQuery deleteByQuery = new DeleteByQuery.Builder(new SearchSourceBuilder().query(boolQueryBuilder).toString())
                .addIndex(GlobalConstants.MAPPER_ + 40)
                .addType(GlobalConstants.OTHER_TYPE)
                .setParameter("scroll_size", 5000)
                .refresh(true)
                .setParameter("slices", 5)
                .build();
        //scroll_size=5000&refresh&slices=5
        System.out.println( );
        try {
            JestResult execute =jestClient.execute(deleteByQuery);
            System.out.println(execute.getJsonString());
            jestClient.shutdownClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}
