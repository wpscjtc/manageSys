package xxf.other.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import xxf.com.entities.UsersEntity;

import java.util.List;

/**
  *@Author:wangping
  *@Despribtion:从其他其他读取用户信息
  *@Date 2018/4/13 10:46
*/
public interface OtherUserMapper {

    /**
      *@Author:wangping
      *@Despribtion:获取列表用户信息
      *@Date 2018/4/13 11:26
    */
    @SelectProvider(type = OtherUserMapperProvider.class,method = "getAllUsers")//多条件关键注释
    List<UsersEntity> getAllUsers(UsersEntity user);

    @Select("select ID, UserID,Password,Name,ID,Status,OrgId from tUser where UserID=#{userID}")
    UsersEntity getUserForLogin(String userId);

}
