package org.wall;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {

    private Wall wall;

    @BeforeEach
    void setUp() {
        wall = new Wall();
    }

    @Test
    void testCount() {
        assertEquals(0, wall.count());
    }

}