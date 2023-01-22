//package bot;
//
//import org.redisson.Redisson;
//import org.redisson.api.RBucket;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//
//public class RedissonApp {
//    public static void main(String[] args) {
//        Config config = new Config();
//        config.useClusterServers()
//                .addNodeAddress("redis://192.168.0.105:7181");
//        RedissonClient redisson = Redisson.create(config);
//        Animal cow = new Animal();
//        cow.setAge(10);
//        cow.setName("Amy");
//        RBucket<Animal> bucket = redisson.getBucket("anyObject");
//        bucket.set(new Animal());
//        Animal myObject = bucket.get();
//        bucket.trySet(cow);
//      //  bucket.compareAndSet(oldObject, newObject);
//        Animal prevObject = bucket.getAndSet(new Animal());
//    }
//}
