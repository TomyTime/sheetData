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

    public void addUser(User user) {
        baseDao.save(user);
    }

    public void updateUser(User user) {
        baseDao.update(user);
    }

    public boolean deleteUser(String id) {
        return baseDao.delOrUpdate("delete User u where u.Id = ?", new String[]{id});
    }
}
