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
        return findBlocksByColorInCompositeBlock(blocks, color);
    }

    private Optional<Block> findBlocksByColorInCompositeBlock(List<Block> blocks, String color) {
       Optional<Block> result = Optional.empty();

        for (Block block : blocks) {
            if (block.getColor().equals(color)) {
                result = Optional.of(block);
                break;
            }
            if (block instanceof CompositeBlock) {
                result = findBlocksByColorInCompositeBlock(((CompositeBlock) block).getBlocks(), color);
                if (result.isPresent()) {
                    break;
                }
            }
        }

        return result;
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return findBlocksByMaterialInCompositeBlock(blocks, material);
    }

    private List<Block> findBlocksByMaterialInCompositeBlock(List<Block> blocks, String material) {
        List<Block> result = new LinkedList<>();

        for (Block block : blocks) {
            if (block.getMaterial().equals(material)) {
                result.add(block);
            }
            if (block instanceof CompositeBlock) {
                result.addAll(findBlocksByMaterialInCompositeBlock(((CompositeBlock) block).getBlocks(), material));
            }
        }

        return result;
    }

    @Override
    public int count() {
        return countInCompositeBlock(blocks);
    }

    private int countInCompositeBlock(List<Block> blocks) {
        int count = 0;

        for (Block block : blocks) {
            if (block instanceof CompositeBlock) {
                count += countInCompositeBlock(((CompositeBlock) block).getBlocks());
            }
            count++;
        }

        return count;
    }
}