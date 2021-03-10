class Calc {
	static Cell calculation(Cell c) {
		Calc calc = new Calc();
		do {
			while(true) {
				if(calc.calc(c)) continue;
				break;
			}
		} while(calc.calc2(c));
		return c;
	}

	static boolean check(Cell c) {
		int x, y;
		for(y = 0; y < 9; y++) {
			for(x = 0; x < 9; x++) {
				if(c.cell[y][x] == 0) return false;
			}
		}
		return true;
	}

	boolean calc(Cell c) {
		c.row = new int[9][10];
		c.col = new int[9][10];
		c.room = new int[9][10];
		c.possible = new int[9][9][10];

		int x,y,n;
		for(y = 0; y < 9; y++) {
			for(x = 0; x < 9; x++) {
				if(c.cell[y][x] != 0) {
					c.row[y][c.cell[y][x]] = 1;
					c.col[x][c.cell[y][x]] = 1;
					c.room[c.getRoom(y,x)][c.cell[y][x]] = 1;
				}
			}
		}
		for(y = 0; y < 9; y++) {
			for(x = 0; x < 9; x++) {
				if(c.cell[y][x] != 0) continue;
				for(n = 1; n < 10; n++) {
					if( (c.row[y][n] == 0) && (c.col[x][n] == 0) && (c.room[c.getRoom(y,x)][n] == 0) ) {
						c.possible[y][x][n] = 1;
						c.possible[y][x][0]++;
					}
				}
			}
		}
		return kakutei(c);
	}

	boolean kakutei(Cell c) {
		int x,y,n;
		for(y = 0; y < 9; y++) {
			for(x = 0; x < 9; x++) {
				if(c.cell[y][x] != 0) continue;
				if(c.possible[y][x][0] == 1) {
					for(n = 1; n < 10; n++) {
						if(c.possible[y][x][n] == 1) {
							c.cell[y][x] = n;
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	boolean calc2(Cell c) {
		int x,y,n;

		int rowPossible[][] = new int[9][10];
		for(y = 0; y < 9; y++) {
			for(x = 0; x < 9; x++) {
				if(c.cell[y][x] != 0) continue;
				for(n = 1; n < 10; n++) {
					if(c.possible[y][x][n] == 1) {
						rowPossible[y][n]++;
					}
				}
			}
			for(n = 1; n < 10; n++) {
				if(rowPossible[y][n] == 1) {
					for(x = 0; x < 9; x++) {
						if(c.possible[y][x][n] == 1) {
							c.cell[y][x] = n;
							return true;
						}
					}
				}
			}
		}

		int colPossible[][] = new int[9][10];
		for(x = 0; x < 9; x++) {
			for(y = 0; y < 9; y++) {
				if(c.cell[y][x] != 0) continue;
				for(n = 1; n < 10; n++) {
					if(c.possible[y][x][n] == 1) {
						colPossible[x][n]++;
					}
				}
			}
			for(n = 1; n < 10; n++) {
				if(colPossible[x][n] == 1) {
					for(y = 0; y < 9; y++) {
						if(c.possible[y][x][n] == 1) {
							c.cell[y][x] = n;
							return true;
						}
					}
				}
			}
		}

		int roomPossible[][] = new int[9][10];
		int m;
		for(int r = 0; r < 9; r++) {
			for(m = 0; m < 9; m++) {
				y = getPointY(r,m);
				x = getPointX(r,m);
				if(c.cell[y][x] != 0) continue;
				for(n = 1; n < 10; n++) {
					if(c.possible[y][x][n] == 1) {
						roomPossible[r][n]++;
					}
				}
			}
			for(n = 1; n < 10; n++) {
				if(roomPossible[r][n] == 1) {
					for(m = 0; m < 9; m++) {
						y = getPointY(r,m);
						x = getPointX(r,m);
						if(c.possible[y][x][n] == 1) {
							c.cell[y][x] = n;
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	int getPointY(int r, int m) {
		if     ( (r >= 0 && r <= 2) && (m >= 0 && m <= 2) ) return 0;
		else if( (r >= 0 && r <= 2) && (m >= 3 && m <= 5) ) return 1;
		else if( (r >= 0 && r <= 2) && (m >= 6 && m <= 8) ) return 2;
		else if( (r >= 3 && r <= 5) && (m >= 0 && m <= 2) ) return 3;
		else if( (r >= 3 && r <= 5) && (m >= 3 && m <= 5) ) return 4;
		else if( (r >= 3 && r <= 5) && (m >= 6 && m <= 8) ) return 5;
		else if( (r >= 6 && r <= 8) && (m >= 0 && m <= 2) ) return 6;
		else if( (r >= 6 && r <= 8) && (m >= 3 && m <= 5) ) return 7;
		else if( (r >= 6 && r <= 8) && (m >= 6 && m <= 8) ) return 8;
		return 0;
	}

	int getPointX(int r, int m) {
		if     ( (r == 0 || r == 3 || r == 6) && (m == 0 || m == 3 || m == 6) ) return 0;
		else if( (r == 0 || r == 3 || r == 6) && (m == 1 || m == 4 || m == 7) ) return 1;
		else if( (r == 0 || r == 3 || r == 6) && (m == 2 || m == 5 || m == 8) ) return 2;
		else if( (r == 1 || r == 4 || r == 7) && (m == 0 || m == 3 || m == 6) ) return 3;
		else if( (r == 1 || r == 4 || r == 7) && (m == 1 || m == 4 || m == 7) ) return 4;
		else if( (r == 1 || r == 4 || r == 7) && (m == 2 || m == 5 || m == 8) ) return 5;
		else if( (r == 2 || r == 5 || r == 8) && (m == 0 || m == 3 || m == 6) ) return 6;
		else if( (r == 2 || r == 5 || r == 8) && (m == 1 || m == 4 || m == 7) ) return 7;
		else if( (r == 2 || r == 5 || r == 8) && (m == 2 || m == 5 || m == 8) ) return 8;
		return 0;
	}

}

