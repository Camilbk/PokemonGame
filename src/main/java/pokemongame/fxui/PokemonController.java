package pokemongame.fxui;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JFileChooser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

// import model 
import pokemongame.model.Pokemon;
import pokemongame.model.PokemonGame;
  // subclasses of pokemon
import pokemongame.model.Pikachu;
import pokemongame.model.Bulbasaur;
import pokemongame.model.Squirtle;
import pokemongame.model.Charmander;

/*
 *   Controlleren håndterer det som skjer i appen, 
 *   og tar feks. knappetrykk som input og peker på sørger for at rikitg opførsel skal skje 
 * 
 */

public class PokemonController {
	
	private PokemonGame pokemongame; 
	
	private Pokemon pokemon1; 
	private Pokemon pokemon2; 
	
	private int turn; // keeps track of which pokemon is next 
	
	@FXML
	private Label statusOut; 
	
	@FXML
	private Label HP_1; 
	
	@FXML 
	private Label HP_2; 
	
	@FXML
	private Label displayTurn; 
	
	@FXML
	private Label pokemon1Type; 
	
	@FXML 
	private Label pokemon2Type;  
	
	@FXML
	private Text player2_pokemon; 
	
	@FXML
	private Text player1_pokemon; 
	
	@FXML
	private ImageView pokemonFigure1; 
	
	@FXML
	private ImageView pokemonFigure2; 
	
	@FXML
	private Label pokemonName1; 
	
	@FXML
	private Label pokemonName2; 
	
	@FXML
	private MenuBar Menubar; 
	
	@FXML
	private Menu Edit; 
	
	@FXML
	private MenuItem NewGame; 
	
	@FXML 
	private MenuItem SaveGame; 
	
	@FXML
	private MenuItem LoadGame;
	
	@FXML
	private MediaPlayer mediaPlayer; 
	
	//ObservableList<String> attackList = FXCollections.<String>observableArrayList("Attack 1", "Attack 2", "Attack 2");
	@FXML
	private ListView<String> attackDisplay; // = new ListView<>(attackList); 
	
	
	
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
		else {
			return new Squirtle("Squirtle");
		}
	}
	
	
	@FXML
	void recieveData(List<String> userdata) throws Exception{
		setBattleTune();
		String player1 = userdata.get(0);
		String player2 = userdata.get(1);
		final var pokemon1 = setCorrectSubclass(player1);  // creating pokemon
		final var pokemon2 = setCorrectSubclass(player2); // creating pokemon
		
		
		pokemonName1.setText(player1);   // setting correct label for name
		pokemonName2.setText(player2);   // setting correct label for name
		pokemon1Type.setText(pokemon1.getType());
		pokemon2Type.setText(pokemon2.getType());
		
		this.pokemonFigure1.setImage( setCorrectFigure(player1));
		this.pokemonFigure2.setImage( setCorrectFigure(player2));
		
		this.turn = 0; 
		
		setPokemonGame( pokemon1, pokemon2); 
	}
	
	private Image setCorrectFigure(String name) {
		if( name.equals("Pikachu"))  {
			return new Image("pokemongame/fxui/Pikachu.png", true);
		}
		if( name.equals("Bulbasaur")) {
			return new Image("pokemongame/fxui/Bulbasaur.png", true);
		}
		if( name.equals("Charmander")) {
			return  new Image("pokemongame/fxui/Charmander.png", true);
		}
		else {
			return  new Image("pokemongame/fxui/squirtle2.png", true);
		}
	}
	
	private void showAttacks() {
		ArrayList<String> attacks; 
		attacks = turn == 0 ? pokemon1.getAttacks() : pokemon2.getAttacks(); 
		for(int i = 0; i < attacks.size(); i++) {
			attackDisplay.getItems().add(i, attacks.get(i));
		}
	}
	
	private void showTurn() {
	String name = this.turn == 0 ? this.pokemon1.getName(): this.pokemon2.getName();
	String text = name + "s turn: ";  
	displayTurn.setText(text);
	}	

	private void setPokemonGame( final Pokemon pokemon1, final Pokemon pokemon2) {
		this.pokemon1 = pokemon1; 
		this.pokemon2 = pokemon2; 
		updatePokemonGameView(); 
	}
	
	void loadGame(PokemonGame pokemongame) {
		if( mediaPlayer == null) {
			setBattleTune();
		}
		this.pokemongame = pokemongame; 
		this.pokemon1 = this.pokemongame.getPokemon1();
		this.pokemon2 = this.pokemongame.getPokemon2();
		this.turn = this.pokemongame.getTurn();
		
		statusOut.setText(this.pokemongame.getLastAction());
		pokemonName1.setText(pokemon1.getName());
		pokemonName2.setText(pokemon2.getName());
		pokemon1Type.setText(pokemon1.getType());
		pokemon2Type.setText(pokemon2.getType());
		
		pokemonFigure1.setImage( setCorrectFigure(pokemon1.getName()));
		pokemonFigure2.setImage( setCorrectFigure(pokemon2.getName()));
		
		attackDisplay.getItems().clear();
		updatePokemonGameView();
	}
	

	@FXML
	void handleAttackButton() throws Exception {
		if( ! Pokemon.gameOver(pokemon2, pokemon1)){
			attackDisplay.getItems().clear();
			//showAttacks();
			if( turn == 0) {
				statusOut.setText( pokemon1.attack(pokemon2));
				if(pokemon2.hasFainted()) {
					statusOut.setText(pokemon2.toString());
				}
				this.turn = 1;
			}
			else {
				statusOut.setText( pokemon2.attack(pokemon1)); 
				if(pokemon1.hasFainted()) {
					statusOut.setText(pokemon1.toString());
				}
				this.turn = 0; 
			}
			updatePokemonGameView();
		}
	}
	
	@FXML
	void updatePokemonGameView() {
		HP_1.setText( String.valueOf(pokemon1.getHP()));
		HP_2.setText( String.valueOf(pokemon2.getHP()));
		showTurn();
		showAttacks();
	}
	
	@FXML
	void handleNewGame() throws Exception{
		System.out.println("oo nytt spill??");
		PokemonApp game = new PokemonApp(); 
		mediaPlayer.pause();
		game.changeNewGame();
	}
	
	
    public void setBattleTune() {
    	String path = "battle.mp3";
    	try {
	    	Media media = new Media(new File(path).toURI().toString());
	    	mediaPlayer = new MediaPlayer(media);
	    	mediaPlayer.play();
    	} catch ( MediaException e) {
    		e.printStackTrace();
    	}
    } 
  

	// ---------------- filesupport ------------
	
	private JFileChooser fileChooser;
	//private final IPokemonFileReading fileSupport = new PokemonFileSupport();
	private final PokemonFileSupport fileSupport = new PokemonFileSupport();
	
	
	@FXML
	void handleLoadGame() {
		String name = "pokemongame";
		try {
			File gamefile = fileSupport.getFile(name);
			PokemonGame game = fileSupport.readPokemonGame(name, gamefile);
			loadGame(game);
			
		} catch (final IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@FXML
	void handleSaveGame() throws Exception {
		System.out.println("whoopie");
		String name = "pokemongame"; // TODO 
		if( this.pokemongame == null) {
			this.pokemongame = new PokemonGame(this.pokemon1, this.pokemon2, this.turn, this.statusOut.getText(), "pokemongame");
		}
		try {
			File gamefile = fileSupport.getFile(name);
			fileSupport.writePokemonGame(pokemongame, gamefile);
			
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
