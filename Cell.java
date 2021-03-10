class Cell {
	int cell[][]; //int
	int row[][]; //boolean
	int col[][]; //boolean
	int room[][]; //boolean
	int possible[][][]; //boolean
	int hozonx[][];
	int hozony[];

	int getRoom(int y, int x) {
		if     ( (y >= 0 && y <= 2) && (x >= 0 && x <= 2) ) return 0;
		else if( (y >= 0 && y <= 2) && (x >= 3 && x <= 5) ) return 1;
		else if( (y >= 0 && y <= 2) && (x >= 6 && x <= 8) ) return 2;
		else if( (y >= 3 && y <= 5) && (x >= 0 && x <= 2) ) return 3;
		else if( (y >= 3 && y <= 5) && (x >= 3 && x <= 5) ) return 4;
		else if( (y >= 3 && y <= 5) && (x >= 6 && x <= 8) ) return 5;
		else if( (y >= 6 && y <= 8) && (x >= 0 && x <= 2) ) return 6;
		else if( (y >= 6 && y <= 8) && (x >= 3 && x <= 5) ) return 7;
		else if( (y >= 6 && y <= 8) && (x >= 6 && x <= 8) ) return 8;
		return 0;
	}

	Cell() {
		cell = new int[9][9];
	}

}

