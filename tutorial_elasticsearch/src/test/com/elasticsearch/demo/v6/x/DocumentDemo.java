package com.elasticsearch.demo.v6.x;

import com.elasticsearch.ElasticsearchStart;
import com.elasticsearch.domain.Book;
import com.elasticsearch.domain.Picture;
import com.elasticsearch.domain.Zone;
import com.elasticsearch.util.Constant;
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
import java.util.ArrayList;
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
        List<Picture> picture_1 = new ArrayList<>();
        List<Picture> picture_2 = new ArrayList<>();
        picture_2.add(new Picture(2,"刘德华","刘德华演唱会"));
        picture_2.add(new Picture(3,"张学友","张学友演唱会"));
        picture_2.add(new Picture(4,"黎明","黎明巡回演唱会"));
        picture_2.add(new Picture(5,"郭富城","郭富城红磡演唱会"));

        List<Picture> picture_3 = new ArrayList<>();
        picture_3.add(new Picture(1,"java编程思想_1","java编程思想图1"));

        List<Book> bookList = Lists.newArrayList();
        bookList.add(Book.builder().id(1L).bookName("Java").price(10.0F).author(new String[]{"jimmy"}).zone(new Zone("美国","加州")).build());
        bookList.add(Book.builder().id(2L).bookName("Elasticsearch").price(20.0F).author(new String[]{"jimmy","Jack"}).zone(new Zone("美国","加州")).build());
        bookList.add(Book.builder().id(3L).bookName("JavaScript").price(10.0F).author(new String[]{"lucy"}).zone(new Zone("英国","北威尔士")).build());
        bookList.add(Book.builder().id(4L).bookName("Python").price(100.0F).author(new String[]{"billy","Lucy"}).zone(new Zone("美国","洛杉矶")).build());
        bookList.add(Book.builder().id(5L).bookName("汽车杂志").price(68.8F).author(new String[]{"jimmy"}).zone(new Zone("美国","加州")).build());
        bookList.add(Book.builder().id(6L).bookName("读者").price(12.0F).author(new String[]{"jimmy","张三"}).zone(new Zone("中国","北京")).build());
        bookList.add(Book.builder().id(7L).bookName("四大天王演唱会").price(98.0F).author(new String[]{"刘德华","张学友"}).zone(new Zone("中国","香港")).pictures(picture_2).build());
        bookList.add(Book.builder().id(8L).bookName("car天下").price(30.0F).author(new String[]{"jason&smith","黎明"}).zone(new Zone("中国","北京")).build());
        bookList.add(Book.builder().id(9L).bookName("四大名著").price(280.0F).author(new String[]{"曹雪芹","施耐庵","罗贯中","吴承恩"}).zone(new Zone("中国","")).build());
        bookList.add(Book.builder().id(10L).bookName("唐诗三百首").price(58.0F).author(new String[]{"李白"}).zone(new Zone("中国","")).build());
        bookList.add(Book.builder().id(11L).bookName("中华上下五千年").price(100.0F).author(new String[]{"无名氏"}).zone(new Zone("中国","")).build());
        bookList.add(Book.builder().id(12L).bookName("时间简史").price(99.9F).author(new String[]{"斯蒂芬·威廉·霍金"}).zone(new Zone("英国","")).build());
        bookList.add(Book.builder().id(13L).bookName("elasticsearch权威指南(上)").price(108.0F).author(new String[]{"clinton gormley","zachary tong"}).zone(new Zone("美国","")).build());
        bookList.add(Book.builder().id(14L).bookName("elasticsearch权威指南(下)").price(108.0F).author(new String[]{"clinton gormley","zachary tong"}).zone(new Zone("美国","")).build());
        bookList.add(Book.builder().id(15L).bookName("java编程思想").price(140.0F).author(new String[]{"埃克尔"}).zone(new Zone("美国","")).pictures(picture_3).build());


        Bulk bulk = new Bulk.Builder()
                .defaultIndex(Constant.INDEX_NAME)
                .defaultType(Constant.TYPE_NAME)
                .addAction((BulkableAction) Arrays.asList(bookList))
                .build();
        //
        JestResult jestResult = jestClient.execute(bulk);
        System.out.println(jestResult);
    }
}
