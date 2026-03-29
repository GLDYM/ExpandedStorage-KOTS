package compasses.expandedstorage.block;

import compasses.expandedstorage.block.misc.CopperBlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public final class CopperMiniStorageBlock extends MiniStorageBlock implements WeatheringCopper {
    private final WeatheringCopper.WeatherState weatherState;

    public CopperMiniStorageBlock(Properties settings, WeatheringCopper.WeatherState weatherState) {
        super(settings, false);
        this.weatherState = weatherState;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return CopperBlockHelper.getNextOxidisedState(state).isPresent();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        this.onRandomTick(state, level, pos, random);
    }

    @NotNull
    @Override
    public Optional<BlockState> getNext(BlockState state) {
        return CopperBlockHelper.getNextOxidisedState(state);
    }

    @NotNull
    @Override
    public WeatheringCopper.WeatherState getAge() {
        return weatherState;
    }

}

