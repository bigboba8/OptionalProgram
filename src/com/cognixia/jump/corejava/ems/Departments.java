package com.cognixia.jump.corejava.ems;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Departments {
	void createTable()
	{
		int k = 4;
		Object[] row = new Object[k];
		JTable dTable= new JTable();
		Object[] columns = { "Department", "Contact", "Name" };
		DefaultTableModel model = new DefaultTableModel();
		dTable=ems.setTable(columns, model,dTable);
		JScrollPane dPane = new JScrollPane(dTable);
		ems.setPane(dPane,"Deparments List");
		File file = new File(".\\EMS.txt");
		readFile(file,row,model,k);
		
	}

	private static JTextField textDepartment = new JTextField();

	public void setTextDepartment(JTextField setTextDepartment) {
		textDepartment = setTextDepartment;
	}

	public JTextField getTextDepartment() {
		return textDepartment;
	}

	static void readFile(File file,Object[] row,DefaultTableModel model,int k)  {
		// read file if existed
		if (file.exists()) {
			Scanner scan = null;
			try {
				scan = new Scanner(file);
			} catch (FileNotFoundException e) {
				System.out.println("file not found");
			}

			String fileContent = "";
			while (scan.hasNextLine()) {
				fileContent = fileContent.concat(scan.nextLine());
			}

			// assign data from file to Jtable
			// data separated by ";"
			String words[] = fileContent.split(";");
			int arrayCount = words.length;
			for (int j = 0; j < arrayCount;) {
				if (j%k==0)
				{
					row[0] = words[j+2];
					row[1] = words[j+3];
					row[2] = words[j+1];
					model.addRow(row);
					j+=k;
				}

			}
		}
		
	}
}
