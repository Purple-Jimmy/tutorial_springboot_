package com.tutorial.model.mapper;

import com.tutorial.domain.Account;
import com.tutorial.domain.User;
import com.tutorial.model.UserAccountModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author jimmy
 * @date 2019-03-1922:44
 */
@Mapper(componentModel = "spring")
public interface UserAccountMapper {

    @Mappings({
            @Mapping(target = "userId",source = "user.id"),
            @Mapping(target = "accountId",source = "account.id"),
            @Mapping(target = "userName",source = "user.userName"),
            @Mapping(target = "email",source = "account.email")})
    UserAccountModel userAccountModel(User user, Account account);






    default Boolean convert2Bool(Integer value) {
        if (value == null || value < 1) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }
}
