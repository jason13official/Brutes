package io.github.jason13official.brutes.impl.common.registry;

import io.github.jason13official.brutes.Brutes;
import java.util.function.BiConsumer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

public class ModPotions {

  public static Potion BRUTING;

  public static void register(BiConsumer<Potion, ResourceLocation> consumer) {

    BRUTING = new Potion(new MobEffectInstance(ModEffects.BRUTING));
    consumer.accept(BRUTING, Brutes.identifier("bruting"));
  }
}
