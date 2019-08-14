package com.DAO;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.WeChat.Blog;

public interface BlogDAO {
   public boolean addBlog(Blog blog);
   public boolean updateBlog(Blog blog);
   public boolean deleteblog(Blog blog);
   public Blog getBlog(int blogid);
   public List<Blog> listBlog();
   public boolean incrementLikes(int blogid);
   public boolean incrementDisLikes(int blogid);
   public boolean approveBlog(Blog blog);
   public boolean rejectBlog(Blog blog);
   
}
