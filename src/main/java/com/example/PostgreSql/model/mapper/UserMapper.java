package com.example.PostgreSql.model.mapper;

import org.springframework.stereotype.Component;

import com.example.PostgreSql.model.UserModel;
import com.example.PostgreSql.model.dto.UserDTO;
import com.example.PostgreSql.model.dto.UserRegisterDTO;

@Component
public class UserMapper {
	public static UserDTO toUserDTO(UserModel user) {
		UserDTO tmp = new UserDTO();
		tmp.setId(user.getId());
		tmp.setUserName(user.getUserName());
		tmp.setEmail(user.getEmail());
		return tmp;
	}
	public static UserModel toUserModel(UserRegisterDTO user) {
		UserModel tmp = new UserModel();
		tmp.setUserName(user.getUserName());
		tmp.setPassword(user.getPassword());
		tmp.setEmail(user.getEmail());
		return tmp;
	}
}
