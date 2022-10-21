package pokemongame.fxui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import pokemongame.model.Pokemon;
import pokemongame.model.PokemonGame;

public class PokemonFileSupport implements IPokemonFileReading {

	@Override
	public PokemonGame readPokemonGame(String name, File gamefile) throws IOException {
		String data = ""; 
		try {
			Scanner scanner = new Scanner(gamefile);
			while(scanner.hasNext()) {
				data += scanner.nextLine() + "\n";
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] gameinfo = data.split(":");
		for( String info : gameinfo) {
			System.out.println(info);
		}
		return new PokemonGame(gameinfo); 
	}



	@Override
	public void writePokemonGame(PokemonGame pokemongame, File gamefile) throws IOException {
		try {
			FileWriter writer = new FileWriter(gamefile);
			writer.write(pokemongame.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public File getFile(String name) {
		String path = name + ".txt";
		try {
		      File gamefile = new File(path);
		      if (gamefile.createNewFile()) {
		        System.out.println("File created: " + gamefile.getName());
		        return gamefile; 
		      } else {
		        System.out.println("File already exists.");
		        return gamefile; 
		      }
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 	}
		return null; 
	}

	 
	 
}
