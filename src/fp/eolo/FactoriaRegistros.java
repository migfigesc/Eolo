package fp.eolo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fp.utiles.Checkers;

public class FactoriaRegistros {
	private static Registro parsearRegistro(String linea) {
		Checkers.checkNoNull(linea);
		String [] trozos= linea.split(",");
		Checkers.check("La línea debe tener 5 campos", trozos.length==5);
		LocalDate fecha = LocalDate.parse(trozos[0].trim());
		String ciudad = trozos[1].trim();
		Double direccion = Double.parseDouble(trozos[2].trim());
		Double velocidadMedia= Double.parseDouble(trozos[3].trim());
		Double velocidadMaxima= Double.parseDouble(trozos[4].trim());
		return new Registro(fecha,ciudad,direccion,velocidadMedia,velocidadMaxima);
	}
	
	private static List<Registro> leerRegistros(String fichero){
		List<Registro> l = null;
		Path p= Paths.get(fichero);
		try {
			l=Files.lines(p)
				.skip(1)
				.map(linea->parsearRegistro(linea))
				.collect(Collectors.toList());
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return l;

	}
	
	public static Registros leerRegistross (String fichero) {
		Registros res= null;
		try {
			Stream<Registro> sp = 
					Files.lines(Paths.get(fichero))
					.skip(1)
					.map(FactoriaRegistros::parsearRegistro);
			res= new Registros(sp);
		} catch (IOException e) {
			System.out.println("No se ha encontrado el fivhero"+ fichero);
			e.printStackTrace();
		} catch (Exception e) {
		System.out.println("Otra excepción"+ fichero);
		}
		return res;
	}

}
