package com.example.PostgreSql.model.mapper;

import org.springframework.stereotype.Component;

import com.example.PostgreSql.model.EmployeeModel;
import com.example.PostgreSql.model.dto.EmployeeDTO;
import com.example.PostgreSql.model.dto.EmployeeRegisterDTO;

@Component
public class EmployeeMapper {
	public static EmployeeDTO toEmployeeDTO(EmployeeModel model) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setUserName(model.getUserName());
		dto.setEmail(model.getEmail());
		dto.setRole(model.getRole());
		return dto;
	}

	public static EmployeeModel toEmployeeModel(EmployeeRegisterDTO dto) {
		EmployeeModel model = new EmployeeModel();
		model.setUserName(dto.getUserName());
		model.setEmail(dto.getEmail());
		model.setPassword(dto.getPassword());
		model.setRole("Employee");
		System.out.println("To Entity: " + model);
		return model;
	}
}
