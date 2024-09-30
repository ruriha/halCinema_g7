package com.example.halCinema.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.halCinema.model.Member;
import com.example.halCinema.model.News;
import com.example.halCinema.model.ScreeningSchedule;
import com.example.halCinema.service.EmailService;
import com.example.halCinema.service.MemberService;
import com.example.halCinema.service.MovieService;
import com.example.halCinema.service.NewsService;
import com.example.halCinema.service.ReservationService;
import com.example.halCinema.service.ScreenService;
import com.example.halCinema.service.ScreeningScheduleService;
import com.example.halCinema.service.TimeService;
import com.example.halCinema.service.TimeTableService;
import com.example.halCinema.service.TimeTableService2;
import com.example.halCinema.service.TimeTableService3;
import com.example.halCinema.service.TimeTableService4;
import com.example.halCinema.service.TimeTableService5;
import com.example.halCinema.service.TimeTableService6;
import com.example.halCinema.service.TimeTableService7;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    ScreeningScheduleService ScreeningScheduleService;
    @Autowired
    MemberService MemberService;
    @Autowired
    ReservationService ReservationService;
    @Autowired
    EmailService EmailService;
    @Autowired
    NewsService NewsService;
    @Autowired
    MovieService MovieService;
    @Autowired
    ScreenService ScreenService;
    @Autowired
    TimeService TimeService;
    @Autowired
    TimeTableService TimeTableService;
    @Autowired
    TimeTableService2 TimeTableService2;
    @Autowired
    TimeTableService3 TimeTableService3;
    @Autowired
    TimeTableService4 TimeTableService4;
    @Autowired
    TimeTableService5 TimeTableService5;
    @Autowired
    TimeTableService6 TimeTableService6;
    @Autowired
    TimeTableService7 TimeTableService7;
    @Autowired
    NewsService newsService;
    
    
	
	// index.html
//	  @RequestMapping("/")
//	  public String index(HttpSession session ,Model model){
//	    session.invalidate();
//
//	    //news表示
//        List<Object[]> newsList = NewsService.findNewsStreamingDate();
//        model.addAttribute("newsList", newsList);
//        
//	    return "index"; 
//	  }	
	  
	  
	  //  ログイン（Securityなし仮）
