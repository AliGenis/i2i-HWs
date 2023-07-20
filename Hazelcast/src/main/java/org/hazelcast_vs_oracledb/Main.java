package org.hazelcast_vs_oracledb;

public class Main {
    public static void main(String[] args){
        //To disable hazelcast logs
        System.setProperty( "hazelcast.logging.type", "none" );

        //Hazelcast
        HazelcastSample hazelcast = new HazelcastSample();
        System.out.println("------Hazelcast------");
        System.out.println("Time for adding 20.000 random numbers: " +
                (hazelcast.appenderInNumber(20000)));
        System.out.println("Time for getting 20.000 numbers: " +
                hazelcast.getterInNumber(20000));
        System.out.println("Time for adding 100.000 random numbers: " +
                (hazelcast.appenderInNumber(100000)));
        System.out.println("Time for getting 100.000 numbers: " +
                hazelcast.getterInNumber(100000));
        hazelcast.shutdown();

        //Oracle
        OracleSample oracle = new OracleSample();
        System.out.println("------Oracle------");
        System.out.println("Time for adding 20.000 random numbers: " +
                (oracle.appenderInNumber(20000)));
        System.out.println("Time for getting 20.000 numbers: " +
                oracle.getterInNumber(20000));
        System.out.println("Time for adding 100.000 random numbers: " +
                (oracle.appenderInNumber(100000)));
        System.out.println("Time for getting 100.000 numbers: " +
                oracle.getterInNumber(100000));
        oracle.shutdown();
    }
}
