package com.cognixia.jump.corejava.ems;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JTextField;

import org.junit.jupiter.api.Test;

import com.cognixia.jump.corejava.ems.Departments;

class DepartmentsTest {
	
	@Test
	void testSetTextDepartment() {
		Departments testDepartment = new Departments();
		
		JTextField testDepartmentText = new JTextField();
		
		testDepartmentText.setText("Hello there!");
		
		testDepartment.setTextDepartment(testDepartmentText);
		
		JTextField testTextResult = testDepartment.getTextDepartment();
		
		assertEquals(testDepartmentText, testTextResult);
	}
}
