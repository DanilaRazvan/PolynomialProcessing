package model;

import java.util.Comparator;

public class PowerComparator implements Comparator<Monomial> {

	public int compare(Monomial o1, Monomial o2) {
		return o2.getPower() - o1.getPower();
	}
}
