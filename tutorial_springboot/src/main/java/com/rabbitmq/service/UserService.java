package com.rabbitmq.service;

import com.rabbitmq.domain.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: jimmy
 * @Date: 2018/7/25
 */
public interface UserService {
    public List<User> findByUserName(String userName);

    public User saveOrUpdate(User user);

    void deleteById(Long id);

    public User findById(Long id);

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
     * @param pageNum 起始页 从0开始
     * @param size    查询数
     * @param userName
     * @return
     */
    Page<User> pageByUserName(Integer pageNum, Integer size, String userName);

    /**
     * hql 更新
     * @param userName
     * @param id
     */
    void modifyUser(String userName, Long id);

    void modifyUser(User user);

    /**
     * 复杂查询
     * @return
     */
    Page<User> queryUserByParam(String userName, Integer pageNum, Integer size);


   /* SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
      Predicate dueTime = criteriaBuilder.between(root.<Date>get("dueTime"),sdf2.parse(prid.getBeginTime()),sdf2.parse(prid.getEndTime()));*/
}
