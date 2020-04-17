
public class MapStore {

	public static final int[][] MAPDATA = new int[48][32];
	
	static {
		
		// overwrite known locations
		for (int i = 0; i < 48; i ++) 
			for (int j = 0; j < 32; j++)
				MAPDATA[i][j] = -1;
		
		
		for(int i = 3; i <= 8; i++)
			for (int j = 6; j <= 8; j++)
				MAPDATA[i][j] = 0;

		for(int i = 6; i <= 7; i++)
				MAPDATA[i][11] = 1;
			MAPDATA[7][10] = 1;
		
		for(int i = 7; i <= 10; i++)
			for (int j = 10; j <= 11; j++)
				MAPDATA[i][j] = 2;
		
		for(int i = 7; i <= 10; i++)
			MAPDATA[i][13] = 3;
		
		for(int i = 4; i <= 5; i++)
			for (int j = 11; j <= 12; j++)
				MAPDATA[i][j] = 4;
		
		for(int i = 0; i <= 2; i++)
			for (int j = 10; j <= 11; j++)
				MAPDATA[i][j] = 5;
		
		for(int i = 13; i <= 15; i++)
			MAPDATA[i][10] = 6;
		
		for(int i = 4; i <= 7; i++)
			for (int j = 3; j <= 4; j++)
				MAPDATA[i][j] = 7;
		
		for(int i = 9; i <= 11; i++)
			for (int j = 3; j <= 4; j++)
				MAPDATA[i][j] = 8;
		
		for(int i = 6; i <= 8; i++)
			for (int j = 0; j <= 1; j++)
				MAPDATA[i][j] = 9;
		
		for(int i = 9; i <= 10; i++)
				MAPDATA[i][15] = 10;
		
		for(int i = 10; i <= 12; i++)
			for (int j = 6; j <= 7; j++)
				MAPDATA[i][j] = 11;
		
		for(int i = 13; i <= 17; i++)
			MAPDATA[i][7] = 12;
		
		for(int i = 16; i <= 19; i++)
			for (int j = 9; j <= 10; j++)
				MAPDATA[i][j] = 13;
		
		for(int i = 11; i <= 12; i++)
			for (int j = 12; j <= 15; j++)
				MAPDATA[i][j] = 14;
		
		for(int i = 3; i <= 5; i++)
			for (int j = 0; j <= 1; j++)
				MAPDATA[i][j] = 15;
		
		for(int i = 19; i <= 21; i++)
			for (int j = 12; j <= 13; j++)
				MAPDATA[i][j] = 16;
		
		for(int i = 14; i <= 18; i++)
			for (int j = 13; j <= 15; j++)
				MAPDATA[i][j] = 17;
		
		for(int i = 12; i <= 15; i++)
			for (int j = 17; j <= 18; j++)
				MAPDATA[i][j] = 18;
		
		for(int i = 6; i <= 8; i++)
			for (int j = 15; j <= 16; j++)
				MAPDATA[i][j] = 19;
		
		for(int i = 3; i <= 5; i++)
			for (int j = 14; j <= 16; j++)
				MAPDATA[i][j] = 20;
		
		for(int i = 0; i <= 1; i++)
			for (int j = 13; j <= 16; j++)
				MAPDATA[i][j] = 21;
		
		for(int i = 3; i <= 5; i++)
			for (int j = 17; j <= 18; j++)
				MAPDATA[i][j] = 22;
		
		for(int i = 5; i <= 7; i++)
			for (int j = 20; j <= 21; j++)
				MAPDATA[i][j] = 23;
		
		for(int i = 0; i <= 2; i++)
			for (int j = 19; j <= 20; j++)
				MAPDATA[i][j] = 24;
		
		for(int i = 0; i <= 2; i++)
			for (int j = 23; j <= 24; j++)
				MAPDATA[i][j] = 25;

		for(int i = 0; i <= 1; i++)
			for (int j = 27; j <= 30; j++)
				MAPDATA[i][j] = 26;
		
		for(int i = 2; i <= 3; i++)
			for (int j = 27; j <= 29; j++)
				MAPDATA[i][j] = 27;
		
		for(int i = 15; i <= 19; i++)
			for (int j = 20; j <= 21; j++)
				MAPDATA[i][j] = 28;
		
		for(int i = 12; i <= 14; i++)
			for (int j = 21; j <= 23; j++)
				MAPDATA[i][j] = 29;
		
		for(int i = 10; i <= 14; i++)
			for (int j = 24; j <= 26; j++)
				MAPDATA[i][j] = 30;
		
		for(int i = 4; i <= 5; i++)
			for (int j = 27; j <= 29; j++)
				MAPDATA[i][j] = 31;
		for (int j = 28; j <= 29; j++)
			MAPDATA[6][j] = 31;
		
		for(int i = 23; i <= 25; i++)
			for (int j = 13; j <= 15; j++)
				MAPDATA[i][j] = 32;
		
		for(int i = 7; i <= 9; i++)
			for (int j = 27; j <= 28; j++)
				MAPDATA[i][j] = 33;
		
		for (int j = 21; j <= 23; j++)
			MAPDATA[9][j] = 34;
		for(int i = 8; i <= 10; i++)
			MAPDATA[i][22] = 34;
		
		for(int i = 6; i <= 7; i++)
			for (int j = 23; j <= 25; j++)
				MAPDATA[i][j] = 35;
		MAPDATA[8][24] = 35;
		
		for(int i = 17; i <= 19; i++)
			for (int j = 16; j <= 18; j++)
				MAPDATA[i][j] = 36;
		
		for(int i = 11; i <= 14; i++)
			for (int j = 28; j <= 30; j++)
				MAPDATA[i][j] = 37;
		
		for(int i = 21; i <= 24; i++)
			for (int j = 17; j <= 19; j++)
				MAPDATA[i][j] = 38;
		
		for (int i = 26; i <= 29;i++)
			for (int j = 20; j <= 22; j++)
				MAPDATA[i][j] = 39;

		for (int i = 19; i <= 23; i++)
			for (int j = 22; j <= 23; j++)
				MAPDATA[i][j] = 40;

		for (int i = 15; i <= 17; i++)
			for (int j = 24; j <= 25; j++)
				MAPDATA[i][j] = 41;

		for (int i = 28; i <= 30; i++)
			for (int j = 17; j <= 18; j++)
				MAPDATA[i][j] = 42;

		for (int i = 31; i <= 32; i++)
			for (int j = 19; j <= 22; j++)
				MAPDATA[i][j] = 43;
		
		for (int i = 30; i <= 31; i++)
				MAPDATA[i][16] = 44;
		for (int i = 28; i <= 29; i++)
			for (int j = 15; j <= 16; j++)
				MAPDATA[i][j] = 44;
		MAPDATA[32][17] = 44;
		
		for (int i = 34; i <= 35; i++)
			for (int j = 15; j <= 17; j++)
				MAPDATA[i][j] = 45;

		for (int i = 30; i <= 33; i++)
			for (int j = 14; j <= 15; j++)
				MAPDATA[i][j] = 46;
		
		for(int i = 28; i <= 31; i++)
			for (int j = 9; j <= 10; j++)
				MAPDATA[i][j] = 47;
		
		for(int i = 25; i <= 26; i++)
			for (int j = 8; j <= 10; j++)
				MAPDATA[i][j] = 48;
		
		for(int i = 20; i <= 22; i++)
			for (int j = 6; j <= 8; j++)
				MAPDATA[i][j] = 49;
		
		for(int i = 22; i <= 24; i++)
			for (int j = 4; j <= 5; j++)
				MAPDATA[i][j] = 50;
		
		for(int i = 27; i <= 28; i++)
			for (int j = 5; j <= 6; j++)
				MAPDATA[i][j] = 51;
		
		for(int i = 25; i <= 26; i++)
			for (int j = 3; j <= 4; j++)
				MAPDATA[i][j] = 52;
		
		for(int i = 29; i <= 30; i++)
			for (int j = 6; j <= 7; j++)
				MAPDATA[i][j] = 53;
		
		for(int i = 32; i <= 33; i++)
			for (int j = 6; j <= 7; j++)
				MAPDATA[i][j] = 54;
		
		for(int i = 29; i <= 31; i++)
			for (int j = 2; j <= 3; j++)
				MAPDATA[i][j] = 55;

		for (int i = 32; i <= 35; i++)
			for (int j = 9; j <= 11; j++)
				MAPDATA[i][j] = 56;

		for (int i = 35; i <= 38; i++)
			for (int j = 5; j <= 7; j++)
				MAPDATA[i][j] = 57;

		for (int i = 42; i <= 43; i++)
			for (int j = 7; j <= 8; j++)
				MAPDATA[i][j] = 58;

		for (int i = 39; i <= 41; i++)
			for (int j = 3; j <= 5; j++)
				MAPDATA[i][j] = 59;

		for (int i = 42; i <= 45; i++)
			for (int j = 2; j <= 4; j++)
				MAPDATA[i][j] = 60;

		for (int i = 42; i <= 44; i++)
			for (int j = 10; j <= 13; j++)
				MAPDATA[i][j] = 61;

		for (int j = 6; j <= 11; j++)
			MAPDATA[44][j] = 62;

		for (int i = 37; i <= 41; i++)
			for (int j = 8; j <= 11; j++)
				MAPDATA[i][j] = 63;

		for (int i = 34; i <= 41; i++)
			for (int j = 12; j <= 14; j++)
				MAPDATA[i][j] = 64;

		for (int i = 38; i <= 39; i++)
			for (int j = 15; j <= 17; j++)
				MAPDATA[i][j] = 65;

		for (int i = 41; i <= 42; i++)
			for (int j = 20; j <= 21; j++)
				MAPDATA[i][j] = 66;
		
		for (int i = 40; i <= 41; i++)
				MAPDATA[i][22] = 67;
		for (int i = 41; i <= 42; i++)
				MAPDATA[i][23] = 67;
		
		for (int i = 39; i <= 41; i++)
			for (int j = 24; j <= 25; j++)
				MAPDATA[i][j] = 68;

		for (int i = 38; i <= 39; i++)
			MAPDATA[i][26] = 69;

		for (int i = 37; i <= 38; i++)
			MAPDATA[i][27] = 70;

		for (int i = 34; i <= 36; i++)
			for (int j = 20; j <= 22; j++)
				MAPDATA[i][j] = 71;


		for (int i = 41; i <= 44; i++)
			for (int j = 27; j <= 31; j++)
				MAPDATA[i][j] = 80;

		
		for (int i = 37; i <= 38; i++)
			for (int j = 29; j <= 30; j++)
				MAPDATA[i][j] = 81;

		for (int i = 33; i <= 36; i++)
			for (int j = 30; j <= 31; j++)
				MAPDATA[i][j] = 82;

		for (int i = 43; i <= 44; i++)
			for (int j = 16; j <= 17; j++)
				MAPDATA[i][j] = 83;

		for (int i = 31; i <= 33; i++)
			for (int j = 27; j <= 29; j++)
				MAPDATA[i][j] = 84;
	
	}
	
}
