package fp.eolo;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fp.utiles.Checkers;

public class Registros {
	private Set<Registro> registros;
	
	public Registros() {
		this.registros = new HashSet<>();
	}
	
	public Registros(Stream <Registro> streamRegistros) {
		this.registros = streamRegistros
				.collect(Collectors.toSet());
	}
	

	//METODOS
	
	public Integer getNumeroRegistros(){
		return registros.size();
		}
	
	public void añadirRegistro(Registro registro) {
		this.registros.add(registro);
	}
	
	public Set<Registro> calcularRegistroDeViento(Viento viento) {
		return registros.stream().filter(r->r.getViento().equals(viento)).collect(Collectors.toSet());
	}
	
	public Long calcularDiasDeVientoEnCiudadEnFechas(Viento viento, String ciudad, LocalDate fechaInicio, LocalDate fechaFinal) {
		Checkers.check("", fechaInicio.isBefore(fechaFinal));
		
		Predicate<Registro> pred = r->r.getCiudad().equals(ciudad) &&
				r.getFecha().isBefore(fechaFinal) && r.getFecha().isAfter(fechaInicio) &&
				r.getViento().equals(viento);
		
		return registros.stream()
				.filter(pred)
				.count();
	}
	
	public List<Registro> obtenerRegistrosMayorVelocidadMaxima(Integer limite) {
		return registros.stream().
				sorted(Comparator.comparing(Registro::getVelocidadMaxima).reversed()).
				limit(limite).
				collect(Collectors.toList());
	}
	
	public Double calcularPromedioVelocidadEnAño(Integer año) {
		return registros.stream().
				filter(r -> r.getAnyo().equals(año)).
				mapToDouble(Registro::getVelocidad).
				average().
				orElse(0.);
	}
	
	public Boolean existeVientoVelocidadMayor(Viento v, Double umbral) {
		return registros.stream()
				.filter(r->r.getViento().equals(v))
				.anyMatch(r->r.getVelocidadMaxima()>umbral);
	}
	
	public Boolean todosVientosCiudad(String ciudad, Set<Viento> vientos) {
		return registros.stream()
				.filter(r->r.getCiudad().equals(ciudad))
				.allMatch(r->vientos.contains(r.getViento()));
	}
	
	public Double velocidadMaximaVientoCiudad(String ciudad, Viento viento) {
		return registros.stream()
				.filter(r->r.getCiudad().equals(ciudad))
				.mapToDouble(r->r.getVelocidadMaxima()).max().getAsDouble();
	}
	
	public Set<LocalDate> fechasNVientosMayorVelocidadMediaCiudad(String ciudad, Viento viento, Integer n) {
		return registros.stream().filter(r->r.getViento().equals(viento) && r.getCiudad().equals(ciudad))
				.sorted(Comparator.comparing(Registro::getVelocidad).reversed())
				.limit(n)
				.map(Registro::getFecha)
				.collect(Collectors.toSet());
	}
	
	

	
	
	@Override
	public String toString() {
		return "Registros [registros=" + registros + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((registros == null) ? 0 : registros.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Registros))
			return false;
		Registros other = (Registros) obj;
		if (registros == null) {
			if (other.registros != null)
				return false;
		} else if (!registros.equals(other.registros))
			return false;
		return true;
	}	

}
