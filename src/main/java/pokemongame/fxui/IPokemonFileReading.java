package pokemongame.fxui;

import java.io.IOException;
import pokemongame.model.*;
import java.io.File;

public interface IPokemonFileReading {
	/**
	 * Read a PokemonGame with a given name, from a d location.
	 * @param name The name of the pokemongame
	 * @return The PokemonGame with the given name from the location
	 * @throws IOException if the game can't be found.
	 */
	PokemonGame readPokemonGame(String name,  File gamefile) throws IOException;
	
	/**
	 * Write a PokemonGame to a file in a location.
	 * @param pokemonGame to write
	 * @throws IOException If a file  can't be written to
	 */
	void writePokemonGame(PokemonGame pokemongame, File gamefile) throws IOException;
}
