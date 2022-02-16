package me.voidxwalker.anchiale.mixin;

import net.minecraft.server.PlayerWorldManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Mixin(targets = "net/minecraft/server/PlayerWorldManager$PlayerInstance")
public class PlayerInstanceMixin {
    @Mutable @Shadow @Final private List<ServerPlayerEntity> field_8885;

    @Inject(method = "<init>",at=@At("TAIL"))
    private void modifyField_8885(PlayerWorldManager i, int j, int playerWorldManager, CallbackInfo ci){
        field_8885 = new CopyOnWriteArrayList<>();
    }
}
