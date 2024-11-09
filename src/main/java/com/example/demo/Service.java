package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class Service {

  @Autowired
  DAO dao;

  @GetMapping("/")
  public String fun1() {
    return "This is Home Page";
  }

  @GetMapping("/welcome/{name}")
  public String fun2(@PathVariable("name") String name) {
    return "Welcome " + name;
  }

  @PostMapping("/user")
  public String fun3(@RequestBody User user) {
    dao.insert(user);
    return "User Inserted";
  }

  @GetMapping("/user")
  public String fun4(@RequestParam("email") String email) {
    return dao.findUser(email).toString();
  }

  @GetMapping("/page")
  public String fun5(@RequestParam("page") int page,@RequestParam("limit") int limit) {
    return dao.findPage(page,limit).toString();
  }

  @GetMapping("/all")
  public String fun6() {
    return dao.find().toString();
  }

  @DeleteMapping("/delete")
  public String fun7(@RequestParam("email") String email) {
    return dao.deleteUser(email);
  }

  @PutMapping("/update")
  public String fun8(@RequestBody User user) {
    dao.updateUser(user);
    return "User Updated";
  }
  
  @PostMapping("/login")
  public User fun9( @RequestBody User user) {
	  User user2 = dao.findUser(user.getEmail());
	  if(user2==null) {
		  System.out.println("user2 is null");
		  return user;
	  }else if(user.password.equals(user2.password)) {
		  return user2;
	  }else {
		  return user;
	  }
  }
  
}
