package br.com.dio;

import br.com.dio.ui.custom.screen.MainScreen;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class UIMain {

    public static void main(String[] args) {

        Map<String, String> gameConfig = null;

        if (args != null && args.length > 0) {
            gameConfig = Stream.of(args)
                    .collect(toMap(k -> k.split(";")[0], v -> v.split(";")[1]));
        }

        var mainScreen = new MainScreen(gameConfig);
        mainScreen.buildMainScreen();
    }
}
