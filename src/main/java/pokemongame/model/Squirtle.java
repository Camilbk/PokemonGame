package pokemongame.model;

import java.util.ArrayList;
import java.util.Collection;
	
public class Squirtle extends Pokemon {
	
	
	public Squirtle(String pokemon) {
		super(pokemon);
		if( !pokemon.equals("Squirtle")) {
			throw new IllegalStateException();
		}
		
		specialAttacks = new ArrayList<>();
		String option1 = "Tail Whip"; // normal type attack
		String option2 = "Water Gun"; // water type attack
		String option3 = "Hydro Pump"; // water type attack
		specialAttacks.add(option1);
		specialAttacks.add(option2);
		specialAttacks.add(option3);
	}


	
}