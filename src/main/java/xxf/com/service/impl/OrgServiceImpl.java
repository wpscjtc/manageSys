package xxf.com.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xxf.com.entities.OrgnazationEntity;
import xxf.com.entities.RoleEntity;
import xxf.com.service.OrgService;
import xxf.com.utils.GsonUtil;
import xxf.com.vo.Stat;
import xxf.com.vo.TreeForBootTrap;
import xxf.other.mapper.OtherOrgMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrgServiceImpl implements OrgService {
    @Autowired
    private OtherOrgMapper otherOrgMapper;

    /**
      *@Author:wangping
      *@Despribtion:获取所有组织
      *@Date 2018/4/22 10:45
    */
    @Override
    public String getOrgList(OrgnazationEntity orgnazationEntity,int pageNum,int pageSize) {
        String resultGson;
        try{
            Page<RoleEntity> page =PageHelper.startPage(pageNum,pageSize,true);
            List<OrgnazationEntity> orgs=otherOrgMapper.getOrgList(orgnazationEntity );
            resultGson=GsonUtil.setResult(1,page.toPageInfo()).toString();
        }catch (Exception e){
            resultGson=GsonUtil.setResult(0,e.getMessage()).toString();
        }
        return resultGson;
    }

    @Override
    public String getOrgTree() {
        String resultGson;
        List<TreeForBootTrap> allByGrade2=new ArrayList<>();
        try{
            //取总共有多少层级
            int totalGrade=otherOrgMapper.getTotalGrade();
            TreeForBootTrap root=new TreeForBootTrap();
            for(int i=0;i<=totalGrade;i++){
                List<TreeForBootTrap> allByGrade1=otherOrgMapper.getOrgByGrade(totalGrade-i);
                List<TreeForBootTrap> sunByGrade2=new ArrayList<>();
                for(int n=0;n<allByGrade1.size();n++){
                    List<TreeForBootTrap> sunByGrade1=new ArrayList<>();
                    TreeForBootTrap one1=allByGrade1.get(n);
                    for(int x=0;x<allByGrade2.size();x++){
                        TreeForBootTrap one2=allByGrade2.get(x);

                        if(one2.getFid().equals(one1.getId())){
                            sunByGrade1.add(one2);
                        }
                    }
                    one1.setStat(new Stat(false,false,false,false));
                    one1.setNodes(sunByGrade1);
                    one1.setHref("javascript:getAuths("+one1.getId()+")");
                    sunByGrade2.add(one1);
                }
                allByGrade2=sunByGrade2;
            }
            resultGson=GsonUtil.setResult(1,allByGrade2).toString();
        }catch (Exception e){
            resultGson=GsonUtil.setResult(0,e.getMessage()).toString();
        }
        return resultGson;
    }

    /*private TreeForBootTrap getTree(List<TreeForBootTrap> allByGrade1,List<TreeForBootTrap> allByGrade2){
        List<List<TreeForBootTrap>> allByGrade=new ArrayList<List<TreeForBootTrap>>();
        List<TreeForBootTrap> allByGradeOne=otherOrgMapper.getOrgByGrade(totalGrade-i);
        allByGrade.add(allByGrade);
        return tfbt;
    }*/

}
