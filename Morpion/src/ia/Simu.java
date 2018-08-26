package ia;

public class Simu {
	
	private static int seriesJ, seriesIA;
	

	public static int Min(int[][] gameState, int profondeur) { //SIMULATION DES POSSIBILITE DE JOUEUR
		
		if(profondeur == 1 /*|| AJOUTER TEST DE FIN DE PARTIE*/) {
			//return(eval); AJOUTER POUR L'EVAL DU JEU ACTUEL
		}
		
		int min = 1000;
		int x, y, sim;
		
		for(y = 0; y < 3; y++) {
			for(x = 0; x < 3; x++) {
				
				if(gameState[x][y] == 0) {
					
					gameState[x][y] = 2; //On simul le pion
					
					sim = Max(gameState, profondeur-1); // On simul ce que pourait joueur l'IA
					
				}
			}
		}
		
		return(0);
	}
	

	public static int Max(int[][] gameState, int profondeur) {  //SIMULATION DES POSSIBILITE DE L'IA
		
		if(profondeur == 1 /*|| AJOUTER TEST DE FIN DE PARTIE*/) {
			//return(eval); AJOUTER POUR L'EVAL DU JEU ACTUEL
		}
		
		int max = -1000;
		int x, y, sim;
		
		for(y = 0; y < 3; y++) {
			for(x = 0; x < 3; x++) {
				
				if(gameState[x][y] == 0) {
					
					gameState[x][y] = 1; //On simul le pion
					
					sim = Min(gameState, profondeur-1); // On simul ce que pourait joueur le joueur
					
				}
			}
		}
		
		return(0);
	}
	
	
	/*
	 * Methode qui évalue l'état de la partie en simulation
	 */
	
	public static int eval(int gameState[][]) {
		
		int nbr_pions = 0;
		int x, y, winer;
		
		
		for(y = 0; y < 3; y++) {
			for(x = 0; x < 3; x++) {
				
				if(gameState[x][y] != 0) {
					nbr_pions++;
				}
			}
		}
		
		
		return(0);
	}
	

}
