package pokemongame.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Pokemon {
	
	private int HP; 
	private String pokemon; 
	private String type; 
	protected ArrayList<String> specialAttacks; 
	
	
	public Pokemon( String pokemon) {
		this.HP = 100; 
		this.pokemon = pokemon;
		setPokemonType();
		
	}

	public int getHP() {
		return this.HP; 
	}

	
	public String getName() {
		return this.pokemon;
	}
	
	public String getType() {
		return this.type;
	}
	
	public ArrayList<String> getAttacks(){
		if( this.specialAttacks != null) {
			return (ArrayList<String>) this.specialAttacks;
		} else throw new NullPointerException(); 
	}
	
	public String attack(Pokemon pokemon) throws Exception {
		if(pokemon != this) {
			if( !gameOver(this, pokemon)) {
				int damage = calculateAttackStrength(pokemon);
				String out = this.getName() + " attacks " + pokemon.getName() + "\n";
				System.out.println(this.getName() + " attacks " + pokemon.getName() + "\n");
				pokemon.takeDamage(this, damage);
				System.out.println(this.effectiveness(damage));
				return out + this.effectiveness(damage);
				
			} else throw new Exception("Cannot attack a fainted pokemon"); 
		}
		else throw new IllegalArgumentException("Cannot attack yourself");
	}
	
	private void takeDamage(Pokemon pokemon, int damage) {
		if( pokemon != this) {
			if( !this.hasFainted() ){ 
				if ( damage > 0) {
					this.HP -= damage; 
				}
			}
		}
	}
	
	private String effectiveness(int damage) {
		if( damage < 10) {
			return "It's not very effective... \n"; 
		}
		if(damage > 30) {
			return "It's super effective! \n";
		}
		if( damage == 0) {
			return "But, it failed! \n"; 
		}
		return "It deals " + damage + " damage. \n"; 
	}
	
	public boolean hasFainted() {
		if( this.HP <= 0) {
			return true; 
		}
		else return false; 
	}
	
	public static boolean gameOver(Pokemon o1, Pokemon o2) {
		if( o1.hasFainted() || o2.hasFainted()) {
			return true;
		}
		else return false; 
	}
	
	private void setPokemonType() {
		if( this.pokemon.equals("Pikachu")) {
			this.type = "Electric";
		}
		else if( this.pokemon.equals("Charmander")) {
			this.type = "Fire";
		}
		else if( this.pokemon.equals("Squirtle")) {
			this.type = "Water";
		}
		else if( this.pokemon.equals("Bulbasaur")) {
			this.type = "Grass";
		} 
		else {
			this.type = "Unknown";
		}
	}
	
	@Override
	public String toString() {
		if( !this.hasFainted()) {
			return this.pokemon + " has " + this.getHP() + " HP. \n";
		}
		else {return this.pokemon + " has fainted. \n GAME OVER \n";}
	}
	
	// should only be used when called from PokemonGame, to recreate a game
	// so you can save a game with -14 hp f.ex.. 
	protected void setHP(int hp) {
		this.HP = hp;
	}
	
	private int calculateAttackStrength(Pokemon pokemon) {
		Random rand = new Random();
		int damage = 0;
		
		if( this.type == "Grass") {
			// weakness against
			if( pokemon.getType().equals("Fire") || pokemon.getType().equals("Grass") ) {
				damage = rand.nextInt(30);
			}
			// strong against
			else if( pokemon.getType().equals("Water") ){
				damage = rand.nextInt(40) + 10;
			}
			else {
				damage = rand.nextInt(40);
			}
		}
		
		else if( this.type == "Electric") {
			// weakness against
			if( pokemon.getType().equals("Electric") || pokemon.getType().equals("Grass")) {
				damage = rand.nextInt(30);
			}
			// strong against
			else if( pokemon.getType().equals("Water") ){
				damage = rand.nextInt(40) + 10;
			}
			else {
				damage = rand.nextInt(40);
			}
		}
		
		else if( this.type == "Fire") {
			// weakness against
			if( pokemon.getType().equals("Water") || pokemon.getType().equals("Fire")) {
				damage = rand.nextInt(30);
			}
			// strong against
			else if( pokemon.getType().equals("Grass") ){
				damage = rand.nextInt(40) + 10;
			}
			else {
				damage = rand.nextInt(40);
			}
		}
		
		else if( this.type == "Water") {
			// weakness against
			if( pokemon.getType().equals("Water") || pokemon.getType().equals("Grass")) {
				damage = rand.nextInt(30);
			}
			// strong against
			else if( pokemon.getType().equals("Fire") ){
				damage = rand.nextInt(40) + 10;
			}
			else {
				damage = rand.nextInt(40);
			}
		}
		else {
			damage = rand.nextInt(40);
		}
		return damage; 
	}
		
}
