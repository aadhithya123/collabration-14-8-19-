package com.DAO;

import java.util.List;

import com.WeChat.Forum;

public interface ForumDAO {

 public boolean addForum(Forum forum);
 public Forum getForum(int forumid); 
 public boolean delete(Forum forum);
 public boolean updateForum(Forum forum);
 List<Forum> listForum();
}
