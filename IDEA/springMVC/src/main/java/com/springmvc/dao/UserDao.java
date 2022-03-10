package com.springmvc.dao;

import com.springmvc.bean.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
// 持久层注解
@Repository
public class UserDao {
    private static Map<Integer, User> users=null;
    static {
        users=new HashMap<Integer, User>();
        users.put(1001,new User("n1","aaa",1001,"男",12,"111@qq.com", new String[]{"排球", "足球"}));
        users.put(1002,new User("n2","bbb",1002,"男",22,"121@qq.com", new String[]{"排球", "台球"}));
        users.put(1003,new User("n4","ccc",1003,"女",11,"113@qq.com", new String[]{"篮球", "足球"}));
        users.put(1004,new User("n6","ddd",1004,"女",12,"141@qq.com", new String[]{"排球", "羽毛球"}));
        users.put(1005,new User("n3","eee",1005,"女",112,"511@qq.com", new String[]{"排球", "乒乓球"}));
        users.put(1006,new User("n5","fff",1006,"男",122,"161@qq.com", new String[]{"网球", "足球"}));
    }

    private static Integer initId=1007;

    public void save(User user){
        if(user.getId()==null){
            user.setId(initId++);
        }
        users.put(user.getId(),user);
    }

    public Collection<User> getAll(){
        return users.values();
    }

    public User get(Integer id){
        return users.get(id);
    }

    public void delete(Integer id){
        users.remove(id);
    }
}
