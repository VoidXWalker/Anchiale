package me.voidxwalker.anchiale.mixin;

import me.voidxwalker.anchiale.Anchiale;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin extends Screen {

    @Shadow protected abstract void buttonClicked(ButtonWidget button);

    @Inject(method = "init", at = @At("TAIL"))
    private void addMenuQuitWorldButton(CallbackInfo ci) {
        String menuQuitWorld = I18n.translate("menu.quitWorld");
        int width = this.textRenderer.getStringWidth(menuQuitWorld) + 30;
        this.buttons.add(new ButtonWidget(1507, this.width - width - 5, this.height - 25, width, 20, menuQuitWorld));
    }

    @Inject(method = "buttonClicked", at = @At("HEAD"), cancellable = true)
    private void onMenuQuitWorldClicked(ButtonWidget button, CallbackInfo ci) {
        if (button.id == 1507) {
            Anchiale.fastReset = true;
            this.buttonClicked(this.buttons.get(0));
            Anchiale.fastReset = false;
            ci.cancel();
        }
    }
}