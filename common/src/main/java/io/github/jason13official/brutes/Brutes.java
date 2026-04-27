package io.github.jason13official.brutes;

import net.minecraft.resources.ResourceLocation;


public class Brutes {

  public static void init() {
  }

  public static ResourceLocation identifier(final String path) {
    return new ResourceLocation(Constants.MOD_ID, path);
  }
}