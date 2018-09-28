package com.rabbitmq.repository;

import com.rabbitmq.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: jimmy
 * @Date: 2018/7/25
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {

    /**
     * 默认方法
     */
   /* userRepository.findAll();
    userRepository.findOne(1l);
    userRepository.save(user);
    userRepository.delete(user);
    userRepository.count();
    userRepository.exists(1l);*/
    /**
     * 限制查询
     */
   /*
    Page<User> queryFirst10ByLastname(String lastname, Pageable pageable);
    List<User> findFirst10ByLastname(String lastname, Sort sort);
    List<User> findTop10ByLastname(String lastname, Pageable pageable);*/
    /**
     * 自定义简单查询
     */
    List<User> findByUserName(String userName);


    /**
     * AND 查询
     * @param userName
     * @param password
     * @return
     */
    User findByUserNameAndPassword(String userName, String password);

    /**
     * OR 查询
     * @param userName
     * @param password
     * @return
     */
    List<User> findByUserNameOrPassword(String userName, String password);

    /**
     * count 统计
     * @param userName
     * @return
     */
    Long countByUserName(String userName);

    /**
     * like
     * @param userName
     * @return
     */
    List<User> findByUserNameLike(String userName);

    /**
     * ignoreCase
     * @param userName
     * @return
     */
    List<User> findByUserNameIgnoreCase(String userName);

    /**
     * orderBy
     * @param userName
     * @return
     */
    List<User> findByUserNameOrderByBirthDayDesc(String userName);

    /**
     * 分页查询
     * @param userName
     * @param pageable
     * @return
     */
    Page<User> findByUserName(String userName, Pageable pageable);

    /**
     * 自定义sql查询
     */
    @Modifying
    //  @Query("update User u set u.userName = ?1 where u.id = ?2")
    @Query("update User set userName = :userName where id =:id")
    void modifyUser(@Param("userName") String userName, @Param("id") Long id);

    //TODO 对象属性查询
    @Modifying
    @Query("update User u set u.userName = :#{#user.userName} where u.id = :#{#user.id}")
    void modifyUser(User user);

}
