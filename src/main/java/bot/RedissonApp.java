package bot;

import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
public class RedissonApp {


    public static void main(String[] args) {
        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();

        // implements java.util.List
        RMap<String,Animal> map = redisson.getMap("myMap");
        Animal cow = new Animal();
        cow.setAge(10);
        cow.setName("Amy");
        Animal cow2 = new Animal();
        cow2.setAge(7);
        cow2.setName("Yura");

        map.put("amy",cow);
        map.put("yura",cow2);

        System.out.println("Map size: " + map.size());


        System.out.println("Map contains key 1: " + map.containsKey("amy"));
        System.out.println("Map contains key no: " + map.containsKey("no"));
        Animal anyAmyCow = map.get("amy");

        System.out.println("Object name with index amy: " + anyAmyCow.getName());
        anyAmyCow = map.get("yura");
        System.out.println("Object age with index yura: " + anyAmyCow.getAge());


        redisson.shutdown();
    }

}