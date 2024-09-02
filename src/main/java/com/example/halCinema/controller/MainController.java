package com.example.halCinema.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import com.example.halCinema.service.NewsService;
import com.example.halCinema.service.ReservationService;
import com.example.halCinema.service.ScreeningScheduleService;

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
    
    
	
	// index.html
	  @RequestMapping("/")
	  public String index(HttpSession session ,Model model){
	    session.invalidate();

	    //news表示
        List<Object[]> newsList = NewsService.findNewsStreamingDate();
        model.addAttribute("newsList", newsList);
        
	    return "index"; 
	  }	
	  
	  
	  //  ログイン（Securityなし仮）
	  @RequestMapping("/entry")
	  public String entry(@RequestParam(name = "usermail", required = false) String usermail, @RequestParam(name = "password", required = false) String password, HttpSession session){
			List<Object[]> users = MemberService.loginEntry(usermail, password);
	        if (!users.isEmpty()) {
	            Object[] usersElement = users.get(0);
	            Integer userId = (Integer) usersElement[0];
	            
	            // userId が null でないか確認
	            if (userId != null) {
	                session.setAttribute("userId", userId);
	                return "redirect:/toppage";
	            }
	        }
	        return "redirect:/";
	  }
	  
	  
	  //  ログアウト（Securityなし仮）
	  @RequestMapping("/logout")
	  public String logout(HttpSession session){
	      session.invalidate();
	      return "redirect:/";
	  }
	  
	  
	  
	  
	  
	  // toppage.html
	  @RequestMapping("/toppage")
	  public String toppage(@RequestParam(name = "screenScheduleDate", required = false) String screenScheduleDate, Model model){
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
	        model.addAttribute("topLink", "/");		
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
	        model.addAttribute("topLink", "/");		
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
	        model.addAttribute("topLink", "/");		
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
	        model.addAttribute("topLink", "/");		
	    }
	    return "service"; 
	  }	
	  
	  
	  
	  //  映画情報ページ
	  @RequestMapping("/showmovie")
	  public String showmovie(Model model, HttpSession session){
		Integer memberId = (Integer) session.getAttribute("userId");
		if (memberId != null) {
	        model.addAttribute("topLink", "/toppage");
	    } else {
	        model.addAttribute("topLink", "/");		
	    }
	    return "showmovie"; 
	  }	

}
