package com.jie.service.user;
import com.jie.dao.BaseDao;
import com.jie.dao.user.UserDao;
import com.jie.dao.user.UserDaoImpI;
import com.jie.pojo.User;
import com.sun.deploy.uitoolkit.impl.fx.AppletStageManager;
import org.junit.Test;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

public class UserServicesImpI implements UserService {
    //业务层都会调用dao层，所以我们要引入dao层；
    private UserDao userDao;

    public UserServicesImpI() {
        userDao = new UserDaoImpI();

    }

    @Override
    public boolean add(User user) {
        // TODO Auto-generated method stub

        boolean flag = false;
        Connection connection = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            //开启JDBC事务管理
            int updateRows = userDao.add(connection,user);
            connection.commit();
            if(updateRows > 0){
                flag = true;
                System.out.println("add success!");
            }else{
                System.out.println("add failed!");
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }finally{
            //在service层进行connection连接的关闭
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Override
     public User login(String userCode, String password) {
        Connection connection = null;
        User user = null;

        try {
            connection = BaseDao.getConnection();
            //通过业务层调用相应的具体的数据库操作
            user = userDao.getLoginUser(connection, userCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
//            if(null != user){
//                if(!user.getUserPassword().equals(userPassword))
//                    user = null;
        }
        return user;
    }

    @Override
     public boolean updatePwd(int id, String pwd) {
        System.out.println("UserServlet:" + pwd);
        Connection connection = null;
        boolean flag = false;
        // connection = BaseDao.getConnection();
        try {
            connection = BaseDao.getConnection();
            //踩坑，先try catch
            if (userDao.updatePwd(connection, id, pwd) > 0) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    //查询记录数
    @Override
     public int getUserCount(String username, int userRole) {
        Connection connection = null;
        int count = 0;
        try {
            connection = BaseDao.getConnection();
            //
            count = userDao.getUserCount(connection, username, userRole);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection,null,null);
        }
        return  count;
    }

    @Override
     public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize) {
       Connection connection=null;
       List<User> userList =null;
        System.out.println("queryUserName ---->"+ queryUserName);
        System.out.println("queryUserRole ---->"+queryUserRole);
        System.out.println("currentPageNo ---->"+ currentPageNo);
        System.out.println("pageSize ---->"+ pageSize);
        try {
            //
            connection=BaseDao.getConnection();
            userList=userDao.getUserList(connection,queryUserName,queryUserRole,currentPageNo,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return userList;
        }

    @Override
      public User selectUserCodeExist(String userCode) {
          // TODO Auto-generated method stub
          Connection connection = null;
          User user = null;
          try {
              connection = BaseDao.getConnection();
              user = userDao.getLoginUser(connection, userCode);
          } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }finally{
              BaseDao.closeResource(connection, null, null);
          }
          return user;
      }

    @Override
      public boolean deleteUserById(Integer delId) {
          // TODO Auto-generated method stub
          Connection connection = null;
          boolean flag = false;
          try {
              connection = BaseDao.getConnection();
              if(userDao.deleteUserById(connection,delId) > 0) {
                  flag = true;
              }
          } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }finally{
              BaseDao.closeResource(connection, null, null);
          }
          return flag;
      }

    @Override
      public User getUserById(String id) {
          // TODO Auto-generated method stub
          User user = null;
          Connection connection = null;
          try{
              connection = BaseDao.getConnection();
              user = userDao.getUserById(connection,id);
          }catch (Exception e) {
              // TODO: handle exception
              e.printStackTrace();
              user = null;
          }finally{
              BaseDao.closeResource(connection, null, null);
          }
          return user;
      }

    @Override
      public boolean modify(User user) {
          // TODO Auto-generated method stub
          Connection connection = null;
          boolean flag = false;
          try {
              connection = BaseDao.getConnection();
              if(userDao.modify(connection,user) > 0) {
                  flag = true;
              }
          } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }finally{
              BaseDao.closeResource(connection, null, null);
          }
          return flag;
      }




  }



