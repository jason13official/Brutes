package io.github.jason13official.brutes.impl.common.registry;

import io.github.jason13official.brutes.Brutes;
import io.github.jason13official.brutes.impl.common.registry.entity.BrutishVillager;
import java.util.function.BiConsumer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {

  public static EntityType<BrutishVillager> BRUTISH_VILLAGER;

  public static void register(BiConsumer<EntityType<?>, ResourceLocation> consumer) {

    BRUTISH_VILLAGER = EntityType.Builder.<BrutishVillager>of(BrutishVillager::new, MobCategory.MONSTER).sized(1.4F, 2.3F).build(Brutes.identifier("brutish_villager").toString());
    consumer.accept(BRUTISH_VILLAGER, Brutes.identifier("brutish_villager"));
  }
}
