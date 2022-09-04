package me.voidxwalker.anchiale.mixin;

import com.google.common.util.concurrent.Futures;
import me.voidxwalker.anchiale.Anchiale;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.File;
import java.net.Proxy;
import java.util.concurrent.Future;

@Mixin(IntegratedServer.class)
public abstract class IntegratedServerMixin<V> extends MinecraftServer {

    public IntegratedServerMixin(Proxy proxy, File file) {
        super(proxy, file);
    }

    @Redirect(method = "stopRunning", at = @At(value = "INVOKE", target = "Lcom/google/common/util/concurrent/Futures;getUnchecked(Ljava/util/concurrent/Future;)Ljava/lang/Object;"))
    public V e(Future<V> e){
        if (Anchiale.fastReset) {
            Anchiale.LOGGER.info("Exiting world without waiting for server tasks to finish.");
            return null;
        } else {
            Anchiale.LOGGER.info("Exiting world normally.");
            return Futures.getUnchecked(e);
        }
    }
}