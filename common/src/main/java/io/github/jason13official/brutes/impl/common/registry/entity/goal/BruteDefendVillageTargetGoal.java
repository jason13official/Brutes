package io.github.jason13official.brutes.impl.common.registry.entity.goal;

import io.github.jason13official.brutes.impl.common.registry.entity.BrutishVillager;
import java.util.EnumSet;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

public class BruteDefendVillageTargetGoal extends TargetGoal {

  private final BrutishVillager brute;
  private final TargetingConditions attackTargeting = TargetingConditions.forCombat().range(64.0F);
  @Nullable
  private LivingEntity potentialTarget;

  public BruteDefendVillageTargetGoal(BrutishVillager brute) {
    super(brute, false, true);
    this.brute = brute;
    this.setFlags(EnumSet.of(Flag.TARGET));
  }

  public boolean canUse() {
    AABB aabb = this.brute.getBoundingBox().inflate(10.0F, 8.0F, 10.0F);
    List<? extends LivingEntity> list = this.brute.level().getNearbyEntities(Villager.class, this.attackTargeting, this.brute, aabb);
    List<Player> list1 = this.brute.level().getNearbyPlayers(this.attackTargeting, this.brute, aabb);

    for (LivingEntity livingentity : list) {
      Villager villager = (Villager) livingentity;

      for (Player player : list1) {
        int i = villager.getPlayerReputation(player);
        if (i <= -100) {
          this.potentialTarget = player;
        }
      }
    }

    if (this.potentialTarget == null) {
      return false;
    } else {
      return !(this.potentialTarget instanceof Player) || !this.potentialTarget.isSpectator() && !((Player) this.potentialTarget).isCreative();
    }
  }

  public void start() {
    this.brute.setTarget(this.potentialTarget);
    super.start();
  }
}

