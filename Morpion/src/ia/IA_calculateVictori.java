package ia;

public class IA_calculateVictori {

		

		
		public static int[] nbr_series(int gameState[][], int n) {
			int x, y;
			int comptJ = 0, comptIA = 0;
			int[] playerSeries = {0, 0}; // le premier est les doublets de l'ia et le second ceux du joueur
			
		//Horizontalement
		for(y = 0; y < 3; y++) {
			int tempComptIA = 0;
			int tempComptJ = 0;
			for(x = 0; x < 3; x++) {
				if(gameState[x][y] == 1) {
					tempComptIA++;
					tempComptJ = 0;
					if(tempComptIA == n) {
						comptIA = tempComptIA;
					}
				}else if(gameState[x][y] == 2) {
					tempComptJ++;
					tempComptIA = 0;
					if(tempComptJ == n) {
						comptJ = tempComptJ;
					}
				}
			}
		}
		//Verticalement
		for(x = 0; x < 3; x++) {
			int tempComptIA = 0;
			int tempComptJ = 0;
			for(y = 0; y < 3; y++) {

					if(gameState[x][y] == 1) {
						tempComptIA++;
						tempComptJ = 0;
						if(tempComptIA == n) {
							comptIA = tempComptIA;
						}
						
					}else if(gameState[x][y] == 2) {
						tempComptJ++;
						tempComptIA = 0;
						if(tempComptJ == n) {
							comptJ = tempComptJ;
							
						}
						
					}
				
				}
				
			}
		
		//Diagonale decendante
		int tempComptIA = 0;
		int tempComptJ = 0;
		for(x = 0; x < 3; x++) {

			if(gameState[x][x] == 1) {
				tempComptIA++;
				tempComptJ = 0;
				if(tempComptIA == n) {
					comptIA = tempComptIA;
				}
			}else if(gameState[x][x] == 2) {
				tempComptJ++;
				tempComptIA = 0;
				if(tempComptJ == n) {
					comptJ = tempComptJ;
				}
			}
		
		}
		//Diagonale montante
		for(x = 0; x < 3; x++) {

			if(gameState[x][2-x] == 1) {
				tempComptIA++;
				tempComptJ = 0;
				if(tempComptIA == n) {
					comptIA = tempComptIA;
				}
			}else if(gameState[x][2-x] == 2) {
				tempComptJ++;
				tempComptIA = 0;
				if(tempComptJ == n) {
					comptJ = tempComptJ;
				}
			}

		}
				
				playerSeries[0] = comptIA;
				playerSeries[1] = comptJ;
				return playerSeries;

		}
		
		
		public static int Winer(int[][] gameState) {
			
			int playerSeries[] = {0, 0};
			
			playerSeries = nbr_series(gameState, 3);
			
			if(playerSeries[0] != 0 && playerSeries[1] == 0) { //Si IA gagne
				return(1);
				
			}else if(playerSeries[0] == 0 && playerSeries[1] != 0) { //Si player
				return(2);
				
			}else{ 
				for(int y = 0; y < 3; y++) {
					for(int x = 0; x < 3; x++) {
						if(gameState[x][y] == 0) {//Si pas fini
							return(0);
						}
					}
				}
				return(3);//Si égalité
			}
			
			
		}
}