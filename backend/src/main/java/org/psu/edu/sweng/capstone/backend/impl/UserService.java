package org.psu.edu.sweng.capstone.backend.impl;

import java.util.List;

import org.psu.edu.sweng.capstone.backend.dto.UserDTO;

public interface UserService {
	List<UserDTO> getUsers();
	UserDTO getUser(String userName);
	String deleteUser(String userName);
	String updateUser(String userName, UserDTO user);
	String createUser(String userName, UserDTO user);
}