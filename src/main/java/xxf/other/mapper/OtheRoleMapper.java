package xxf.other.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import xxf.com.entities.RoleEntity;

import java.util.List;

/**
  *@Author:wangping
  *@Despribtion:从其他其他读取用户信息
  *@Date 2018/4/13 10:46
*/
public interface OtheRoleMapper {

    /**
      *@Author:wangping
      *@Despribtion:获取列表用户信息
      *@Date 2018/4/13 11:26
    */
    @SelectProvider(type = OtherRoleMapperProvider.class,method = "getAllRoles")//多条件关键注释
    List<RoleEntity> getAllRoles(RoleEntity role);

    @SelectProvider(type = OtherRoleMapperProvider.class,method = "getRoleByOderId")//多条件关键注释
    List<RoleEntity> getRoleByOderId(RoleEntity role);

    @Select("select o.ID,o.Type,o.Name,o.Category,o.RoleCode from lbrole o,lbOrganization orz,lbMember m where o.ID=m.RoleID and m.OrgID=orz.ID and m.UserId=#{userId} group by o.ID,o.Type,o.Name,o.Category,o.RoleCode")//多条件关键注释
    List<RoleEntity> getRoleByUserId(int userId);

}
