package ru.nicholas.bukkit.utils.items.deserialize;

import org.bukkit.potion.PotionEffect;
import ru.nicholas.core.VersionAdapter;
import ru.nicholas.core.builder.PotionEffectBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Класс для десериализации текста в зелья
 *
 * @author Nicholas Alexandrov 03.06.2023
 */
public class PotionDeserialize implements StringDeserialize<List<?>> {

    @Override
    public List<PotionEffect> deserialize(String str) {

        List<PotionEffect> potionList = new ArrayList<>();

        String[] array = str.split(":");

        if (Arrays.asList(array).isEmpty()) return potionList;

        String output = IntStream.range(1, array.length)
                .mapToObj(i -> array[i] + ((i + 1) % 3 == 0 ? " " : ":"))
                .collect(Collectors.joining());

        String[] effects = output.split(" ");

        for (String effect : effects) {

            PotionEffectBuilder potionEffectBuilder = VersionAdapter.getPotionEffectBuilder();

            String[] arrayEffect = effect.split(":");

             potionList.add(potionEffectBuilder
                    .setType(arrayEffect[0].toUpperCase())
                    .setDuration(Integer.parseInt(arrayEffect[1]) * 20)
                    .setAmplifier(Integer.parseInt(arrayEffect[2])).build());

        }

        return potionList;
    }
}
