package com.tutorial.model.mapper;

import com.tutorial.domain.User;
import com.tutorial.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * spring方式
 * @Mapping(target = "模型属性",source = "实体属性")
 * @Author: jimmy
 * @Date: 2019/3/19
 */
@Mapper(componentModel = "spring")
public interface UserModelMapper {


    @Mapping(target = "name",source = "userName")
    @Mapping(target = "test1",ignore = true)
    UserModel userModel(User user);

/*
    @Mappings({
            @Mapping(source = "type.name",target = "typeName"),
            @Mapping(source = "good.id",target = "goodId"),
            @Mapping(source = "good.title",target = "goodName"),
            @Mapping(source = "good.price",target = "goodPrice"),
            @Mapping(source = "birthday", target = "birthDateFormat", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })*/
    List<UserModel> userModelList(List<User> userList);
}
