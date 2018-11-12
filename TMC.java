package tmc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TMC {

	public static void main(String[] args) {

		int m = 0;
		int autosPorEstacionar = 0;
	
		autosPorEstacionar = IngresoCantidadDeAutosPorEstacionar();
		m = RetornaParametroM();
		System.out.println("");
		System.out.println("");
		ProbilidadPorLaplace(m,autosPorEstacionar);
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("COMIENZO DEL INCISO D");
		IncisoD(m, autosPorEstacionar);

	}
	
	public static int GeneraPatenteAleatoria() {

		int patente = 0;

		patente = (int )Math.floor(Math.random()*1000);	
		return patente;
	}

	public static int  AsignaLugar(int patente) {

		int lugar;

		lugar = patente%20;
		return lugar;
	}

	public static int IngresoCantidadDeAutosPorEstacionar() {

		int valor = 0;
		int cantAutos = 0;
		boolean control = true;

		while (control) {
			try {
				BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
				System.out.println ("Ingrese la cantidad de autos a estacionar. Debe ser menor o igual  a 20");
				cantAutos = new Integer(entrada.readLine());
				if ((cantAutos > 0) && (cantAutos <=  20) ) {
					control = false;
					valor = cantAutos;
				}
			}
			catch (Exception exc ) { 
				System.out.println (exc);
				System.out.println ("NO HA INGRESADO UN VALOR VALIDO");
				control = true; 
			}
		}
		return valor;
	}

	public static int RetornaParametroM() {

		int m = 0;
		//int cantAutos = 0;
		boolean control = true;

		while (control) {
			try {
				BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
				System.out.println ("Ingrese la cantidad de veces que desea iterar. Debe ser mayor o igual a 10");
				m = new Integer(entrada.readLine());
				if (m >= 10) {
					control = false;
				}
			}
			catch (Exception exc ) { 
				System.out.println (exc);
				System.out.println ("NO HA INGRESADO UN VALOR VALIDO");
				control = true; 
			}	
		}
		return m;
	}

	public static int RetornaCantidadAutosEstacionados(int autosPorEstacionar) {

		int contadorDeAutosEstacionados = 0;
		int arregloAutos[] = new int [20];
		int patenteAleatoria = 0;
		int lugarAleatorio = 0;

		System.out.println("La cantidad de autos a ingresar es de "+autosPorEstacionar+ " vehiculos");
		System.out.println("");

		for(int i = 0; i < autosPorEstacionar; i++) {
			patenteAleatoria = GeneraPatenteAleatoria();
			System.out.println("Patente: " + patenteAleatoria);
			lugarAleatorio = AsignaLugar(patenteAleatoria);
			System.out.println("Lugar: " + lugarAleatorio); 
			if(arregloAutos[lugarAleatorio]  == 0) {
				arregloAutos[lugarAleatorio] = patenteAleatoria;
				contadorDeAutosEstacionados++;
			}
			else {
				System.out.println("");
				System.out.println("Se produjo un choque");
				i = autosPorEstacionar;
			}
			System.out.println("");
		}
		return contadorDeAutosEstacionados;
	}

	public static void  ProbilidadPorLaplace(int m, int autosPorEstacionar) {
		
		int autosEstacionados = 0;	
		int colisiones = 0;
		double probabilidad = 0;
		
		for (int i=1; i <= m; i++){
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println("Iteracion "+i);
			autosEstacionados = RetornaCantidadAutosEstacionados(autosPorEstacionar);
			if(autosPorEstacionar != autosEstacionados) {
				colisiones++;
				System.out.println("Se ingresaron "+autosEstacionados+" autos");
				System.out.println("");
				System.out.println("Colisiones Totales: "+colisiones);
				probabilidad =((double)colisiones*100)/((double)i);
				System.out.println("");
				System.out.println("La Probabilidad de Colision es "+String.format("%.3f", probabilidad)+ " %");
				System.out.println("----------------------------------------------------------------------------------------------------------------------");
				System.out.println("");
				System.out.println("");
			}else {		
				System.out.println("Se ingresaron -1 autos");
				System.out.println("");
				System.out.println("Colisiones Totales: "+colisiones);
				probabilidad =((double)colisiones*100)/((double)i);
				System.out.println("");
				System.out.println("La Probabilidad de Colision es "+String.format("%.3f", probabilidad)+ " %");
				System.out.println("----------------------------------------------------------------------------------------------------------------------");
				System.out.println("");
				System.out.println("");
			}
		}
	}

	public static void IncisoD(int m, int autosPorEstacionar) {

		int autosEstacionados = 0;	
		double epsilon = 0.01;
		double probabilidad = 0;
		double anterior = 0;
		double diferencia = 20;
		int i = 1;
		final int CONSTANTE = 10;
		int colisiones = 0;

		while((diferencia > epsilon) || (i <= CONSTANTE)) {
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			System.out.println("Iteracion "+i);
			autosEstacionados = RetornaCantidadAutosEstacionados(autosPorEstacionar);
			if(autosPorEstacionar != autosEstacionados) {
				colisiones++;
				System.out.println("Se ingresaron "+autosEstacionados+" autos hasta que se produjo un choque");
				System.out.println("");
				System.out.println("Colisiones Totales: "+colisiones);
				probabilidad =((double)colisiones*100)/((double)i);
				System.out.println("");
				System.out.println("La Probabilidad de Colision es "+String.format("%.4f", probabilidad)+ " %");
				System.out.println("");
				System.out.println("Valor Anterior de Probabilidad: "+String.format("%.3f", anterior)+ " %");
				diferencia = Math.abs((probabilidad - anterior));
				System.out.println("");
				System.out.println("Diferencia: "+diferencia+" % = "+ String.format("%.4f", (diferencia/100)));
				anterior = probabilidad;			
				i++;
				System.out.println("----------------------------------------------------------------------------------------------------------------------");
				System.out.println("");
				System.out.println("");
			}else {		
				System.out.println("Se ingresaron -1 autos");
				System.out.println("");
				System.out.println("Colisiones Totales: "+colisiones);
				probabilidad =((double)colisiones*100)/((double)i);
				System.out.println("");
				System.out.println("La Probabilidad de Colision es "+String.format("%.4f", probabilidad)+ " %");
				System.out.println("");
				System.out.println("Valor Anterior de Probabilidad: "+String.format("%.4f", anterior)+" %");
				diferencia = Math.abs((probabilidad - anterior));
				System.out.println("");
				System.out.println("Diferencia: "+diferencia+" % = "+ String.format("%.4f", (diferencia/100)));
				anterior = probabilidad;
				i++;
				System.out.println("----------------------------------------------------------------------------------------------------------------------");
				System.out.println("");
				System.out.println("");
			}
		}
	}
}
