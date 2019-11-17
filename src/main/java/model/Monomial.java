package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Monomial {

	private double coef;
	private int power;

	public Monomial() {
	}

	public Monomial(double coefficient, int power) {
		this.coef = coefficient;
		this.power = power;
	}

	public Monomial add(Monomial m) {
		Monomial r = new Monomial();

		if (m.getPower() == this.getPower()) {
			r.setCoef(this.getCoef() + m.getCoef());
			r.setPower(m.getPower());
			return r;
		}

		return null;
	}

	public Monomial subtract(Monomial m) {
		Monomial r = new Monomial();

		if (m.getPower() == this.getPower()) {
			r.setCoef(this.getCoef() - m.getCoef());
			r.setPower(m.getPower());
			return r;
		}

		return null;
	}

	public Monomial multiply(Monomial m) {
		Monomial r = new Monomial();

		r.setCoef(m.getCoef() * this.getCoef());
		r.setPower(m.getPower() + this.getPower());

		return r;
	}

	public Monomial divide(Monomial m) {
		Monomial r = new Monomial();

		if (this.getPower() - m.getPower() >= 0) {
			r.setPower(this.getPower() - m.getPower());
			r.setCoef(this.getCoef() / m.getCoef());
			return r;
		}

		return null;
	}

	public Monomial derive() {
		Monomial r = new Monomial();

		if (this.getPower() != 0) {
			r.setCoef(this.getCoef() * this.getPower());
			r.setPower(this.getPower() - 1);
		} else {
			r.setCoef(0);
			r.setPower(0);
		}
		return r;
	}

	public Monomial integrate() {
		Monomial r = new Monomial();

		r.setCoef(this.getCoef() / (this.getPower() + 1));
		r.setPower(this.getPower() + 1);

		return r;
	}

	public double getCoef() {
		return round(coef, 2);
	}

	public void setCoef(double coef) {
		this.coef = coef;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void display() {
		System.out.println(this.getCoef() + "x^" + this.getPower());
	}
	
	private double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}
