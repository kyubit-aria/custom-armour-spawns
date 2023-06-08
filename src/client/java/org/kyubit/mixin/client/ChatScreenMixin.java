package org.kyubit.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.kyubit.entity.Mob;
import org.kyubit.entity.util.DistanceUtil;
import org.kyubit.mob.Armourer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChatScreen.class)
public class ChatScreenMixin {
    @Shadow
    protected TextFieldWidget chatField;

    @Inject(at = @At("HEAD"),
            method = "sendMessage(Ljava/lang/String;Z)Z",
            cancellable = true)
    public void onSendMessage(String message, boolean addToHistory,
                              CallbackInfoReturnable<Boolean> cir) {
        if ((message = this.normalize(message)).isEmpty()) {
            return;
        }

        if (addToHistory) {
            MinecraftClient.getInstance().inGameHud.getChatHud().addToMessageHistory(message);
        }

        if( MinecraftClient.getInstance().player == null) {
            cir.setReturnValue(true);
            return;
        }
        // If the message starts with #, it is a command to be processed
        if (message.startsWith("#")) {
            cir.setReturnValue(true);

            Armourer.equipMobWithArmour(DistanceUtil.findClosestEntity(Mob.getHostileMobs()));
            return;
        }

        if (message.startsWith("/")) {
            MinecraftClient.getInstance().player.networkHandler.sendChatCommand(message.substring(1));
        } else {
            MinecraftClient.getInstance().player.networkHandler.sendChatMessage(message);
        }
        cir.setReturnValue(true);
    }

    @Shadow
    public String normalize(String chatText)
    {
        return null;
    }
}