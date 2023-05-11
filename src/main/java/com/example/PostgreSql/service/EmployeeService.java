package com.example.PostgreSql.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.PostgreSql.model.dto.EmployeeDTO;
import com.example.PostgreSql.model.dto.EmployeeRegisterDTO;

public interface EmployeeService {
	public List<EmployeeDTO> listEmployee();

	public boolean registerEmployee(EmployeeRegisterDTO employee);

	public boolean editEmployee(String id, EmployeeRegisterDTO employee);

	public boolean removeEmployee(String id);

	public EmployeeDTO getEmployeeById(String id);

	public boolean excelToData(MultipartFile file);
}
