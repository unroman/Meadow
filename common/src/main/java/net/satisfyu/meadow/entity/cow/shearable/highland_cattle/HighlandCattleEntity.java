package net.satisfyu.meadow.entity.cow.shearable.highland_cattle;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.satisfyu.meadow.entity.cow.shearable.ShearableCowEntity;
import net.satisfyu.meadow.registry.EntityRegistry;
import net.satisfyu.meadow.registry.ObjectRegistry;


public class HighlandCattleEntity extends ShearableCowEntity {
    public HighlandCattleEntity(EntityType<? extends Cow> entityType, Level world) {
        super(entityType, world, ObjectRegistry.HIGHLAND_WOOL.get());
    }

    @Override
    public Cow getBreedOffspring(ServerLevel serverWorld, AgeableMob passiveEntity) {
        return EntityRegistry.HIGHLAND_CATTLE.get().create(serverWorld);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        boolean bl;
        if (((bl = itemStack.is(Items.BUCKET)) || itemStack.is(ObjectRegistry.WOODEN_BUCKET.get())) && !this.isBaby()) {
            player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            ItemStack itemStack2 = ItemUtils.createFilledResult(itemStack, player, ObjectRegistry.WOODEN_MILK_BUCKET.get().getDefaultInstance());
            player.setItemInHand(hand, itemStack2);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            return super.mobInteract(player, hand);
        }
    }
}


