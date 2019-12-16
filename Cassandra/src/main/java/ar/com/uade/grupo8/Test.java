package ar.com.uade.grupo8;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;


public class Test {

  public static void main(String[] args) {

    Test cassandra = new Test();

    try {
    	System.out.println("Estableciendo conexión");
    	cassandra.connect();
    	System.out.println("IMPORTANTE: Recuerde crear previamente el Keyspace y las correspondientes FamilyColumns.");
    	System.out.println("Consulto músico temas:");
    	cassandra.consultarMusicoTemas("");
    	System.out.println("Inserto a Luis Miguel con el tema 'Ahora te podés marchar'.");
    	cassandra.ejecutarConsulta("INSERT INTO musica.musico_temas (id_cancion, nombre_cancion, nombre_musico_interprete, id_musico_interprete) VALUES (11, 'Ahora te podés marchar', 'Luis Miguel', 6);");
    	System.out.println("Consulto músico temas:");
    	cassandra.consultarMusicoTemas("");
    	System.out.println("Elimino a Luis Miguel.");
    	cassandra.ejecutarConsulta("DELETE from musica.musico_temas WHERE id_musico_interprete = 11;");
    	System.out.println("Consulto músico temas:");
    	cassandra.consultarMusicoTemas("");

    	    	
    	
    	System.out.println("Busco cuantos temas tiene cada interprete:");
    	cassandra.consultarTemasPorInterprete();
    	System.out.println("Busco al autor de la cancion de id 3:");
    	cassandra.consultarAutoresLetra("WHERE id_cancion = 3");

    	
    	System.out.println("Busco todos los albumes:");
    	cassandra.buscarAlbums();
    	System.out.println("Cambio el año del album de id 5:");
    	cassandra.ejecutarConsulta("UPDATE musica.temas_album SET album_anio = 1542 WHERE id_album = 5");

    	System.out.println("Busco todos los albumes:");
    	cassandra.buscarAlbums();
    	
    	System.out.println("Busco todos los albumes de la discográfica Emi:");
    	cassandra.buscarAlbumesDiscografica("WHERE discografica_nombre = 'Emi' ALLOW FILTERING");


    	
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
    	cassandra.close();
    }
  }

  private CqlSession session;

  public void connect() {

    session = CqlSession.builder().build();

    System.out.printf("Conectado.");
  }

	public void buscarAlbumesDiscografica(String params) {
	  	ResultSet results =
			        session.execute(
			        		"	SELECT * FROM musica.album_discografica " + params + ";");

	    System.out.printf("%-30s\t%-20s\t%-20s\t%-20s\\t%-20s%n", "id_discografica", "album_nombre", "album_anio", "discografica_nombre", "interprete_nombre");
	    System.out.println(
	        "-------------------------------+-----------------------+--------------------+--------------------+--------------------");

	    for (Row row : results) {

	      System.out.printf(
	          "%-30s\t%-20s\t%-20s\t%-20s\\t%-20s%n",
	          row.getInt("id_discografica"), row.getString("album_nombre"), row.getInt("album_anio"), row.getString("discografica_nombre"), row.getString("interprete_nombre"));
	    }
	  }  

  
  
  public void ejecutarConsulta(String consulta) {

    session.execute(consulta);
  }
	public void consultarAutoresLetra(String params) {
	  	ResultSet results =
			        session.execute(
			        		"	SELECT * FROM musica.autor_letra " + params + ";");

	    System.out.printf("%-30s\t%-20s\t%-20s\t%-20s%n", "id_cancion", "nombre_musico_letra", "id_musico_letra", "nombre_cancion");
	    System.out.println(
	        "-------------------------------+-----------------------+--------------------+--------------------");

	    for (Row row : results) {

	      System.out.printf(
	          "%-30s\t%-20s\t%-20s\t%-20s%n",
	          row.getInt("id_cancion"), row.getString("nombre_musico_letra"), row.getInt("id_musico_letra"), row.getString("nombre_cancion"));
	    }
	  }  
	public void buscarAlbums() {
	  	ResultSet results =
			        session.execute(
			        		"select id_album, album_anio, album_img_portada, album_nombre from musica.temas_album;");
	    System.out.printf("%-30s\t%-20s\t%-20s\t%-20s%n", "id_album", "album_anio", "album_img_portada", "album_nombre");
	    System.out.println(
	        "-------------------------------+-----------------------+--------------------+--------------------");

	    for (Row row : results) {

	      System.out.printf(
	          "%-30s\t%-20s\t%-20s\t%-20s%n",
	          row.getInt("id_album"), row.getInt("album_anio"), row.getString("album_img_portada"), row.getString("album_nombre"));
	    }
	  }  
  public void consultarTemasPorInterprete() {
	  	ResultSet results =
			        session.execute(
			        		"SELECT nombre_musico_interprete, count(*) FROM musica.musico_temas GROUP BY id_musico_interprete;");

	    System.out.printf("%-30s\t%-20s%n", "nombre_musico_interprete", "cantidad de canciones");
	    System.out.println(
	        "-------------------------------+-----------------------");

	    for (Row row : results) {

	      System.out.printf(
	          "%-30s\t%-20s%n",
	          row.getString("nombre_musico_interprete"), row.getLong("count"));
	    }
	  }  

  
  public void consultarMusicoTemas(String where) {
	  	ResultSet results = null;
	  	if(where != "")
	  	{
		    results =
			        session.execute(
			            "SELECT * FROM musica.musico_temas "
			                + "WHERE " + where + ";");

	  	}
	  	else 
	  	{
		    results =
			        session.execute(
			            "SELECT * FROM musica.musico_temas;");
  		
	  	}
	    System.out.printf("%-30s\t%-20s\t%-20s\t%-20s%n", "id_cancion", "nombre_cancion", "nombre_musico_interprete", "id_musico_interprete");
	    System.out.println(
	        "-------------------------------+-----------------------+--------------------+--------------------");

	    for (Row row : results) {

	      System.out.printf(
	          "%-30s\t%-20s\t%-20s\t%-20s%n",
	          row.getInt("id_cancion"), row.getString("nombre_cancion"), row.getString("nombre_musico_interprete"), row.getInt("id_musico_interprete"));
	    }
	  }  

  public void close() {
    if (session != null) {
      session.close();
    }
  }
}