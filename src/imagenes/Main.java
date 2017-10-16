package imagenes;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			Imagenes img = new Imagenes("imagenes.in");
			img.resolver();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
