package net.swofty.types.generic.block;

import net.minestom.server.instance.block.Block;
import net.minestom.server.tag.Tag;
import net.swofty.types.generic.block.attribute.BlockAttribute;
import net.swofty.types.generic.block.attribute.BlockAttributeHandler;
import net.swofty.types.generic.block.attribute.attributes.BlockAttributeType;
import net.swofty.types.generic.block.impl.CustomSkyBlockBlock;

import java.util.ArrayList;
import java.util.List;

public class SkyBlockBlock {
    public List<BlockAttribute> attributes = new ArrayList<>();
    public Class<? extends CustomSkyBlockBlock> clazz;
    public CustomSkyBlockBlock instance = null;

    public SkyBlockBlock(BlockType type) {
        clazz = type.clazz;

        for (BlockAttribute attribute : BlockAttribute.getPossibleAttributes()) {
            attribute.setValue(attribute.getDefaultValue(clazz));
            attributes.add(attribute);
        }

        try {
            instance = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        BlockAttributeType typeAttribute = (BlockAttributeType) getAttribute("block_type");
        typeAttribute.setValue(type.name());
    }

    public SkyBlockBlock(Block block) {
        clazz = BlockType.valueOf(block.getTag(Tag.String("block_type"))).clazz;

        for (BlockAttribute attribute : BlockAttribute.getPossibleAttributes()) {
            attribute.setValue(attribute.loadFromString(block.getTag(Tag.String(attribute.getKey()))));
            attributes.add(attribute);
        }

        try {
            instance = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public BlockAttributeHandler getAttributeHandler() {
        return new BlockAttributeHandler(this);
    }

    public Block toBlock() {
        Block block = instance.getDisplayMaterial();

        for (BlockAttribute attribute : attributes) {
            block = block.withTag(Tag.String(attribute.getKey()), attribute.saveIntoString());
        }

        return block;
    }

    public Object getGenericInstance() {
        if (instance != null)
            return instance;

        try {
            instance = clazz.newInstance();
            return instance;
        } catch (Exception e) {}
        return null;
    }

    public static boolean isSkyBlockBlock(Block block) {
        return block.hasTag(Tag.String("block_type"));
    }

    public Object getAttribute(String key) {
        return attributes.stream().filter(attribute -> attribute.getKey().equals(key)).findFirst().orElse(null);
    }
}
