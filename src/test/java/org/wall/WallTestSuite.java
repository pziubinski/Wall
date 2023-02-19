package org.wall;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WallTestSuite {
    private Wall wall;
    private Block block1;
    private Block block2;
    private CompositeBlock compositeBlock;

    @BeforeEach
    void setUp() {
        wall = new Wall();
        block1 = new BlockImpl("white", "marble");
        block2 = new BlockImpl("brown", "wood");
        compositeBlock = new CompositeBlockImpl("grey", "concrete", List.of(block1, block2));
    }

    @Test
    void testCount() {
        wall.addBlock(block1);
        wall.addBlock(block2);

        assertEquals(2, wall.count());
    }

    @Test
    void testCompositeCount() {
        wall.addBlock(compositeBlock);

        assertEquals(3, wall.count());
    }
}