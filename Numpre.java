import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

public class Numpre {
	static Frame myframe;
	static Cell c;
	static Answer answer;
	public static void main(String args[]) {
		int x,y;
		answer = new Answer();
		answer.answer = false;

		myframe = new Frame();
		myframe.setLayout(null);
		myframe.setBounds(0,30,1900,900);
		myframe.setVisible(true);
		myframe.setTitle("数独解答");
		myframe.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				System.exit(0);
			}
		});

		Font fo = new Font("Dialog",Font.PLAIN,60);
		Font fo1 = new Font("Dialog",Font.PLAIN,20);

		TextField ts[][] = new TextField[9][9];
		int ys = 24;
		int xs;
		for(y = 0; y < 9; y++) {
			xs = 24;
			if(y == 3 || y == 6) ys += 80;
			else ys += 73;
			for(x = 0; x < 9; x++) {
				if(x == 3 || x == 6) xs += 80;
				else xs += 73;
				ts[y][x] = new TextField();
				myframe.add(ts[y][x]);
				ts[y][x].setBounds(xs,ys,70,70);
				ts[y][x].setFont(fo);
			}
		}

		Panel bigPanel = new Panel();
		myframe.add(bigPanel);
		bigPanel.setBounds(85,85,695,695);
		bigPanel.setBackground(Color.black);

		Label l = new Label();
		myframe.add(l);
		l.setBounds(85,800,400,60);
		l.setText("問題を入力し、左のボタンを押してください");
		l.setFont(fo1);

		Button b = new Button();
		myframe.add(b);
		b.setBounds(600,810,150,50);
		b.setLabel("解答を表示");
		b.setFont(fo1);
		b.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if(answer.answer == true) answer.reanswer();
				c = new Cell();
				int xx,yy;
				String buf;
				for(yy = 0; yy < 9; yy++) {
					for(xx = 0; xx < 9; xx++) {
						buf = ts[yy][xx].getText();
						if(buf.equals("")) {
							c.cell[yy][xx] = 0;
						}
						else {
							try {
								int a = Integer.parseInt(buf);
								if(a < 0 || a > 9) {
									myframe.remove(l);
									l.setText("エラー：１から９の間で入力してください");
									myframe.add(l);
									sleep(1000);
									myframe.remove(l);
									l.setText("問題を入力し、左のボタンを押してください");
									myframe.add(l);
									return;
								} else {
									c.cell[yy][xx] = a;
								}
							} catch(Exception e) {
								myframe.remove(l);
								l.setText("エラー：半角数字で入力してください");
								myframe.add(l);
								sleep(1000);
								myframe.remove(l);
								l.setText("問題を入力し、左のボタンを押してください");
								myframe.add(l);
								return;
							}
						}
					}
				}
				c = Calc.calculation(c);
				if(! Calc.check(c)) {
					BT bt = new BT();
					c = bt.bt(c);
				} else {
					answer.answer(c);
				}
			}
		});
	}

	static void sleep(long msec) {
		try{
			Thread.sleep(msec);
		} catch(InterruptedException ie) {
		}
	}
}

