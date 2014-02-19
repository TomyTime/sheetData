package com.canteen.service;

import com.canteen.common.dao.BaseDao;
import com.canteen.entity.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: lixy
 * Mail: admin@TomyTime.com
 * Date: 14-2-16 下午1:13
 */
@Component("userService")
public class UserServiceImpl {
    @Resource(name="baseDao")
    private BaseDao baseDao;

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    public User getUserById(String id){
        String hql = "from User u where u.Id=?";

        return (User) baseDao.find(hql, new String[]{id});
    }

   /* public List<User> getUser(String type) {
        String hql = "from User g where g.Type=?";

        return baseDao.find(hql, new String[]{type});
    }*/

    public List<User> getAllUser() {
        return baseDao.find("from User");
    }

    public boolean addUser(User user) {
        try{
            baseDao.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateUser(User user) {
        try{
            baseDao.update(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deleteUser(String id) {
        return baseDao.delOrUpdate("delete User u where u.Id = ?", new String[]{id});
    }
}
