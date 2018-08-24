package ia;

public class IA_calculateVictori {

	public static void nbr_series(int gameState[][]) {
		
		int x, y;
		int comptJ = 0, comptIA = 0;
		int n = 3; //le nbr de pion devant être alignée pour gagner
		
		for(y = 0; y < 3; y++) {
			for(x = 0; x < 3; x++) {
				
				switch(gameState[x][y]) {
				
					case 1 :
						comptIA++;
						comptJ = 0;
						if(comptIA == n) {
							
						}
						
						break;
				
					case 2 :
						comptJ++;
						comptIA = 0;
						break;
				
					}	
				
				}
				
				
			}
		}
			
	}

}
