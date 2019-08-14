package com.DAO;

import com.WeChat.UserDetails;

public interface UserDetailsDAO 
{
  public boolean addUser(UserDetails user);
  public boolean updateUser(UserDetails user);
  public UserDetails getUserDetails(int userid);
  public UserDetails checkUserCredential(UserDetails user);
  
}
