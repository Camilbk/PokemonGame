package pokemongame.model;

import java.util.List;

public class anotherLittleTest {

	public static void main(String[] args) throws Exception {
		
		Pokemon pikachu = new Pokemon("Pikachu");
		Pokemon bulbasaur =  new Pokemon("Bulbasaur");
		int turn = 1; 
		String lastOut = "Bulbasaur attacks Pikachu. \n It was not very effective..."; 
		String name = "pokemongame";
		
		PokemonGame game = new PokemonGame(pikachu, bulbasaur, turn, lastOut, name); 
		System.out.println(game.toString());
		String[] gameinfo = game.toString().split(":");
		for( String info : gameinfo) {
			System.out.println(info);
		}
		System.out.println(gameinfo.length);
		
		PokemonGame newGame = new PokemonGame(gameinfo); 
		System.out.println(newGame.toString());
		System.out.println(" ---------------  ");
		
		String[] s = newGame.getLastAction().split("\n"); 
		System.out.println(s[0]);
		System.out.println(s[1]);
	}
}
