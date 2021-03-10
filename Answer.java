import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;

class Answer {
	Label ls[][];
	Panel bigPanel;
	Label migi;
	boolean answer;

	Answer() {
		Font fo = new Font("Dialog",Font.PLAIN,60);
		ls = new Label[9][9];
		int ys = 25;
		int xs;
		for(int y = 0; y < 9; y++) {
			xs = 840;
			if(y == 3 || y == 6) ys += 80;
			else ys += 73;
			for(int x = 0; x < 9; x++) {
				if(x == 3 || x == 6) xs += 80;
				else xs += 73;
				ls[y][x] = new Label();
				ls[y][x].setBounds(xs,ys,70,70);
				ls[y][x].setBackground(Color.white);
				ls[y][x].setFont(fo);
			}
		}

		bigPanel = new Panel();
		bigPanel.setBounds(900,85,695,695);
		bigPanel.setBackground(Color.black);

		migi = new Label();
		migi.setBounds(800,400,70,70);
		migi.setText("→");
		migi.setFont(fo);

		answer = false;

	}

	void answer(Cell c) {
		for(int y = 0; y < 9; y++) {
			for(int x = 0; x < 9; x++) {
				Numpre.myframe.add(ls[y][x]);
				ls[y][x].setText("" + c.cell[y][x]);
			}
		}
		Numpre.myframe.add(bigPanel);
		Numpre.myframe.add(migi);
		answer = true;
	}

	void reanswer() {
		for(int y = 0; y < 9; y++) {
			for(int x = 0; x < 9; x++) {
				Numpre.myframe.remove(ls[y][x]);
			}
		}
		Numpre.myframe.remove(bigPanel);
		Numpre.myframe.remove(migi);
		answer = false;

	}

}

