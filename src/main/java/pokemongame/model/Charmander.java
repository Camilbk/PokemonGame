package pokemongame.model;

import java.util.ArrayList;
import java.util.Collection;
	
public class Charmander extends Pokemon {
	
	
	public Charmander(String pokemon) {
		super(pokemon);
		if( !pokemon.equals("Charmander")) {
			throw new IllegalStateException();
		}
		
		specialAttacks = new ArrayList<>();
		String option1 = "Scratch"; // normal type attack
		String option2 = "Fire Fang"; // fire type attack
		String option3 = "Flamethrower"; // fire type attack
		specialAttacks.add(option1);
		specialAttacks.add(option2);
		specialAttacks.add(option3);
	}
	
}