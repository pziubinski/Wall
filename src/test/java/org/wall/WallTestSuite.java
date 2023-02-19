package org.wall;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WallTestSuite {
    private Wall wall;
    private Block block1;
    private Block block2;

    @BeforeEach
    void setUp() {
        wall = new Wall();
        block1 = new BlockImpl("white", "marble");
        block2 = new BlockImpl("brown", "wood");
    }

    @Test
    void testCount() {
        wall.addBlock(block1);
        wall.addBlock(block2);

        assertEquals(2, wall.count());
    }
}