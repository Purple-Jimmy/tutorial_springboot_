package com.batch.elasticsearch;

import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author jimmy
 * @date 2019-02-2621:15
 */
@Component
public class EsPagingItemReader<Program> extends AbstractPagingItemReader<Program> {
    public static final String ES_INDEX = "ysten_program";
    public static final String ES_TYPE = "program_mapping";
    @Autowired
    JestClient jestClient;

    @Override
    protected void doReadPage() {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().from(this.getPage()*this.getPageSize()).size(this.getPageSize());
        String query = searchSourceBuilder.toString();
        Search search = new Search.Builder(query).addIndex(ES_INDEX).addType(ES_TYPE).build();
        //
        SearchResult result = null;
        try {
            result = jestClient.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (results == null) {
            results = new CopyOnWriteArrayList<>();
        }
        else {
            results.clear();
        }
        List<SearchResult.Hit<com.batch.domain.Program, Void>> hits = result.getHits(com.batch.domain.Program.class);
        for(SearchResult.Hit<com.batch.domain.Program, Void> hit : hits){
            System.out.println(hit.source);
            this.results.add((Program) hit.source);
        }
    }

    @Override
    protected void doJumpToPage(int itemIndex) {

    }
}
