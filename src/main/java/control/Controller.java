package control;

import view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.*;

public class Controller {

	private Data dataPanel;
	private Result resPanel;
	private Polynomial p1;
	private Polynomial res;
	private boolean pressedD = false;
	private boolean pressedS = false;
	private char button;

	public Controller(UI view) {
		this.dataPanel = view.getData();
		this.resPanel = view.getRes();
		ActionListener add = new AddButtonListener();
		ActionListener sub = new SubtractButtonListener();
		ActionListener mult = new MultiplyButtonListener();
		ActionListener div = new DivideButtonListener();
		ActionListener integr = new IntegrationButtonListener();
		ActionListener deriv = new DerivationButtonListener();
		ActionListener eq = new EqualsButtonListener();
		ActionListener del = new DeleteButtonListener();

		dataPanel.setListeners(add, sub, mult, div, integr, deriv, eq, del);
	}

	private boolean checkResult(Polynomial poly) {
		if (poly.getElements().size() == 0) {
			return false;
		}

		return true;
	}

	class AddButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				addButtonPressed();
			} catch (WrongInputException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Wrong Input", JOptionPane.ERROR_MESSAGE);
				dataPanel.setFirstPolynomial("");
			}
			button = '+';
		}
	}

	private void addButtonPressed() throws WrongInputException {

		if (!dataPanel.getFirstPolynomial().equals("")) {
			p1 = new Polynomial(dataPanel.getFirstPolynomial());
			res = new Polynomial(resPanel.getResult());
			Polynomial r = p1.add(res);

			if (checkResult(r)) {
				dataPanel.setFirstPolynomial("");
				resPanel.setResult(r.toString());
				resPanel.setRemainder("");
			} else {
				dataPanel.setFirstPolynomial("");
				resPanel.setResult("0");
				resPanel.setRemainder("");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Polynomial Textbox is empty. No info to precess", "Input Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	class SubtractButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				subtractButtonPressed();
			} catch (WrongInputException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Wrong Input", JOptionPane.ERROR_MESSAGE);
				dataPanel.setFirstPolynomial("");
			}
			button = '-';

		}
	}

	private void subtractButtonPressed() throws WrongInputException {

		if (!dataPanel.getFirstPolynomial().equals("")) {
			p1 = new Polynomial(dataPanel.getFirstPolynomial());
			res = new Polynomial(resPanel.getResult());

			if (resPanel.getResult().equals("0") && pressedS == false) {
				resPanel.setResult(p1.toString());
				dataPanel.setFirstPolynomial("");
			} else {
				Polynomial r = res.subtract(p1);

				if (checkResult(r)) {
					dataPanel.setFirstPolynomial("");
					resPanel.setResult(r.toString());
					resPanel.setRemainder("");
				} else {
					dataPanel.setFirstPolynomial("");
					resPanel.setResult("0");
					resPanel.setRemainder("");
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Polynomial Textbox is empty. No info to precess", "Input Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
		pressedS = true;
	}

	class MultiplyButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				multiplyButtonPressed();
			} catch (WrongInputException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Wrong Input", JOptionPane.ERROR_MESSAGE);
				dataPanel.setFirstPolynomial("");
			}
			button = '*';

		}
	}

	private void multiplyButtonPressed() throws WrongInputException {

		if (!dataPanel.getFirstPolynomial().equals("")) {
			p1 = new Polynomial(dataPanel.getFirstPolynomial());

			if (resPanel.getResult().equals("0")) {
				res = new Polynomial("1");
			} else {
				res = new Polynomial(resPanel.getResult());
			}

			Polynomial r = res.multiply(p1);

			if (checkResult(r)) {
				dataPanel.setFirstPolynomial("");
				resPanel.setResult(r.toString());
				resPanel.setRemainder("");
			} else {
				dataPanel.setFirstPolynomial("");
				resPanel.setResult("0");
				resPanel.setRemainder("");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Polynomial Textbox is empty. No info to precess", "Input Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	class DivideButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				divideButtonPressed();
			} catch (WrongInputException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Wrong Input", JOptionPane.ERROR_MESSAGE);
				dataPanel.setFirstPolynomial("");
			}
			button = '/';

		}
	}

	private void divideButtonPressed() throws WrongInputException {

		if (!dataPanel.getFirstPolynomial().equals("") && !dataPanel.getFirstPolynomial().equals("0")) {
			p1 = new Polynomial(dataPanel.getFirstPolynomial());
			res = new Polynomial(resPanel.getResult());

			if (resPanel.getResult().equals("0") && pressedD == false) {
				resPanel.setResult(p1.toString());
				dataPanel.setFirstPolynomial("");
			} else {
				Polynomial r = res.divide(p1);
				System.out.println(r.toString());
				Polynomial rm = res.divideGetRest(p1);

				if (checkResult(r)) {
					dataPanel.setFirstPolynomial("");
					resPanel.setResult(r.toString());
				} else {
					dataPanel.setFirstPolynomial("");
					resPanel.setResult("0");
				}

				if (checkResult(rm)) {
					dataPanel.setFirstPolynomial("");
					resPanel.setRemainder(rm.toString());
				} else {
					dataPanel.setFirstPolynomial("");
					resPanel.setRemainder("");
				}
			}
		} else if(!dataPanel.getFirstPolynomial().equals("0")) {
			JOptionPane.showMessageDialog(null, "Polynomial Textbox is empty. No info to precess", "Input Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Division by 0", "Input Error",
					JOptionPane.ERROR_MESSAGE);
			dataPanel.setFirstPolynomial("");
		}

		pressedD = true;
	}

	class IntegrationButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				integrationButtonPressed();
			} catch (WrongInputException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Wrong Input", JOptionPane.ERROR_MESSAGE);
				dataPanel.setFirstPolynomial("");
			}
			button = 'S';

		}
	}

	private void integrationButtonPressed() throws WrongInputException {
		if (!dataPanel.getFirstPolynomial().equals("")) {
			p1 = new Polynomial(dataPanel.getFirstPolynomial());
			res = p1.integrate();
			if (checkResult(res)) {
				dataPanel.setFirstPolynomial("");
				resPanel.setResult(res.toString());
				resPanel.setRemainder("");
			} else {
				resPanel.setResult("0");
				resPanel.setRemainder("");
			}
		} else if (!resPanel.getResult().equals("")) {
			
			res = res.integrate();
			resPanel.setResult(res.toString());
			resPanel.setRemainder("");
		} else {
			JOptionPane.showMessageDialog(null, "Polynomial Textbox is empty. No info to process", "Input Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	class DerivationButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				derivationButtonPressed();
			} catch (WrongInputException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Wrong Input", JOptionPane.ERROR_MESSAGE);
				dataPanel.setFirstPolynomial("");
			}
			button = '\'';

		}
	}

	private void derivationButtonPressed() throws WrongInputException {
		if (!dataPanel.getFirstPolynomial().equals("")) {
			p1 = new Polynomial(dataPanel.getFirstPolynomial());
			res = p1.derivate();
			if (checkResult(res)) {
				dataPanel.setFirstPolynomial("");
				resPanel.setResult(res.toString());
				resPanel.setRemainder("");
			} else {
				dataPanel.setFirstPolynomial("");
				resPanel.setResult("0");
				resPanel.setRemainder("");
			}
		} else if (!resPanel.getResult().equals("")) {
			res = res.derivate();
			resPanel.setResult(res.toString());
			resPanel.setRemainder("");
		} else {
			JOptionPane.showMessageDialog(null, "Polynomial Textbox is empty. No info to process", "Input Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	class EqualsButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			switch (button) {
			case '+': {
				try {
					addButtonPressed();
				} catch (WrongInputException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Wrong Input", JOptionPane.ERROR_MESSAGE);
					dataPanel.setFirstPolynomial("");
				}
				break;
			}

			case '-': {
				try {
					subtractButtonPressed();
				} catch (WrongInputException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Wrong Input", JOptionPane.ERROR_MESSAGE);
					dataPanel.setFirstPolynomial("");
				}
				break;
			}

			case '*': {
				try {
					multiplyButtonPressed();
				} catch (WrongInputException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Wrong Input", JOptionPane.ERROR_MESSAGE);
					dataPanel.setFirstPolynomial("");
				}
				break;
			}

			case '/': {
				try {
					divideButtonPressed();
				} catch (WrongInputException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Wrong Input", JOptionPane.ERROR_MESSAGE);
					dataPanel.setFirstPolynomial("");
				}
				break;
			}

			case 'S': {
				try {
					integrationButtonPressed();
				} catch (WrongInputException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Wrong Input", JOptionPane.ERROR_MESSAGE);
					dataPanel.setFirstPolynomial("");
				}
				break;
			}

			case '\'': {
				try {
					derivationButtonPressed();
				} catch (WrongInputException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Wrong Input", JOptionPane.ERROR_MESSAGE);
					dataPanel.setFirstPolynomial("");
				}
				break;
			}

			default:
				break;
			}
		}
	}

	class DeleteButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			dataPanel.setFirstPolynomial("");
			resPanel.setResult("0");
			resPanel.setRemainder("");
			pressedD = false;
			pressedS = false;
		}
	}

}
