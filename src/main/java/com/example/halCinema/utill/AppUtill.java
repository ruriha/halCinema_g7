package com.example.halCinema.utill;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AppUtill {

    private final MessageSource messageSource;

    // コンストラクタインジェクション
    public AppUtill(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public static String getMessage(MessageSource messageSource, String key, Locale locale, Object... params) {
        return messageSource.getMessage(key, params, locale);
    }

    // オーバーロードしてデフォルトをLocale.JAPANにするメソッド
    public static String getMessage(MessageSource messageSource, String key, Object... params) {
        return messageSource.getMessage(key, params, Locale.JAPAN);
    }

    public String getMessage(String code) {
        try {
            return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            // 例外処理: デフォルトメッセージを返すか、ログを出力する
            return "メッセージが見つかりません: " + code;
        }
    }
}
