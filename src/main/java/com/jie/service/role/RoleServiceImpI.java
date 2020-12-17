package com.jie.service.role;

import com.jie.dao.BaseDao;
import com.jie.dao.role.RoleDao;
import com.jie.dao.role.RoleDaoImpI;
import com.jie.pojo.Role;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;

public class RoleServiceImpI implements RoleService{
    private RoleDao roleDao;
    public RoleServiceImpI(){
        roleDao=new RoleDaoImpI() ;
        }
    @Override
    public List<Role> getRoleList() {
        Connection connection=null;
        List <Role> roleList =null;
        try {
            connection= BaseDao.getConnection();
            roleList=roleDao.getRoleList(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
            return roleList;

    }
}
