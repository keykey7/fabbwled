package ch.bbw.fabbwled.lands.service;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ShardSystem {
    private int shardCount;


    public ShardSystem(int initialShards ){
        shardCount = initialShards;
    }
    public int getShardCount(){
        return shardCount;
    }
    public void addShards(int amount){
        if(amount < 0){
            throw new IllegalArgumentException("Cannot add a negative number of shards.");
        }
        shardCount += amount;
    }
    public boolean substractShards(int amount){
        if (amount < 0)
        {
            throw new IllegalArgumentException("Cannot subtract a negative number of shards.");
        }

        if (shardCount >= amount)
        {
            shardCount -= amount;
            return true;
        }
        else
        {
            return false;
        }
    }

}
