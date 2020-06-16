package com.cognixia.jump.corejava.ems;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JTextField;

import org.junit.jupiter.api.Test;

class EmployeesTest {

	@Test
	void testTextSet() {
		Employees testEmployee = new Employees();
		
		JTextField testEmployeeText = new JTextField();
		
		testEmployeeText.setText("Hello there!");
		
		testEmployee.setTextName(testEmployeeText);
		
		JTextField testTextResult = testEmployee.getTextName();
		
		assertEquals(testEmployeeText, testTextResult);
	}
}
