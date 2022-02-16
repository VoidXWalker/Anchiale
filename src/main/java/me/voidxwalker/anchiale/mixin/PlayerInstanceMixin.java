package me.voidxwalker.anchiale.mixin;

import net.minecraft.server.PlayerWorldManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Mixin(targets = "net/minecraft/server/PlayerWorldManager$PlayerInstance")
public class PlayerInstanceMixin {
    @Mutable @Shadow @Final private List<ServerPlayerEntity> field_8885;

    @Redirect(method = "<init>",at=@At(value="FIELD",target="Lnet/minecraft/server/PlayerWorldManager$PlayerInstance;field_8885:Ljava/util/List;",opcode = Opcodes.PUTFIELD))
    private void modifyField_8885(PlayerWorldManager.PlayerInstance instance, List<ServerPlayerEntity> value){
        this.field_8885 = new CopyOnWriteArrayList<>();
    }
}
