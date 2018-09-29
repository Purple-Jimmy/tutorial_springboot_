package com.tutorial.service.impl;

import com.tutorial.domain.User;
import com.tutorial.repository.UserRepository;
import com.tutorial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author: jimmy
 * @Date: 2018/7/25
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User saveOrUpdate(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User findByUserNameAndPassword(String userName, String password) {
        return userRepository.findByUserNameAndPassword(userName,password);
    }

    @Override
    public List<User> findByUserNameOrPassword(String userName, String password) {
        return userRepository.findByUserNameOrPassword(userName,password);
    }

    @Override
    public Long countByUserName(String userName) {
        return userRepository.countByUserName(userName);
    }

    @Override
    public List<User> findByUserNameLike(String userName) {
        return userRepository.findByUserNameLike(userName);
    }

    @Override
    public List<User> findByUserNameIgnoreCase(String userName) {
        return userRepository.findByUserNameIgnoreCase(userName);
    }

    @Override
    public List<User> findByUserNameOrderByBirthDayDesc(String userName) {
        return userRepository.findByUserNameOrderByBirthDayDesc(userName);
    }

    @Override
    public Page<User> pageByUserName(Integer pageNum, Integer size, String userName) {
        Sort.Order idOrder = new Sort.Order(Sort.Direction.DESC, "id");
        Sort.Order nameOrder = new Sort.Order(Sort.Direction.ASC,"userName");
       // Sort sort = new Sort(idOrder,nameOrder);
        Sort sort = Sort.by(idOrder,nameOrder);
       //  Sort sort1 = new Sort(Sort.Direction.DESC, "id");
       // Pageable pageable = new PageRequest(pageNum, size);
        Pageable pageable = PageRequest.of(pageNum,size,sort);
        return userRepository.findByUserName(userName,pageable);
    }

    @Override
    @Transactional
    public void modifyUser(String userName, Long id) {
        userRepository.modifyUser(userName,id);
    }

    @Override
    @Transactional
    public void modifyUser(User user) {
        userRepository.modifyUser(user);
    }

    @Override
    public Page<User> queryUserByParam(String userName, final Integer pageNum, Integer size) {
       /* Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path path = root.get("id");
                Predicate predicate = cb.lt(path, 5);
                return predicate;
            }
        };
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageNum, size, sort);
        return userRepository.findAll(specification,pageable);*/
        /**
         * 单个参数
         */
        // 查询 id =
      /*  Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.equal(root.get("id").as(Long.class),1L);
                query.where(predicate);
                return predicate;
            }
        };
        List<User> userList = userRepository.findAll(specification);
        System.out.println(userList);*/

        //查询 in
       /* Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Long> idList = new ArrayList<Long>();
                idList.add(1L);
                idList.add(2L);
                Predicate predicate = root.get("id").in(idList);
                query.where(predicate);
                return predicate;
            }
        };
        List<User> userList = userRepository.findAll(specification);
        System.out.println(userList);*/

        /**
         * 复杂查询 password = xx and (name like or id =)
         */
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p1=cb.like(root.get("userName").as(String.class), "%"+"jimmy"+"%");

                Predicate p2=cb.equal(root.get("id").as(Long.class), 2L);

                Predicate p3=cb.equal(root.get("password").as(String.class), "123456");

                Predicate predicate = cb.and(p3,cb.or(p1,p2));
                query.where(predicate);
                return query.getRestriction();
            }
        };
        List<User> userList = userRepository.findAll(specification);
        System.out.println(userList);
        /**
         * 多个参数
         */
        //查询 id>xx and name like
       /* Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                predicateList.add(cb.gt(root.get("id").as(Long.class),1L));
                predicateList.add(cb.like(root.get("userName").as(String.class), "%" + "lucy" + "%"));
                Predicate[] arrayPredicates = new Predicate[predicateList.size()];
                query.where(predicateList.toArray(arrayPredicates));
                return query.getRestriction();
            }
        };
        List<User> userList = userRepository.findAll(specification);
        System.out.println(userList);
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(1, 1, sort);
        Page<User> userPage = userRepository.findAll(specification,pageable);
        System.out.println(userPage);
*/

        //查询 name=xx or id in()
        /*Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                List<Long> idList = new ArrayList<Long>();
                idList.add(1L);
                idList.add(2L);
                Predicate predicate = cb.or(root.get("id").in(idList),cb.equal(root.get("userName").as(String.class),"Jack1"));
                predicateList.add(predicate);
                Predicate[] arrayPredicates = new Predicate[predicateList.size()];
                query.where(predicateList.toArray(arrayPredicates));
                return query.getRestriction();
            }
        };
                List<User> userList = userRepository.findAll(specification);
                System.out.println(userList);
*/

        return null;
    }
}
