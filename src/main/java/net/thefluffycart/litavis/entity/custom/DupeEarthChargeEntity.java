package net.thefluffycart.litavis.entity.custom;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.AbstractWindChargeEntity;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.explosion.AdvancedExplosionBehavior;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Function;

public class DupeEarthChargeEntity extends ExplosiveProjectileEntity implements FlyingItemEntity {
    private static final float EXPLOSION_POWER = 1.2F;
    private int deflectCooldown = 5;
    public static final ExplosionBehavior EXPLOSION_BEHAVIOR;
    public static final double field_52224 = (double)0.25F;

    public DupeEarthChargeEntity(EntityType<? extends DupeEarthChargeEntity> entityType, World world) {
        super(entityType, world);
        this.accelerationPower = (double)0.0F;
    }

    public DupeEarthChargeEntity(EntityType<? extends DupeEarthChargeEntity> type, World world, Entity owner, double x, double y, double z) {
        super(type, x, y, z, world);
        this.setOwner(owner);
        this.accelerationPower = (double)0.0F;
    }

    DupeEarthChargeEntity(EntityType<? extends DupeEarthChargeEntity> entityType, double d, double e, double f, Vec3d vec3d, World world) {
        super(entityType, d, e, f, vec3d, world);
        this.accelerationPower = (double)0.0F;
    }

    protected Box calculateBoundingBox() {
        float f = this.getType().getDimensions().width() / 2.0F;
        float g = this.getType().getDimensions().height();
        float h = 0.15F;
        return new Box(this.getPos().x - (double)f, this.getPos().y - (double)0.15F, this.getPos().z - (double)f, this.getPos().x + (double)f, this.getPos().y - (double)0.15F + (double)g, this.getPos().z + (double)f);
    }

    public boolean collidesWith(Entity other) {
        return other instanceof AbstractWindChargeEntity ? false : super.collidesWith(other);
    }

    protected boolean canHit(Entity entity) {
        if (entity instanceof AbstractWindChargeEntity) {
            return false;
        } else {
            return entity.getType() == EntityType.END_CRYSTAL ? false : super.canHit(entity);
        }
    }

    public boolean deflect(ProjectileDeflection deflection, @Nullable Entity deflector, @Nullable Entity owner, boolean fromAttack) {
        return this.deflectCooldown > 0 ? false : super.deflect(deflection, deflector, owner, fromAttack);
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!this.getWorld().isClient) {
            Entity damageSource = this.getOwner();
            LivingEntity var10000;
            if (damageSource instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity)damageSource;
                var10000 = livingEntity;
            } else {
                var10000 = null;
            }

            LivingEntity livingEntity2 = var10000;
            Entity entity = entityHitResult.getEntity();
            if (livingEntity2 != null) {
                livingEntity2.onAttacking(entity);
            }

            DamageSource dmgSource = this.getDamageSources().windCharge(this, livingEntity2);
            if (entity.damage(dmgSource, 1.0F) && entity instanceof LivingEntity) {
                LivingEntity livingEntity3 = (LivingEntity)entity;
                EnchantmentHelper.onTargetDamaged((ServerWorld)this.getWorld(), livingEntity3, dmgSource);
            }

            this.createExplosion(this.getPos());
        }
    }

    public void addVelocity(double deltaX, double deltaY, double deltaZ) {
    }

    protected void createExplosion(Vec3d pos) {
        this.getWorld().createExplosion(this, (DamageSource)null, EXPLOSION_BEHAVIOR, pos.getX(), pos.getY(), pos.getZ(), 1.2F, false, World.ExplosionSourceType.TRIGGER, ParticleTypes.GUST_EMITTER_SMALL, ParticleTypes.GUST_EMITTER_LARGE, SoundEvents.ENTITY_WIND_CHARGE_WIND_BURST);
    }

    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if (!this.getWorld().isClient) {
            Vec3i vec3i = blockHitResult.getSide().getVector();
            Vec3d vec3d = Vec3d.of(vec3i).multiply((double)0.25F, (double)0.25F, (double)0.25F);
            Vec3d vec3d2 = blockHitResult.getPos().add(vec3d);
            this.createExplosion(vec3d2);
            this.discard();
        }

    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.discard();
        }

    }

    protected boolean isBurning() {
        return false;
    }

    public ItemStack getStack() {
        return ItemStack.EMPTY;
    }

    protected float getDrag() {
        return 1.0F;
    }

    protected float getDragInWater() {
        return this.getDrag();
    }

    @Nullable
    protected ParticleEffect getParticleType() {
        return null;
    }

    public void tick() {
        if (!this.getWorld().isClient && this.getBlockY() > this.getWorld().getTopY() + 30) {
            this.createExplosion(this.getPos());
            this.discard();
        } else {
            super.tick();
        }

    }

    public boolean damage(DamageSource source, float amount) {
        return false;
    }

    static {
        EXPLOSION_BEHAVIOR = new AdvancedExplosionBehavior(true, false, Optional.of(1.22F), Registries.BLOCK.getEntryList(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity()));
    }
}