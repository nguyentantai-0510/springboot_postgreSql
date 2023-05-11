package com.example.PostgreSql.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.PostgreSql.helper.ExcelHelper;
import com.example.PostgreSql.model.dto.EmployeeDTO;
import com.example.PostgreSql.model.dto.EmployeeRegisterDTO;
import com.example.PostgreSql.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	EmployeeService service;

	@GetMapping
	public List<EmployeeDTO> listEmployee() {
		return service.listEmployee();
	}

	@GetMapping("{id}")
	public EmployeeDTO getEmployee(@PathVariable String id) {
		return service.getEmployeeById(id);
	}

	@PostMapping("")
	public String registerEmployee(@RequestBody EmployeeRegisterDTO register) {
		if (service.registerEmployee(register)) {
			return "Successful registered";
		}
		return "Unable to register";
	}

	@DeleteMapping("{id}")
	public String deleteEmployee(@PathVariable String id) {
		if (service.removeEmployee(id)) {
			return "Employee removed";
		}
		return "Can not remove Employee";
	}

	@PatchMapping("{id}")
	public String editEmployee(@PathVariable String id, @RequestBody EmployeeRegisterDTO update) {
		if (service.editEmployee(id, update)) {
			return "User has updated";
		}
		return "Can not update user";
	}

	@PostMapping("/import")
	public String importExcel(@RequestParam("file") MultipartFile file) {
		String message = "";
		if (ExcelHelper.isExcelFormat(file)) {
			if (service.excelToData(file)) {
				message = "Uploaded the file successfully " + file.getOriginalFilename();
			} else {
				message = "Can not import excel to database";
			}

		} else {
			message = "Please upload the excel file!";
		}
		return message;
	}
}
