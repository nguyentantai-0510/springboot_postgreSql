package com.example.PostgreSql.service;

import java.util.List;


import com.example.PostgreSql.model.dto.UserDTO;
import com.example.PostgreSql.model.dto.UserRegisterDTO;

public interface UserService {
	public List<UserDTO> getListUser();
	public String registerUser(UserRegisterDTO user);
	public String deleteUser(String id);
}
