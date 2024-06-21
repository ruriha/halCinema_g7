package com.example.halCinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	// ルーティング
	  @RequestMapping("/")
	  public String index(){
		// index.htmlを利用
	    return "index"; 
	  }	
	  
	  @RequestMapping("/toppage")
	  public String toppage(){
	    return "toppage"; 
	  }	

}
