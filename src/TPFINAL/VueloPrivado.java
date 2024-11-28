package TPFINAL;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class VueloPrivado extends Vuelo{
	private double costoPorJet;
	private int dniComprador;
	private int[] acompaniantes; //array de dni
	
	public VueloPrivado(String codigo, Aeropuerto origen, Aeropuerto destino,
			LocalDate fechaSalida, double costoPorJet, int cantTripulantes, int dniComprador, int[] acompaniantes) {
		
		super(codigo, origen, destino, fechaSalida, null, new HashMap<>(), new HashMap<>(), cantTripulantes, 1.3);
		this.costoPorJet = costoPorJet;
		this.dniComprador = dniComprador;
		this.acompaniantes = acompaniantes;
	}
	
	public double calcularPrecioVuelo() {
		int cantJets = calcularCantJets();

		double precioVuelo = (costoPorJet * cantJets)*this.impuesto;
		
        return precioVuelo;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.codigo)
	      .append(" - ")
	      .append(this.origen.toString())
	      .append(" - ")
	      .append(this.destino.toString())
	      .append(" - ")
	      .append(this.convertirFechaAString(this.fechaSalida))
	      .append(" - ")
	      .append("PRIVADO")
	      .append(" (")
	      .append(this.calcularCantJets())
	      .append(")");
		
		
		return sb.toString();
	}
	
	private int calcularCantJets() {
		return (int) Math.ceil((double) (acompaniantes.length+1)/15);
	}

	@Override
	protected double calcularPrecioPasaje(String seccion) {
		// TODO Auto-generated method stub
		return 0;
	}

}