//	  @RequestMapping("/entry")
//	  public String entry(@RequestParam(name = "usermail", required = false) String usermail, @RequestParam(name = "password", required = false) String password, HttpSession session){
//			List<Object[]> users = MemberService.loginEntry(usermail, password);//
//	        if (!users.isEmpty()) {
//	            Object[] usersElement = users.get(0);
//	            Integer userId = (Integer) usersElement[0];
//	            
//	            // userId が null でないか確認
//	            if (userId != null) {
//	                session.setAttribute("userId", userId);
//	                return "redirect:/toppage";
//	            }
//	        }
//	        return "redirect:/";
//	  }
	  
	  
	  //  ログアウト（Securityなし仮）
	  @RequestMapping("/logout")
	  public String logout(HttpSession session){
	      session.invalidate();
	      return "redirect:/login";
	  }
	  
	  
	  @RequestMapping("/toppage")
	  public String toppage(@RequestParam(name = "screenScheduleDate", required = false) String screenScheduleDate, Model model, HttpSession session) {
	    String loggedInUserEmail = (String) session.getAttribute("loggedInUserEmail");
	    model.addAttribute("loggedInUserEmail", loggedInUserEmail);
		  //  カレンダー  ////////
		List<String> dates = new ArrayList<>();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");
        DateTimeFormatter screenScheduleDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 14日分の日付を生成
        for (int i = 0; i < 14; i++) {
            LocalDate date = today.plusDays(i);
            String formattedDate = date.format(formatter);
            String selectScreenScheduleDate = date.format(screenScheduleDateFormatter);
            String dayOfWeek = date.getDayOfWeek().toString().substring(0, 3); 
            String dayInfo = "<a class=\"day\" href=/screenScheduleSelect?screenScheduleDate="+selectScreenScheduleDate+">"+formattedDate + "<span class=\"mini\">(" + dayOfWeek + ")</span></a>";
            dates.add(dayInfo);
        }
        model.addAttribute("dates", dates);
        
        //  上映スケジュール  //////////////////////
        String nowScreenScheduleDate;
        String nowOfWeek;
        LocalDate nowDate;
        if(screenScheduleDate==null) {
        	nowDate = today;
            DateTimeFormatter todayFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            nowScreenScheduleDate = today.format(todayFormatter);
        	nowOfWeek = today.getDayOfWeek().toString().substring(0, 3); 
        }else {
        	LocalDate selectedDate = LocalDate.parse(screenScheduleDate, screenScheduleDateFormatter);
        	nowDate = selectedDate;
            DateTimeFormatter todayFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            nowScreenScheduleDate = selectedDate.format(todayFormatter);
        	nowOfWeek = selectedDate.getDayOfWeek().toString().substring(0, 3); 
        }
        String nowScreeningSchedule = nowScreenScheduleDate + "<span class=\"mini\">("+ nowOfWeek +")</span>";
        model.addAttribute("nowScreeningSchedule", nowScreeningSchedule);
        //  作品一覧&上映時間
		List<String> titleLinkList = new ArrayList<>();
		List<String> screenScheduleList = new ArrayList<>();
		List<Object[]> titleList = ScreeningScheduleService.findSelectScreeningTitle(nowDate);
		String titleInfo = "";
		for(Object[] title: titleList) {
			String titleId = title[0].toString();
			String titleName = (String)title[1];
            titleInfo = titleInfo + "<a href=\"#m"+titleId+"\">"+titleName+"</a>";
		}
        titleLinkList.add(titleInfo);
        model.addAttribute("titleLinkList", titleLinkList);
		for(Object[] title: titleList) {
			String titleId = title[0].toString();
			Integer movieId = Integer.parseInt(titleId);
			String titleName = (String)title[1];
            
            String screenScheduleInfo1 =
            		"<div class=\"s_all\">"
            		+ "          <div id=\"m"+titleId+"\"></div>"
            		+ "          <div class=\"s_head\">"
            		+ "            <p class=\"jp\">"+titleName+"</p>"
            		+ "            <a href=\"showmovie.html\" class=\"s_more\">more&thinsp;⇀</a>"
            		+ "          </div>";

    		List<Object[]> screenList = ScreeningScheduleService.findSelectScreeningScreen(movieId, nowDate);
    		String screenScheduleInfo2 = "";
    		String screenScheduleInfo4 = "";
    		for(Object[] screen:screenList) {
    			String screenId = screen[1].toString();
    			Integer intScreenId = Integer.parseInt(screenId);
    			String runningTime = screen[2].toString();
    			Integer intRunningTime = Integer.parseInt(runningTime);
    			screenScheduleInfo2 = screenScheduleInfo4
    					+"<div class=\"s_rooms\">"
    					+ "            <div class=\"s_room\">"
    					+ "              <p class=\"theater\">THEATER&thinsp;&thinsp;<span class=\"big\">"
    					+ screenId+"</span></p>"
						+ "              <p class=\"xd\">2D</p>"
						+ "              <p class=\"min\">"
						+ runningTime+"min</p>"
						+ "            </div>";
    			
        		List<Object[]> screenDatetimeList = ScreeningScheduleService.findSelectScreeningDatetime(intScreenId, nowDate, movieId);
        		String screenScheduleInfo3 = "";
                LocalDateTime now = LocalDateTime.now();
        		for(Object[] screenDatetime:screenDatetimeList) {
        			LocalDateTime screeningDatetime = (LocalDateTime)screenDatetime[1];
                    DateTimeFormatter screeningDatetimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                    String strScreeningDatetime = screeningDatetime.format(screeningDatetimeFormatter);
                    LocalDateTime endScreeningDatetime = screeningDatetime.plus(intRunningTime, ChronoUnit.MINUTES);
                    String strEndScreeningDatetime = endScreeningDatetime.format(screeningDatetimeFormatter);
                    String sBought;
                    String fBought;
                    String buyStatus;
                    String buyStatusJp;
                    String reserve;
                    if(now.isAfter(screeningDatetime)) {
                    	buyStatus = "cantBuy";
                    	buyStatusJp = "購入不可";
                    	sBought = "s_bought";
                    	fBought = "f_bought";
                    	reserve = "href=#";
                    }else {
                    	buyStatus = "canBuy";         
                    	buyStatusJp = "購入";  
                    	sBought = "";
                    	fBought = "";       
                    	reserve = "href=/reserve?screeningScheduleId="+(Integer)screenDatetime[0];
                    }
                    
        			screenScheduleInfo3 = screenScheduleInfo3
        					+ "<div class=\"s_time\">"
        					+ "              <p class=\"start "+sBought+"\">"
        					+ strScreeningDatetime
        					+ "</p>"
        					+ "              <p class=\"finish "+fBought+"\">~"
        					+ strEndScreeningDatetime
        					+ "</p>"
        					+ "              <a class=\"buyFl1\" "+reserve+">"
        					+ "                <img src=\"../images/"+buyStatus+".png\" alt=\""+buyStatusJp+"\" />"
        					+ "                <div class=\""+buyStatus+"\">"+buyStatusJp+"</div>"
        					+ "              </a>"
        					+ "            </div>";
        		}
        		screenScheduleInfo4 = screenScheduleInfo2 + screenScheduleInfo3 + "</div>";    			
    		}
    		String screenScheduleInfo5 = screenScheduleInfo1 + screenScheduleInfo4 + "</div>";
    		screenScheduleList.add(screenScheduleInfo5);
		}
        model.addAttribute("screenScheduleList", screenScheduleList);
        
		//newsの表示
        List<Object[]> newsList = NewsService.findNewsStreamingDate();
        model.addAttribute("newsList", newsList);
        
	    return "toppage"; 
	  }	
	  
	  
	  
	  // news.html
	  @RequestMapping("/news/{id}")
	  public String news(Model model, HttpSession session ,@PathVariable Integer id){
//			会員情報取得
		Integer memberId = (Integer) session.getAttribute("userId");
		if (memberId != null) {
	        model.addAttribute("topLink", "/toppage");			
	    } else {
	        model.addAttribute("topLink", "/login");		
	    }
//	    return "news"; 

        News news = NewsService.findNewsById(id);
        news.setNewsTitle(addLineBreaks1(news.getNewsTitle(), 23)); // タイトルに改行を追加
        model.addAttribute("news", news);
        return "news";
	  }	

      // タイトルに改行を追加するメソッド
      private String addLineBreaks1(String text, int maxLineLength) {
          if (text == null || text.isEmpty()) {
              return text;
          }
          StringBuilder result = new StringBuilder();
          int length = text.length();
          for (int i = 0; i < length; i++) {
              result.append(text.charAt(i));
              if ((i + 1) % maxLineLength == 0) {
                  result.append("<br/>");
              }
          }
          return result.toString();
      }
	  
	  
	  



      // 上映スケジュールの表示切替(toppage)
	  @GetMapping("/screenScheduleSelect")
	  public String screenScheduleSelect(@RequestParam(name = "screenScheduleDate") String screenScheduleDate, Model model){
		  return "redirect:/toppage?screenScheduleDate="+screenScheduleDate+"#sche_b";
	  }	
	  
	  // 上映スケジュールの表示切替(showmovie)
	  @GetMapping("/screenScheduleSelectInfo")
	  public String screenScheduleSelectInfo(@RequestParam(name = "screenScheduleDate") String screenScheduleDate, Model model){
		  return "redirect:/showmovie?screenScheduleDate="+screenScheduleDate+"#sche_b";
	  }
	  

	  // seat.html
	  @RequestMapping("/reserve")
	  public String reserve(@RequestParam(name = "screeningScheduleId") Integer screeningScheduleId, Model model, HttpSession session){
//		会員情報取得
		Integer memberId = (Integer) session.getAttribute("userId");//  ログイン時のセッションからID取得
		System.out.println("memberid:"+memberId);
		List<Object[]> memberList = MemberService.findReservationMember(memberId);
		model.addAttribute("memberList", memberList);
        model.addAttribute("memberId", memberId);
//      上映スケジュール取得        
		List<Object[]> screeningScheduleList = ScreeningScheduleService.findSelectScreeningSchedule(screeningScheduleId);
		model.addAttribute("screeningScheduleList", screeningScheduleList);
        model.addAttribute("screeningScheduleId", screeningScheduleId);
		Object[] screeningScheduleElement = screeningScheduleList.get(0);
		Integer capacity = (Integer) screeningScheduleElement[2];
		String seatCapacity = "seat";
		Integer seatNumberCapacity = 83;
		if(capacity == 70) {
			seatCapacity = "seat";
			seatNumberCapacity = 83;
		}else if(capacity == 120) {			  
			seatCapacity =  "seat2"; 
			seatNumberCapacity = 140;
		}else {			
			seatCapacity =  "seat3"; 
			seatNumberCapacity = 220;
		}
//      空き座席状況取得
		List<Object[]> seatList = ReservationService.findReservationSeat(screeningScheduleId);
		for (Integer seatNumber = 1; seatNumber <= seatNumberCapacity; seatNumber++) {
		    String reserved = "s" + seatNumber;
		    boolean isReserved = false;
		    for (Object[] seat : seatList) {
		        Integer seatNum = (Integer) seat[0];
		        if (seatNum.equals(seatNumber)) {
		            isReserved = true;
		            break;
		        }
		    }
		    model.addAttribute(reserved, isReserved);
		}
		  
	    return seatCapacity;
	  }	
	  

	  // rsv_cfm.html
	  @RequestMapping("/reserve_conf")
	  public String reserve_conf(@RequestParam(required = false) Integer seatNumber,@RequestParam(required = false) Integer guestSeatNumber,@RequestParam(required = false) Integer screeningScheduleId ,@RequestParam(required = false) Integer memberId,@RequestParam(required = false) String selectedCell1Content,@RequestParam(required = false) String selectedCell2Content,  Model model){
//		上映スケジュール取得
		List<Object[]> screeningScheduleList = ScreeningScheduleService.findSelectScreeningSchedule(screeningScheduleId);
		model.addAttribute("screeningScheduleList", screeningScheduleList);
//		会員情報取得
		List<Object[]> memberList = MemberService.findReservationMember(memberId);
		model.addAttribute("memberList", memberList);
//		予約内容確認
        model.addAttribute("seatNumber", seatNumber);
        model.addAttribute("guestSeatNumber", guestSeatNumber);
        model.addAttribute("selectedCell1Content", selectedCell1Content);
        model.addAttribute("selectedCell2Content", selectedCell2Content);
        model.addAttribute("screeningScheduleId", screeningScheduleId);
        model.addAttribute("memberId", memberId);
	    return "rsv_cfm"; 
	  }	
	  
	  

	  // rsv_comp.html
	  @RequestMapping("/reserve_comp")
	  public String reserve_comp(@RequestParam(required = false) Integer seatNumber,@RequestParam(required = false) Integer guestSeatNumber,@RequestParam(required = false) Integer screeningScheduleId, @RequestParam(required = false) Integer memberId,  Model model){
//		予約
		if(guestSeatNumber == null) {
			guestSeatNumber = 0;
		}
        Member member = MemberService.findMemberById(memberId);
        ScreeningSchedule screeningSchedule = ScreeningScheduleService.findScreeningScheduleById(screeningScheduleId);
        LocalDateTime reservationDatetime = LocalDateTime.now();
        ReservationService.saveReservation(seatNumber, guestSeatNumber, member, screeningSchedule, reservationDatetime);
//	    メールアドレス取得
		List<Object[]> memberMail = MemberService.findMailaddress(memberId);
		Object[] mailElement = memberMail.get(0);
		String mailAddress = (String) mailElement[0];
		model.addAttribute("mailAddress", mailAddress);
		System.out.println(mailAddress);
//		予約ID取得
		List<Object[]> nowReservationId = ReservationService.findReservationId(memberId, reservationDatetime);
		Object[] reservationIdElement = nowReservationId.get(0);
		UUID reservationId = (UUID) reservationIdElement[0];
		String strReservationId = reservationId.toString();
		model.addAttribute("strReservationId", strReservationId);
		System.out.println(strReservationId);
//		上映スケジュール取得
		List<Object[]> screeningScheduleList = ScreeningScheduleService.findSelectScreeningSchedule(screeningScheduleId);
		Object[] screeningScheduleElement = screeningScheduleList.get(0);
		String movieTitle = (String) screeningScheduleElement[0];
		LocalDateTime screeningDatetime = (LocalDateTime) screeningScheduleElement[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String strScreeningDatetime = screeningDatetime.format(formatter);
        Integer screenId = (Integer) screeningScheduleElement[3];
        String screenName = "THEATER" + screenId;
//		会員情報取得
		List<Object[]> memberList = MemberService.findReservationMember(memberId);
		Object[] memberElement = memberList.get(0);
		String memberName = (String) memberElement[0];
		model.addAttribute("memberName", memberName);
		System.out.println(memberName);
		
		
		
//		QRコード生成とメール送信
		String subject = "HALCINEMA | 映画の予約が完了しました";
		EmailService.sendQRCodeEmail(mailAddress, subject, strReservationId, movieTitle, strScreeningDatetime, screenName, memberName);
		
	    return "rsv_comp"; 
	  }	
	  
	  
	  
	  //  アクセスページ
	  @RequestMapping("/access")
	  public String access(Model model, HttpSession session){
		Integer memberId = (Integer) session.getAttribute("userId");
		if (memberId != null) {
	        model.addAttribute("topLink", "/toppage");			
	    } else {
	        model.addAttribute("topLink", "/login");		
	    }
	    return "access"; 
	  }	
	  
	  
	  
	  //  メンバーページ
	  @RequestMapping("/member")
	  public String member(Model model, HttpSession session){
		Integer memberId = (Integer) session.getAttribute("userId");
		if (memberId != null) {
	        model.addAttribute("topLink", "/toppage");			
	    } else {
	        model.addAttribute("topLink", "/login");		
	    }
	    return "member"; 
	  }	
	  
	  
	  
	  //  サービスページ
	  @RequestMapping("/service")
	  public String service(Model model, HttpSession session){
		Integer memberId = (Integer) session.getAttribute("userId");
		if (memberId != null) {
	        model.addAttribute("topLink", "/toppage");			
	    } else {
	        model.addAttribute("topLink", "/login");		
	    }
	    return "service"; 
	  }	
	  
	  
	  
	  //  映画情報ページ
	  @RequestMapping("/showmovie")
	  public String showmovie(@RequestParam(name = "screenScheduleDate", required = false) String screenScheduleDate, Model model, HttpSession session){
		Integer memberId = (Integer) session.getAttribute("userId");
		if (memberId != null) {
	        model.addAttribute("topLink", "/toppage");
	    } else {
	        model.addAttribute("topLink", "/login");		
	    }
		
		//  公開中映画情報  ////////
		List<String> nowShowingList = new ArrayList<>();
		List<Object[]> nowShowingTitleList = MovieService.findMovie();
		Integer nowShowingCount = 1;
		for(Object[] nowShowingTitle: nowShowingTitleList) {
			String titleId = nowShowingTitle[0].toString();
			Integer movieId = Integer.parseInt(titleId);
			String titleName = (String)nowShowingTitle[1];
			String staff = (String)nowShowingTitle[2];
			String movieDetails = (String)nowShowingTitle[3];
			String movieUrl = (String)nowShowingTitle[4];
			String movieImg = (String)nowShowingTitle[5];
			
			String nowShowingCountStr = nowShowingCount.toString();
			System.out.println(nowShowingCountStr);
			String nowShowingInfo1 = 
					"<div class=\"content-item\">"
					+ "<div class=\"unique-item\">"
					+ "            <img src=\"../images/"+ movieImg +"\" alt=\"Image 1\" class=\"content-image\" />"
					+ "            <div class=\"content-details\">"
					+ "			   <h2 class=\"content-title\">"+ titleName +"</h2>"
					+ "			   <p class=\"content-description\">"+ staff +"</p>"
					+ "              <button class=\"content-button"+nowShowingCountStr+"\">+ more show</button>"
					+ "            </div>"
					+ "            </div>"
					+ "            <div class=\"more-detailFl"+nowShowingCountStr+"\">"
					+ "            <h1 class=\"introduce\">INTRODUCTION</h1>"
					+ "			   <p class=\"more-detail\">"+ movieDetails +"</p>"
					+ "			   <a href=\""+ movieUrl +"\" class=\"officialSiteCheck\">オフィシャルサイト &#8362;</a>"
					+ "            <h1 class=\"schedule\">SCHEDULE</h1>"
					+ "            <div class=\"calendar-wrapper\">"
					+ "              <button id=\"scroll-left1\" class=\"scroll-button\">◀</button>"
					+ "              <div class=\"calendar-container\">"
					+ "                <div class=\"calendar\" id=\"calendar\">";
			//  カレンダー  ////////
	        //  上映スケジュール  //////////////////////
	        LocalDate today = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");
	        DateTimeFormatter screenScheduleDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        // 14日分の日付を生成
	        for (int j = 0; j < 14; j++) {
	            LocalDate date = today.plusDays(j);
	            String formattedDate = date.format(formatter);
	            String selectScreenScheduleDate = date.format(screenScheduleDateFormatter);
	            String dayOfWeek = date.getDayOfWeek().toString().substring(0, 3); 
	            String dayInfo = "<a class=\"day\" href=/screenScheduleSelectInfo?screenScheduleDate="+selectScreenScheduleDate+">"+formattedDate + "<span class=\"mini\">(" + dayOfWeek + ")</span></a>";
	            nowShowingInfo1 = nowShowingInfo1 + dayInfo;
	        }
	        String nowScreenScheduleDate;
	        String nowOfWeek;
	        LocalDate nowDate;
	        if(screenScheduleDate==null) {
	        	nowDate = today;
	            DateTimeFormatter todayFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	            nowScreenScheduleDate = today.format(todayFormatter);
	        	nowOfWeek = today.getDayOfWeek().toString().substring(0, 3); 
	        }else {
	        	LocalDate selectedDate = LocalDate.parse(screenScheduleDate, screenScheduleDateFormatter);
	        	nowDate = selectedDate;
	            DateTimeFormatter todayFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	            nowScreenScheduleDate = selectedDate.format(todayFormatter);
	        	nowOfWeek = selectedDate.getDayOfWeek().toString().substring(0, 3); 
	        }
	        String nowShowingInfo3 = nowShowingInfo1
	        		+ "           </div>"
	        		+ "           </div>"
	        		+ "           <button id=\"scroll-right1\" class=\"scroll-button\">▶</button>"
	        		+ "           </div>"
	        		+ "           <div id=\"s_top\">"
	        		+ "             <p id=\"now\">"+nowScreenScheduleDate+"<span class=\"mini\">("+nowOfWeek+")</span></p>"
	        		+ "           </div>"
	        		+ "            <div class=\"s_waku\">"
	        		+ "              <div class=\"s_all\">"
	        		+ "                <div id=\"BLUELOCK\"></div>"
	        		+ "                <div class=\"s_head\">"
	        		+ "                  <p class=\"jp\">"+titleName+"</p>"
	        		+ "                </div>";

    		List<Object[]> screenList = ScreeningScheduleService.findSelectScreeningScreen(movieId, nowDate);
    		String screenScheduleInfo2 = "";
    		String screenScheduleInfo4 = "";
    		for(Object[] screen:screenList) {
    			String screenId = screen[1].toString();
    			Integer intScreenId = Integer.parseInt(screenId);
    			String runningTime = screen[2].toString();
    			Integer intRunningTime = Integer.parseInt(runningTime);
    			screenScheduleInfo2 = screenScheduleInfo4
    					+"<div class=\"s_rooms\">"
    					+ "            <div class=\"s_room\">"
    					+ "              <p class=\"theater\">THEATER&thinsp;&thinsp;<span class=\"big\">"
    					+ screenId+"</span></p>"
						+ "              <p class=\"xd\">2D</p>"
						+ "              <p class=\"min\">"
						+ runningTime+"min</p>"
						+ "            </div>";
    			
        		List<Object[]> screenDatetimeList = ScreeningScheduleService.findSelectScreeningDatetime(intScreenId, nowDate, movieId);
        		String screenScheduleInfo3 = "";
                LocalDateTime now = LocalDateTime.now();
        		for(Object[] screenDatetime:screenDatetimeList) {
        			LocalDateTime screeningDatetime = (LocalDateTime)screenDatetime[1];
                    DateTimeFormatter screeningDatetimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                    String strScreeningDatetime = screeningDatetime.format(screeningDatetimeFormatter);
                    LocalDateTime endScreeningDatetime = screeningDatetime.plus(intRunningTime, ChronoUnit.MINUTES);
                    String strEndScreeningDatetime = endScreeningDatetime.format(screeningDatetimeFormatter);
                    String sBought;
                    String fBought;
                    String buyStatus;
                    String buyStatusJp;
                    String reserve;
            		if (memberId != null) {
                        if(now.isAfter(screeningDatetime)) {
                        	buyStatus = "cantBuy";
                        	buyStatusJp = "購入不可";
                        	sBought = "s_bought";
                        	fBought = "f_bought";
                        	reserve = "href=#";
                        }else {
                        	buyStatus = "canBuy";         
                        	buyStatusJp = "購入";  
                        	sBought = "";
                        	fBought = "";       
                        	reserve = "href=/reserve?screeningScheduleId="+(Integer)screenDatetime[0];
                        }
            	    } else {
                        if(now.isAfter(screeningDatetime)) {
                        	buyStatus = "cantBuy";
                        	buyStatusJp = "購入不可";
                        	sBought = "s_bought";
                        	fBought = "f_bought";
                        	reserve = "href=#";
                        }else {
                        	buyStatus = "canBuy";         
                        	buyStatusJp = "ログイン";  
                        	sBought = "";
                        	fBought = "";       
                        	reserve = "href=/";
                        }
            	    }
                    
        			screenScheduleInfo3 = screenScheduleInfo3
        					+ "<div class=\"s_time\">"
        					+ "              <p class=\"start "+sBought+"\">"
        					+ strScreeningDatetime
        					+ "</p>"
        					+ "              <p class=\"finish "+fBought+"\">~"
        					+ strEndScreeningDatetime
        					+ "</p>"
        					+ "              <a class=\"buyFl1\" "+reserve+">"
        					+ "                <img src=\"../images/"+buyStatus+".png\" alt=\""+buyStatusJp+"\" />"
        					+ "                <div class=\""+buyStatus+"\">"+buyStatusJp+"</div>"
        					+ "              </a>"
        					+ "            </div>";
        		}
        		screenScheduleInfo4 = screenScheduleInfo2 + screenScheduleInfo3 + "</div>";
    		}
    		String screenScheduleInfo5 = nowShowingInfo3 + screenScheduleInfo4 + "</div>" + "</div>" + "</div>" + "</div>";
    		nowShowingList.add(screenScheduleInfo5);
    		nowShowingCount = nowShowingCount + 1;
		}
		model.addAttribute("nowShowingList", nowShowingList);	
		
        //  公開前映画情報  //////////////////////
		List<String> upcomingList = new ArrayList<>();
		List<Object[]> upcomingTitleList = MovieService.findUpcomingMovie();
		String upcommingInfo2 = "";
		for(Object[] upcomingTitle: upcomingTitleList) {
			String titleId = upcomingTitle[0].toString();
			Integer movieId = Integer.parseInt(titleId);
			String titleName = (String)upcomingTitle[1];
			String staff = (String)upcomingTitle[2];
			String movieDetails = (String)upcomingTitle[3];
			String movieUrl = (String)upcomingTitle[4];
			String movieImg = (String)upcomingTitle[5];
			LocalDate releaseDay = (LocalDate)upcomingTitle[6];
	        DateTimeFormatter releaseFormatter = DateTimeFormatter.ofPattern("yyyy年MM月公開予定");
	        String releaseDayStr = releaseDay.format(releaseFormatter);
	        
	        String nowShowingCountStr2 = nowShowingCount.toString();
			System.out.println(nowShowingCountStr2);
			String upcommingInfo1 =  "<div class=\"content-item\">"
					+ "          <div class=\"unique-item\">"
					+ "            <img src=\"../images/"+movieImg+"\" alt=\"Image 1\" class=\"content-image\" />"
					+ "            <div class=\"content-details\">"
					+ "              <h2 class=\"content-title\">"+titleName+"</h2>"
					+ "              <p class=\"content-description\">"+staff+"</p>"
					+ "              <button class=\"content-button"+nowShowingCountStr2+"\">+ more show</button>"
					+ "            </div>"
					+ "          </div>"
					+ "          <div class=\"more-detailFl"+nowShowingCountStr2+"\">"
					+ "            <h1 class=\"introduce\">INTRODUCTION</h1>"
					+ "            <p class=\"more-detail\">"+movieDetails+"</p>"
					+ "            <a href=\""+movieUrl+"\" class=\"officialSiteCheck\">オフィシャルサイト &#8362;</a>"
					+ "            <h1 class=\"schedule\">SCHEDULE</h1>"
					+ "            <p class=\"more-detail\">"+releaseDayStr+"</p>"
					+ "          </div>"
					+ "        </div>";
			nowShowingCount = nowShowingCount + 1;
			upcomingList.add(upcommingInfo1);
		}
		model.addAttribute("upcomingList", upcomingList);
	    return "showmovie"; 
	  }	
	  
	  
	  
	  //  ３次開発  //////////////////////////////////////////////////////////////////////////////////////////////
	  
	  @RequestMapping("/data2")
	  public String data2(Model model){	 
		//  上映スケジュール一覧を取得して表示
		List<Object[]> screeningSchedules = ScreeningScheduleService.findAllScreeningSchedule();
		model.addAttribute("screeningSchedules", screeningSchedules);
		
		//  情報公開中の公開中映画タイトル一覧取得
		List<Object[]> movieTitles = MovieService.findMovieTitle();
		model.addAttribute("movieTitles", movieTitles);
		
		//  スクリーン一覧取得
		List<Object[]> screens = ScreenService.findAllScreen();
		model.addAttribute("screens", screens);

		Integer screeningScheduleIdCount = 1;
		List<String> screeningScheduleList = new ArrayList<>();
		String screeningScheduleInfo = "";
		String screeningScheduleInfo3 = "";
		for(Object[] screeningSchedule: screeningSchedules) {
			String strScreeningScheduleId = screeningSchedule[0].toString();
			Integer screeningScheduleId = Integer.parseInt(strScreeningScheduleId);
			String movieTitle = (String)screeningSchedule[1];
			String strScreenId = screeningSchedule[2].toString();
			Integer screenId = Integer.parseInt(strScreenId);
			LocalDateTime screeningDatetime = (LocalDateTime)screeningSchedule[3];
            DateTimeFormatter screeningDayFormatter = DateTimeFormatter.ofPattern("yy-MM-dd");
            String screeningday = screeningDatetime.format(screeningDayFormatter);
            DateTimeFormatter screeningTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            String screeningTime = screeningDatetime.format(screeningTimeFormatter);
			String runningTime = screeningSchedule[4].toString();
			Integer intRunningTime = Integer.parseInt(runningTime);
            LocalDateTime endScreeningDatetime = screeningDatetime.plus(intRunningTime, ChronoUnit.MINUTES);
            String strEndScreeningDatetime = endScreeningDatetime.format(screeningTimeFormatter);
			String strMovieId = screeningSchedule[5].toString();
			Integer movieId = Integer.parseInt(strMovieId);

		    List<Object[]> freeTimes = null;
				LocalDate today = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String formattedDate = today.format(formatter);
				String strDay = Integer.toString(today.getDayOfMonth());
				

	        if (strDay.equals("1") || strDay.equals("8") || strDay.equals("15") || strDay.equals("22") || strDay.equals("29")) {
	      	    freeTimes = TimeTableService.findFreeTime(screenId, intRunningTime);
	      	} else if (strDay.equals("2") || strDay.equals("9") || strDay.equals("16") || strDay.equals("23") || strDay.equals("30")) {
	      	    freeTimes = TimeTableService2.findFreeTime(screenId, intRunningTime);
	      	} else if (strDay.equals("3") || strDay.equals("10") || strDay.equals("17") || strDay.equals("24") || strDay.equals("31")) {
	      	    freeTimes = TimeTableService3.findFreeTime(screenId, intRunningTime);
	      	} else if (strDay.equals("4") || strDay.equals("11") || strDay.equals("18") || strDay.equals("25")) {
	      	    freeTimes = TimeTableService4.findFreeTime(screenId, intRunningTime);
	      	} else if (strDay.equals("5") || strDay.equals("12") || strDay.equals("19") || strDay.equals("26")) {
	      	    freeTimes = TimeTableService5.findFreeTime(screenId, intRunningTime);
	      	} else if (strDay.equals("6") || strDay.equals("13") || strDay.equals("20") || strDay.equals("27")) {
	      	    freeTimes = TimeTableService6.findFreeTime(screenId, intRunningTime);
	      	} else if (strDay.equals("7") || strDay.equals("14") || strDay.equals("21") || strDay.equals("28")) {
	      	    freeTimes = TimeTableService7.findFreeTime(screenId, intRunningTime);
	      	}
		      
		      List<String> startTimeList = new ArrayList<>();
		      for (Object[] record : freeTimes) {
		          Integer startTimeId = (Integer) record[0];
		          List<Object[]> timeList = TimeService.findTime(startTimeId);
		          LocalTime getTime = (LocalTime) timeList.get(0)[0];
		          startTimeList.add(getTime.toString());
		      }
            
			screeningScheduleInfo = screeningScheduleInfo + "<tr>"
					+ "			<form>"
					+ "			    <td class=\"title2\">"
					+ "					<p id=\"titleTxt\">"+movieTitle+"</p>"
					+ "					<select name=\"movieTitle\" class=\"cell-input\">"
					+ "						<option value=\""+strMovieId+"\" selected>"+movieTitle+"</option>";
			String screeningScheduleInfo4 = "";
			for(Object[] movieTitleName: movieTitles) {
				String strMovieTitle = (String)movieTitleName[0];
				String strMovieId2 = movieTitleName[1].toString();
				Integer movieId2 = Integer.parseInt(strMovieId2);
				screeningScheduleInfo4 = screeningScheduleInfo4 + "<option value=\""+movieId2+"\">"+strMovieTitle+"</option>";
			}
			screeningScheduleInfo3 = screeningScheduleInfo + screeningScheduleInfo4 + "</select>"
					+ "				</td>"
					+ "			    <td class=\"sc\">"
					+ "					<p>"+strScreenId+"</p>"
					+ "					<select name=\"screenId\" class=\"cell-input\">"
					+ "						<option value=\""+strScreenId+"\" selected>"+strScreenId+"</option>";
			String screeningScheduleInfo6 = "";
			for(Object[] screen: screens) {
				String strScreenId2 = screen[0].toString();
				Integer screenId2 = Integer.parseInt(strScreenId2);
				screeningScheduleInfo6 = screeningScheduleInfo6 + "<option value=\""+screenId2+"\">"+screenId2+"</option>";
			}					
			String screeningScheduleInfo5 = screeningScheduleInfo3 + screeningScheduleInfo6 + "</select></td>"
					+ "			    <td class=\"day\">"
					+ "					<p>"+screeningday+"</p>"
					+ "					<select name=\"day\" class=\"cell-input\">";
			
			
			String screeningScheduleInfo8 = "<option>aa</option>";
			String screeningScheduleInfo7 =screeningScheduleInfo5+screeningScheduleInfo8+ "</select></td>"
					+ "				<td class=\"open\">"
					+ "					<p>"+screeningTime+"</p>"
					+ "					<select  name=\"open\" class=\\\"cell-input\">";
			String screeningScheduleInfo9 = "";
			for(String startTime: startTimeList) {
				screeningScheduleInfo9 = screeningScheduleInfo9 + "<option value=\""+startTime+"\">"+startTime+"</option>";
			}			
			String screeningScheduleInfo10 = screeningScheduleInfo7+screeningScheduleInfo9+"</select></td>"
					+ "				<td class=\"close\">"
					+ "					<p>"+strEndScreeningDatetime+"</p>"
					+ "				</td>"
					+ "			    <td class=\"btn\">"
					+ "			        <div class=\"btn_g\">"
					+ "			            <button type=\"submit\" class=\"edit\"><i class=\"fas fa-edit\"></i></button>"
					+ "						<form>"
					+ "			            	<button class=\"del\"><i class=\"fas fa-trash-alt\"></i></button>"
					+ "						</form>"
					+ "			        </div>"
					+ "			    </td>"
					+ "			</form>"
					+ "		</tr>";
			screeningScheduleInfo = screeningScheduleInfo10;
		}
		screeningScheduleList.add(screeningScheduleInfo);
		model.addAttribute("screeningScheduleList", screeningScheduleList);
		
	    return "data2"; 
	  }	
	  
	  
	  
	  //  データ追加あとまわし
//	  @PostMapping("/data2")
//	  public String handleFormSubmit(@RequestBody AddFormData formData, Model model) {
//	      String movieTitle = formData.getMovieTitle();
//	      String screen = formData.getScreen();
//	      String date = formData.getDate();
//
//	      // 上映スケジュール一覧を取得して表示
//	      List<Object[]> screeningSchedules = ScreeningScheduleService.findAllScreeningSchedule();
//	      model.addAttribute("screeningSchedules", screeningSchedules);
//
//	      // 情報公開中の公開中映画タイトル一覧取得
//	      List<Object[]> movieTitles = MovieService.findMovieTitle();
//	      model.addAttribute("movieTitles", movieTitles);
//
//	      // スクリーン一覧取得
//	      List<Object[]> screens = ScreenService.findAllScreen();
//	      model.addAttribute("screens", screens);
//
//	      // 日付の処理
//	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//	      LocalDate selectedDate = LocalDate.parse(date, formatter);
//	      String strDay = Integer.toString(selectedDate.getDayOfMonth());
//
//	      Integer screenId = Integer.parseInt(screen);
//	      List<Object[]> runningTimeList = MovieService.findRunningTime(movieTitle);
//	      Integer runningTime = (Integer) runningTimeList.get(0)[0] + 15;
//	      String strRunningTime = runningTime.toString();
//	      
//	      
//
//	      // フリータイムの取得
//	      List<Object[]> freeTimes = null;
//
//	      if (strDay.equals("1") || strDay.equals("8") || strDay.equals("15") || strDay.equals("22") || strDay.equals("29")) {
//	    	    freeTimes = TimeTableService.findFreeTime(screenId, runningTime);
//	    	} else if (strDay.equals("2") || strDay.equals("9") || strDay.equals("16") || strDay.equals("23") || strDay.equals("30")) {
//	    	    freeTimes = TimeTableService2.findFreeTime(screenId, runningTime);
//	    	} else if (strDay.equals("3") || strDay.equals("10") || strDay.equals("17") || strDay.equals("24") || strDay.equals("31")) {
//	    	    freeTimes = TimeTableService3.findFreeTime(screenId, runningTime);
//	    	} else if (strDay.equals("4") || strDay.equals("11") || strDay.equals("18") || strDay.equals("25")) {
//	    	    freeTimes = TimeTableService4.findFreeTime(screenId, runningTime);
//	    	} else if (strDay.equals("5") || strDay.equals("12") || strDay.equals("19") || strDay.equals("26")) {
//	    	    freeTimes = TimeTableService5.findFreeTime(screenId, runningTime);
//	    	} else if (strDay.equals("6") || strDay.equals("13") || strDay.equals("20") || strDay.equals("27")) {
//	    	    freeTimes = TimeTableService6.findFreeTime(screenId, runningTime);
//	    	} else if (strDay.equals("7") || strDay.equals("14") || strDay.equals("21") || strDay.equals("28")) {
//	    	    freeTimes = TimeTableService7.findFreeTime(screenId, runningTime);
//	    	}
//
//	      
//	      List<String> startTimeList = new ArrayList<>();
//	      for (Object[] record : freeTimes) {
//	          Integer startTimeId = (Integer) record[0];
//	          List<Object[]> timeList = TimeService.findTime(startTimeId);
//	          LocalTime getTime = (LocalTime) timeList.get(0)[0];
//	          startTimeList.add(getTime.toString());
//	      }
//	      model.addAttribute("startTimeList", startTimeList);
//	      
//	      return "data2";
//	  }


	    public static class AddFormData {
	        private String movieTitle;
	        private String screen;
	        private String date;

	        public String getMovieTitle() {
	            return movieTitle;
	        }

	        public void setMovieTitle(String movieTitle) {
	            this.movieTitle = movieTitle;
	        }

	        public String getScreen() {
	            return screen;
	        }

	        public void setScreen(String screen) {
	            this.screen = screen;
	        }

	        public String getDate() {
	            return date;
	        }

	        public void setDate(String date) {
	            this.date = date;
	        }
	    }

}



