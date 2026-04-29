package io.github.jason13official.brutes.impl.common.registry.entity;

import io.github.jason13official.brutes.impl.common.registry.entity.goal.BruteDefendVillageTargetGoal;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.GolemRandomStrollInVillageGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveBackToVillageGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class BrutishVillager extends PathfinderMob {

  private static final EntityDataAccessor<String> DATA_STATE = SynchedEntityData.defineId(BrutishVillager.class, EntityDataSerializers.STRING);
  public final AnimationState idleAnimationState = new AnimationState();
  public final AnimationState walkAnimationState = new AnimationState();
  public final AnimationState runAnimationState = new AnimationState();
  public final AnimationState attackAnimationState = new AnimationState();
  private int idleAnimationTimeout = 0;

  public BrutishVillager(EntityType<? extends PathfinderMob> entityType, Level level) {
    super(entityType, level);
  }

  public static AttributeSupplier.Builder createAttributes() {
    return LivingEntity.createLivingAttributes()
        .add(Attributes.MAX_HEALTH, 80.0)
        .add(Attributes.MOVEMENT_SPEED, 0.32)
        .add(Attributes.ATTACK_DAMAGE, 10.0)
        .add(Attributes.ATTACK_KNOCKBACK, 1.0)
        .add(Attributes.FOLLOW_RANGE, 20.0)
        .add(Attributes.KNOCKBACK_RESISTANCE, 0.5)
        .add(Attributes.ARMOR, 4.0);
  }

  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(0, new FloatGoal(this));
    this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, true));
    this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9, 32.0F));
    this.goalSelector.addGoal(2, new MoveBackToVillageGoal(this, 0.6, false));
    this.goalSelector.addGoal(4, new GolemRandomStrollInVillageGoal(this, 0.6));
    this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
    this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

    this.targetSelector.addGoal(1, new BruteDefendVillageTargetGoal(this));
    this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (mob) -> mob instanceof Enemy && !(mob instanceof Creeper)));
  }

  @Override
  protected void defineSynchedData() {
    super.defineSynchedData();
    this.entityData.define(DATA_STATE, State.IDLE.name().toLowerCase());
  }

  @Override
  public void addAdditionalSaveData(CompoundTag compound) {
    super.addAdditionalSaveData(compound);
    compound.putString("BruteState", this.entityData.get(DATA_STATE));
  }

  @Override
  public void readAdditionalSaveData(CompoundTag compound) {
    super.readAdditionalSaveData(compound);
    if (compound.contains("BruteState")) {
      this.entityData.set(DATA_STATE, compound.getString("BruteState"));
    }
  }

  @Nullable
  @Override
  public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
    return super.finalizeSpawn(level, difficulty, reason, spawnData, dataTag);
  }

  @Override
  public void tick() {
    super.tick();
    if (this.level().isClientSide()) {
      setupAnimationStates();
    }
  }

  private void setupAnimationStates() {
    if (this.walkAnimation.isMoving()) {
      this.idleAnimationState.stop();
      if (this.isAggressive()) {
        this.walkAnimationState.stop();
        this.runAnimationState.startIfStopped(this.tickCount);
      } else {
        this.runAnimationState.stop();
        this.walkAnimationState.startIfStopped(this.tickCount);
      }
    } else {
      this.walkAnimationState.stop();
      this.runAnimationState.stop();
      if (this.idleAnimationTimeout <= 0) {
        this.idleAnimationTimeout = this.random.nextInt(40) + 80;
        this.idleAnimationState.start(this.tickCount);
      } else {
        --this.idleAnimationTimeout;
      }
    }
  }

  @Override
  public boolean doHurtTarget(Entity target) {
    this.level().broadcastEntityEvent(this, (byte) 4);
    return super.doHurtTarget(target);
  }

  @Override
  public void handleEntityEvent(byte id) {
    if (id == 4) {
      this.attackAnimationState.start(this.tickCount);
    } else {
      super.handleEntityEvent(id);
    }
  }

  public State getDataState() {
    return switch (this.entityData.get(DATA_STATE)) {
      case "walk" -> State.WALK;
      case "run" -> State.RUN;
      case "attack" -> State.ATTACK;
      default -> State.IDLE;
    };
  }

  public BrutishVillager transitionTo(State state) {
    this.entityData.set(DATA_STATE, state.name().toLowerCase());
    return this;
  }

  public enum State {
    IDLE, WALK, RUN, ATTACK
  }
}
