package org.hazelcast_vs_oracledb;

public interface Database {
    long appenderInNumber(int amountToAdded);
    long getterInNumber(int amountToGet);
    void shutdown();
}
