package TPFINAL;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class VueloInternacional extends VueloPublico {

	protected String[] escalas; // ARRAY String con nombres de los aeropuertos
	
	public VueloInternacional(String codigo, Aeropuerto origen, Aeropuerto destino,
			LocalDate fechaSalida, int[] cantAsientos, double[] precios,
			int cantTripulantes, double costoRefrigerio, int cantRefrigerios, String[] escalas) {

		super(codigo, origen, destino, fechaSalida, null, new HashMap<>(), new HashMap<>(), 
			      cantTripulantes, cantAsientos, 1.2, precios, cantRefrigerios, costoRefrigerio);
		this.escalas = escalas;
	}
	
	@Override
	public double calcularPrecioPasaje(String seccion) {

		    double precioBase = 0;

		    if (seccion.equals("Turista")) {
		        precioBase = this.precios[0];
		    } else if (seccion.equals("Ejecutiva")) {
		        precioBase = this.precios[1];
		    } else {
		        precioBase = this.precios[2];
		    }

		    double precioConRefrigerios = precioBase + (this.costoRefrigerio * this.cantRefrigerios);
		    double precioFinal = precioConRefrigerios * this.impuesto;

		    return precioFinal;
		}
	}
	
	
	

