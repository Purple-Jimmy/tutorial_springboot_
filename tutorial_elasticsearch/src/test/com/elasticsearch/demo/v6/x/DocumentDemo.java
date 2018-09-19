package com.elasticsearch.demo.v6.x;

import com.elasticsearch.ElasticsearchStart;
import com.elasticsearch.domain.Book;
import com.elasticsearch.domain.Zone;
import com.google.common.collect.Lists;
import io.searchbox.action.BulkableAction;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jimmy. 2018/1/29  15:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchStart.class)
public class DocumentDemo {
    @Autowired
    private JestClient jestClient;

    /**
     * 索引文档
     */
    @Test
    public void indexDocument() throws IOException {
        Book book1 = Book.builder().id(1L).bookName("Java").build();
    }

    /**
     * 批量索引文档
     * @throws IOException
     */
    @Test
    public void batchIndexDocument() throws IOException {
        List<Book> bookList = Lists.newArrayList();
        bookList.add(Book.builder().id(1L).bookName("Java").price(10.0F).author(new String[]{"jimmy"}).zone(new Zone("美国","加州")).build());
        bookList.add(Book.builder().id(2L).bookName("Elasticsearch").price(20.0F).author(new String[]{"jimmy","Jack"}).zone(new Zone("美国","加州")).build());
        bookList.add(Book.builder().id(3L).bookName("Java").price(10.0F).author(new String[]{"jimmy"}).zone(new Zone("美国","加州")).build());
        bookList.add(Book.builder().id(4L).bookName("Java").price(10.0F).author(new String[]{"jimmy"}).zone(new Zone("美国","加州")).build());
        bookList.add(Book.builder().id(5L).bookName("Java").price(10.0F).author(new String[]{"jimmy"}).zone(new Zone("美国","加州")).build());
        bookList.add(Book.builder().id(6L).bookName("Java").price(10.0F).author(new String[]{"jimmy"}).zone(new Zone("美国","加州")).build());
        bookList.add(Book.builder().id(7L).bookName("Java").price(10.0F).author(new String[]{"jimmy"}).zone(new Zone("美国","加州")).build());
        bookList.add(Book.builder().id(8L).bookName("Java").price(10.0F).author(new String[]{"jimmy"}).zone(new Zone("美国","加州")).build());
        bookList.add(Book.builder().id(9L).bookName("Java").price(10.0F).author(new String[]{"jimmy"}).zone(new Zone("美国","加州")).build());
        bookList.add(Book.builder().id(10L).bookName("Java").price(10.0F).author(new String[]{"jimmy"}).zone(new Zone("美国","加州")).build());
        bookList.add(Book.builder().id(11L).bookName("Java").price(10.0F).author(new String[]{"jimmy"}).zone(new Zone("美国","加州")).build());
        bookList.add(Book.builder().id(12L).bookName("Java").price(10.0F).author(new String[]{"jimmy"}).zone(new Zone("美国","加州")).build());
        bookList.add(Book.builder().id(13L).bookName("Java").price(10.0F).author(new String[]{"jimmy"}).zone(new Zone("美国","加州")).build());


        Bulk bulk = new Bulk.Builder()
                .defaultIndex("facebook")
                .defaultType("tweet")
                .addAction((BulkableAction) Arrays.asList(bookList))
                .build();
        //
        JestResult jestResult = jestClient.execute(bulk);
        System.out.println(jestResult);
    }
}
