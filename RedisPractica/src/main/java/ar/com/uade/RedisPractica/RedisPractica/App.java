package ar.com.uade.RedisPractica.RedisPractica;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Jedis jedis = new Jedis();
    	System.out.println("Establecida la conexión");
    	
    	/* HASH
    	jedis.hmset("Gabriel", "dni", "2014592", "pecas", "5", "radiografias", "radiografiasGabriel");
    	Map<String, String> cosasHmSet= new HashMap<String, String>();
    	cosasHmSet.put("DNI", "123141254");
    	cosasHmSet.put("Pecas", "5");
    	cosasHmSet.put("radiografias", "radiografiasGabriel");
    	
    	jedis.hmset("Gabriel", cosasHmSet);
    	System.out.println(jedis.hgetAll("Gabriel"));
    	*/
    	
    	/* UPDATE HASH
    	Map<String, String> actualizacion = new HashMap<String, String>();
    	actualizacion.put("DNI", "55555");
    	actualizacion.put("Pecas", "1000");
    	jedis.hmset("Gabriel", actualizacion); //Actualización
    	System.out.println(jedis.hgetAll("Gabriel"));
    	*/
    	
    	
    	/* LIST
    	jedis.lpush("radiografiasGabriel", "torax", "pelvis", "intrauterina", "extrauterina", "extra extra", "vejiga natatoria");
    	System.out.println(jedis.lrange("radiografiasGabriel", 0, -1));
    	*/
    	
    	
    	/* Sets
    	jedis.sadd("AmigosDeRuben", "Pepe", "Santiago", "Chiqui Tapia", "Choclo Carrizo", "Almeja Alejandro", "Molleja Tostada");
    	System.out.println(jedis.smembers("AmigosDeRuben"));
    	*/
    	
    	
    	
    	
    }
}
