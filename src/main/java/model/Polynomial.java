package model;

import java.util.*;

public class Polynomial implements Cloneable {

	private ArrayList<Monomial> elements;

	public Polynomial() {
		this.elements = new ArrayList<Monomial>();
	}

	public Polynomial(String poly) throws WrongInputException {
		this.elements = obtainMonomials(poly);
	}

	/**
	 * 
	 * Create a new poly with length = max(this.degree, p.degree); Its monomials
	 * have coefficient 0 and powers in descending order
	 * 
	 * ex: pos: 0 1 2 3 4 5 coeff: 0 0 0 0 0 0 power: 5 4 3 2 1 0
	 *
	 *
	 * Iterate on each poly separately and add its elements with the element from
	 * the right position in newly created polynomial.
	 * 
	 * At the end, the elements from the newly created poly will be the sum of the 2
	 * polynomials.
	 * 
	 * @param p - polynomial
	 * @return polynomial - addition of 2 polynomials
	 */
	public Polynomial add(Polynomial p) {
		this.organize();
		p.organize();

		Polynomial r = new Polynomial();
		ArrayList<Monomial> addition = new ArrayList<Monomial>();

		int nbOfElements = Math.max(this.elements.get(0).getPower(), p.elements.get(0).getPower()) + 1;

		for (int i = 0; i < nbOfElements; i++) {
			addition.add(i, new Monomial(0, nbOfElements - 1 - i));
		}

		for (Monomial m : this.elements) {
			int index = nbOfElements - 1 - m.getPower();
			addition.set(index, addition.get(index).add(m));
		}

		for (Monomial m : p.elements) {
			int index = nbOfElements - 1 - m.getPower();
			addition.set(index, addition.get(index).add(m));
		}

		r.elements = addition;
		r.clean();

		return r;
	}

	/**
	 * 
	 * Create a new poly with length = max(this.degree, p.degree); Its monomials
	 * have coefficient 0 and powers in descending order
	 * 
	 * ex: pos: 0 1 2 3 4 5 coeff: 0 0 0 0 0 0 power: 5 4 3 2 1 0
	 *
	 * First, copy the elements from the first poly into the newly created one, then
	 * subtract the elements from the seconf poly
	 * 
	 * At the end, the elements from the newly created poly will be the subtraction
	 * of the 2 polynomials.
	 * 
	 * @param p - polynomial
	 * @return polynomial - subtraction of 2 polynomials
	 */
	public Polynomial subtract(Polynomial p) {
		this.organize();
		p.organize();

		Polynomial r = new Polynomial();
		ArrayList<Monomial> subtraction = new ArrayList<Monomial>();
		int nbOfElements = Math.max(this.elements.get(0).getPower(), p.elements.get(0).getPower()) + 1;

		for (int i = 0; i < nbOfElements; i++) {
			subtraction.add(i, new Monomial(0, nbOfElements - 1 - i));
		}

		for (Monomial m : this.elements) {
			int index = nbOfElements - 1 - m.getPower();
			subtraction.set(index, subtraction.get(index).add(m));
		}

		for (Monomial m : p.elements) {
			int index = nbOfElements - 1 - m.getPower();
			subtraction.set(index, subtraction.get(index).subtract(m));
		}

		r.elements = subtraction;
		r.clean();

		return r;
	}

