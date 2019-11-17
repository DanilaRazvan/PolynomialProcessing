package mvc;

import control.*;
import view.*;

public class Main {

	public static void main(String[] args) {
		UI ui = new UI();
		
		@SuppressWarnings("unused")
		Controller c = new Controller(ui);
	}
}


//3x^3-2x^2+4x-3
//x^2+3x+3