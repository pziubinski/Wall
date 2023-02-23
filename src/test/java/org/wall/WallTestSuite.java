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
        assertAll(
                () -> assertFalse(red.isPresent()),
                () -> assertTrue(white.isPresent()),
                () -> assertEquals(block1, white.get())
        );
    }

    @Test
    void testFindBlocksByColorWithOneLevelOfCompositeBlock() {
        //WHEN
        wall.addBlock(compositeBlock1);

        Optional<Block> red = wall.findBlockByColor("red");
        Optional<Block> grey = wall.findBlockByColor("grey");
        Optional<Block> white = wall.findBlockByColor("white");

        //THEN
        assertAll(
                () -> assertFalse(red.isPresent()),
                () -> assertTrue(grey.isPresent()),
                () -> assertEquals(compositeBlock1, grey.get()),
                () -> assertTrue(white.isPresent()),
                () -> assertEquals(block1, white.get())
        );

    }

    @Test
    void testFindBlocksByColorWithTwoLevelsOfCompositeBlock() {
        //WHEN
        wall.addBlock(compositeBlock2);

        Optional<Block> red = wall.findBlockByColor("red");
        Optional<Block> grey = wall.findBlockByColor("grey");
        Optional<Block> white = wall.findBlockByColor("white");

        //THEN
        assertAll(
                () -> assertTrue(red.isPresent()),
                () -> assertEquals(compositeBlock2, red.get()),
                () -> assertTrue(grey.isPresent()),
                () -> assertEquals(compositeBlock1, grey.get()),
                () -> assertTrue(white.isPresent()),
                () -> assertEquals(block1, white.get())
        );
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
        assertAll(
                () -> assertTrue(orange.isPresent()),
                () -> assertEquals(compositeBlock3, orange.get()),
                () -> assertTrue(red.isPresent()),
                () -> assertEquals(compositeBlock2, red.get()),
                () -> assertTrue(grey.isPresent()),
                () -> assertEquals(compositeBlock1, grey.get()),
                () -> assertTrue(white.isPresent()),
                () -> assertEquals(block1, white.get())
        );
    }

    @Test
    void testFindBlocksByMaterialWithSimpleBlock() {
        //WHEN
        wall.addBlock(block1);
        wall.addBlock(block2);

        int concrete = wall.findBlocksByMaterial("concrete").size();
        int wood = wall.findBlocksByMaterial("wood").size();

        //THEN
        assertAll(
                () -> assertEquals(1, concrete),
                () -> assertEquals(1, wood)
        );
    }

    @Test
    void testFindBlocksByMaterialWithOneLevelOfCompositeBlock() {
        //WHEN
        wall.addBlock(compositeBlock1);

        int concrete = wall.findBlocksByMaterial("concrete").size();
        int wood = wall.findBlocksByMaterial("wood").size();

        //THEN
        assertAll(
                () -> assertEquals(2, concrete),
                () -> assertEquals(1, wood)
        );
    }

    @Test
    void testFindBlocksByMaterialWithTwoLevelsOfCompositeBlock() {
        //WHEN
        wall.addBlock(compositeBlock2);

        int concrete = wall.findBlocksByMaterial("concrete").size();
        int wood = wall.findBlocksByMaterial("wood").size();

        //THEN
        assertAll(
                () -> assertEquals(3, concrete),
                () -> assertEquals(1, wood)
        );
    }

    @Test
    void testFindBlocksByMaterialWithThreeLevelsOfCompositeBlock() {
        //WHEN
        wall.addBlock(compositeBlock3);

        int concrete = wall.findBlocksByMaterial("concrete").size();
        int wood = wall.findBlocksByMaterial("wood").size();
        int iron = wall.findBlocksByMaterial("iron").size();
        int clay = wall.findBlocksByMaterial("clay").size();

        //THEN
        assertAll(
                () -> assertEquals(3, concrete),
                () -> assertEquals(1, wood),
                () -> assertEquals(1, iron),
                () -> assertEquals(1, clay)
        );
    }

    @Test
    void testCountWithSimpleBlock() {
        //WHEN
        wall.addBlock(block1);
        wall.addBlock(block2);

        int count = wall.count();

        //THEN
        assertEquals(2, count);
    }

    @Test
    void testCountWithOneLevelOfCompositeBlock() {
        //WHEN
        wall.addBlock(compositeBlock1);

        int count = wall.count();

        //THEN
        assertEquals(3, count);
    }

    @Test
    void testCountWithTwoLevelsOfCompositeBlock() {
        //WHEN
        wall.addBlock(compositeBlock2);

        int count = wall.count();

        //THEN
        assertEquals(4, count);
    }

    @Test
    void testCountWithThreeLevelsOfCompositeBlock() {
        //WHEN
        wall.addBlock(compositeBlock3);

        int count = wall.count();

        //THEN
        assertEquals(6, count);
    }
}