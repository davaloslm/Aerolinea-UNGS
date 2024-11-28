package TPFINAL;

import java.time.LocalDate;
import java.util.HashMap;

public class VueloNacional extends VueloPublico {


	public VueloNacional(String codigo, Aeropuerto origen, Aeropuerto destino,
			LocalDate fechaSalida, int[] cantAsientos, double[] precios,
			int cantTripulantes, double costoRefrigerio) {

		super(codigo, origen, destino, fechaSalida, null, new HashMap<>(), new HashMap<>(), 
		      cantTripulantes, cantAsientos, 1.2, precios, 1, costoRefrigerio);
		
	}
	
	@Override
	public double calcularPrecioPasaje(String seccion) {
		double precioFinal = 0; //con impuesto
		
		if (seccion.equals("Turista")) {
			precioFinal = this.precios[0] + this.costoRefrigerio*this.cantRefrigerios + this.precios[0] + this.costoRefrigerio*this.cantRefrigerios*this.impuesto;
		}
		
		if (seccion.equals("Ejecutiva")) {
			precioFinal = this.precios[1] + this.costoRefrigerio*this.cantRefrigerios + this.precios[1] + this.costoRefrigerio*this.cantRefrigerios*this.impuesto;
		}

        return precioFinal;
	}
	

}
