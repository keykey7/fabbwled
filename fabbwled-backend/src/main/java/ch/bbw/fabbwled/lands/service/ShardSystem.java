package ch.bbw.fabbwled.lands.service;




public record ShardSystem (int shardCount){
    public ShardSystem addShards(int amount){
        if(amount < 0){
            throw new IllegalArgumentException("Cannot add a negative number of shards.");
        }
        return new ShardSystem(shardCount + amount);
    }
    public ShardSystem subtractShards(int amount){
        if (amount < 0)
        {
            throw new IllegalArgumentException("Cannot subtract a negative number of shards.");
        }

        if (shardCount >= amount)
        {
            return new ShardSystem(shardCount - amount);
        }
        throw new IllegalStateException("Cannot return negative number");
    }

}
