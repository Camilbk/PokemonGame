package pokemongame.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PokemonGameTest {

	private Pokemon pikachu, bulbasaur,charmander;
	private PokemonGame game, game1, game2,  newgame;
	private String status, name; 
	
	@BeforeEach
	public void setup() throws Exception {
		bulbasaur = new Pokemon("Bulbasaur");
		pikachu = new Pokemon("Pikachu");
		charmander = new Pokemon("Charmander");
		
		status = bulbasaur.attack(pikachu);
		name = "pokemongame";
		
		//game = new PokemonGame(charmander, bulbasaur, 0, status, null);
		//game1 = new PokemonGame(charmander, bulbasaur, 0, null, null );
		game2 = new PokemonGame(charmander, bulbasaur, 0, status, name);
	}
	
	@Test
	public void testConstructors() {
		// testing first constructor
		assertEquals(charmander, game2.getPokemon1());
		assertEquals(bulbasaur, game2.getPokemon2());
		assertEquals(0, game2.getTurn());
		
		Assertions.assertThrows(Exception.class, () -> {
			new PokemonGame(charmander, bulbasaur, 6, null, null) ;
		});
		Assertions.assertThrows(Exception.class, () -> {
			new PokemonGame(charmander, bulbasaur, 0, null, "hei:hei") ;
		});
		Assertions.assertThrows(Exception.class, () -> {
			new PokemonGame(charmander, bulbasaur, 0,  "bulbasaur:beats:pika", null) ;
		});
		
		// testing second constructor, generating a PokemonGame from a toString
		String gameinfo2 = game2.toString();
		String[] strings = gameinfo2.split("a");
		String fakeToString = bulbasaur.getName() +":"+ bulbasaur.getHP() + ":"+ charmander.getName() + ":"+ charmander.getHP() + ":" +56 + ":"+ "bulbasaur attacked charmander";
		String[] fakeString = fakeToString.split(":"); 
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new PokemonGame(fakeString) ;
		});

		
	}
	
	@Test
	public void testSetName() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			game2.setName("some:thing"); ;;
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			game2.setName("so:m:e:thin:g"); ;;
		});
		
		Assertions.assertThrows(NullPointerException.class, () -> {
			game2.setName(null);
		});
	}
	
	
	@Test
	public void testGetLastAction() {
		Assertions.assertThrows(NullPointerException.class, () -> {
			game1.getLastAction() ;;
		});
	}
	
	@Test
	public void testToString() {
		// some testing is done in the constructor test
		String gameToSring = game2.toString();
		String[] splitted = gameToSring.split(":"); 
		assertEquals("Charmander", splitted[0] ); 
		assertEquals(100, Integer.valueOf(splitted[1]) ); 
		assertEquals("Bulbasaur", splitted[2] ); 
		assertEquals(100, Integer.valueOf(splitted[3]) );
		assertEquals(0, Integer.valueOf(splitted[4]) );
		assertEquals(status.toString(), splitted[5] ); 
		
	}
}
