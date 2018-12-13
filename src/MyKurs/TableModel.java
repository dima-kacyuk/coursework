package MyKurs;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

	private int columnCount = 1;

	private ArrayList<PatientInformation> dataList;

	public TableModel() {
		dataList = new ArrayList<PatientInformation>();
		load();
	}

	public void editPatient(int i, String fio, int numberOfCard, Procedure procedure) {
		dataList.get(i).setFio(fio);
		dataList.get(i).setNumberOfCard(numberOfCard);
		dataList.get(i).setProcedure(procedure);
	}

	public int correctCardNumber(int cardNum) {
		for (int i = 0; i < dataList.size(); i++) {
			if (cardNum == dataList.get(i).getNumberOfCard()) {
				return 0;
			}
		}
		return 1;
	}

	public void pay(int i) {
		dataList.get(i).payForProcedure();
	}

	public String searchPatient(int searchCardNumber) {
		for (PatientInformation patientInformation : dataList) {
			if (patientInformation.getNumberOfCard() == searchCardNumber) {
				return patientInformation.toString();
			}
		}
		return "Not found";
	}

	public String showPatientInfo(int i) {
		return "\nFIO: " + dataList.get(i).getFio() + "\nCard number: " + dataList.get(i).getNumberOfCard() + "\n"
				+ dataList.get(i).getProcedure() + "\nPaid for operation: " + dataList.get(i).getIsPaid();
	}

	@SuppressWarnings("unchecked")
	public void load() {
		try {
			FileInputStream fileIn = new FileInputStream("./patients.ser");
			ObjectInputStream inStream = new ObjectInputStream(fileIn);
			this.dataList = (ArrayList<PatientInformation>) inStream.readObject();
			inStream.close();
			fileIn.close();

			FileOutputStream fileOutCopy = new FileOutputStream("./backup/copyFile" + (new Date().getTime()) + ".ser");
			ObjectOutputStream outStreamCopy = new ObjectOutputStream(fileOutCopy);

			outStreamCopy.writeObject(dataList);
			outStreamCopy.flush();
			outStreamCopy.close();
			fileOutCopy.flush();
			fileOutCopy.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return;
		}
	}

	public void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream("./patients.ser");
			ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
			outStream.writeObject(dataList);
			outStream.flush();
			outStream.close();
			fileOut.flush();
			fileOut.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public int getColumnCount() {
		return columnCount;
	}

	@Override
	public int getRowCount() {
		return dataList.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Patient Information";
		}
		return "";
	}

	@Override
	public PatientInformation getValueAt(int rowIndex, int colomnIndex) {
		PatientInformation rows = dataList.get(rowIndex);
		return rows;
	}

	public void remData(int row) {
		dataList.remove(row);
	}

	public void addPatient(PatientInformation pi) {
		for (int i = 0; i < dataList.size(); i++) {
			if (dataList.get(i).getNumberOfCard() == pi.getNumberOfCard()) {

			} else {
				dataList.add(pi);
				break;
			}
		}
	}

}
