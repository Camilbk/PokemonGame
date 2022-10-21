package pokemongame.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.DisplayName;

public class PokemonTest {

	private Pokemon bulbasaur, pikachu, charmander, squirtle;
	
	
	@BeforeEach
	public void setup() {
		bulbasaur = new Pokemon("bulbasaur");
		pikachu = new Pokemon("pikachu");
		charmander = new Pokemon("charmander");
		charmander.setHP(0);
		
		squirtle = new Squirtle("Squirtle"); 
	}
	
	@Test
	public void testAttack() throws Exception {
		/*
		 *  Attacks cannot deal negative damage,
		 *   and has an upper bound,
		 *   and cannot attack itself or fainted pokemon
		 */
		pikachu.setHP(100000);
		int damageTaken = 0; 
		boolean negativeDamage = false; 
		boolean max_exceeded = false; 
		while(!pikachu.hasFainted()) {
			int hp_before = pikachu.getHP();  
			bulbasaur.attack(pikachu);
			int hp_after = pikachu.getHP(); 
			damageTaken = hp_before - hp_after; 
			if( damageTaken < 0) {
				negativeDamage = true; 
			}
			if( damageTaken >= 100) {
				max_exceeded = true; 
			}
		}
		
		Assertions.assertEquals(false, negativeDamage);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			bulbasaur.attack(bulbasaur);;
		});
		Assertions.assertThrows(Exception.class, () -> {
			bulbasaur.attack(charmander);;
		});
		
	}
	
	public void testGetAttacks() {
		Assertions.assertThrows(NullPointerException.class, () -> {
			bulbasaur.getAttacks();;
		});
		String option1 = "Tail Whip"; // normal type attack
		String option2 = "Water Gun"; // water type attack
		String option3 = "Hydro Pump"; // water type attack
		ArrayList<String> squirtle_attacks = new ArrayList<>(Arrays.asList(option1, option2, option3));
		assertEquals(squirtle_attacks, squirtle.getAttacks());
	}
	
//	@Test
//	public void testTakeDamage() {
//		/*
//		 * Cannot take negative damage
//		 * 	and cannot damage itself
//		 */
//		Assertions.assertThrows(IllegalArgumentException.class, () -> {
//			bulbasaur.takeDamage(charmander, -50);;
//		});
//		Assertions.assertThrows(IllegalArgumentException.class, () -> {
//			bulbasaur.takeDamage(bulbasaur, 50);;
//		});
//		
//	}
	
	@Test
	public void testHasFainted() {
		Assertions.assertEquals(false, pikachu.hasFainted());
		Assertions.assertEquals(true, charmander.hasFainted());
	}
	
	@Test
	public void testGameOver() {
		Assertions.assertEquals(true, Pokemon.gameOver(pikachu, charmander));
		Assertions.assertEquals(false, Pokemon.gameOver(pikachu, bulbasaur));
	}
	
}
