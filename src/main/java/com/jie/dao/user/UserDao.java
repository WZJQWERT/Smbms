package com.jie.dao.user;

import com.jie.pojo.Role;
import com.jie.pojo.User;

import java.sql.Connection;
import java.util.List;

public interface UserDao {
  //得到要登陆的用户
  public User getLoginUser(Connection connection,String userCode) throws Exception;


  //修改当前用户密码
  public int updatePwd(Connection connection,int id ,String password) throws Exception;

  //查询用户总数

  public int getUserCount(Connection connection, String username, int userRole) throws Exception;

  //获取用户列表,条件查询
  public List<User> getUserList(Connection connection,String userName,int userRole,int currentPageNO,int pageSize)throws Exception;

  //获取角色列表
//  public List<Role> getRoleList (Connection connection)throws Exception;

  public int add(Connection connection, User user)throws Exception;

  public User getUserById(Connection connection, String id)throws Exception;

  public int modify(Connection connection, User user)throws Exception;

  public int deleteUserById(Connection connection, Integer delId)throws Exception;
}
