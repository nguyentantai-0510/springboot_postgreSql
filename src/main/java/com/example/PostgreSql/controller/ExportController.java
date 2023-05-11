package com.example.PostgreSql.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExportController {
	@GetMapping(value = "/getFile")
public ResponseEntity<byte[]> downloadExcel() throws IOException {
	File file = new ClassPathResource("/Book1.xlsx").getFile();
	byte[] fileContent = Files.readAllBytes(file.toPath());
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	headers.setContentDispositionFormData("attachment", file.getName());
	return ResponseEntity.ok().headers(headers).body(fileContent);
	}
}
