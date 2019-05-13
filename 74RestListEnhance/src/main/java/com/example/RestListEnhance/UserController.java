package com.example.RestListEnhance;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

@RestController
@RequestMapping("/user")
public class UserController {



    private static ArrayList<User> usersList = new ArrayList<User>(3);
    private static int userCount = 3;

    static {
        User user1 = new User(1, "Abhinash", new Date());
        User user2 = new User(2, "Mohit", new Date());
        User user3 = new User(3, "Rahul", new Date());
        usersList.add(user1);
        usersList.add(user2);
        usersList.add(user3);
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public ArrayList<User> findAll() {
        return usersList;
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User findById(@PathVariable Integer id) {
        User resulted_user = null;
        for (User user : usersList) {
            if (user.id == id) {
                resulted_user = user;
            }
        }
        return resulted_user;
    }


    @RequestMapping(value = "",method = RequestMethod.POST)
    public User save(@RequestBody User user) {
        if (null != user && user.id == 0) {
            user.setId((++userCount));
        }
        usersList.add(user);
        return user;
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public User update(@PathVariable Integer id,@RequestBody User user){
        for(int i=0;i<usersList.size();i++){
            User u=usersList.get(i);
            if(u.id==id){
                usersList.set(i,user);
            }
        }
        return user;
    }



    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        Iterator<User> iterator = usersList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().id == id) {
                iterator.remove();
            }
        }
    }
}
