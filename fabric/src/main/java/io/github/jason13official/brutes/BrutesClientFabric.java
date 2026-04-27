package io.github.jason13official.brutes;

import net.fabricmc.api.ClientModInitializer;

public class BrutesClientFabric implements ClientModInitializer {

  @Override
  public void onInitializeClient() {

    BrutesClient.init();
  }
}
