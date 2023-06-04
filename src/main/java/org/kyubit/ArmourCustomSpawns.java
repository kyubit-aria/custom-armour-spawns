package org.kyubit;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArmourCustomSpawns implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("mobarmourcustom");
    public static ServerWorld theWorld;
    ServerWorld theNether;




	@Override
	public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            LOGGER.info("Server started!");
            theWorld = server.getWorld(World.OVERWORLD);

            if(theWorld == null)
            {
                LOGGER.info("World is null");
            }
            else
            {
                LOGGER.info("World is not null");
            }
        });
	}
}