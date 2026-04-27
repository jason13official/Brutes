package io.github.jason13official.brutes.impl.common.registry.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class BrutishVillager extends Monster {

  public BrutishVillager(EntityType<? extends Monster> entityType, Level level) {
    super(entityType, level);
  }
}
