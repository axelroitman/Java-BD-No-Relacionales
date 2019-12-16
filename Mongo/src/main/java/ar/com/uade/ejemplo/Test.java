package ar.com.uade.ejemplo;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.Block;
import com.mongodb.client.model.Aggregates;


public class Test {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws UnknownHostException, ParseException {
		
		Block<Document> printBlock = new Block<Document>() {
		       public void apply(final Document document) {
		           System.out.println(document.toJson());
		       }
		};
		
		//Conecto con Mongo y selecciono la colección de Asesores
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase("tpBD");
		MongoCollection<Document> asesoresCollection = database.getCollection("asesor");

		
		/*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaComoTexto1 = "15/06/2016";
		String fechaComoTexto2 = "13/09/2019";
		String fechaComoTexto3 = "12/09/2019";
		String fechaComoTexto4 = "02/04/1999";
		String fechaComoTexto5 = "18/09/2019";
		
		
		//Agrego Asesores a la Base de Datos
		Document doc1 = new Document("id", 1).append("nombre","Fernando Perez").append("opiniones", (Arrays.asList(new Document("opinion", "Las acciones van a subir").append("recomendacion", "Comprar").append("inversion",1).append("fecha", sdf.parse(fechaComoTexto1)), new Document("opinion", "Esa empresa esta por quebrar").append("recomendacion", "Vender").append("inversion", 3).append("fecha", sdf.parse(fechaComoTexto2)))));
		Document doc2 = new Document("id", 2).append("nombre","Roberto Rodriguez").append("opiniones", (Arrays.asList(new Document("opinion", "Creo que esta accion va a subir").append("recomendacion", "Comprar").append("inversion",1).append("fecha", sdf.parse(fechaComoTexto3)), new Document("opinion", "Creo que esta accion va a bajar").append("recomendacion", "Vender").append("inversion", 3).append("fecha", sdf.parse(fechaComoTexto4)))));
		Document doc3 = new Document("id", 3).append("nombre","William Paul").append("opiniones", (Arrays.asList(new Document("opinion", "Las acciones van a subir").append("recomendacion", "Comprar").append("inversion",1).append("fecha", sdf.parse(fechaComoTexto5)))));
		
		
		List<Document> documents = new ArrayList<Document>();
		documents.add(doc1);
		documents.add(doc2);
		documents.add(doc3);
		asesoresCollection.insertMany(documents);
		System.out.println("Inserto al asesor Fernando Perez.");
		System.out.println("Inserto al asesor Roberto Rodriguez.");
		System.out.println("Inserto al asesor William Paul.");
				
		System.out.println("Recuperando desde Mongo, aquí están los asesores: \n");
		asesoresCollection.find().forEach(printBlock);
		 */
		
		
		
		//UPDATE sobre Fernando Perez: cambia su nombre de "Fernando Perez" a "David Ramirez".
		/*
		System.out.println("Modifico el nombre del asesor de id = 1.");
		Document query = new Document();
        query.append("id",1);
		Document setData = new Document();
        setData.append("nombre", "David Ramirez");
        Document update = new Document();
        update.append("$set", setData);
        asesoresCollection.updateOne(query, update);
        
        Document AMostrar= asesoresCollection.find(eq("id", 1)).first();

		System.out.println("Ahora el documento de Fernando Perez (renombrado 'David Ramirez') es: \n" + AMostrar.toJson());
		*/
		
		
		//ELIMINO al asesor de id=1
		/*
		System.out.println("Eliminamos al asesor de id = 1.");
		asesoresCollection.deleteOne(eq("id", 1));
        System.out.println("A continuación se listan todos los asesores para demostrar que el de id = 1 fue eliminado satisfactoriamente:");
        asesoresCollection.find().forEach(printBlock);
        */
     
		
		
		//CONSULTAS
		/**
		 * ACLARACIÓN:
		 * Para realizar las consultas, borrar la base anteriormente
		 * creada y cargar la adjuntada en el .txt del rar.
		 **/
		
		//1°- Borrar la base:
		/*
		database.drop();
		*/
		
		//¿Quién es el asesor con más recomendaciones para una inversion determinada?		
		/*
		asesoresCollection.aggregate(Arrays.asList( Aggregates.unwind("$opiniones"), Aggregates.match(eq("opiniones.inversion", 1)),Aggregates.sortByCount("$id"), Aggregates.limit(1))).forEach(printBlock);
		*/
		
		//¿Cuál es la inversión que tuvo más operaciones?
		/*
		MongoCollection<Document> inversioncol = database.getCollection("inversion");
		inversioncol.aggregate(Arrays.asList( Aggregates.unwind("$historial"), Aggregates.sortByCount("$id"), Aggregates.limit(1))).forEach(printBlock);
		*/
	}
}
