package xxf.com.mapper;

import org.apache.ibatis.annotations.*;
import xxf.com.entities.AuthEntity;
import xxf.com.entities.AuthRole;

import java.util.List;

/**
  *@Author:wangping
  *@Despribtion:读取权限信息
  *@Date 2018/4/13 10:46
*/
public interface OtheAuthMapper {

    /**
      *@Author:wangping
      *@Despribtion:获取列表用户信息
      *@Date 2018/4/13 11:26
    */
    @SelectProvider(type = OtherAuthMapperProvider.class,method = "getAllAuths")//多条件关键注释
    List<AuthEntity> getAllAuths (AuthEntity auth);

    @SelectProvider(type = OtherAuthMapperProvider.class,method = "getAuthByRoleId")
    List<AuthEntity> getAuthByRoleId(int roleId,int orgId);

    @SelectProvider(type = OtherAuthMapperProvider.class,method = "getAuthByRoleIds")
    List<AuthEntity> getAuthByRoleIds(String roleIds,Integer orgId);

    @Select("SELECT AUTHID,AuthName,AuthType,AuthDesc,AuthWay,authMethod FROM TAUTH ta where AUTHID=#{roleId}")
    List<AuthEntity> getAuthById(int authId);

    @Select("SELECT count(1) FROM RROLEAUTH ta where RoleId=#{roleId} and AuthId=#{authId} and OrgId =#{orgId}")
    int getNumByRolIdAndAuthId(AuthRole role);

    @Insert("insert into RROLEAUTH(RoleId,AuthId,OrgId) values(#{roleId},#{authId},#{orgId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addRoleAuth(AuthRole role);

    @Delete("delete from RROLEAUTH where RoleId=#{roleId} and AuthId=#{authId} and OrgId=#{orgId}")
    int delRoleAuth(AuthRole role);

    @DeleteProvider(type = OtherAuthMapperProvider.class,method = "delRoleAuthByAuthId")//多条件关键注释
    int delRoleAuthByAuthId(String authIds);

    @Insert("insert into TAUTH(authName,authType,authDesc,authWay,authMethod) values(#{authName},#{authType},#{authDesc},#{authWay},#{authMethod})")
    @Options(useGeneratedKeys = true, keyProperty = "authID")
    int addAuth(AuthEntity auth);

    @Update("update TAUTH set authName=#{authName},authType=#{authType},authDesc=#{authDesc},authWay=#{authWay},authMethod=#{authMethod} where AuthId=#{AuthID} ")
    int updAuth(AuthEntity auth);

    @DeleteProvider(type = OtherAuthMapperProvider.class,method = "delAuth")//多条件关键注释
    int delAuth(String authIds);

}
