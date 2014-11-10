package edu.sjsu.cmpe.cache.client;
import com.google.common.hash.*;
import java.util.ArrayList;

public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Cache Client...");

        ArrayList<CacheServiceInterface> servers = new ArrayList();
        servers.add(new DistributedCacheService("http://localhost:3000"));
        servers.add(new DistributedCacheService("http://localhost:3001"));
        servers.add(new DistributedCacheService("http://localhost:3002"));

        //part of the original code
        //CacheServiceInterface cache = new DistributedCacheService("http://localhost:3000");
    ConsistentHash hash = new ConsistentHash(Hashing.md5(), 3, servers);

       

for(long i = 1 ; i <= 10 ; i++)
   {
        String value = Character.toString((char)('a' + i - 1)); //inc character till j
        hash.get(i).put(i, value);
        int bucket = servers.indexOf(hash.get(i));
        
    }
    
for(long i = 1; i <= 10 ; i++)
   {

    System.out.println("get(" + i + ") => " + hash.get(i).get(i));
        
    }
    System.out.println("Existing Cache Client...");

}
}
