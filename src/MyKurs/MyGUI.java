package MyKurs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MyGUI {

	private String addFio;
	private String addCardNumber;
	private String operationNumber;
	private PatientInformation pi;
	private Procedure procedure;
	private String searchCardNumber;

	public MyGUI() {

		JFrame frame = new JFrame();
		frame.setTitle("Dentist");
		frame.setSize(new Dimension(800, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		// frame.setResizable(false);

		JButton addBtn = new JButton("Add patient");
		JButton remBtn = new JButton("Delete patient");
		JButton payForOpBtn = new JButton("Pay for operation");
		JButton infoBtn = new JButton("Get information");
		JButton searchBtn = new JButton("Search patient");
		JButton editBtn = new JButton("Edit patient info");
		JButton saveBtn = new JButton("Save data");

		JPanel listPanel = new JPanel(new GridLayout());
		JPanel buttonPanel = new JPanel(new GridLayout(4, 3));

		TableModel tb = new TableModel();
		JTable patientTable = new JTable(tb);
		JScrollPane scroll = new JScrollPane(patientTable);
		scroll.setPreferredSize(new Dimension(800, 400));

		// add button
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					addFio = JOptionPane.showInputDialog("Enter name:");

					addCardNumber = JOptionPane.showInputDialog("Enter card number:");
					int temp1 = Integer.parseInt(addCardNumber);

					// int isExists = -1; Проверка на существующий номер карты

					while (temp1 < 1000 || temp1 > 10000) {
						addCardNumber = JOptionPane.showInputDialog(
								"Uncorrect card number, try again\n(you must write four digit number)");
						temp1 = Integer.parseInt(addCardNumber);
						/*
						 * while(isExists == 0) { isExists = tb.correctCardNumber(temp1); }
						 */
					}

					operationNumber = JOptionPane.showInputDialog(
							"Enter operation type:\n1-DENTALPROSTHETICS\n2-DENTALFILLING\n3-DENTALIMPLANTATION");
					int temp2 = Integer.parseInt(operationNumber);

					while (temp2 > 3 || temp2 < 1) {
						operationNumber = JOptionPane.showInputDialog(
								"Uncorrect operation type, try again:\n1-DENTALPROSTHETICS\n2-DENTALFILLING\n3-DENTALIMPLANTATION");
						temp2 = Integer.parseInt(operationNumber);
					}

					pi = new PatientInformation(addFio, temp1);
					procedure = new Procedure(temp2);

					pi.setProcedure(procedure);

					tb.addPatient(pi);
					tb.fireTableDataChanged();

				} catch (NullPointerException o) {
					System.out.println("add file.log");
				}
			}
		});

		// remove button
		remBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tb.remData(patientTable.getSelectedRow());
				tb.fireTableDataChanged();
			}
		});

		// pay for operation button
		payForOpBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tb.pay(patientTable.getSelectedRow());
				tb.fireTableDataChanged();
			}
		});

		// save button
		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tb.save();
				tb.fireTableDataChanged();
			}
		});

		infoBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, tb.showPatientInfo(patientTable.getSelectedRow()));
			}
		});

		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				searchCardNumber = JOptionPane.showInputDialog("Input search card number");
				int temp = Integer.parseInt(searchCardNumber);
				JOptionPane.showMessageDialog(null, tb.searchPatient(temp));
			}
		});

		editBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addFio = JOptionPane.showInputDialog("Enter name:");

				addCardNumber = JOptionPane.showInputDialog("Enter card number:");
				int temp1 = Integer.parseInt(addCardNumber);

				while (temp1 < 1000 || temp1 > 10000) {
					addCardNumber = JOptionPane
							.showInputDialog("Uncorrect card number, try again\n(you must write four digit number)");
					temp1 = Integer.parseInt(addCardNumber);
				}

				operationNumber = JOptionPane.showInputDialog(
						"Enter operation type:\n1-DENTALPROSTHETICS\n2-DENTALFILLING\n3-DENTALIMPLANTATION");
				int temp2 = Integer.parseInt(operationNumber);

				while (temp2 > 3 || temp2 < 1) {
					operationNumber = JOptionPane.showInputDialog(
							"Uncorrect operation type, try again:\n1-DENTALPROSTHETICS\n2-DENTALFILLING\n3-DENTALIMPLANTATION");
					temp2 = Integer.parseInt(operationNumber);
				}

				pi = new PatientInformation(addFio, temp1);
				procedure = new Procedure(temp2);

				pi.setProcedure(procedure);
				tb.editPatient(patientTable.getSelectedRow(), addFio, temp1, procedure);
				tb.fireTableDataChanged();

			}
		});

		// add frame table and buttons
		listPanel.add(scroll);
		buttonPanel.add(addBtn);
		buttonPanel.add(remBtn);
		buttonPanel.add(infoBtn);
		buttonPanel.add(payForOpBtn);
		buttonPanel.add(searchBtn);
		buttonPanel.add(editBtn);
		buttonPanel.add(saveBtn);

		frame.add(listPanel, BorderLayout.WEST);
		frame.add(buttonPanel, BorderLayout.EAST);

		frame.setVisible(true);
		frame.pack();
	}

	public static void main(String[] args) {
		new MyGUI();
	}

}
