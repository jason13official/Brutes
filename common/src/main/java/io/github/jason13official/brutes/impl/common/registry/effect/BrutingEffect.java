package io.github.jason13official.brutes.impl.common.registry.effect;

import io.github.jason13official.brutes.impl.common.registry.ModEntities;
import io.github.jason13official.brutes.impl.common.registry.entity.BrutishVillager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;

public class BrutingEffect extends MobEffect {

  public BrutingEffect(MobEffectCategory category, int color) {
    super(category, color);
  }

  @Override
  public void applyInstantenousEffect(Entity source, Entity indirectSource, LivingEntity livingEntity, int amplifier, double health) {
    super.applyInstantenousEffect(source, indirectSource, livingEntity, amplifier, health);

    if (livingEntity instanceof Villager && livingEntity.level() instanceof ServerLevel level) {

      BrutishVillager brute = new BrutishVillager(ModEntities.BRUTISH_VILLAGER, level);
      brute.moveTo(livingEntity.position());
      level.addFreshEntity(brute);

      livingEntity.discard();
    }
  }

  @Override
  public boolean isInstantenous() {
    return true;
  }
}