	/**
	 * 
	 * Perform the synthetic division
	 * 
	 * The divident is divided by the first element of the divider Then the result
	 * is subtracted from the dividend and the divident is updated to this result
	 * from the subtraction. This steps are repeated until the degree of the
	 * reminder is < degree of the divider or = 0
	 * 
	 * @param divider - Polynomial
	 * @return Polynomial - result of the division
	 */
	public Polynomial divide(Polynomial divider) {
		try {
			this.organize();
			divider.organize();

			Polynomial r = new Polynomial();
			Polynomial divident = (Polynomial) this.clone();
			Monomial dividentElement;
			Monomial dividerElement = divider.elements.get(0);
			Monomial outElement;
			Polynomial temp;

			while (divident.elements.size() != 0 && dividerElement.getPower() <= divident.elements.get(0).getPower()) {
				dividentElement = divident.elements.get(0);
				outElement = dividentElement.divide(dividerElement);
				r.elements.add(outElement);
				temp = divider.multiply(outElement);
				divident = divident.subtract(temp);
			}

			return r;

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * Perform the synthetic division
	 * 
	 * The divident is divided by the first element of the divider Then the result
	 * is subtracted from the dividend and the divident is updated to this result
	 * from the subtraction. This steps are repeated until the degree of the
	 * reminder is < degree of the divider or = 0
	 * 
	 * @param divider - Polynomial
	 * @return Polynomial - result of the division
	 */
	public Polynomial divideGetRest(Polynomial divider) {
		try {
			this.organize();
			divider.organize();

			Polynomial r = new Polynomial();
			Polynomial divident = (Polynomial) this.clone();
			Monomial dividentElement;
			Monomial dividerElement = divider.elements.get(0);
			Monomial outElement;
			Polynomial temp;

			while (divident.elements.size() != 0 && dividerElement.getPower() <= divident.elements.get(0).getPower()) {
				dividentElement = divident.elements.get(0);
				outElement = dividentElement.divide(dividerElement);
				temp = divider.multiply(outElement);
				divident = divident.subtract(temp);
			}

			r.elements = divident.elements;
			return r;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Polynomial multiply(Polynomial p) {
		this.organize();
		p.organize();

		ArrayList<Monomial> prod = new ArrayList<Monomial>();
		Polynomial r = new Polynomial();
		int nbOfElements = p.elements.get(0).getPower() + this.elements.get(0).getPower() + 1;

		for (int i = 0; i < nbOfElements; i++) {
			prod.add(i, new Monomial(0, nbOfElements - 1 - i));
		}

		for (Monomial m1 : this.elements) {
			for (Monomial m2 : p.elements) {
				int index = nbOfElements - 1 - (m1.getPower() + m2.getPower());
				prod.set(index, prod.get(index).add(m1.multiply(m2)));
			}
		}

		r.elements = prod;
		r.clean();

		return r;
	}

	public Polynomial multiply(Monomial mono) {
		this.organize();
		Polynomial r = new Polynomial();

		for (Monomial m : this.elements) {
			r.elements.add(m.multiply(mono));
		}

		return r;
	}

	public Polynomial derivate() {
		this.organize();

		Polynomial r = new Polynomial();

		for (Monomial m : this.elements) {
			r.elements.add(m.derive());
		}

		r.clean();

		return r;
	}

	public Polynomial integrate() {
		this.organize();

		Polynomial r = new Polynomial();

		for (Monomial m : this.elements) {
			r.elements.add(m.integrate());
		}

		r.clean();

		return r;
	}

	public void organize() {
		PowerComparator comp = new PowerComparator();
		this.elements.sort(comp);
	}

	/**
	 * if there exist polynomials with elements with coefficient 0 this elements are
	 * removed
	 */
	public void clean() {
		ArrayList<Monomial> temp = this.elements;
		int i = 0;
		while (i < temp.size()) {
			if (temp.get(i).getCoef() != 0) {
				i++;
			} else {
				temp.remove(i);
			}
		}

		this.elements = temp;
	}

	/**
	 * 
	 * This method takes a string representing a polynomial written in traditional
	 * form and gets the coefficient and power of each monomial.
	 * 
	 * @param s
	 * @return ArrayList<Monomial>
	 * @throws WrongInputException
	 */
	private ArrayList<Monomial> obtainMonomials(String s) throws WrongInputException {
		ArrayList<Monomial> poly = new ArrayList<Monomial>();

		s = s.replaceAll("-", "+-");
		String[] string = s.split("\\+");
		String split = "x\\^|x";

		for (String elem : string) {
			if (!elem.equals("")) {
				String[] param = elem.split(split);

				if (param.length == 0) {
					poly.add(new Monomial(1, 1));
				} else if (param.length == 1) {

					if (!param[0].matches("^[0-9]*$||^[0-9.]*$||^-[0-9]*$||^-[0-9.]*$")) {
						throw new WrongInputException();
					}

					if (elem.contains("x")) {
						if (elem.equals("-x")) {
							poly.add(new Monomial(-1, 1));
						} else {
							poly.add(new Monomial(Double.parseDouble(param[0]), 1));
						}
					} else {
						poly.add(new Monomial(Double.parseDouble(param[0]), 0));
					}
				} else if (param.length == 2) {

					if (param[0].equals("")) {

						if (!param[1].matches("^[0-9]*$||^[0-9.]*$||^-[0-9]*$||^-[0-9.]*$")) {
							throw new WrongInputException();
						}
						poly.add(new Monomial(1, Integer.parseInt(param[1])));
					} else {

						if (!param[0].matches("^[0-9]*$||^[0-9.]*$||^-[0-9]*$||^-[0-9.]*$")
								|| !param[1].matches("^[0-9]*$||^[0-9.]*$||^-[0-9]*$||^-[0-9.]*$")) {
							throw new WrongInputException();
						}
						poly.add(new Monomial(Double.parseDouble(param[0]), Integer.parseInt(param[1])));
					}
				}
			}
		}
		return poly;
	}

	public String toString() {
		String s = "";
		double coef;
		int power;
		int polyDegree = this.getDegree();
		if (this.elements.size() == 0) {
			s += "0";
		} else {
			for (Monomial m : this.elements) {
				coef = m.getCoef();
				power = m.getPower();
				if (coef == 0) {
					s += "0";
				} else {
					if (power != polyDegree) {
						s += (coef > 0) ? "+" : "";
					}

					if (coef == -1 && power != 0) {
						s += "-";
					}

					if ((power >= 1 && coef != 1 && coef != -1) || (power == 0)) {
						if (Math.floor(coef) == coef) {
							s += (int) coef;
						} else {
							s += coef;
						}
					}

					if (power == 1) {
						s += "x";
					} else if (power > 1) {
						s += "x^" + power;
					}
				}
			}
		}
		return s;
	}

	public ArrayList<Monomial> getElements() {
		return this.elements;
	}

	public int getDegree() {
		return (this.elements.size() == 0) ? 0 : this.elements.get(0).getPower();
	}
}