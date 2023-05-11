package com.example.PostgreSql.service.Implement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.PostgreSql.model.UserModel;
import com.example.PostgreSql.model.dto.UserDTO;
import com.example.PostgreSql.model.dto.UserRegisterDTO;
import com.example.PostgreSql.model.mapper.UserMapper;
import com.example.PostgreSql.repository.UserRepository;
import com.example.PostgreSql.service.UserService;

@Service
public class UserServiceIPM implements UserService {
	@Autowired
	UserRepository repository;

	@Autowired
	UserMapper mapper;

	@Override
	public List<UserDTO> getListUser() {
		List<UserDTO> list = new ArrayList<UserDTO>();
		for (UserModel users : repository.findAll()) {
			list.add(mapper.toUserDTO(users));
			System.out.println(users);
		}
		return list;
	}

	@Override
	public String registerUser(UserRegisterDTO user) {
		// TODO Auto-generated method stub
		try {
			System.out.println(mapper.toUserModel(user));
			repository.save(mapper.toUserModel(user));
			return "Account has registed";
		} catch (Exception ex) {
			System.err.println(ex);
			return "Can not register account";
		}
	}

	@Override
	public String deleteUser(String id) {
		// TODO Auto-generated method stub
		return "";
	}
}
