package MyKurs;

import java.io.Serializable;

public class PatientInformation implements Serializable{

	private String fio;
	private int numberOfCard;
	private boolean isPaid;
	private int debt;
	private Procedure procedure;

	PatientInformation(String fio, int numberOfCard) {
		this.setFio(fio);
		this.setNumberOfCard(numberOfCard);
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		if (fio.matches("(?=.*[A-Za-zА-Яа-я]).{0,64}")) {
			this.fio = fio;
		} else {
			//System.out.println("Допустимое количество вводимых символов = 32.");
		}
	}

	public int getNumberOfCard() {
		return numberOfCard;
	}

	public void setNumberOfCard(int numberOfCard) {
		if (numberOfCard > 999 && numberOfCard < 9999) {
			this.numberOfCard = numberOfCard;
		} else {
			//System.out.println("Недопустимый номер карты.");
		}
	}

	public Procedure getProcedure() {
		return procedure;
	}

	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
		isPaid = false;
		debt = procedure.getOperationPrice();
	}

	public boolean getIsPaid() {
		return isPaid;
	}

	public int getDebt() {
		return debt;
	}

	public void payForProcedure() {
		if (procedure != null) {
			isPaid = true;
		}
	}

	public String toString() {
		return "ФИО: " + fio + "   Номер карты: " + numberOfCard;
	}
}
