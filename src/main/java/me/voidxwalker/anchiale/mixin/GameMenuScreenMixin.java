package me.voidxwalker.anchiale.mixin;

import me.voidxwalker.anchiale.Anchiale;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin extends Screen {

    @Inject(method = "init", at = @At("TAIL"))
    private void anchiale_addMenuQuitWorldButton(CallbackInfo ci) {
        if (this.client.isInSingleplayer()) {
            this.buttons.add(new ButtonWidget(1507, this.width - 106, this.height - 24, 102, 20, I18n.translate("menu.quitWorld")));
        }
    }

    @Inject(method = "buttonClicked", at = @At("HEAD"))
    private void anchiale_onMenuQuitWorldClicked(ButtonWidget button, CallbackInfo ci) {
        if (button.id == 1507) {
            Anchiale.fastReset = true;
            button.id = 1;
        }
    }

    @Inject(method = "buttonClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;<init>()V"))
    private void anchiale_fastResetFalse(CallbackInfo ci) {
        Anchiale.fastReset = false;
    }
}