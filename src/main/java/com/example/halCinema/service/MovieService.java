package com.example.halCinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.halCinema.model.Movie;
import com.example.halCinema.repository.MovieRepository;

@Service
@Transactional
public class MovieService {
    
	@Autowired
    MovieRepository MovieRepository;

	//  情報公開中の映画情報を取得
    public List<Object[]> findMovie() {
        return MovieRepository.findMovie();
    }
    

	//  情報公開中の公開前映画情報を取得
    public List<Object[]> findUpcomingMovie() {
        return MovieRepository.findUpcomingMovie();
    }
    

	//  情報公開中の映画タイトルを取得
    public List<Object[]> findMovieTitle() {
        return MovieRepository.findMovieTitle();
    }
    
    
    //  上映スケジュール追加時に必要
    public Movie findMovieById(Integer movieId) {
        return MovieRepository.findById(movieId).orElse(null);
    }
    


	// 三次開発用 //////////////////////////////////////////////////////////////////////
    
    // 全映画情報を取得
    public List<Object[]> getAllMovies() {
        return MovieRepository.findAllMovies();
    }
    
    // 映画情報を削除
    public void deleteMovieById(Integer movieId) {
        MovieRepository.deleteMovieById(movieId);
    }
    
    // 映画情報を追加
    // 映画情報を編集
    
}