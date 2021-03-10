public class BT {
	Cell bt(Cell c) {
		int x,y;
		c.row = new int[9][10];
		c.col = new int[9][10];
		c.room = new int[9][10];
		c.hozonx = new int[9][10];
		c.hozony = new int[10];

		for(y = 0; y < 9; y++) {
			for(x = 0; x < 9; x++) {
				if(c.cell[y][x] != 0) {
					c.row[y][c.cell[y][x]] = 1;
					c.col[x][c.cell[y][x]] = 1;
					c.room[c.getRoom(y,x)][c.cell[y][x]] = 1;
				}
			}
		}

		backtrack1(c,0,1);
		return c;
	}

	boolean backtrack1(Cell c, int y, int n) {
		int x;
		if( c.row[y][n] == 0 ) {
			for(x = 0; x < 9; x++) {
				if(c.cell[y][x] != 0) continue;
				if((c.col[x][n] == 0) && (c.room[c.getRoom(y,x)][n] == 0)) {
					c.cell[y][x] = n;
					c.row[y][n] = 1;
					c.col[x][n] = 1;
					c.room[c.getRoom(y,x)][n] = 1;
					c.hozonx[y][n] = x;
					if((    c.cell[0][0] != 0) && (c.cell[0][1] != 0)&& (c.cell[0][2] != 0)
							&& (c.cell[0][3] != 0)&& (c.cell[0][4] != 0)&& (c.cell[0][5] != 0)
							&& (c.cell[0][6] != 0)&& (c.cell[0][7] != 0)&& (c.cell[0][8] != 0)
							&& (c.cell[1][0] != 0)&& (c.cell[1][1] != 0)&& (c.cell[1][2] != 0)
							&& (c.cell[1][3] != 0)&& (c.cell[1][4] != 0)&& (c.cell[1][5] != 0)
							&& (c.cell[1][6] != 0)&& (c.cell[1][7] != 0)&& (c.cell[1][8] != 0)
							&& (c.cell[2][0] != 0)&& (c.cell[2][1] != 0)&& (c.cell[2][2] != 0)
							&& (c.cell[2][3] != 0)&& (c.cell[2][4] != 0)&& (c.cell[2][5] != 0)
							&& (c.cell[2][6] != 0)&& (c.cell[2][7] != 0)&& (c.cell[2][8] != 0)
							&& (c.cell[3][0] != 0)&& (c.cell[3][1] != 0)&& (c.cell[3][2] != 0)
							&& (c.cell[3][3] != 0)&& (c.cell[3][4] != 0)&& (c.cell[3][5] != 0)
							&& (c.cell[3][6] != 0)&& (c.cell[3][7] != 0)&& (c.cell[3][8] != 0)
							&& (c.cell[4][0] != 0)&& (c.cell[4][1] != 0)&& (c.cell[4][2] != 0)
							&& (c.cell[4][3] != 0)&& (c.cell[4][4] != 0)&& (c.cell[4][5] != 0)
							&& (c.cell[4][6] != 0)&& (c.cell[4][7] != 0)&& (c.cell[4][8] != 0)
							&& (c.cell[5][0] != 0)&& (c.cell[5][1] != 0)&& (c.cell[5][2] != 0)
							&& (c.cell[5][3] != 0)&& (c.cell[5][4] != 0)&& (c.cell[5][5] != 0)
							&& (c.cell[5][6] != 0)&& (c.cell[5][7] != 0)&& (c.cell[5][8] != 0)
							&& (c.cell[6][0] != 0)&& (c.cell[6][1] != 0)&& (c.cell[6][2] != 0)
							&& (c.cell[6][3] != 0)&& (c.cell[6][4] != 0)&& (c.cell[6][5] != 0)
							&& (c.cell[6][6] != 0)&& (c.cell[6][7] != 0)&& (c.cell[6][8] != 0)
							&& (c.cell[7][0] != 0)&& (c.cell[7][1] != 0)&& (c.cell[7][2] != 0)
							&& (c.cell[7][3] != 0)&& (c.cell[7][4] != 0)&& (c.cell[7][5] != 0)
							&& (c.cell[7][6] != 0)&& (c.cell[7][7] != 0)&& (c.cell[7][8] != 0)
							&& (c.cell[8][0] != 0)&& (c.cell[8][1] != 0)&& (c.cell[8][2] != 0)
							&& (c.cell[8][3] != 0)&& (c.cell[8][4] != 0)&& (c.cell[8][5] != 0)
							&& (c.cell[8][6] != 0)&& (c.cell[8][7] != 0)&& (c.cell[8][8] != 0)
							)
					{
						Numpre.answer.answer(c);
					}
					if (y >= 8) {
						if(n < 9) {
							c.hozony[n] = y;
							y = 0;
							backtrack1(c, y, n+1);
							y = c.hozony[n];
							c.cell[y][c.hozonx[y][n]] = 0;
							c.row[y][n] = 0;
							c.col[c.hozonx[y][n]][n] = 0;
							c.room[c.getRoom(y, c.hozonx[y][n])][n] = 0;
							return true;
						}else {
							return true;
						}
					} else {
						backtrack1(c, y+1, n);
						c.cell[y][c.hozonx[y][n]] = 0;
						c.row[y][n] = 0;
						c.col[c.hozonx[y][n]][n] = 0;
						c.room[c.getRoom(y, c.hozonx[y][n])][n] = 0;
					}
				}
			}
			return true;
		}else {

			if (y >= 8) {
				if(n < 9) {
					y = 0;
					backtrack1(c, y, n+1);
					return true;

				}else {
					return true;
				}
			}else {
				backtrack1(c, y+1, n);
				return true;

			}
		}
	}

}

