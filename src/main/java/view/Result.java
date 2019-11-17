package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Result extends JPanel {

	private JLabel res;
	private JLabel rem;
	
	private JLabel resL;
	private JLabel remL;
	
	public Result() {
		this.setPreferredSize(new Dimension(550, 105));
		this.setBackground(Color.LIGHT_GRAY);
		
		this.resL = new JLabel("Result", SwingConstants.CENTER);
		this.resL.setPreferredSize(new Dimension(550, 20));
		this.resL.setOpaque(true);
		this.resL.setBackground(Color.GRAY);
		
		this.res = new JLabel("0", SwingConstants.CENTER);
		this.res.setPreferredSize(new Dimension(550, 20));
		
		this.remL = new JLabel("Remainder", SwingConstants.CENTER);
		this.remL.setPreferredSize(new Dimension(550, 20));
		this.remL.setOpaque(true);
		this.remL.setBackground(Color.GRAY);
		
		this.rem = new JLabel("", SwingConstants.CENTER);
		this.rem.setPreferredSize(new Dimension(550, 20));
		
		this.add(resL);
		this.add(res);
		this.add(remL);
		this.add(rem);
	}

	public String getResult() {
		return res.getText();
	}

	public void setResult(String result) {
		this.res.setText(result);
	}
	
	public void setRemainder(String remainder) {
		this.rem.setText(remainder);
	}

}
