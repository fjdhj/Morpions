package ia;

public class IA_calculateVictori {

	public static int[] nbr_series(int gameState[][]) {
		
		int x, y;
		int comptJ = 0, comptIA = 0;
		int n = 3; //le nbr de pion devant être alignée pour gagner
		int[] playerSeries = {0, 0};
		
		//Horizontalement
		for(y = 0; y < 3; y++) {
			for(x = 0; x < 3; x++) {
				
				switch(gameState[x][y]) {
				
					case 1 :
						comptIA++;
						comptJ = 0;
						if(comptIA == n) {
							playerSeries[0]++;
							
						}
						break;
				
					case 2 :
						comptJ++;
						comptIA = 0;
						if(comptJ == n) {
							playerSeries[1]++;
							
						}
						break;
				
					}
				
				}
				
			comptJ = 0; 
			comptIA = 0;
			
			}
		
		//Verticalement
		for(y = 0; y < 3; y++) {
			for(x = 0; x < 3; x++) {
				
				switch(gameState[y][x]) {
				
					case 1 :
						comptIA++;
						comptJ = 0;
						if(comptIA == n) {
							playerSeries[0]++;
							
						}
						break;
				
					case 2 :
						comptJ++;
						comptIA = 0;
						if(comptJ == n) {
							playerSeries[1]++;
							
						}
						break;
				
					}
				

				
				}
				
			comptJ = 0; 
			comptIA = 0;
				
			}
		
		//Diagonale decendante
		for(x = 0; x < 3; x++) {
			
			switch(gameState[x][x]) {
			
				case 1 :
					comptIA++;
					comptJ = 0;
					if(comptIA == n) {
						playerSeries[0]++;
						
					}
					break;
			
				case 2 :
					comptJ++;
					comptIA = 0;
					if(comptJ == n) {
						playerSeries[1]++;
						
					}
					break;
			
				}
			

			
			}
		
		comptJ = 0; 
		comptIA = 0;
		
		//Diagonal montante
		for(x = 0; 0 < 3; x++) {
			switch(gameState[x][2-x]) {
			
			case 1 :
				comptIA++;
				comptJ = 0;
				if(comptIA == n) {
					playerSeries[0]++;
					
				}
				break;
		
			case 2 :
				comptJ++;
				comptIA = 0;
				if(comptJ == n) {
					playerSeries[1]++;
					
				}
				break;
		
			}
		
		}
		
		return(playerSeries);
			
	}
	
	
	
	
	/*public static int eval() {
		return(0);
	}*/

}
