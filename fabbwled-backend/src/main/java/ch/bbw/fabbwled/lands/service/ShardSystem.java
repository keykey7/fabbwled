package ch.bbw.fabbwled.lands.service;

import lombok.NoArgsConstructor;

public record ShardSystem (int shardCount){
    public int getShardCount(){
        return shardCount;
    }
    public ShardSystem addShards(int amount){
        if(amount < 0){
            throw new IllegalArgumentException("Cannot add a negative number of shards.");
        }
        return new ShardSystem(shardCount + amount);
    }
    public ShardSystem substractShards(int amount){
        if (amount < 0)
        {
            throw new IllegalArgumentException("Cannot subtract a negative number of shards.");
        }

        if (shardCount >= amount)
        {
            return new ShardSystem(shardCount - amount);
        }
        throw new IllegalStateException("Cannnot return negative nummber");
    }

}
