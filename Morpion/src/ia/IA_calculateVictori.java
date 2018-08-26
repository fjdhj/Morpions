package ia;

public class IA_calculateVictori {

		

		
		public static int[] nbr_series(int gameState[][]) {
			int x, y;
			int comptJ = 0, comptIA = 0;
			int n = 3; //le nbr de pion devant être alignée pour gagner
			int[] playerSeries = {0, 0}; // le premier est les doublets de l'ia et le second ceux du joueur
		//Horizontalement
		for(y = 0; y < 3; y++) {
			int tempComptIA = 0;
			int tempComptJ = 0;
			for(x = 0; x < 3; x++) {
				if(gameState[x][y] == 1) {
					tempComptIA++;
					tempComptJ = 0;
				}
				if(gameState[x][y] == 2) {
					tempComptJ++;
					tempComptIA = 0;
				}
				if(tempComptIA == 2||tempComptJ == 2) {
					comptJ = tempComptJ;
					comptIA=tempComptIA;
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
					}
					if(gameState[x][y] == 2) {
						tempComptJ++;
						tempComptIA = 0;
					}
					if(tempComptIA == 2||tempComptJ == 2) {
						comptJ += tempComptJ;
						comptIA+=tempComptIA;
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
			}
			if(gameState[x][x] == 2) {
				tempComptJ++;
				tempComptIA = 0;
			}
			if(tempComptIA == 2||tempComptJ == 2) {
				comptJ += tempComptJ;
				comptIA+=tempComptIA;
			}
		
		}
		//Diagonale montante
		for(x = 0; x < 3; x++) {

		if(gameState[x][x-2] == 1) {
			tempComptIA++;
			tempComptJ = 0;
			}
			if(gameState[x][x-2] == 2) {
				tempComptJ++;
				tempComptIA = 0;
			}
			if(tempComptIA == 2||tempComptJ == 2) {
				comptJ += tempComptJ;
				comptIA+=tempComptIA;
			}
		}
				
				playerSeries[0] = comptIA;
				playerSeries[1] = comptJ;
				return playerSeries;

		}
		
		
		public static int Winer(int[][] gameState) {
			
			int playerSeries[] = {0, 0};
			
			playerSeries = nbr_series(gameState);
			
			if(playerSeries[0] != 0 && playerSeries[1] == 0) { //Si IA gagne
				
			}else if(playerSeries[0] == 0 && playerSeries[1] != 0) { //Si player
				
			}else if(playerSeries[0] == 0 && playerSeries[1] == 0) { //Si personne ne gagne
				
			}else if(playerSeries[0] != 0 && playerSeries[1] != 0) { //Si égalité
				
			}
			
			
		}
}