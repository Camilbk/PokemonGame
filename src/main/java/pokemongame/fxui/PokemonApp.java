package pokemongame.fxui;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import pokemongame.model.PokemonGame;



public class PokemonApp extends Application{
	
	private static Stage stage; 
	
	@Override
    public void start(Stage stage) throws Exception {
		this.stage = stage;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserInput.fxml"));
    	Parent parent = (Parent) fxmlLoader.load();
    	UserInputController controller =  (UserInputController) fxmlLoader.getController();
    	controller.setOpeningTune();
        stage.setScene(new Scene(parent, 375, 400));
        stage.show();
    }

    public static void main(String[] args) {
        launch(PokemonApp.class, args); 
    }
    
    /*
     *  Takes the chosen pokemons and chamges scene to second scene
     */
    public void changeSetGame(String pokemon1, String pokemon2) throws Exception {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Pokemon.fxml"));
    	Parent parent = (Parent) fxmlLoader.load();
    	List<String> userdata = new ArrayList<>(Arrays.asList(pokemon1, pokemon2)); 
    	PokemonController controller = (PokemonController) fxmlLoader.getController();
    	controller.recieveData(userdata);
    	stage.setScene(new Scene(parent, 600, 450));
    	stage.setTitle("Pokemon Battle Arena");
    	stage.show();
    	
    }
    
    /*
     * Changes scene from second scene to first scene with a clean game
     */
    public void changeNewGame() throws Exception {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserInput.fxml"));
    	Parent parent = (Parent) fxmlLoader.load();
    	UserInputController controller =  (UserInputController) fxmlLoader.getController();
    	controller.setOpeningTune();
        stage.setScene(new Scene(parent, 375, 400));
        stage.setTitle("Pokemon Battle Arena");
        stage.show();
    }
    
    /*
     *  changes scene to a loaded game from first scene
     */
    public void loadSetGame(PokemonGame pokemongame) throws Exception {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Pokemon.fxml"));
    	Parent parent = (Parent) fxmlLoader.load();
    	PokemonController controller = (PokemonController) fxmlLoader.getController();
    	controller.loadGame(pokemongame);
    	stage.setScene(new Scene(parent, 600, 450));
    	stage.setTitle("Pokemon Battle Arena");
    	stage.show();
    }
	


}
