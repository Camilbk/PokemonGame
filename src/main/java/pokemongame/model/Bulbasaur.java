package pokemongame.model;

import java.util.ArrayList;
import java.util.Collection;

public class Bulbasaur extends Pokemon {
	
	//protected Collection<String> specialAttacks; 
	
	public Bulbasaur(String pokemon) {
		super(pokemon);
		if( !pokemon.equals("Bulbasaur")) {
			throw new IllegalStateException();
		}
		
		specialAttacks = new ArrayList<>();
		String option1 = "Tackle"; // normal type attack
		String option2 = "Vine Whip"; // grass type attack
		String option3 = "Razor Leaf"; // grass type attack
		specialAttacks.add(option1);
		specialAttacks.add(option2);
		specialAttacks.add(option3);
	}
	

}
