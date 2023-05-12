package com.example.PostgreSql.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.PostgreSql.model.EmployeeModel;
import com.example.PostgreSql.repository.EmployeeRepository;
import com.example.PostgreSql.util.ExcelUntils;

@Service
public class ExcelService {
	@Autowired
	EmployeeRepository repository;
	
	public ByteArrayInputStream  load() {
		 List<EmployeeModel> employee = repository.findAll();

			try {
				ByteArrayInputStream in;
				in = ExcelUntils.writeToExcel(employee);
				return in;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}
	
	 public List<EmployeeModel> getAllTutorials() { 
		    return repository.findAll();
		  }
}
