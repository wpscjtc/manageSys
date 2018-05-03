package xxf.other.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import xxf.com.entities.OrgnazationEntity;
import xxf.com.vo.TreeForBootTrap;

import java.util.List;

public interface OtherOrgMapper {

   @SelectProvider(type = OtherOrgMapperProvider.class,method = "getOrgList")//多条件关键注释
   List<OrgnazationEntity> getOrgList(OrgnazationEntity orgnazationEntity);

    @Select("SELECT max(len(FDNCode)-len(replace(FDNCode,'.','')) ) from lbOrganization")
    int getTotalGrade();

    @Select("SELECT  ID,FID,Name text from lbOrganization where len(FDNCode)-len(replace(FDNCode,'.',''))=#{grade} ")
    List<TreeForBootTrap> getOrgByGrade(int grade);

    @Select("SELECT  ID,FID,Name text from lbOrganization where FID=#{fid}")
    List<TreeForBootTrap> getOrgByFid(int fid);

}
