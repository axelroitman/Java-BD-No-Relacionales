package conexionRedis;
import redis.clients.jedis.Jedis;

public class RedisDemo {

	public static void main(String[] args) {
		try {
			Jedis jedis = new Jedis("127.0.0.1",6379);
			System.out.println("Connection Succesful");
			System.out.println(jedis.lrange("edificios", 0, -1)); // muestra todos los edificios cargados
			System.out.println(jedis.hmget("edificio1", "nombre")); // muestra el nombre del primer edificio
			System.out.println(jedis.hmget("unidad55", "cantDuenios")); // muestra la cantidad de duenios que tiene la unidad55
			
			// a partir de aca ya nuestra BD creada mediante el archivo BD.lua no nos servira para hacer los insert
			System.out.println(jedis.lpush("equipos", "Boca", "River", "San Lorenzo", "Racing", "Independiente")); // insertamos una tabla equipos 
			System.out.println(jedis.hset("Boca", "jugadores", "Andrada")); // insertar un campo y un valor
			System.out.println(jedis.hmget("Boca", "jugadores")); // consultamos los valores del campo recien creado
			System.out.println(jedis.del("equipos")); // borramos la key equipos que creamos
		} catch(Exception e) {
			System.out.println(e);
		}

	}

}


