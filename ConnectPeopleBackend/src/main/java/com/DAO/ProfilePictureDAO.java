package com.DAO;

import com.WeChat.ProfilePicture;

public interface ProfilePictureDAO {

	public boolean addProfilePicture(ProfilePicture pic);
	public ProfilePicture getProfilePicture(String username);
}
