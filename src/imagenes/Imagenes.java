package imagenes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Imagenes {

	private static final int REPETICIONES = 5;
	
	private String imagenAComprimir;
	private String imagenADescomprimir;
	
	private String imagenComprimida = "";
	private String imagenDescomprimida = "";
	
	public Imagenes(String path) throws FileNotFoundException {
		File file = new File(path);
		Scanner scan = new Scanner(file);
		
		this.imagenAComprimir = scan.next();
		this.imagenADescomprimir = scan.next();
		
		scan.close();
	}
	
	public void resolver() throws IOException {
		comprimir();
		descomprimir();
		escribirSolucion();
	}
	
	private void comprimir() {
		String auxiliar = "" + this.imagenAComprimir.charAt(0);
		char charAnterior = this.imagenAComprimir.charAt(0);
		int cantRepeticiones = 1;
		
		for (int i = 1 ; i < this.imagenAComprimir.length() ; i++) {
			if (this.imagenAComprimir.charAt(i) == charAnterior) {
				cantRepeticiones++;
				auxiliar += this.imagenAComprimir.charAt(i);
			} else {
				if (cantRepeticiones >= REPETICIONES ) {
					this.imagenComprimida += "(" + charAnterior + cantRepeticiones + ")";
				} else {
					this.imagenComprimida += auxiliar;
				}
				auxiliar = "" + this.imagenAComprimir.charAt(i);
				cantRepeticiones = 1;
			}
			charAnterior = this.imagenAComprimir.charAt(i);
		}
		
		if (cantRepeticiones >= REPETICIONES ) {
			this.imagenComprimida += "(" + charAnterior + cantRepeticiones + ")";
		} else {
			this.imagenComprimida += auxiliar;
		}
	}
	
	private void descomprimir() {
		char elCaracter = ' ';
		String repeticionesString = "";
		int repeticiones = 0;
		
		for (int i = 0 ; i < this.imagenADescomprimir.length() ; i++) {
			if (this.imagenADescomprimir.charAt(i) == '(') {
				elCaracter = this.imagenADescomprimir.charAt(i+1);
				i += 2;
				while (this.imagenADescomprimir.charAt(i) != ')') {
					repeticionesString += this.imagenADescomprimir.charAt(i);
					i++;
				}
				repeticiones = Integer.parseInt(repeticionesString);
				for (int j = 0 ; j < repeticiones ; j++) {
					this.imagenDescomprimida += elCaracter;
				}
				repeticionesString = "";
			} else {
				this.imagenDescomprimida += this.imagenADescomprimir.charAt(i);
			}
		}
	}
	
	private void escribirSolucion() throws IOException {
		FileWriter file = new FileWriter("imagenes.out");
		BufferedWriter buffer = new BufferedWriter(file);
		
		buffer.write(imagenComprimida);
		buffer.newLine();
		buffer.write(imagenDescomprimida);
		
		buffer.close();
	}
	
}
