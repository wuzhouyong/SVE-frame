package com.ruoyi.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class PasswordUtils {

    private static final String[] choices = new String[] {"1234567890", "abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZ"};

    public static String GeneratePassword() {
        List<String> result = new ArrayList<>();
        IntStream.range(0, 10).forEach(i -> {
            Random random = new Random();
            String choice = choices[i % 3];
            int index = random.nextInt(choice.length());
            result.add(String.valueOf(choice.charAt(index)));
            Collections.shuffle(result);
        });
        return StringUtils.join(result, "");
    }

}