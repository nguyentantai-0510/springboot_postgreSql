package com.example.PostgreSql.util;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@Slf4j
@Component
public class ExcelUntils {
	public  <T> ByteArrayInputStream writeToExcel(List<T> data) {
        ByteArrayOutputStream fos = null;
        XSSFWorkbook workbook = null;
        ByteArrayInputStream inputStream = null;

        try {
            workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet();
            List<String> fieldNames = getFieldNamesForClass(data.get(0).getClass());
            // start create header
            int rowCount = 0;
            int columnCount = 0;
            Row row = sheet.createRow(rowCount++);
            for (String fieldName : fieldNames) {
                Cell cell = row.createCell(columnCount++);
                cell.setCellValue(fieldName);
            }
            // end create header
            // start writing data
            Class<?> clazz = data.get(0).getClass();
            for (T t : data) {
                row = sheet.createRow(rowCount++);
                columnCount = 0;
                for (String fieldName : fieldNames) {
                    Cell cell = row.createCell(columnCount);
                    Method method;
                    try {
                        method = clazz.getMethod("get" + capitalize(fieldName));
                    } catch (NoSuchMethodException nme) {
                        method = clazz.getMethod("get" + fieldName);
                    }
                    Object value = method.invoke(t, (Object[]) null);
                    if (value != null) {
                        if (value instanceof String) {
                            cell.setCellValue((String) value);
                        } else if (value instanceof Long) {
                            cell.setCellValue((Long) value);
                        } else if (value instanceof Integer) {
                            cell.setCellValue((Integer) value);
                        } else if (value instanceof Double) {
                            cell.setCellValue((Double) value);
                        }else if (value instanceof Date){
                            Locale locale = new Locale("vi", "VN");
                            DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
                            String date = dateFormat.format(value);
                            cell.setCellValue(date);
                        }
                    }
                    columnCount++;
                }
            }
            fos = new ByteArrayOutputStream();
            workbook.write(fos);
            fos.flush();
            inputStream = new ByteArrayInputStream(fos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                log.error("Exceptions in Excel Utils ByteArrayOutPutStream: " + e);
            }
            try {
                if (workbook != null) {
                    workbook.close();
                }
            } catch (IOException e) {
                log.error("Exceptions in Excel Utils WorkBook: " + e);
            }
        }
        return inputStream;
    }

    // retrieve field names from a POJO class
    private static List<String> getFieldNamesForClass(Class<?> clazz) {
        List<String> fieldNames = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }

    // capitalize the first letter of the field name for retriving value of the
    // field later
    private static String capitalize(String s) {
        if (s.length() == 0)
            return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
