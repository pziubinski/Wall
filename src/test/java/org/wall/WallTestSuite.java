package org.wall;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WallTestSuite {
    private Wall wall;
    private Block block1;
    private Block block2;
    private Block block3;
    private CompositeBlockImpl compositeBlock1;
    private CompositeBlockImpl compositeBlock2;
    private CompositeBlockImpl compositeBlock3;

    @BeforeEach
    void setUp() {
        //GIVEN
        wall = new Wall();

        block1 = new BlockImpl("white", "marble");
        block2 = new BlockImpl("brown", "wood");
        block3 = new BlockImpl("yellow", "clay");

        compositeBlock1 = new CompositeBlockImpl("grey", "concrete");
        compositeBlock1.addBlock(block1);
        compositeBlock1.addBlock(block2);

        compositeBlock2 = new CompositeBlockImpl("red", "concrete");
        compositeBlock2.addBlock(compositeBlock1);

        compositeBlock3 = new CompositeBlockImpl("orange", "iron");
        compositeBlock3.addBlock(compositeBlock2);
        compositeBlock3.addBlock(block3);
    }

    @Test
    void testSimpleCount() {
        //WHEN
        wall.addBlock(block1);
        wall.addBlock(block2);

        //THEN
        assertEquals(2, wall.count());
    }

    @Test
    void testOneLevelOfCompositionCount() {
        //WHEN
        wall.addBlock(compositeBlock1);

        //THEN
        assertEquals(3, wall.count());
    }

    @Test
    void testTwoLevelsOfCompositionCount() {
        //WHEN
        wall.addBlock(compositeBlock2);

        //THEN
        assertEquals(4, wall.count());
    }

    @Test
    void testThreeLevelsOfCompositionCount() {
        //WHEN
        wall.addBlock(compositeBlock3);

        //THEN
        assertEquals(6, wall.count());
    }
}