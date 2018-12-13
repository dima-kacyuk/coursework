package MyKurs;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class PatientArrayList{

	private ArrayList<PatientInformation> patients = new ArrayList<PatientInformation>();

	PatientArrayList() {
	}
	
	public PatientInformation[] toArray() {
		PatientInformation[] list = new PatientInformation[patients.size()];
		for(int i=0; i< patients.size(); i++) {
			list[i] = patients.get(i);
		}
		return list;
	}

	public void save(String fileName) {
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
			outStream.writeObject(patients);
			outStream.flush();
			outStream.close();
			fileOut.flush();
			fileOut.close();
			System.out.println("Внесенные данные сохранены в файле: " + fileName);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void load(String fileName) {
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream inStream = new ObjectInputStream(fileIn);
			this.patients = (ArrayList<PatientInformation>) inStream.readObject();
			inStream.close();
			fileIn.close();
			
			FileOutputStream fileOutCopy = new FileOutputStream("./backup/copyFile" + (new Date().getTime()) + ".ser");
			ObjectOutputStream outStreamCopy = new ObjectOutputStream(fileOutCopy);
			outStreamCopy.writeObject(patients);
			outStreamCopy.flush();
			outStreamCopy.close();
			fileOutCopy.flush();
			fileOutCopy.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Класс с таким именем не найден.");
			c.printStackTrace();
			return;
		}
	}

	public void removeAllWithoutDebt() {
		for (int i = 0; i < patients.size(); i++) {
			if (patients.get(i).getIsPaid() == true) {
				patients.remove(patients.get(i));
			}
		}
	}

	public void searchDebtors() {
		for (PatientInformation patientInformation : patients) {
			if (patientInformation.getIsPaid() == false) {
				System.out.println(patientInformation);
			}
		}
	}

	public void searchPatient(String secondName) {
		for (PatientInformation patientInformation : patients) {
			if (patientInformation.toString().contains(secondName)) {
				System.out.println(patientInformation);
			}
		}
	}

	public void addParient(PatientInformation patientArrayList) {
		this.patients.add(patientArrayList);
	}

	public void clearList() {
		this.patients.clear();
		System.out.println("Список очищен...");
	}

	public void showArrayList() {
		if (patients.size() != 0) {
			for (PatientInformation patientInformation : patients) {
				System.out.println(patientInformation);
				System.out.println("----------------------------");
			}
		}else {
			System.out.println("Список пуст...");
		}

	}
}
