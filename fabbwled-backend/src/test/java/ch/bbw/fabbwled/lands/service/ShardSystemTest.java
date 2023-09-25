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
        assertEquals(10, shardSystem.getShardCount());
    }

    @Test
    void testAddShards() {
        shardSystem.addShards(5);
        assertEquals(15, shardSystem.getShardCount());
    }

    @Test
    void testAddNegativeShards() {
        assertThrows(IllegalArgumentException.class, () -> shardSystem.addShards(-5));
    }

    @Test
    void testSubtractShards() {
        assertTrue(shardSystem.substractShards(5));
        assertEquals(5, shardSystem.getShardCount());
    }

    @Test
    void testSubtractNegativeShards() {
        assertThrows(IllegalArgumentException.class, () -> shardSystem.substractShards(-5));
    }

    @Test
    void testSubtractMoreShardsThanAvailable() {
        assertFalse(shardSystem.substractShards(15));
    }
}
