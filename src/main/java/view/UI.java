package view;

import javax.swing.*;

@SuppressWarnings("serial")
public class UI extends JFrame {

	private Data data = new Data();
	private Result res = new Result();

	public UI() {
		setVisible(true);
		setTitle("Operations with polynomials");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		add(data);
		add(res);

		pack();
	}

	public Data getData() {
		return this.data;
	}

	public Result getRes() {
		return this.res;
	}
}
