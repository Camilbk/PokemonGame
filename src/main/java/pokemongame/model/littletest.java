package pokemongame.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class littletest {
	
	public static void main(String[] args) throws Exception {
		
		Pokemon bulbasaur = new Pokemon("bulbasaur");
		Pokemon pikachu = new Pokemon("pikachu");
		
		System.out.println(bulbasaur.toString());
		System.out.println(pikachu.toString());
		
		while( !Pokemon.gameOver(bulbasaur, pikachu)) {
			bulbasaur.attack(pikachu);
			
			System.out.println(bulbasaur.toString());
			System.out.println(pikachu.toString());
			
			if(pikachu.hasFainted()) {
				break;
			}
			
			pikachu.attack(bulbasaur);
			
			System.out.println(bulbasaur.toString());
			System.out.println(pikachu.toString());
		}
		
		List<Pokemon> pokemons = new ArrayList<>(Arrays.asList(bulbasaur, pikachu, new Pokemon("charmander")));

		
		
	}

}

