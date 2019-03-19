package com.tutorial.model.mapper;

import com.tutorial.domain.User;
import com.tutorial.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Mapping(target = "模型属性",source = "实体属性")
 * @Author: jimmy
 * @Date: 2019/3/19
 */
@Mapper
public interface UserModelMapper {
    UserModelMapper instance = Mappers.getMapper(UserModelMapper.class);


    @Mapping(target = "name",source = "userName")
    @Mapping(target = "test1",ignore = true)
    UserModel userModel(User user);

    List<UserModel> userModelList(List<User> userList);
}
