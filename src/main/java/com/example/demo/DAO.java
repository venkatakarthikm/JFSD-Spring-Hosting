package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class DAO {

  @Autowired
  UserInterface repo;

  public void insert(User u1) {
    repo.save(u1);
  }

  public User findUser(String email) {
    return repo.findByEmail(email);
  }

  public List<User> findPage(int page, int limit) {
    Sort sort = Sort.by(Sort.Direction.DESC, "name");
    Pageable pageable = PageRequest.of(page, limit, sort);
    return repo.findAll(pageable).toList();
  }

  public List<User> find() {
    return repo.findAll();
  }

  public String deleteUser(String email) {
    repo.delete(repo.findByEmail(email));
    return "Deleted " + email;
  }
  
  public void updateUser(User user) {
	    User existingUser = repo.findByEmail(user.getEmail());
	    if (existingUser != null) {
	        existingUser.setName(user.getName());
	        existingUser.setRole(user.getRole());
	        existingUser.setEmail(user.getEmail()); // Update email
	        existingUser.setPassword(user.getPassword());
	        repo.save(existingUser); // Save changes
	    }
	}
}
