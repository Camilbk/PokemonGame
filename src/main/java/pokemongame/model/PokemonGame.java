package pokemongame.model;

public class PokemonGame {
	
	private Pokemon pokemon1; 
	private Pokemon pokemon2; 
	
	private int turn; 
	
	private String lastAction; 
	private String name = "";
	
	public PokemonGame(Pokemon pokemon1, Pokemon pokemon2, int turn, String lastAction, String name) throws Exception {
		if (turn == 0 || turn == 1) {
			if( name != null && lastAction != null) {
				if( !name.contains(":") && !lastAction.contains(":")) {
					this.pokemon1 = pokemon1; 
					this.pokemon2 = pokemon2; 
					this.turn = turn; 
					this.lastAction = lastAction; 
					this.name = name;
				} else throw new Exception("name and last action cannot contain colon, and cannot be null");
			} else throw new NullPointerException();
		} else throw new IllegalArgumentException("Illegal next turn argument");
	}
	
	public PokemonGame(String[] gameinfo) throws IllegalArgumentException {
		if( gameinfo.length > 5) {
			int turn = Integer.valueOf(gameinfo[4]);
			if (turn == 0 || turn == 1) {
				this.turn = turn;
				this.pokemon1 = setCorrectSubclass(gameinfo[0]); 
				int hp1 = Integer.valueOf(gameinfo[1]);
				int hp2 = Integer.valueOf(gameinfo[3]);
				if( hp1 <= 100 && hp2 <= 100 ) {
					this.pokemon1.setHP( hp1);
					this.pokemon2 = setCorrectSubclass( gameinfo[2] );
					this.pokemon2.setHP( hp2 );
					this.lastAction = gameinfo[5];
					if( this.name == null) {
					this.name = ""; 
					}
				} else throw new IllegalArgumentException("Illegal HP"); 
			} else throw new IllegalArgumentException("gameinfo must have correct format");
		}
		else throw new IllegalArgumentException("gameinfo must have correct format");
	}
	
	// getters 
	

	private Pokemon setCorrectSubclass(String player) {
		if( player.equals("Pikachu"))  {
			return new Pikachu("Pikachu");
		}
		if( player.equals("Bulbasaur")) {
			return new Bulbasaur("Bulbasaur");
		}
		if( player.equals("Charmander")) {
			return new Charmander("Charmander");
		}
		if( player.equals("Squirtle")) {
			return new Squirtle("Squirtle");
		}
		else {
			return new Pokemon(player); 
		}
	}
	
	public Pokemon getPokemon1() {
		return this.pokemon1; 
	}
	
	public Pokemon getPokemon2() {
		return this.pokemon2;
	}
	
	public int getTurn() {
		// if 0 then pokemon1 if 1 then pokemon2
		return this.turn; 
	}
	
	public String getLastAction() {
		if( this.lastAction != null) {
			return this.lastAction; 
		} else throw new NullPointerException("This game does not yet have a name");
	}
	
	public String getName() throws NullPointerException{
		if( this.name != null) {
			return this.name; 
		} else throw new NullPointerException("This game does not yet have a name");
	}

	
	// setters
	
	public void setName(String name) throws IllegalArgumentException {
		if( name != null) {
			if( !name.contains(":")){
				this.name = name; 
			} else throw new IllegalArgumentException("name cannot contain colon : "); 
		} else throw new NullPointerException("cannot be null"); 
	}
	
	@Override
	public String toString() throws NullPointerException { 
		try {
			return this.pokemon1.getName() +":"+ this.pokemon1.getHP() + ":"+ this.pokemon2.getName() + ":"+ this.pokemon2.getHP() + ":" +this.turn + ":"+ this.lastAction;
	
		} catch ( NullPointerException e) {
			e.printStackTrace();
			return null; 
		}
	}	

}