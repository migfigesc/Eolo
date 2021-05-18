package fp.eolo;

import java.time.LocalDate;

public class Registro {
	private LocalDate fecha;
	private String ciudad;
	private Double direccion;
	private Double velocidad;
	private Double velocidadMaxima;
	
	public Registro(LocalDate fecha, String ciudad, Double direccion, Double velocidad, Double velocidadMaxima) {
		super();
		this.fecha = fecha;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.velocidad = velocidad;
		checkVelocidad(velocidad);
		this.velocidadMaxima = velocidadMaxima;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public String getCiudad() {
		return ciudad;
	}

	public Double getDireccion() {
		return direccion;
	}

	public Double getVelocidad() {
		return velocidad;
	}

	public Double getVelocidadMaxima() {
		return velocidadMaxima;
	}
	
	public Integer getDia() {
		return fecha.getDayOfMonth();
	}
	
	public Integer getMes() {
		return fecha.getMonthValue();
	}
	
	public Integer getAnyo() {
		return fecha.getYear();
	}
	
	public Viento getViento () {
		Viento resultado;
		if(direccion > 135 && direccion <= 225) {
			resultado = Viento.SUR;
		}
		else if(direccion > 225 && direccion <= 315) {
			resultado = Viento.PONIENTE;
		}
		else if(direccion > 315 || direccion <= 45) {
			resultado = Viento.NORTE;
		}
		else if(direccion > 45 && direccion <= 135) {
			resultado = Viento.LEVANTE;
		}
		else resultado=Viento.LEVANTICHON;
		return resultado;
	}
	
	private void checkVelocidad(Double velocidad) {
		if(velocidad > velocidadMaxima) {
			throw new IllegalArgumentException("La velocidad media no puede superar a la máxima");
		}
		
	}

	@Override
	public String toString() {
		return "Registro [fecha=" + fecha + ", ciudad=" + ciudad + ", direccion=" + direccion + ", velocidad="
				+ velocidad + ", velocidadMaxima=" + velocidadMaxima + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Registro other = (Registro) obj;
		if (ciudad == null) {
			if (other.ciudad != null)
				return false;
		} else if (!ciudad.equals(other.ciudad))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		return true;
	}
	
	public int compareTo(Registro o) {
		int res = this.getCiudad().compareTo(o.getCiudad());
		if (res == 0) {
			res = this.getFecha().compareTo(o.getFecha());
		}
		return res;
	}
	

}
