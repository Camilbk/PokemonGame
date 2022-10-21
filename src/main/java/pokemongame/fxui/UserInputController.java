package pokemongame.fxui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import pokemongame.model.PokemonGame;

public class UserInputController {

	
	@FXML
	private MenuButton player2_pokemon; 
	
	
	private MediaPlayer mediaPlayer; 
	
	@FXML
	private MenuButton player1_pokemon; 
	
	@FXML
	private Button LoadGame; 

	private boolean chosen2 = false;  
	private boolean chosen1 = false; 
	
    public void setOpeningTune() {
    	String path = "opening.mp3";
    	try {
	    	Media media = new Media(new File(path).toURI().toString());
	    	mediaPlayer = new MediaPlayer(media);
	    	mediaPlayer.play();
    	} catch (MediaException e) {
    		e.printStackTrace();
    	}
    }
	
	@FXML
	void handleChosenPokemon2(ActionEvent e) throws Exception {
		System.out.println( ((MenuItem)e.getSource()).getText() );
        player2_pokemon.setText(((MenuItem)e.getSource()).getText());
        chosen2 = true;
		letsPlay(); 
		
	}
	
	@FXML
	void handleChosenPokemon1(ActionEvent e) throws Exception {
		System.out.println( ((MenuItem)e.getSource()).getText() );
		player1_pokemon.setText(((MenuItem)e.getSource()).getText());
		chosen1 = true; 
		letsPlay(); 
	
	}
	
	@FXML 
	void letsPlay() throws Exception {
		if(chosen1 == true && chosen2 == true) {
			TimeUnit.SECONDS.sleep(1);
			PokemonApp game = new PokemonApp(); 
			// sending two Strings for user Data
			mediaPlayer.pause();
			game.changeSetGame(player1_pokemon.getText(), player2_pokemon.getText());
		}
		else {
			System.out.println("Waiting for the other player...");
		}
	}

	
	
	
	
	// ------------------- filesupport ----------------
	
	
	private PokemonFileSupport fileSupport = new PokemonFileSupport();
	
	@FXML
	void handleLoadGame() throws Exception {
		String name = "pokemongame";
		try {
			File gamefile = fileSupport.getFile(name);
			PokemonGame game = fileSupport.readPokemonGame(name, gamefile);
			PokemonApp app = new PokemonApp();
			mediaPlayer.pause();
			app.loadSetGame(game);
			
		} catch (final IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
