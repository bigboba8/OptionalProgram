package com.cognixia.jump.corejava.ems;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//update Employee information, remove Employees, and list Employee information
public class Employees extends ems {
void createTable()
{
	int k = 4;
	Object[] row = new Object[k];
	JTable eTable= new JTable();
	Object[] columns = { "Name", "Department", "Contact" };
	DefaultTableModel model = new DefaultTableModel();
	eTable=ems.setTable(columns, model,eTable);
	JScrollPane ePane = new JScrollPane(eTable);
	ems.setPane(ePane,"Employee name List");
	File file = new File(".\\EMS.txt");
	readFile(file,row,model,k);
	
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
				row[0] = words[j+1];
				row[1] = words[j+2];
				row[2] = words[j+3];
				model.addRow(row);
				j+=k;
			}

		}
	}
	
}
private static JTextField textName = new JTextField();;

	public void setTextName(JTextField setTextName) {
		textName = setTextName;
	}

	public static JTextField getTextName() {
		return textName;
	}

}
