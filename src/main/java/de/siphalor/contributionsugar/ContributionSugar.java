package de.siphalor.contributionsugar;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.CustomValue;
import net.fabricmc.loader.api.metadata.ModMetadata;

import java.util.*;
import java.util.stream.Stream;

public class ContributionSugar implements ClientModInitializer {

	public static final String MOD_ID = "contributionsugar";
	public static final String MOD_NAME = "Contribution Sugar";

	public static final Map<String, Pair<List<String>, List<String>>> CONTRIBUTORS = new HashMap<>();

	@Override
	public void onInitializeClient() {
		FabricLoader.getInstance().getAllMods().forEach(modContainer -> {
			ModMetadata metadata = modContainer.getMetadata();
			if (metadata.containsCustomValue(MOD_ID)) {
				CustomValue value = metadata.getCustomValue(MOD_ID);
				if (value.getType() == CustomValue.CvType.OBJECT) {
					CustomValue.CvObject object = value.getAsObject();
					Stream.concat(metadata.getAuthors().stream(), metadata.getContributors().stream()).forEach(person -> {
						Pair<List<String>, List<String>> tex = CONTRIBUTORS.computeIfAbsent(
							person.getContact().get("minecraft").orElseGet(person::getName),
							s -> Pair.of(new LinkedList<>(), new LinkedList<>())
						);
						if (object.containsKey("cape")) {
							tex.getFirst().add(object.get("cape").getAsString());
						}
						if (object.containsKey("elytra")) {
							tex.getSecond().add(object.get("elytra").getAsString());
						}
					});
				}
			}
		});
		System.out.println("[" + MOD_NAME + "] Found " + CONTRIBUTORS.size() + " custom caped contributors");
	}

	public static long getSeed() {
		return new Date().getTime() % 86_400_000L;
	}
}
