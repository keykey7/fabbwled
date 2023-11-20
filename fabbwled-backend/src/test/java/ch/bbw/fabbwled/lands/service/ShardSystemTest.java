package ch.bbw.fabbwled.lands.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ShardSystemTest {

    private ShardSystem shardSystem;

    @BeforeEach
    void setUp() {
        shardSystem = new ShardSystem(10); // Initialize with 10 shards
    }

    @Test
    void testGetShardCount() {
        assertEquals(10, shardSystem.shardCount());
    }

    @Test
    void testAddShards() {
        ShardSystem _shardSystem = shardSystem.addShards(5);
        assertEquals(15, _shardSystem.shardCount());
    }

    @Test
    void testAddNegativeShards() {
        assertThrows(IllegalArgumentException.class, () -> shardSystem.addShards(-5));
    }

    @Test
    void testSubtractShards() {
        ShardSystem shardSystem1 = shardSystem.subtractShards(5);
        assertEquals(5, shardSystem1.shardCount());
    }

    @Test
    void testSubtractNegativeShards() {
        assertThrows(IllegalArgumentException.class, () -> shardSystem.subtractShards(-5));
    }

    @Test
    void testSubtractMoreShardsThanAvailable() {
        assertThrows(IllegalStateException.class,()-> shardSystem.subtractShards(15));
    }
}
