package com.jie.dao.role;

import com.jie.dao.BaseDao;
import com.jie.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public  class RoleDaoImpI  implements  RoleDao{
        @Override
        public List<Role> getRoleList(Connection connection) throws Exception {
        PreparedStatement pstm =null;
        ResultSet rs =null;
        List<Role> roleList =new ArrayList<Role>();


        if (connection!=null){
            //踩坑
            String sql ="select * from smbms_role";
            Object[] params ={};
           rs= BaseDao.execute(connection,pstm,rs,sql,params);


            while(rs.next()){
                Role _role =new Role();
                _role.setId( rs.getInt("id"));
                //踩坑
                _role.setRoleCode(rs.getString("roleCode"));
                _role.setRoleName( rs.getString("roleName"));
                roleList.add(_role);
            }
            BaseDao.closeResource(null,pstm,rs);
        }
        return roleList;
    }
}


