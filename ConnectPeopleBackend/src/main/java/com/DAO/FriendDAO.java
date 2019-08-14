package com.DAO;

import java.util.List;

import com.WeChat.Friend;
import com.WeChat.UserDetails;

public interface FriendDAO 
{
 public List<Friend> showPendingFriendRequest(String username);
 public List<Friend> showFriendList(String username);
 public List<String> showSuggestedFriend(String username);
 
 public boolean deleteFriendRequest(int friendid);
 public boolean acceptFriendRequest(int friendid);
 public boolean sendFriendRequest(Friend friend);
 
}
