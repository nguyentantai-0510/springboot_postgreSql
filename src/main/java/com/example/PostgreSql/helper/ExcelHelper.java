package com.example.PostgreSql.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.example.PostgreSql.model.EmployeeModel;

public class ExcelHelper {
	public static String EXCELTYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String SHEET = "Sheet1";

	public static boolean isExcelFormat(MultipartFile file) {
		if (!EXCELTYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	public static List<EmployeeModel> excelToEmployee(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);
			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();

			List<EmployeeModel> employees = new ArrayList<EmployeeModel>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

//				Skip the header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				Iterator<Cell> cellsInRow = currentRow.iterator();
				EmployeeModel employee = new EmployeeModel();

				int cellIndex = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					switch (cellIndex) {
					case 0:
						break;
					case 1:
						if (currentCell.getStringCellValue() != "") {
							employee.setUserName(currentCell.getStringCellValue().toString());
						}
						break;
					case 2:
						if (currentCell.getStringCellValue() != "") {
							employee.setPassword("123");
						}
						break;
					case 3:
						if (currentCell.getStringCellValue() != "") {
							employee.setEmail(currentCell.getStringCellValue().toString());
						}
						break;
					case 4:
						if(currentCell.getStringCellValue() != "") {
							employee.setRole(currentCell.getStringCellValue().toString());
						}
						
						break;
					default:
						break;
					}
					cellIndex++;
				}
//				if(!Objects.isNull(employees)) {
//					employees.add(employee);					
//				}
				System.out.println(employee);
			}
			workbook.close();
			return employees;
		} catch (Exception ex) {
//			System.out.println(ex);
			throw new RuntimeException("fail to parse Excel file: " + ex.getMessage());
		}
	}

}
