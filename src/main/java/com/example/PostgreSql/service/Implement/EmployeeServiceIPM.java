package com.example.PostgreSql.service.Implement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.PostgreSql.helper.ExcelHelper;
import com.example.PostgreSql.model.EmployeeModel;
import com.example.PostgreSql.model.dto.EmployeeDTO;
import com.example.PostgreSql.model.dto.EmployeeRegisterDTO;
import com.example.PostgreSql.model.mapper.EmployeeMapper;
import com.example.PostgreSql.repository.EmployeeRepository;
import com.example.PostgreSql.service.EmployeeService;

@Service
public class EmployeeServiceIPM implements EmployeeService {
	@Autowired
	EmployeeRepository repository;

	@Autowired
	EmployeeMapper mapper;

	@Override
	public List<EmployeeDTO> listEmployee() {
		// TODO Auto-generated method stub
		List<EmployeeDTO> employeeList = new ArrayList<EmployeeDTO>();
		for (EmployeeModel employees : repository.findAll()) {
			employeeList.add(mapper.toEmployeeDTO(employees));
		}
		return employeeList;
	}

	@Override
	public boolean registerEmployee(EmployeeRegisterDTO employee) {
		// TODO Auto-generated method stub
		try {
			repository.save(mapper.toEmployeeModel(employee));
			return true;
		} catch (Exception ex) {
			System.err.println(ex);
			return false;
		}
	}
	@Override
	public boolean editEmployee(String id, EmployeeRegisterDTO update) {
		// TODO Auto-generated method stub
		try {

			EmployeeModel employee = repository.findById(Integer.parseInt(id)).orElseThrow();
			EmployeeModel updateEmployee = mapper.toEmployeeModel(update);
			updateEmployee.setId(Integer.parseInt(id));
			repository.save(updateEmployee);
			System.out.println("ehe");
			return true;
		} catch (Exception ex) {
			System.err.println(ex);
			return false;

		}

	}

	@Override
	public boolean removeEmployee(String id) {
		// TODO Auto-generated method stub
		try {
			repository.deleteById(Integer.parseInt(id));
			return true;
		} catch (Exception ex) {

			return false;
		}
	}

	@Override
	public EmployeeDTO getEmployeeById(String id) {
		// TODO Auto-generated method stub
		try {
			EmployeeDTO employee = mapper.toEmployeeDTO(repository.findById(Integer.parseInt(id)).orElseThrow());
			return employee;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public boolean excelToData(MultipartFile file) {
		try {
			List<EmployeeModel> employees = ExcelHelper.excelToEmployee(file.getInputStream());
			repository.saveAll(employees);
			return true;
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			return false;
		}
	}

}
