
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

	public static void main(String[] args) throws FileNotFoundException {

		// file path set as under project folder
		File file = new File(".\\EMS.txt");

		// create JFrame and JTable
		JFrame frame = new JFrame();
		JTable table = new JTable();

		// create a table model and set a Column Identifiers to this model
		Object[] columns = { "Id", "Name", "Department", "Contact" };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);

		// set the model to the table
		table.setModel(model);

		// Change A JTable Background Color, Font Size, Font Color, Row Height
		table.setBackground(Color.WHITE);
		table.setForeground(Color.black);
		Font font = new Font("", 1, 12);
		table.setFont(font);
		table.setRowHeight(16);

		// create JTextfields Label
		JLabel labelName = new JLabel("Name:");
		JLabel labelDepartment = new JLabel("Department:");
		JLabel labelContact = new JLabel("Contact:");

		// create JTextFields
		JTextField textName = new JTextField();
		JTextField textDepartment = new JTextField();
		JTextField textContact = new JTextField();

		// create JButtons
		JButton btnAdd = new JButton("Add");
		JButton btnDelete = new JButton("Delete");
		JButton btnSave = new JButton("Save");

		// items coordinate and size
		labelName.setBounds(20, 320, 100, 25);
		labelDepartment.setBounds(20, 350, 100, 25);
		labelContact.setBounds(20, 380, 100, 25);

		textName.setBounds(100, 320, 100, 25);
		textDepartment.setBounds(100, 350, 100, 25);
		textContact.setBounds(100, 380, 100, 25);

		btnAdd.setBounds(230, 320, 100, 25);
		btnDelete.setBounds(230, 350, 100, 25);
		btnSave.setBounds(230, 410, 100, 25);

		// create JScrollPane
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 880, 300);
		frame.setLayout(null);
		frame.add(pane);
		frame.setTitle("Employee Management System V1.0");

		// add JTextFields to the Jframe
		frame.add(textName);
		frame.add(textDepartment);
		frame.add(textContact);

		// add JButtons to the Jframe
		frame.add(btnAdd);
		frame.add(btnDelete);
		frame.add(btnSave);

		// add TextFieldsLabel to the Jframe
		frame.add(labelName);
		frame.add(labelDepartment);
		frame.add(labelContact);

		// create an array of objects to set the row data
		int k = 4;
		Object[] row = new Object[k];

		// read file if existed
		if (file.exists()) {
			Scanner scan = new Scanner(file);

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

		// button add row
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// auto increment for ID
				if (row[0] == null) {
					row[0] = "0";
				}
				int m = Integer.parseInt((String) row[0]) + 1;
				row[0] = Integer.toString(m);

				row[1] = textName.getText();
				row[2] = textDepartment.getText();
				row[3] = textContact.getText();

				// add row to the model
				model.addRow(row);
			}
		});

		// button remove row
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// i = the index of the selected row
				int i = table.getSelectedRow();
				if (i >= 0) {
					// remove a row from jtable
					model.removeRow(i);
				} else {
					System.out.println("You must select a row to delete");
				}
			}
		});

		// save button to export Jtable to a txt file
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

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
		});
		frame.setSize(900, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}