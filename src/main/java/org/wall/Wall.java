package org.wall;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

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

        return blocks.stream()
               .filter(block -> block.getColor().equals(color))
               .findFirst()
               .or(() -> blocks.stream()
                       .filter(block -> block instanceof CompositeBlock)
                       .map(block -> findBlocksByColorInCompositeBlock( ((CompositeBlock) block).getBlocks(), color ))
                       .filter(Optional::isPresent)
                       .map(Optional::get)
                       .findFirst()
               );
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return findBlocksByMaterialInCompositeBlock(blocks, material);
    }

    private List<Block> findBlocksByMaterialInCompositeBlock(List<Block> blocks, String material) {
        List<Block> result = new LinkedList<>();

        blocks.stream()
                .forEach(block -> {
                    if (block.getMaterial().equals(material)) {
                        result.add(block);
                    }
                    if (block instanceof CompositeBlock) {
                        result.addAll(findBlocksByMaterialInCompositeBlock(((CompositeBlock) block).getBlocks(), material));
                    }
                });

        return result;
    }

    @Override
    public int count() {
        return countInCompositeBlock(blocks);
    }

    private int countInCompositeBlock(List<Block> blocks) {
        AtomicInteger count = new AtomicInteger();

        blocks.stream()
                .forEach(block -> {
                    if (block instanceof CompositeBlock) {
                        count.addAndGet(countInCompositeBlock(((CompositeBlock) block).getBlocks()));
                    }
                    count.getAndIncrement();
                });

        return count.get();
    }
}