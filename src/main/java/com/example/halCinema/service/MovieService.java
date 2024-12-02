package com.example.halCinema.service;

import java.time.LocalDate;
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

	//  タイトルから上映時間を取得
	public List<Object[]> findRunningTime(String movieTitle) {
		return MovieRepository.findRunningTime(movieTitle);
	}

	//  タイトルから映画IDを取得
	public List<Object[]> findMovieId(String movieTitle) {
		return MovieRepository.findMovieId(movieTitle);
	}

	// 三次開発用 //////////////////////////////////////////////////////////////////////

	//	映画情報を全件取得する
	public List<Movie> findAllMovies() {
		return MovieRepository.findAll(); // カスタムクエリを呼び出し
	}


	// 映画情報を削除する
	public void deleteMovieById(Integer movieId) {
		if (movieId == null) {
			throw new IllegalArgumentException("movieId must not be null");
		}
		if (!MovieRepository.existsById(movieId)) {
			throw new IllegalArgumentException("No movie found with id: " + movieId);
		}

		MovieRepository.deleteById(movieId);
	}


    // 映画情報を編集
    
    
//    @Autowired
//    private MovieRepository movieRepository;
//
//    public void deleteMovieById(Integer id) {  // 型をIntegerに変更
//        movieRepository.deleteById(id);
//    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    //  指定条件の上映スケジュール取得
    public List<Movie> findSelectAllMovie(String searchTitle, Integer seachDate, Boolean searchStatus) {
        return MovieRepository.findSelectAllMovie(searchTitle, seachDate, searchStatus);
    }
    //  指定条件の上映スケジュール取得
    public List<Movie> findSelectTitleMovie(String searchTitle) {
        return MovieRepository.findSelectTitleMovie(searchTitle);
    }
    //  指定条件の上映スケジュール取得
    public List<Movie> findSelectDateMovie(Integer seachDate) {
        return MovieRepository.findSelectDateMovie(seachDate);
    }
    //  指定条件の上映スケジュール取得
    public List<Movie> findSelectStatusMovie(Boolean searchStatus) {
        return MovieRepository.findSelectStatusMovie(searchStatus);
    }
    //  指定条件の上映スケジュール取得
    public List<Movie> findSelectStatusAndDateMovie(Integer seachDate, Boolean searchStatus) {
        return MovieRepository.findSelectStatusAndDateMovie(seachDate, searchStatus);
    }
    //  指定条件の上映スケジュール取得
    public List<Movie> findSelectStatusAndTitleMovie(String searchTitle, Boolean searchStatus) {
        return MovieRepository.findSelectStatusAndTitleMovie(searchTitle, searchStatus);
    }
    //  指定条件の上映スケジュール取得
    public List<Movie> findSelectDateAndTitleMovie(String searchTitle, Integer seachDate) {
        return MovieRepository.findSelectDateAndTitleMovie(searchTitle, seachDate);
    }




	// 映画情報を追加
	//	public void saveMovie(Movie movie) {
	//		MovieRepository.save(movie);
	//	}

	public Movie addMovie(String MovieTitle,LocalDate ReleaseDay, String MovieDetails, Integer RunningTime,Boolean ReleaseStatus,String Img, String Url, String Staff) {
		Movie movie = new Movie();
		movie.setMovieTitle(MovieTitle);
		movie.setImg(Img);
		movie.setMovieDetails(MovieDetails);
		movie.setReleaseDay(ReleaseDay);
		movie.setRunningTime(RunningTime);
		movie.setUrl(Url);
		movie.setStaff(Staff);
		movie.setReleaseStatus(ReleaseStatus);
		
		return MovieRepository.save(movie);
	}
	// 映画情報を編集

}
