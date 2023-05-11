package com.example.PostgreSql.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.PostgreSql.model.UserModel;
import com.example.PostgreSql.model.dto.UserRegisterDTO;
import com.example.PostgreSql.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService service;

	@GetMapping
	public ResponseEntity<?> getListUser() {
		return ResponseEntity.ok(service.getListUser());
	}

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserRegisterDTO register) {
		return ResponseEntity.ok(service.registerUser(register));
	}

	@PatchMapping
	public ResponseEntity<?> updateUser() {
		return null;
	}

	@DeleteMapping
	public ResponseEntity<?> deleteUser() {
		return null;
	}

	@PostMapping("/import")
	public ResponseEntity<?> importExcelFile(@RequestParam("file") MultipartFile files) {
		try {

//			HttpStatus status = HttpStatus.OK;
			List<UserModel> user = new ArrayList<UserModel>();

			XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);
			for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {
				if (i > 0) {
					XSSFRow row = worksheet.getRow(i);
					System.out.println(row.getCell(0));
					System.out.println(row.getCell(1));
					System.out.println(row.getCell(2));
					System.out.println(row.getCell(3));
//					System.out.println("this is row " + i + " : " + row);
				}
			}
		} catch (Exception ex) {
//			System.err.println(ex);
		}
		return null;
	}
}
