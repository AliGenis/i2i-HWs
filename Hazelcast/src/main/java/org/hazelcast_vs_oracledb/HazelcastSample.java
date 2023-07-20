package org.hazelcast_vs_oracledb;
import com.hazelcast.collection.IList;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.internal.util.Clock;
import java.util.Random;

public class HazelcastSample implements Database{
    private HazelcastInstance hz;
    private Random rand = new Random();

    public HazelcastSample(){
        hz = Hazelcast.newHazelcastInstance();
    }

    /**
     * @return The time passed in millisecond
     */
    public long appenderInNumber(int amountToAdded){
        IList list = hz.getList("my-distributed-list");
        long initTime = Clock.currentTimeMillis();
        // Add elements to the list
        for (int i = 0; i < amountToAdded;i++){
            list.add(rand.nextInt(1000));
        }

        long endTime = Clock.currentTimeMillis();
        return endTime - initTime;
    }
    /**
     * @return The time passed in millisecond
     */
    public long getterInNumber(int amountToGet){
        IList list = hz.getList("my-distributed-list");
        long initTime = Clock.currentTimeMillis();
        // Add elements to the list
        for (int i = 0; i < amountToGet;i++){
            list.get(rand.nextInt(amountToGet -1));
        }

        long endTime = Clock.currentTimeMillis();
        list.clear();
        return endTime - initTime;
    }
    public void shutdown(){
        hz.shutdown();
    }
}