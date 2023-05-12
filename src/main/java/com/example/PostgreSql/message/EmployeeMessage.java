package com.example.PostgreSql.message;

import lombok.Data;

@Data
public class EmployeeMessage {
	private String message;
	
	public EmployeeMessage(String message)
	{
		this.message = message;
	}
	
}
