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
        return compositeCount(blocks, new LinkedList<>());
    }

    private int compositeCount(List<Block> blockList, List<Block> visited) {
        int count = blockList.size();

        for (Block block : blockList) {
            if (visited.contains(block)) {
                // skip counting this block to avoid infinite recursion
                continue;
            }
            visited.add(block);

            if (block instanceof CompositeBlock) {
                count += compositeCount(((CompositeBlock) block).getBlocks(), visited);
            }
        }

        return count;
    }
}