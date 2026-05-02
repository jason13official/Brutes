package io.github.jason13official.brutes.impl.common.registry;

import io.github.jason13official.brutes.Brutes;
import io.github.jason13official.brutes.impl.common.registry.effect.BrutingEffect;
import java.util.function.BiConsumer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ModEffects {

  public static MobEffect BRUTING;

  public static void register(BiConsumer<MobEffect, ResourceLocation> consumer) {

    BRUTING = new BrutingEffect(MobEffectCategory.NEUTRAL, 0x00AA33);
    consumer.accept(BRUTING, Brutes.identifier("bruting"));
  }
}
