package ar.com.uade.grupo8;

import java.util.List;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Session;
import org.neo4j.driver.Statement;
import org.neo4j.driver.StatementResult;
public class Conector {

	
	private Driver driver;
	
	public Conector() {
		driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic( "neo4j", "1234" ));
	}
	
	public Session openSesion() {
		return driver.session();
	}
	
	public void closeSesion(Session sesion) {
		sesion.close();
	}
	public void ejecutarConsulta(String consulta) {
		
		Session sesion = this.openSesion();
		Statement query = new Statement(consulta);
		sesion.run(query);
		this.closeSesion(sesion);
	}

	public void ejecutarConsultaConRespuesta(String tipo, String consulta) {
		
		Session sesion = this.openSesion();
		Statement query = new Statement(consulta);
		StatementResult record = sesion.run(query);
		List<Record> registros = record.list();
		boolean entra = false;
		for(Record registro : registros) {
			entra = true;
			
			if(tipo == "Duenio" || tipo == "Inquilino") 
			{
				System.out.println(registro.get(0).get("nombre") + " " + registro.get(0).get("apellido") + " - DNI: " + registro.get(0).get("dni"));	
			}
			else if(tipo == "Edificio") 
			{
				System.out.println(registro.get(0).get("nombre") + " -- " + registro.get(0).get("direccion"));			
			}
			else if(tipo == "Unidad") 
			{
				String habitado = "No";
				if(registro.get(0).get("habitado").toString() == "S") 
				{
					habitado = "Si";
				}
				
				System.out.println(registro.get(0).get("nombre") + " - Estado: " + registro.get(0).get("estado") + " - Expensas: $" + registro.get(0).get("expensas") + " - Habitado: " + habitado);			
				
			}
		}
		if(entra == false) 
		{
			System.out.println("Sin resultados.");
		}
		this.closeSesion(sesion);
	}

	public void ejecutarConsultaConRespuestaTexto(String consulta) {
		
		Session sesion = this.openSesion();
		Statement query = new Statement(consulta);
		StatementResult record = sesion.run(query);
		List<Record> registros = record.list();
		boolean entra = false;
		for(Record registro : registros) {
			entra = true;
			System.out.println(registro.get(0).toString());							
		}
		if(entra == false) 
		{
			System.out.println("Sin resultados.");
		}
		this.closeSesion(sesion);
	}

	
	public void getListado(String tipo, String parametros) {
		
		Session sesion = this.openSesion();
		Statement query = new Statement("match(n:"+ tipo +" "+ parametros +") return n");
		StatementResult record = sesion.run(query);
		List<Record> registros = record.list();
		boolean entra = false;
		for(Record registro : registros) {
			entra = true;
			
			if(tipo == "Duenio" || tipo == "Inquilino") 
			{
				System.out.println(registro.get(0).get("nombre") + " " + registro.get(0).get("apellido") + " -- " + registro.get(0).get("dni"));	
			}
			else if(tipo == "Edificio") 
			{
				System.out.println(registro.get(0).get("nombre") + " -- " + registro.get(0).get("direccion"));			
			}
			else if(tipo == "Unidad") 
			{
				String habitado = "No";
				if(registro.get(0).get("habitado").toString() == "S") 
				{
					habitado = "Si";
				}
				
				System.out.println(registro.get(0).get("nombre") + " - Estado: " + registro.get(0).get("estado") + " - Expensas: $" + registro.get(0).get("expensas") + " - Habitado: " + habitado);			
				
			}
		}
		if(entra == false) 
		{
			System.out.println("Sin resultados.");
		}
		
		this.closeSesion(sesion);
	}
	
	
}
