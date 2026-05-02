package io.github.jason13official.brutes.impl.common.tracker;

import io.github.jason13official.brutes.impl.common.registry.ModEntities;
import io.github.jason13official.brutes.impl.common.registry.entity.BrutishVillager;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.npc.Villager;
import org.joml.Vector3f;

public class TransformationTracker {

  private static final DustParticleOptions DUST = new DustParticleOptions(new Vector3f(0.0f, 1.0f, 0.2f), 1.0f);
  private static final Map<UUID, TransformationData> VILLAGERS = new HashMap<>();

  public static void start(Villager villager) {
    VILLAGERS.put(villager.getUUID(), new TransformationData(villager));
  }

  public static void tick(ServerLevel level) {

    Set<UUID> markedForRemoval = new HashSet<>();

    VILLAGERS.forEach((id, data) -> {

      Villager villager = data.villager;

      if (villager == null) {
        markedForRemoval.add(id);
        return;
      }

      // Only tick in the villager's own level; other levels will see null from getEntity
      if (villager.level() != level) return;

      if (villager.isRemoved() || villager.isDeadOrDying()) {
        markedForRemoval.add(id);
        return;
      }

      if (data.elapsedTicks % 10 == 0) {
        level.sendParticles(DUST, villager.getX(), villager.getY() + villager.getEyeHeight(), villager.getZ(), 3, 0.05, 0.05, 0.05, 0.0);
        level.sendParticles(ParticleTypes.LARGE_SMOKE, villager.getX(), villager.getY() + villager.getEyeHeight(), villager.getZ(), 3, 0.05, 0.05, 0.05, 0.0);
      }

      if (data.elapsedTicks % 15 == 0) {
        villager.setHealth(villager.getMaxHealth());
        villager.hurt(level.damageSources().magic(), 1.0f);
      }

      if (data.elapsedTicks >= TransformationData.TOTAL_TIME_IN_TICKS) {
        BrutishVillager brute = new BrutishVillager(ModEntities.BRUTISH_VILLAGER, level);
        brute.moveTo(villager.position());
        level.addFreshEntity(brute);
        villager.discard();
      }

      data.elapsedTicks++;
    });

    markedForRemoval.forEach(VILLAGERS::remove);
  }

  public static class TransformationData {

    public static final int TOTAL_TIME_IN_TICKS = 20 * 3; // 3 seconds

    int elapsedTicks = 0;
    private final Villager villager;

    public TransformationData(Villager villager) {
      this.villager = villager;
    }
  }
}
