package ia;

public class Simu {
	
	private static int seriesJ, seriesIA;
	

	public static int Min(int[][] gameState, int profondeur) { //SIMULATION DES POSSIBILITE DE JOUEUR
		
		if(profondeur == 0 || IA_calculateVictori.Winer(gameState) != 0) {
			return(eval(gameState)); 
		}
		
		int min = 10000;
		int x, y, sim;
		
		for(y = 0; y < 3; y++) {
			for(x = 0; x < 3; x++) {
				
				if(gameState[x][y] == 0) {
					
					gameState[x][y] = 2; //On simul le pion
					
					sim = Max(gameState, profondeur-1); // On simul ce que pourait joueur l'IA
					
					if(sim < min || (sim == min && Math.random() >=  0.5)) {
						min = sim;
					}
					gameState[x][y] = 0;
					
				}
			}
		}
		
		return(min);
	}
	

	public static int Max(int[][] gameState, int profondeur) {  //SIMULATION DES POSSIBILITE DE L'IA
		
		if(profondeur == 0 || IA_calculateVictori.Winer(gameState) != 0) {
			return(eval(gameState)); 
		}
		
		int max = -10000;
		int x, y, sim;
		
		for(y = 0; y < 3; y++) {
			for(x = 0; x < 3; x++) {
				
				if(gameState[x][y] == 0) {
					
					gameState[x][y] = 1; //On simul le pion
					
					sim = Min(gameState, profondeur-1); // On simul ce que pourait joueur le joueur
					
					if(sim > max || (sim == max && Math.random() >=  0.5)) {
						max = sim;
					}
					gameState[x][y] = 0;
					
				}
			}
		}
		
		return(max);
	}
	
	
	/*
	 * Methode qui �value l'�tat de la partie en simulation
	 */
	
	public static int eval(int gameState[][]) {
		
		int nbr_pions = 0;
		int x, y;
		
		
		for(y = 0; y < 3; y++) {
			for(x = 0; x < 3; x++) {
				
				if(gameState[x][y] != 0) {
					nbr_pions++;
				}
			}
		}
		
		int winer = IA_calculateVictori.Winer(gameState);
		
		if(winer == 1) { //Victoire IA
			return(1000 - nbr_pions);
			
		}else if(winer == 2) { //Victoire Player
			return(-1000 + nbr_pions);
			
		}else if(winer == 0){ //Si pas fini
			return(0);

		}else {
			int playerSeries[] = IA_calculateVictori.nbr_series(gameState, 2);	
			return(playerSeries[0] - playerSeries[1]);
			
		}
		
		
	}
	

}
