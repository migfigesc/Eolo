package fp.eolo;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Registros {
	private Set<Registro> registros;
	
	public Registros() {
		this.registros = new HashSet<>();
	}
	
	public Registros(Stream <Registro> streamRegistros) {
		this.registros = streamRegistros
				.collect(Collectors.toSet());
	}
	
	public void addRegistros(Registro r) {
		this.registros.add(r);
	}
	
	public Integer getNumeroRegistros(){
		return registros.size();
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
