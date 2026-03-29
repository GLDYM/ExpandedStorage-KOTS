package compasses.expandedstorage.registry;

import compasses.expandedstorage.item.BlockMutatorBehaviour;
import compasses.expandedstorage.item.MutationMode;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public final class BlockMutatorBehaviours {
    private static final Map<Map.Entry<Predicate<Block>, MutationMode>, BlockMutatorBehaviour> BLOCK_MUTATOR_BEHAVIOURS = new HashMap<>();

    private BlockMutatorBehaviours() {
    }

    public static void register(Predicate<Block> predicate, MutationMode mode, BlockMutatorBehaviour behaviour) {
        BLOCK_MUTATOR_BEHAVIOURS.put(Map.entry(predicate, mode), behaviour);
    }

    public static BlockMutatorBehaviour get(Block block, MutationMode mode) {
        for (Map.Entry<Map.Entry<Predicate<Block>, MutationMode>, BlockMutatorBehaviour> entry : BLOCK_MUTATOR_BEHAVIOURS.entrySet()) {
            Map.Entry<Predicate<Block>, MutationMode> pair = entry.getKey();
            if (pair.getValue() == mode && pair.getKey().test(block)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
