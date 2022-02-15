package me.voidxwalker.anchiale.mixin;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.apache.commons.lang3.Validate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.File;
import java.net.Proxy;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Mixin(IntegratedServer.class)
public abstract class IntegratedServerMixin<V> extends MinecraftServer {

    public IntegratedServerMixin(Proxy proxy, File file) {
        super(proxy, file);
    }

    @Redirect(method = "stopRunning", at = @At(value = "INVOKE", target = "Lcom/google/common/util/concurrent/Futures;getUnchecked(Ljava/util/concurrent/Future;)Ljava/lang/Object;"))
    public V e(Future<V> e){
        List<ServerPlayerEntity> list = Lists.newArrayList((Iterable)this.getPlayerManager().getPlayers());
        Iterator iterator = list.iterator();

        while(iterator.hasNext()) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)iterator.next();
            this.getPlayerManager().remove(serverPlayerEntity);
        }
        return null;
    }


}