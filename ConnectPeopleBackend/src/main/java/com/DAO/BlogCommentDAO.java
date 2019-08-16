package com.DAO;

import java.util.List;

import com.WeChat.Blogcomment;

public interface BlogCommentDAO
{
	public boolean addcomment(Blogcomment comment);
	public boolean deletecomment(Blogcomment comment);
	public List<Blogcomment> listBlogcomment(int blogcommentid);
	public Blogcomment getBlogcomment(int blogcommentid);
}
