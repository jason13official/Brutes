package io.github.jason13official.brutes.impl.common.registry.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.entity.animal.sniffer.Sniffer.State;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

/// [Sniffer]
public class BrutishVillager extends Monster {

  private State state = State.IDLE;
  public final AnimationState idleAnimationState = new AnimationState();
  private int idleAnimationTimeout = 0;

  private static final EntityDataAccessor<String> DATA_STATE = SynchedEntityData.defineId(BrutishVillager.class, EntityDataSerializers.STRING);

  public BrutishVillager(EntityType<? extends Monster> entityType, Level level) {
    super(entityType, level);
  }

  @Override
  protected void defineSynchedData() {
    super.defineSynchedData();
    this.entityData.define(DATA_STATE, State.IDLE.toString().toLowerCase());
  }

  private void resetAnimations() {
    this.idleAnimationState.stop();
  }

  public BrutishVillager transitionTo(State state) {
    this.setState(state);
    return this;
  }

  public State getDataState() {
    return switch (this.entityData.get(DATA_STATE)) {
      case "idle" -> State.IDLE;
      default -> State.IDLE;
    };
  }

  private BrutishVillager setState(BrutishVillager.State state) {
    this.entityData.set(DATA_STATE, state.toString().toLowerCase());
    return this;
  }

  @Override
  public void tick() {
    super.tick();
    if (this.level().isClientSide()) {
      this.setupAnimationStates();
    }
  }

  private void setupAnimationStates() {
    if (this.idleAnimationTimeout <= 0) {
      this.idleAnimationTimeout = this.random.nextInt(40) + 80;
      this.idleAnimationState.start(this.tickCount);
    } else {
      --this.idleAnimationTimeout;
    }
  }

  public enum State {
    IDLE
  }
}
