package com.example.halCinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.halCinema.repository.MovieRepositoty;

@Service
@Transactional
public class MovieService {
    
	@Autowired
    MovieRepositoty MovieRepositoty;

	//  情報公開中の映画タイトルを取得
//    public List<Object[]> findMovieTitle() {
//        return MovieRepositoty.findMovieTitle();
//    }

}
