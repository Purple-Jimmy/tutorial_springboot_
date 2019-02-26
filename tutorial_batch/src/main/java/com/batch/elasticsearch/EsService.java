package com.batch.elasticsearch;

import com.batch.domain.Program;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jimmy
 * @date 2019-02-2621:29
 */
@Service
public class EsService {

    public static final String ES_INDEX = "ysten_program";
    public static final String ES_TYPE = "program_mapping";

    @Autowired
    JestClient jestClient;

    /**
     * 分页查询
     * @param start
     * @param pageSize
     * @return
     */
    public List<Program> pageProgram(int start,int pageSize) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders
                .termQuery("name.keyword", "赌神1");
       // searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        String query = searchSourceBuilder.toString();
        System.out.println(query);
        Search search = new Search.Builder(query).addIndex(ES_INDEX).addType(ES_TYPE).build();
        //
        SearchResult result = jestClient.execute(search);
        List<Program> list = new ArrayList<>();
        List<SearchResult.Hit<Program, Void>> hits = result.getHits(Program.class);
        for(SearchResult.Hit<Program, Void> hit : hits){
            System.out.println(hit.source);
            list.add(hit.source);
        }
        return list;
    }
}
