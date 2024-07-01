package com.example.halCinema.utill;

import java.util.Locale;

import org.springframework.context.MessageSource;

public class AppUtill {

    public static String getMessage(MessageSource messageSource, String key, Locale locale, Object... params) {
        return messageSource.getMessage(key, params, locale);
    }

    // オーバーロードしてデフォルトをLocale.JAPANにするメソッド
    public static String getMessage(MessageSource messageSource, String key, Object... params) {
        return messageSource.getMessage(key, params, Locale.JAPAN);
    }
}
