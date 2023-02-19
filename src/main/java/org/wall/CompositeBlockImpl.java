package org.wall;

import java.util.LinkedList;
import java.util.List;

public class CompositeBlockImpl extends BlockImpl implements CompositeBlock {

    private List<Block> blocks = new LinkedList<>();

    public CompositeBlockImpl(String color, String material) {
        super(color, material);
    }

    @Override
    public List<Block> getBlocks() {
        return blocks;
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }
}
