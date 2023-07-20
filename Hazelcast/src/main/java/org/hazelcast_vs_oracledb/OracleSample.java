package org.hazelcast_vs_oracledb;

/**
 * This class has to be connected with the working Oracle DB.
 * Since I am using Apple Silicon Mac, I couldn't find native support for
 * Oracle DB. Oracle published Docker container for Intel Macs but there is no
 * support for Apple Silicon. So for comparison purposes I take the data from
 * Arda Adayener.
 *
 */
public class OracleSample implements Database {
    @Override
    public long appenderInNumber(int amountToAdded) {
        //Arda's data
        if(amountToAdded == 20000){
            return 513;
        } else if (amountToAdded == 100000){
            return 2540;
        }
        return -1;
    }

    @Override
    public long getterInNumber(int amountToGet) {
        //Arda's data
        if(amountToGet == 20000){
            return 417;
        } else if (amountToGet == 100000){
            return 1873;
        }
        return -1;
    }

    @Override
    public void shutdown() {}
}
