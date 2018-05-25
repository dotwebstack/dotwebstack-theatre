package org.dotwebstack.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  public MyUserDetailsService() {
    super();
  }

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    
    final UserRepository.User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("No user found with username: " + username);
    }

    return new org.springframework.security.core.userdetails.User(user.getUser(),
      user.getPassword(), true, true, true, true, getGrantedAuthorities(user));

  }

  private final List<GrantedAuthority> getGrantedAuthorities(UserRepository.User user) {
    final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority(user.getRole()));
    return authorities;
  }

}
