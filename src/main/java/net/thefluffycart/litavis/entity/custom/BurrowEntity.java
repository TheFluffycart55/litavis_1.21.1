package net.thefluffycart.litavis.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.thefluffycart.litavis.entity.variant.BurrowVariant;
import org.jetbrains.annotations.Nullable;

public class BurrowEntity extends HostileEntity {
    private static final String JOHNNY_KEY = "Johnny";
    boolean johnny;
    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
            DataTracker.registerData(BurrowEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public static final AnimationState diggingAnimationState = new AnimationState();
    private static final TrackedData<Byte> BURROW_FLAGS = DataTracker.registerData(BurrowEntity.class, TrackedDataHandlerRegistry.BYTE);



    public BurrowEntity(EntityType<? extends BurrowEntity> entityType, World world) {
        super((EntityType<? extends HostileEntity>)entityType, world);
        this.experiencePoints = 15;
    }

    //IDLE ANIMATION
    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(this.getWorld().isClient()) {
            this.setupAnimationStates();
        }
    }

    //LOBOTOMIZED THE BURROW FOR NOW
    protected void initGoals() {
        this.goalSelector.add(2, new FleeEntityGoal<PlayerEntity>(this, PlayerEntity.class, 8.0f, 0.6, 1.0));
        this.goalSelector.add(2, new GoToWalkTargetGoal(this, 1.0));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1, 0.8f));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(5, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this, new Class[0]).setGroupRevenge(new Class[0]));
        this.targetSelector.add(2, new ActiveTargetGoal<PlayerEntity>((MobEntity)this, PlayerEntity.class, true).setMaxTimeWithoutVisibility(300));
    }

    public static DefaultAttributeContainer.Builder createburrowAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 32f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20f);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(DATA_ID_TYPE_VARIANT, 0);
        super.initDataTracker(builder);
        builder.add(BURROW_FLAGS, (byte)0);
    }

    @Override
    public boolean hurtByWater() {
        return true;
    }

    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    //WILL IMPLEMENT CUSTOM SOUNDS IN NEXT PATCH, NEED TO RECORD THEM FIRST
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_BREEZE_IDLE_GROUND;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_BREEZE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BREEZE_DEATH;
    }

    //BURROW VARIANTS

    private int getTypeVariant() {
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }

    public BurrowVariant getVariant() {
        return BurrowVariant.byId(this.getTypeVariant() & 255);
    }

    public void setVariant(BurrowVariant variant) {
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        BurrowVariant variant = getWeightedRandomVariant();
        setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, nbt.getInt("Variant"));
    }

    private BurrowVariant getWeightedRandomVariant() {
        float[] weights = {75, 20, 20, 20, 20, 20, 20, 20, 20, 10, 1}; // Example: Adjust weights as needed
        BurrowVariant[] variants = BurrowVariant.values();
        int totalWeight = 0;

        for (float weight : weights) {
            totalWeight += weight;
        }
        int randomWeight = this.random.nextInt(totalWeight);

        int cumulativeWeight = 0;
        for (int i = 0; i < variants.length; i++) {
            cumulativeWeight += (int) weights[i];
            if (randomWeight < cumulativeWeight) {
                return variants[i];
            }
        }
        return variants[0];
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getTypeVariant());
    }
}