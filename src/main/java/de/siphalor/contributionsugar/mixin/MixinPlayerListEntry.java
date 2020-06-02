package de.siphalor.contributionsugar.mixin;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.datafixers.util.Pair;
import de.siphalor.contributionsugar.ContributionSugar;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Mixin(PlayerListEntry.class)
public class MixinPlayerListEntry {
	@Shadow @Final private GameProfile profile;

	@Shadow @Final private Map<MinecraftProfileTexture.Type, Identifier> textures;

	@Inject(method = "loadTextures", at = @At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lnet/minecraft/client/texture/PlayerSkinProvider;loadSkin(Lcom/mojang/authlib/GameProfile;Lnet/minecraft/client/texture/PlayerSkinProvider$SkinTextureAvailableCallback;Z)V"))
	public void onTexturesLoaded(CallbackInfo callbackInfo) {
		String name = profile.getName();
		if (ContributionSugar.CONTRIBUTORS.containsKey(name)) {
			Pair<List<String>, List<String>> tex = ContributionSugar.CONTRIBUTORS.get(profile.getName());
			int capes = tex.getFirst().size();
			int elytras = tex.getSecond().size();
			if (capes > 0) {
				textures.put(MinecraftProfileTexture.Type.CAPE, new Identifier(tex.getFirst().get((int) (ContributionSugar.getSeed() % capes))));
			}
			if (elytras > 0) {
				textures.put(MinecraftProfileTexture.Type.ELYTRA, new Identifier(tex.getSecond().get((int) (ContributionSugar.getSeed() % elytras))));
			}
		}
	}
}
