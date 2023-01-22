package bot;

import redis.clients.jedis.Jedis;

public class JedisApp {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("http://localhost:6379");
        Animal cow = new Animal();
        cow.setAge(10);
        cow.setName("Amy");
        System.out.println("Connection to server successfully");
        jedis.set("1", String.valueOf(cow.age));
        jedis.set("2", cow.name);
        System.out.println("Stored string in redis:: "+ jedis.get("1") + jedis.get("2"));
    }
}
