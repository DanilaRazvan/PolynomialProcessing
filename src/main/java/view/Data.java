package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class Data extends JPanel {

	private JLabel firstPolL;
	private JTextField firstPolT;

	private JButton addB;
	private JButton subB;
	private JButton mulB;
	private JButton divB;
	private JButton derB;
	private JButton intB;
	private JButton equalsB;
	private JButton deleteButton;

	public Data() {

		this.setVisible(true);
		this.setLayout(null);
		this.setBackground(Color.LIGHT_GRAY);
		this.setPreferredSize(new Dimension(545, 225));

		// display info about first polynomial
		this.firstPolL = new JLabel("Polynomial:");
		this.firstPolL.setBounds(45, 50, 120, 25);
		this.firstPolL.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(firstPolL);
		this.firstPolT = new JTextField("");
		this.firstPolT.setBounds(145, 50, 355, 25);
		this.firstPolT.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(firstPolT);

		// display buttons
		this.addB = new JButton("+");
		this.addB.setBounds(5, 125, 85, 25);
		this.addB.setActionCommand("addition");
		this.add(addB);

		this.subB = new JButton("-");
		this.subB.setBounds(95, 125, 85, 25);
		this.subB.setActionCommand("subtraction");
		this.add(subB);

		this.mulB = new JButton("*");
		this.mulB.setBounds(185, 125, 85, 25);
		this.mulB.setActionCommand("multiplication");
		this.add(mulB);

		this.divB = new JButton("/");
		this.divB.setBounds(275, 125, 85, 25);
		this.divB.setActionCommand("division");
		this.add(divB);

		this.derB = new JButton("'");
		this.derB.setBounds(365, 125, 85, 25);
		this.derB.setActionCommand("derivation");
		this.add(derB);

		this.intB = new JButton("S");
		this.intB.setBounds(455, 125, 85, 25);
		this.intB.setActionCommand("integration");
		this.add(intB);
		
		this.equalsB = new JButton("=");
		this.equalsB.setBounds(141, 175, 86, 25);
		this.add(equalsB);
		
		this.deleteButton = new JButton("DEL");
		this.deleteButton.setBounds(317, 175, 86, 25);
		this.add(deleteButton);

	}

	public void setListeners(ActionListener add, ActionListener sub, ActionListener mult, ActionListener div,
			ActionListener integr, ActionListener deriv, ActionListener eq, ActionListener del) {
		addB.addActionListener(add);
		subB.addActionListener(sub);
		mulB.addActionListener(mult);
		divB.addActionListener(div);
		intB.addActionListener(integr);
		derB.addActionListener(deriv);
		equalsB.addActionListener(eq);
		deleteButton.addActionListener(del);
	}

	public String getFirstPolynomial() {
		return firstPolT.getText();
	}
	
	public void setFirstPolynomial(String s) {
		this.firstPolT.setText(s);
	}
}