package ar.com.uade.grupo8;

public class Test {

	public static void main(String[] args) {
		System.out.println("Importante: En caso de tener errores, verificar que los datos de conexión sean correctos.");		
		System.out.println("Busco al edificio AlvearTower:");
		new Conector().getListado("Edificio","{nombre:'AlvearTower'}");
		System.out.println("Inserto al edificio AlvearTower.");
		new Conector().ejecutarConsulta("CREATE (ed11:Edificio {nombre: 'AlvearTower' , direccion: 'Azucena Villaflor 559'} )");		
		System.out.println("Busco al edificio AlvearTower:");
		new Conector().getListado("Edificio","{nombre:'AlvearTower'}");
		System.out.println("Elimino al edificio AlvearTower.");
		new Conector().ejecutarConsulta("MATCH (n:Edificio {nombre:'AlvearTower'}) detach delete n");
		System.out.println("Busco al edificio AlvearTower:");
		new Conector().getListado("Edificio","{nombre:'AlvearTower'}");
		System.out.println("Busco a todos los dueños:");				
		new Conector().getListado("Duenio","");
		System.out.println("Inserción de un nuevo dueño.");
		new Conector().ejecutarConsulta("CREATE ( d26: Duenio {nombre: 'Octavio' , apellido: 'Rodriguez', dni: '98754321' } )");
		System.out.println("Busco a todos los dueños:");						
		new Conector().getListado("Duenio","");
		System.out.println("Busco a todos los dueños de apellido Exposito:");
		new Conector().getListado("Duenio","{apellido:'Exposito'}");		
		System.out.println("Busco a todos los dueños de la Unidad 23:");
		new Conector().ejecutarConsultaConRespuesta("Duenio","MATCH(x1:Unidad{nombre:'Unidad 23'})-[:PerteneceA{}]->(x3:Duenio{}) return x3");		
		System.out.println("Agrego a Octavio como dueño de la unidad 23.");
		new Conector().ejecutarConsulta("MATCH (u23:Unidad {nombre:'Unidad 23'}), (d26:Duenio {nombre:'Octavio'}) CREATE (u23)-[:PerteneceA{}]->(d26)");
		System.out.println("Busco a todos los dueños de la Unidad 23:");
		new Conector().ejecutarConsultaConRespuesta("Duenio","MATCH(x1:Unidad{nombre:'Unidad 23'})-[:PerteneceA{}]->(x3:Duenio{}) return x3");		
		System.out.println("Elimino a Octavio como dueño de la unidad 23.");
		new Conector().ejecutarConsulta("MATCH(x1:Unidad{nombre:'Unidad 23'})-[n:PerteneceA{}]->(x2:Duenio{nombre:'Octavio'}) detach delete n");
		System.out.println("Busco a todos los dueños de la Unidad 23:");
		new Conector().ejecutarConsultaConRespuesta("Duenio","MATCH(x1:Unidad{nombre:'Unidad 23'})-[:PerteneceA{}]->(x3:Duenio{}) return x3");		
		System.out.println("Busco a Octavio:");
		new Conector().getListado("Duenio","{nombre:'Octavio'}");				
		System.out.println("Elimino al dueño Octavio.");
		new Conector().ejecutarConsulta("MATCH (n:Duenio {nombre:'Octavio'}) detach delete n");
		System.out.println("Busco a Octavio:");
		new Conector().getListado("Duenio","{nombre:'Octavio'}");		

		System.out.println("Dueños de las unidades del Rascacielo:");
		new Conector().ejecutarConsultaConRespuesta("Duenio","MATCH(x1:Edificio{nombre:'Rascacielo'})-[:Tiene]->(x2:Unidad{})-[:PerteneceA{}]->(x3:Duenio{}) return x3");		

		System.out.println("Unidades se encuentran alquiladas:");
		new Conector().ejecutarConsultaConRespuesta("Unidad","MATCH (x1: Unidad{inquilino: \"S\"}) return x1");		

		System.out.println("Nombre de los inquilinos:");
		new Conector().ejecutarConsultaConRespuestaTexto("MATCH (x1: Unidad{})-[:Alquilada_Por]->(x2: Inquilino{}) return x2.nombre");		

		
		System.out.println("Unidad con inquilino de nombre Carlos:");
		new Conector().ejecutarConsultaConRespuesta("Unidad","MATCH (x1: Unidad{})-[:Alquilada_Por]->(x2: Inquilino{nombre:'Carlos'}) return x1");		

		System.out.println("Expensas de Leopoldo:");
		new Conector().ejecutarConsultaConRespuestaTexto("MATCH (x1:Duenio{nombre:'Leopoldo'})-[:Titular{}]->(x2:Unidad) return sum(x2.expensas)");		

		
		System.exit(0);

		

	}

}
