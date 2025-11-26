package net.thefluffycart.litavis.entity.ai.tasks;

import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.WalkTarget;
import net.minecraft.entity.ai.brain.task.BreezeMovementUtil;
import net.minecraft.entity.ai.brain.task.MultiTickTask;
import net.minecraft.entity.mob.BreezeEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.thefluffycart.litavis.entity.custom.BurrowEntity;

import java.util.Map;

public class BurrowDigToTargetTask extends MultiTickTask<BurrowEntity> {
    public BurrowDigToTargetTask() {
        super(Map.of(MemoryModuleType.ATTACK_TARGET, MemoryModuleState.VALUE_PRESENT, MemoryModuleType.WALK_TARGET, MemoryModuleState.VALUE_ABSENT, MemoryModuleType.BREEZE_JUMP_COOLDOWN, MemoryModuleState.VALUE_ABSENT, MemoryModuleType.BREEZE_SHOOT, MemoryModuleState.VALUE_ABSENT));
    }

    protected boolean shouldRun(ServerWorld serverWorld, BurrowEntity burrowEntity) {
        return burrowEntity.isOnGround() && !burrowEntity.isTouchingWater() && burrowEntity.getPose() == EntityPose.STANDING;
    }

//    protected void run(ServerWorld serverWorld, BurrowEntity burrowEntity, long l) {
//        LivingEntity livingEntity = (LivingEntity)burrowEntity.getBrain().getOptionalRegisteredMemory(MemoryModuleType.ATTACK_TARGET).orElse((LivingEntity) null);
//        if (livingEntity != null) {
//            boolean bl = burrowEntity.isWithinShortRange(livingEntity.getPos());
//            Vec3d vec3d = null;
//            if (bl) {
//                Vec3d vec3d2 = NoPenaltyTargeting.findFrom(burrowEntity, 5, 5, livingEntity.getPos());
//                if (vec3d2 != null && BreezeMovementUtil.canMoveTo(burrowEntity, vec3d2) && livingEntity.squaredDistanceTo(vec3d2.x, vec3d2.y, vec3d2.z) > livingEntity.squaredDistanceTo(burrowEntity)) {
//                    vec3d = vec3d2;
//                }
//            }
//
//            if (vec3d == null) {
//                vec3d = burrowEntity.getRandom().nextBoolean() ? BreezeMovementUtil.getRandomPosBehindTarget(livingEntity, burrowEntity.getRandom()) : getRandomPosInMediumRange(burrowEntity, livingEntity);
//            }
//
//            burrowEntity.getBrain().remember(MemoryModuleType.WALK_TARGET, new WalkTarget(BlockPos.ofFloored(vec3d), 0.6F, 1));
//        }
//    }

    private static Vec3d getRandomPosInMediumRange(BreezeEntity breeze, LivingEntity target) {
        Vec3d vec3d = target.getPos().subtract(breeze.getPos());
        double d = vec3d.length() - MathHelper.lerp(breeze.getRandom().nextDouble(), (double)8.0F, (double)4.0F);
        Vec3d vec3d2 = vec3d.normalize().multiply(d, d, d);
        return breeze.getPos().add(vec3d2);
    }
}
