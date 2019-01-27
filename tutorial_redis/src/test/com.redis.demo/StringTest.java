package com.redis.demo;

import com.redis.RedisStart;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Jimmy. 2018/4/18  09:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisStart.class)
public class StringTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    //@Test
    public void saveStringTest(){
        //redisTemplate.opsForValue().set("zzp","big z");
        stringRedisTemplate.opsForValue().set("zz","hello");
    }

    //@Test
    public void getStringTest(){
        System.out.println(stringRedisTemplate.opsForValue().get("zz"));
    }


    //@Test
    public void saveUserTest(){
       /* Book book = new Book();
        book.setId(1L);
        book.setName("java");
        redisTemplate.opsForValue().set("book",book);*/
    }

    //@Test
    public void getUserTest(){
        System.out.println(redisTemplate.opsForValue().get("book"));
    }



    //@Test
    public void objToStringTest(){
       /* ValueOperations<String,Book> operations = redisTemplate.opsForValue();
        Book book_1 = new Book();
        book_1.setId(100L);
        book_1.setName("elasticsearch");
        operations.set("Book:b1",book_1);

        Book book_2 = new Book();
        book_2.setId(200L);
        book_2.setName("python");
        operations.set("Book:b2",book_2);*/
    }

    //@Test
    public void getObjToStringTest(){
        System.out.println(redisTemplate.opsForValue().get("Book:b1"));
    }
}
