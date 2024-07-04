package com.example.halCinema.service;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.halCinema.entity.UserInfo;
import com.example.halCinema.repository.UserInfoRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserInfoRepository userRepository; // UserRepositoryを注入

    @Override
    public UserDetails loadUserByUsername(String memberMailaddress) throws UsernameNotFoundException {
        // UserInfoRepository の findByMemberMailaddress メソッドを使ってユーザーを検索
        UserInfo user = userRepository.findByMemberMailaddress(memberMailaddress)
                .orElseThrow(() -> {
                    // ユーザーが見つからない場合はエラーログを出力し、例外をスロー
                    logger.error("Authentication failed: User not found with username: " + memberMailaddress);
                    return new UsernameNotFoundException("User not found with username: " + memberMailaddress);
                });

        // ユーザーが見つかった場合は成功メッセージをコンソールに出力
        logger.info("Authentication succeeded for user: " + memberMailaddress);

        // ユーザー情報を返す
        return new org.springframework.security.core.userdetails.User(
                user.getMemberMailaddress(), // ユーザーのメールアドレスを取得
                user.getMemberPassword(), // ユーザーのパスワードを取得
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")) // ユーザーに付与する権限を設定
        );
    }
}
