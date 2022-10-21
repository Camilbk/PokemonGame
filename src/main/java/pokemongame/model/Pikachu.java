package pokemongame.model;

import java.util.ArrayList;
import java.util.Collection;
	
public class Pikachu extends Pokemon {
	
	
	public Pikachu(String pokemon) {
		super(pokemon);
		if( !pokemon.equals("Pikachu")) {
			throw new IllegalStateException();
		}
		
		specialAttacks = new ArrayList<>();
		String option1 = "Quick Attack"; // normal type attack
		String option2 = "Thunder Shock"; // electric type attack
		String option3 = "Thunderbolt"; // electric type attack
		specialAttacks.add(option1);
		specialAttacks.add(option2);
		specialAttacks.add(option3);
	}

	
}
