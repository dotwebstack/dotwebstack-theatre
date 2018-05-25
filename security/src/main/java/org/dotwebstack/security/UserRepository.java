package org.dotwebstack.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("security")
public class UserRepository {

  private String authentication;
  private List<User> users = new ArrayList<User>();
  private List<ProtectedPath> protect = new ArrayList<ProtectedPath>();
  
  public void setAuthentication(String authentication) {
    this.authentication = authentication;
  }

  public String getAuthentication() {
    return authentication;
  }
  
  public static class User {

    private String user;
    private String password;
    private String role;

    public String getUser() {
      return user;
    }

    public void setUser(String user) {
      this.user = user;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }

    
    public String getRole() {
      return role;
    }

    public void setRole(String role) {
      this.role = role;
    }
    
  }
  
  public static class ProtectedPath {
    
    private String path;
    private String role;
    
    public String getPath() {
      return path;
    }
    
    public void setPath(String path) {
      this.path = path;
    }
    
    public String getRole() {
      return role;
    }
    
    public void setRole(String role) {
      this.role = role;
    }
    
  }

  public List<User> getUsers() {
    return users;
  }

  public List<ProtectedPath> getProtect() {
    return protect;
  }

  public User findByUsername(String username) {
    //Not so good. Implementation should be a HashMap...
    User found = null;
    for (User user : users) {
      if (username.equals(user.getUser())) {
        found = user;
      }
    }
    return found;
  }

}