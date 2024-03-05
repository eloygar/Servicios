package introduccion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Ejemplo2 {
	public static void main(String[] args) {
		URL url = null;
		try {
			url = new URL("http://www.elaltozano.es");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		BufferedReader in;
		try {
			InputStream inputstream = url.openStream();
			in = new BufferedReader(new InputStreamReader(inputstream));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				System.out.println(inputLine);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// Fin de main
}// Fin de Ejemplo2URL
