package org.wall;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

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

        block1 = new BlockImpl("white", "concrete");
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
    void testFindBlocksByColorWithSimpleBlock() {
        //WHEN
        wall.addBlock(block1);
        wall.addBlock(block2);

        Optional<Block> red = wall.findBlockByColor("red");
        Optional<Block> white = wall.findBlockByColor("white");

        //THEN
        assertFalse(red.isPresent());

        assertTrue(white.isPresent());
        assertEquals(block1, white.get());
    }

    @Test
    void testFindBlocksByColorWithOneLevelOfCompositeBlock() {
        //WHEN
        wall.addBlock(compositeBlock1);

        Optional<Block> red = wall.findBlockByColor("red");
        Optional<Block> grey = wall.findBlockByColor("grey");
        Optional<Block> white = wall.findBlockByColor("white");

        //THEN
        assertFalse(red.isPresent());

        assertTrue(grey.isPresent());
        assertEquals(compositeBlock1, grey.get());

        assertTrue(white.isPresent());
        assertEquals(block1, white.get());
    }

    @Test
    void testFindBlocksByColorWithTwoLevelsOfCompositeBlock() {
        //WHEN
        wall.addBlock(compositeBlock2);

        Optional<Block> red = wall.findBlockByColor("red");
        Optional<Block> grey = wall.findBlockByColor("grey");
        Optional<Block> white = wall.findBlockByColor("white");

        //THEN
        assertTrue(red.isPresent());
        assertEquals(compositeBlock2, red.get());

        assertTrue(grey.isPresent());
        assertEquals(compositeBlock1, grey.get());

        assertTrue(white.isPresent());
        assertEquals(block1, white.get());
    }

    @Test
    void testFindBlocksByColorWithThreeLevelsOfCompositeBlock() {
        //WHEN
        wall.addBlock(compositeBlock3);

        Optional<Block> red = wall.findBlockByColor("red");
        Optional<Block> grey = wall.findBlockByColor("grey");
        Optional<Block> white = wall.findBlockByColor("white");
        Optional<Block> orange = wall.findBlockByColor("orange");

        //THEN
        assertTrue(orange.isPresent());
        assertEquals(compositeBlock3, orange.get());

        assertTrue(red.isPresent());
        assertEquals(compositeBlock2, red.get());

        assertTrue(grey.isPresent());
        assertEquals(compositeBlock1, grey.get());

        assertTrue(white.isPresent());
        assertEquals(block1, white.get());
    }

    @Test
    void testFindBlocksByMaterialWithSimpleBlock() {
        //WHEN
        wall.addBlock(block1);
        wall.addBlock(block2);

        //THEN
        assertEquals(1, wall.findBlocksByMaterial("concrete").size());
        assertEquals(1, wall.findBlocksByMaterial("wood").size());
    }

    @Test
    void testFindBlocksByMaterialWithOneLevelOfCompositeBlock() {
        //WHEN
        wall.addBlock(compositeBlock1);

        //THEN
        assertEquals(2, wall.findBlocksByMaterial("concrete").size());
        assertEquals(1, wall.findBlocksByMaterial("wood").size());
    }

    @Test
    void testFindBlocksByMaterialWithTwoLevelsOfCompositeBlock() {
        //WHEN
        wall.addBlock(compositeBlock2);

        //THEN
        assertEquals(3, wall.findBlocksByMaterial("concrete").size());
        assertEquals(3, wall.findBlocksByMaterial("concrete").size());
    }

    @Test
    void testFindBlocksByMaterialWithThreeLevelsOfCompositeBlock() {
        //WHEN
        wall.addBlock(compositeBlock3);

        //THEN
        assertEquals(3, wall.findBlocksByMaterial("concrete").size());
    }

    @Test
    void testCountWithSimpleBlock() {
        //WHEN
        wall.addBlock(block1);
        wall.addBlock(block2);

        //THEN
        assertEquals(2, wall.count());
    }

    @Test
    void testCountWithOneLevelOfCompositeBlock() {
        //WHEN
        wall.addBlock(compositeBlock1);

        //THEN
        assertEquals(3, wall.count());
    }

    @Test
    void testCountWithTwoLevelsOfCompositeBlock() {
        //WHEN
        wall.addBlock(compositeBlock2);

        //THEN
        assertEquals(4, wall.count());
    }

    @Test
    void testCountWithThreeLevelsOfCompositeBlock() {
        //WHEN
        wall.addBlock(compositeBlock3);

        //THEN
        assertEquals(6, wall.count());
    }
}