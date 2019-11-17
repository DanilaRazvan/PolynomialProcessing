package model;

@SuppressWarnings("serial")
public class WrongInputException extends Exception {

	public WrongInputException() {
		super("Input format ERROR. Please introduce a polynomial in traditional form and with integer coefficients");
	}

}
