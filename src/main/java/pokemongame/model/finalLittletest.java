package pokemongame.model;

import java.util.ArrayList;

public class finalLittletest {
	
	public static void main(String[] args) {
		Pokemon bulbasaur = new Bulbasaur("Bulbasaur");
		Pokemon pikachu = new Pikachu("Pikachu");
		Pokemon squirtle = new Squirtle("Squirtle");
		Pokemon charmander = new Charmander("Charmander");
		
		for( String attack : bulbasaur.getAttacks()) {
			System.out.println(attack);
		}
		
		for( String attack : squirtle.getAttacks()) {
			System.out.println(attack);
		}
		
		for( String attack : charmander.getAttacks()) {
			System.out.println(attack);
		}
		
		for( String attack : pikachu.getAttacks()) {
			System.out.println(attack);
		}
		
		
		
		

	}
	


}



