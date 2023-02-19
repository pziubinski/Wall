package org.wall;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {
    private List<Block> blocks;

    public Wall() {
        this.blocks = new LinkedList<>();
    }

    public void addBlock(Block block) {
        this.blocks.add(block);
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return Optional.empty();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return null;
    }

    @Override
    public int count() {
        return blocks.size();
    }
}