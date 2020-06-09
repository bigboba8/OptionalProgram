
package com.cognixia.jump.corejava.ems;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ems {


	private static JButton btnAdd,btnDelete,btnSave,btnEmployees,btnDepartments,btnMangement;//creates Jbuttons
	// create JTextfields Label
	private static JLabel labelName = new JLabel("Name:");
	private static JLabel labelDepartment = new JLabel("Department:");
	private static JLabel labelContact = new JLabel("Contact:");
	//creates textFields
	private static JTextField textName = new JTextField();
	private static JTextField textDepartment = new JTextField();
	private static JTextField textContact = new JTextField();
	// create JFrame and JTable
	private static JFrame frame = new JFrame();
	
	
	
	//creates a frame where you can add  and delete rows 
	public static void main(String[] args) {
		// file path set as under project folder
		JTable table = new JTable();
		File file = new File(".\\EMS.txt");
		// create a table model and set a Column Identifiers to this model
		Object[] columns = { "Id", "Name", "Department", "Contact" };
		DefaultTableModel model = new DefaultTableModel();
		String title="Employee Management System";
		// create an array of objects to set the row data
		int k = 4;
		Object[] row = new Object[k];
		setFrame();
		setContents();
		setTable(columns,model,table);
		JScrollPane pane = new JScrollPane(table);
		setPane(pane,title);
		readFile(file,row,model,k);
		//adds row when button pressed
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addRow(row,model);
				}
			});
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteRow(model,table);
				}
			});
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile(file,table);
				}
			});
		btnEmployees.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Employees switchTable = new Employees();
				//saveFile(file,table);
				switchTable.createTable();
				togleHideElements(false,table,pane);
				}
			});
		btnDepartments.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Departments switchTable = new Departments();
				switchTable.createTable();
				togleHideElements(false,table,pane);
				
				}
			});
		btnMangement.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				togleHideElements(true,table,pane);
				}
			});
	}
	static void togleHideElements(boolean visable,JTable table,JScrollPane pane) {
		table.setVisible(visable);
		pane.setVisible(visable);
		btnAdd.setVisible(visable);
		btnDelete.setVisible(visable);
		btnSave.setVisible(visable);
		textName.setVisible(visable);
		textDepartment.setVisible(visable);
		textContact.setVisible(visable);
		labelName.setVisible(visable);
		labelDepartment.setVisible(visable);
		labelContact.setVisible(visable);
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
			int i;
			int j;
			int arrayCount = words.length;
			for (i = 0, j = 0; j < arrayCount; i++, j++) {
				row[i] = words[j];
				if (i == k - 1) {
					i = -1;
					model.addRow(row);
				}
			}
		}
		
	}
	
	static void saveFile(File file,JTable table) {
		try {

			// if the file not exist create one
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			// loop for jtable rows
			for (int i = 0; i < table.getRowCount(); i++) {
				// loop for jtable column
				for (int j = 0; j < table.getColumnCount(); j++) {
					bw.write(table.getModel().getValueAt(i, j) + ";");
				}
			}
			// close BufferedWriter
			bw.close();
			// close FileWriter
			fw.close();
			JOptionPane.showMessageDialog(null, "File saved");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	static void addRow(Object[] row,DefaultTableModel model) {
		// auto increment for ID
		if (row[0] == null||row[0]=="") {
			row[0] = "0";
		}
		
		int m = Integer.parseInt((String) row[0]) + 1;
		row[0] = Integer.toString(m);
		if (textName.getText().isEmpty()) {
			System.out.println("No Name Given!");
			return;
		}
			
		if (textDepartment.getText().isEmpty()){
			System.out.println("No Department Given!");
			return;
		}

		if (textContact.getText().isEmpty()){
			System.out.println("No Contact Given!");
			return;
		}

		row[1] = textName.getText();
		row[2] = textDepartment.getText();
		row[3] = textContact.getText();
		// add row to the model

		model.addRow(row);

	}
	
	static void deleteRow(DefaultTableModel model, JTable table) {
		// i = the index of the selected row
		int i = table.getSelectedRow();
		if (i >= 0) {
			// remove a row from jtable
			model.removeRow(i);
		} else {
			System.out.println("You must select a row to delete");
		}
	}
	

	
	//sets the window frame and the content locations
	static void setFrame() {
		//sets window frame size,location and allows user to see the window
		frame.setSize(900, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	//sets Button names and location
	static void setButtons() {
		btnMangement = new JButton("Mangement");
		btnDepartments = new JButton("Departments");
		btnEmployees = new JButton("Employees");
		btnAdd = new JButton("Add");
		btnDelete = new JButton("Delete");
		btnSave = new JButton("Save");
		//set action listeners for buttons
		
        // define a custom short action command for the button
		btnMangement.setActionCommand("Mangement");
		btnDepartments.setActionCommand("Departments");
		btnEmployees.setActionCommand("Employees");
		btnAdd.setActionCommand("add");
		btnDelete.setActionCommand("delete");
		btnSave.setActionCommand("save");
		// buttons coordinate and size
		btnMangement.setBounds(350, 320, 125, 25);
		btnDepartments.setBounds(650, 320, 125, 25);
		btnEmployees.setBounds(500, 320, 125, 25);
		btnAdd.setBounds(230, 320, 100, 25);
		btnDelete.setBounds(230, 350, 100, 25);
		btnSave.setBounds(230, 410, 100, 25);
		
		// add JButtons to the Jframe
		frame.add(btnMangement);
		frame.add(btnDepartments);
		frame.add(btnEmployees);
		frame.add(btnAdd);
		frame.add(btnDelete);
		frame.add(btnSave);

	}
	//sets labels names and location
	static void setText() {
		
		// Text coordinate and size
		labelName.setBounds(20, 320, 100, 25);
		labelDepartment.setBounds(20, 350, 100, 25);
		labelContact.setBounds(20, 380, 100, 25);
		
		// add TextFieldsLabel to the Jframe
		frame.add(labelName);
		frame.add(labelDepartment);
		frame.add(labelContact);

	}
	//set text box location
	static void setTextBox() {	
		// Text-box coordinate and size
		textName.setBounds(100, 320, 100, 25);
		textDepartment.setBounds(100, 350, 100, 25);
		textContact.setBounds(100, 380, 100, 25);
		
		// add JTextFields to the Jframe
		frame.add(textName);
		frame.add(textDepartment);
		frame.add(textContact);
	}
	
	//set contents of window buttons,labels,text fields
	//calls setButtons,setText,setTextBox
	static void setContents() {
		setButtons();
		setText();
		setTextBox();
	}
	//set table color, font, pane size
	static JTable setTable(Object[] columns, DefaultTableModel model,JTable table) {
		
		
		model.setColumnIdentifiers(columns);

		// set the model to the table
		table.setModel(model);

		// Change A JTable Background Color, Font Size, Font Color, Row Height
		table.setBackground(Color.WHITE);
		table.setForeground(Color.black);
		Font font = new Font("", 1, 12);
		table.setFont(font);
		table.setRowHeight(16);
		//setPane(table);
		return table;

	}
	static void setPane(JScrollPane pane,String title) {
		// create JScrollPane
				
				pane.setBounds(0, 0, 880, 300);
				frame.setLayout(null);
				frame.add(pane);
				frame.setTitle(title);
	}
	

}