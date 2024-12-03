package com.example.halCinema.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.halCinema.model.Member;
import com.example.halCinema.model.Movie;
import com.example.halCinema.model.News;
import com.example.halCinema.model.Product;
import com.example.halCinema.model.Sale;
import com.example.halCinema.model.Screen;
import com.example.halCinema.model.ScreeningSchedule;
import com.example.halCinema.service.EmailService;
import com.example.halCinema.service.MemberService;
import com.example.halCinema.service.MovieService;
import com.example.halCinema.service.NewsService;
import com.example.halCinema.service.ProductService;
import com.example.halCinema.service.ReservationService;
import com.example.halCinema.service.SaleDetailService;
import com.example.halCinema.service.SaleService;
import com.example.halCinema.service.ScreenService;
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
	@Autowired
	MovieService MovieService;
	@Autowired
	ScreenService ScreenService;
	@Autowired
	NewsService newsService;
	@Autowired
	ProductService ProductService;
	@Autowired
	SaleService SaleService;
	@Autowired
	SaleDetailService SaleDetailService;

	//  ログアウト（Securityなし仮）
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

	@RequestMapping("/toppage")
	public String toppage(@RequestParam(name = "screenScheduleDate", required = false) String screenScheduleDate,
			Model model, HttpSession session) {
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
			String dayInfo = "<a class=\"day\" href=/screenScheduleSelect?screenScheduleDate="
					+ selectScreenScheduleDate + ">" + formattedDate + "<span class=\"mini\">(" + dayOfWeek
					+ ")</span></a>";
			dates.add(dayInfo);
		}
		model.addAttribute("dates", dates);

		//  上映スケジュール  //////////////////////
		String nowScreenScheduleDate;
		String nowOfWeek;
		LocalDate nowDate;
		if (screenScheduleDate == null) {
			nowDate = today;
			DateTimeFormatter todayFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			nowScreenScheduleDate = today.format(todayFormatter);
			nowOfWeek = today.getDayOfWeek().toString().substring(0, 3);
		} else {
			LocalDate selectedDate = LocalDate.parse(screenScheduleDate, screenScheduleDateFormatter);
			nowDate = selectedDate;
			DateTimeFormatter todayFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			nowScreenScheduleDate = selectedDate.format(todayFormatter);
			nowOfWeek = selectedDate.getDayOfWeek().toString().substring(0, 3);
		}
		String nowScreeningSchedule = nowScreenScheduleDate + "<span class=\"mini\">(" + nowOfWeek + ")</span>";
		model.addAttribute("nowScreeningSchedule", nowScreeningSchedule);
		//  作品一覧&上映時間
		List<String> titleLinkList = new ArrayList<>();
		List<String> screenScheduleList = new ArrayList<>();
		List<Object[]> titleList = ScreeningScheduleService.findSelectScreeningTitle(nowDate);
		String titleInfo = "";
		for (Object[] title : titleList) {
			String titleId = title[0].toString();
			String titleName = (String) title[1];
			titleInfo = titleInfo + "<a href=\"#m" + titleId + "\">" + titleName + "</a>";
		}
		titleLinkList.add(titleInfo);
		model.addAttribute("titleLinkList", titleLinkList);
		for (Object[] title : titleList) {
			String titleId = title[0].toString();
			Integer movieId = Integer.parseInt(titleId);
			String titleName = (String) title[1];

			String screenScheduleInfo1 = "<div class=\"s_all\">"
					+ "          <div id=\"m" + titleId + "\"></div>"
					+ "          <div class=\"s_head\">"
					+ "            <p class=\"jp\">" + titleName + "</p>"
					+ "            <a href=\"showmovie.html\" class=\"s_more\">more&thinsp;⇀</a>"
					+ "          </div>";

			List<Object[]> screenList = ScreeningScheduleService.findSelectScreeningScreen(movieId, nowDate);
			String screenScheduleInfo2 = "";
			String screenScheduleInfo4 = "";
			for (Object[] screen : screenList) {
				String screenId = screen[1].toString();
				Integer intScreenId = Integer.parseInt(screenId);
				String runningTime = screen[2].toString();
				Integer intRunningTime = Integer.parseInt(runningTime);
				screenScheduleInfo2 = screenScheduleInfo4
						+ "<div class=\"s_rooms\">"
						+ "            <div class=\"s_room\">"
						+ "              <p class=\"theater\">THEATER&thinsp;&thinsp;<span class=\"big\">"
						+ screenId + "</span></p>"
						+ "              <p class=\"xd\">2D</p>"
						+ "              <p class=\"min\">"
						+ runningTime + "min</p>"
						+ "            </div>";

				List<Object[]> screenDatetimeList = ScreeningScheduleService.findSelectScreeningDatetime(intScreenId,
						nowDate, movieId);
				String screenScheduleInfo3 = "";
				LocalDateTime now = LocalDateTime.now();
				for (Object[] screenDatetime : screenDatetimeList) {
					LocalDateTime screeningDatetime = (LocalDateTime) screenDatetime[1];
					DateTimeFormatter screeningDatetimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
					String strScreeningDatetime = screeningDatetime.format(screeningDatetimeFormatter);
					LocalDateTime endScreeningDatetime = screeningDatetime.plus(intRunningTime, ChronoUnit.MINUTES);
					String strEndScreeningDatetime = endScreeningDatetime.format(screeningDatetimeFormatter);
					String sBought;
					String fBought;
					String buyStatus;
					String buyStatusJp;
					String reserve;
					if (now.isAfter(screeningDatetime)) {
						buyStatus = "cantBuy";
						buyStatusJp = "購入不可";
						sBought = "s_bought";
						fBought = "f_bought";
						reserve = "href=#";
					} else {
						buyStatus = "canBuy";
						buyStatusJp = "購入";
						sBought = "";
						fBought = "";
						reserve = "href=/reserve?screeningScheduleId=" + (Integer) screenDatetime[0];
					}

					screenScheduleInfo3 = screenScheduleInfo3
							+ "<div class=\"s_time\">"
							+ "              <p class=\"start " + sBought + "\">"
							+ strScreeningDatetime
							+ "</p>"
							+ "              <p class=\"finish " + fBought + "\">~"
							+ strEndScreeningDatetime
							+ "</p>"
							+ "              <a class=\"buyFl1\" " + reserve + ">"
							+ "                <img src=\"../images/" + buyStatus + ".png\" alt=\"" + buyStatusJp
							+ "\" />"
							+ "                <div class=\"" + buyStatus + "\">" + buyStatusJp + "</div>"
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
	public String news(Model model, HttpSession session, @PathVariable Integer id) {
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
	public String screenScheduleSelect(@RequestParam(name = "screenScheduleDate") String screenScheduleDate,
			Model model) {
		return "redirect:/toppage?screenScheduleDate=" + screenScheduleDate + "#sche_b";
	}

	// 上映スケジュールの表示切替(showmovie)
	@GetMapping("/screenScheduleSelectInfo")
	public String screenScheduleSelectInfo(@RequestParam(name = "screenScheduleDate") String screenScheduleDate,
			Model model) {
		return "redirect:/showmovie?screenScheduleDate=" + screenScheduleDate + "#sche_b";
	}

	// seat.html
	@RequestMapping("/reserve")
	public String reserve(@RequestParam(name = "screeningScheduleId") Integer screeningScheduleId, Model model,
			HttpSession session) {
		//		会員情報取得
		UUID memberId = (UUID) session.getAttribute("userId");//  ログイン時のセッションからID取得
		System.out.println("memberid:" + memberId);
		List<Object[]> memberList = MemberService.findReservationMember(memberId);
		model.addAttribute("memberList", memberList);
		model.addAttribute("memberId", memberId);
		//      上映スケジュール取得        
		List<Object[]> screeningScheduleList = ScreeningScheduleService
				.findSelectScreeningSchedule(screeningScheduleId);
		model.addAttribute("screeningScheduleList", screeningScheduleList);
		model.addAttribute("screeningScheduleId", screeningScheduleId);
		Object[] screeningScheduleElement = screeningScheduleList.get(0);
		Integer capacity = (Integer) screeningScheduleElement[2];
		String seatCapacity = "seat";
		Integer seatNumberCapacity = 83;
		if (capacity == 70) {
			seatCapacity = "seat";
			seatNumberCapacity = 83;
		} else if (capacity == 120) {
			seatCapacity = "seat2";
			seatNumberCapacity = 140;
		} else {
			seatCapacity = "seat3";
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
	public String reserve_conf(@RequestParam(required = false) Integer seatNumber,
			@RequestParam(required = false) Integer guestSeatNumber,
			@RequestParam(required = false) Integer screeningScheduleId, @RequestParam(required = false) UUID memberId,
			@RequestParam(required = false) String selectedCell1Content,
			@RequestParam(required = false) String selectedCell2Content, Model model) {
		//		上映スケジュール取得
		List<Object[]> screeningScheduleList = ScreeningScheduleService
				.findSelectScreeningSchedule(screeningScheduleId);
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
	public String reserve_comp(@RequestParam(required = false) Integer seatNumber,
			@RequestParam(required = false) Integer guestSeatNumber,
			@RequestParam(required = false) Integer screeningScheduleId, @RequestParam(required = false) UUID memberId,
			Model model) {
		//		予約
		if (guestSeatNumber == null) {
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
		List<Object[]> screeningScheduleList = ScreeningScheduleService
				.findSelectScreeningSchedule(screeningScheduleId);
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
		EmailService.sendQRCodeEmail(mailAddress, subject, strReservationId, movieTitle, strScreeningDatetime,
				screenName, memberName);

		return "rsv_comp";
	}

	//  アクセスページ
	@RequestMapping("/access")
	public String access(Model model, HttpSession session) {
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
	public String member(Model model, HttpSession session) {
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
	public String service(Model model, HttpSession session) {
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
	public String showmovie(@RequestParam(name = "screenScheduleDate", required = false) String screenScheduleDate,
			Model model, HttpSession session) {
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
		for (Object[] nowShowingTitle : nowShowingTitleList) {
			String titleId = nowShowingTitle[0].toString();
			Integer movieId = Integer.parseInt(titleId);
			String titleName = (String) nowShowingTitle[1];
			String staff = (String) nowShowingTitle[2];
			String movieDetails = (String) nowShowingTitle[3];
			String movieUrl = (String) nowShowingTitle[4];
			String movieImg = (String) nowShowingTitle[5];

			String nowShowingCountStr = nowShowingCount.toString();
			System.out.println(nowShowingCountStr);
			String nowShowingInfo1 = "<div class=\"content-item\">"
					+ "<div class=\"unique-item\">"
					+ "            <img src=\"../images/" + movieImg + "\" alt=\"Image 1\" class=\"content-image\" />"
					+ "            <div class=\"content-details\">"
					+ "			   <h2 class=\"content-title\">" + titleName + "</h2>"
					+ "			   <p class=\"content-description\">" + staff + "</p>"
					+ "              <button class=\"content-button" + nowShowingCountStr + "\">+ more show</button>"
					+ "            </div>"
					+ "            </div>"
					+ "            <div class=\"more-detailFl" + nowShowingCountStr + "\">"
					+ "            <h1 class=\"introduce\">INTRODUCTION</h1>"
					+ "			   <p class=\"more-detail\">" + movieDetails + "</p>"
					+ "			   <a href=\"" + movieUrl + "\" class=\"officialSiteCheck\">オフィシャルサイト &#8362;</a>"
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
				String dayInfo = "<a class=\"day\" href=/screenScheduleSelectInfo?screenScheduleDate="
						+ selectScreenScheduleDate + ">" + formattedDate + "<span class=\"mini\">(" + dayOfWeek
						+ ")</span></a>";
				nowShowingInfo1 = nowShowingInfo1 + dayInfo;
			}
			String nowScreenScheduleDate;
			String nowOfWeek;
			LocalDate nowDate;
			if (screenScheduleDate == null) {
				nowDate = today;
				DateTimeFormatter todayFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				nowScreenScheduleDate = today.format(todayFormatter);
				nowOfWeek = today.getDayOfWeek().toString().substring(0, 3);
			} else {
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
					+ "             <p id=\"now\">" + nowScreenScheduleDate + "<span class=\"mini\">(" + nowOfWeek
					+ ")</span></p>"
					+ "           </div>"
					+ "            <div class=\"s_waku\">"
					+ "              <div class=\"s_all\">"
					+ "                <div id=\"BLUELOCK\"></div>"
					+ "                <div class=\"s_head\">"
					+ "                  <p class=\"jp\">" + titleName + "</p>"
					+ "                </div>";

			List<Object[]> screenList = ScreeningScheduleService.findSelectScreeningScreen(movieId, nowDate);
			String screenScheduleInfo2 = "";
			String screenScheduleInfo4 = "";
			for (Object[] screen : screenList) {
				String screenId = screen[1].toString();
				Integer intScreenId = Integer.parseInt(screenId);
				String runningTime = screen[2].toString();
				Integer intRunningTime = Integer.parseInt(runningTime);
				screenScheduleInfo2 = screenScheduleInfo4
						+ "<div class=\"s_rooms\">"
						+ "            <div class=\"s_room\">"
						+ "              <p class=\"theater\">THEATER&thinsp;&thinsp;<span class=\"big\">"
						+ screenId + "</span></p>"
						+ "              <p class=\"xd\">2D</p>"
						+ "              <p class=\"min\">"
						+ runningTime + "min</p>"
						+ "            </div>";

				List<Object[]> screenDatetimeList = ScreeningScheduleService.findSelectScreeningDatetime(intScreenId,
						nowDate, movieId);
				String screenScheduleInfo3 = "";
				LocalDateTime now = LocalDateTime.now();
				for (Object[] screenDatetime : screenDatetimeList) {
					LocalDateTime screeningDatetime = (LocalDateTime) screenDatetime[1];
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
						if (now.isAfter(screeningDatetime)) {
							buyStatus = "cantBuy";
							buyStatusJp = "購入不可";
							sBought = "s_bought";
							fBought = "f_bought";
							reserve = "href=#";
						} else {
							buyStatus = "canBuy";
							buyStatusJp = "購入";
							sBought = "";
							fBought = "";
							reserve = "href=/reserve?screeningScheduleId=" + (Integer) screenDatetime[0];
						}
					} else {
						if (now.isAfter(screeningDatetime)) {
							buyStatus = "cantBuy";
							buyStatusJp = "購入不可";
							sBought = "s_bought";
							fBought = "f_bought";
							reserve = "href=#";
						} else {
							buyStatus = "canBuy";
							buyStatusJp = "ログイン";
							sBought = "";
							fBought = "";
							reserve = "href=/";
						}
					}

					screenScheduleInfo3 = screenScheduleInfo3
							+ "<div class=\"s_time\">"
							+ "              <p class=\"start " + sBought + "\">"
							+ strScreeningDatetime
							+ "</p>"
							+ "              <p class=\"finish " + fBought + "\">~"
							+ strEndScreeningDatetime
							+ "</p>"
							+ "              <a class=\"buyFl1\" " + reserve + ">"
							+ "                <img src=\"../images/" + buyStatus + ".png\" alt=\"" + buyStatusJp
							+ "\" />"
							+ "                <div class=\"" + buyStatus + "\">" + buyStatusJp + "</div>"
							+ "              </a>"
							+ "            </div>";
				}
				screenScheduleInfo4 = screenScheduleInfo2 + screenScheduleInfo3 + "</div>";
			}
			String screenScheduleInfo5 = nowShowingInfo3 + screenScheduleInfo4 + "</div>" + "</div>" + "</div>"
					+ "</div>";
			nowShowingList.add(screenScheduleInfo5);
			nowShowingCount = nowShowingCount + 1;
		}
		model.addAttribute("nowShowingList", nowShowingList);

		//  公開前映画情報  //////////////////////
		List<String> upcomingList = new ArrayList<>();
		List<Object[]> upcomingTitleList = MovieService.findUpcomingMovie();
		String upcommingInfo2 = "";
		for (Object[] upcomingTitle : upcomingTitleList) {
			String titleId = upcomingTitle[0].toString();
			Integer movieId = Integer.parseInt(titleId);
			String titleName = (String) upcomingTitle[1];
			String staff = (String) upcomingTitle[2];
			String movieDetails = (String) upcomingTitle[3];
			String movieUrl = (String) upcomingTitle[4];
			String movieImg = (String) upcomingTitle[5];
			LocalDate releaseDay = (LocalDate) upcomingTitle[6];
			DateTimeFormatter releaseFormatter = DateTimeFormatter.ofPattern("yyyy年MM月公開予定");
			String releaseDayStr = releaseDay.format(releaseFormatter);

			String nowShowingCountStr2 = nowShowingCount.toString();
			System.out.println(nowShowingCountStr2);
			String upcommingInfo1 = "<div class=\"content-item\">"
					+ "          <div class=\"unique-item\">"
					+ "            <img src=\"../images/" + movieImg + "\" alt=\"Image 1\" class=\"content-image\" />"
					+ "            <div class=\"content-details\">"
					+ "              <h2 class=\"content-title\">" + titleName + "</h2>"
					+ "              <p class=\"content-description\">" + staff + "</p>"
					+ "              <button class=\"content-button" + nowShowingCountStr2 + "\">+ more show</button>"
					+ "            </div>"
					+ "          </div>"
					+ "          <div class=\"more-detailFl" + nowShowingCountStr2 + "\">"
					+ "            <h1 class=\"introduce\">INTRODUCTION</h1>"
					+ "            <p class=\"more-detail\">" + movieDetails + "</p>"
					+ "            <a href=\"" + movieUrl + "\" class=\"officialSiteCheck\">オフィシャルサイト &#8362;</a>"
					+ "            <h1 class=\"schedule\">SCHEDULE</h1>"
					+ "            <p class=\"more-detail\">" + releaseDayStr + "</p>"
					+ "          </div>"
					+ "        </div>";
			nowShowingCount = nowShowingCount + 1;
			upcomingList.add(upcommingInfo1);
		}
		model.addAttribute("upcomingList", upcomingList);
		return "showmovie";
	}

	//  ３次開発  //////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping("/data1")
	public String data1(Model model, @RequestParam(required = false) String titleName,
			@RequestParam(required = false) LocalDate publicationDate,
			@RequestParam(required = false) Integer runningTime, @RequestParam(required = false) String discription,
			@RequestParam(required = false) String imgPath, @RequestParam(required = false) Boolean tgl,
			@RequestParam(required = false) String searchTitle, @RequestParam(required = false) Boolean searchStatus,
			@RequestParam(required = false) Integer seachDate) {

		// 映画情報を取得
		List<Movie> movies = null;
		if (searchTitle != null || searchStatus != null || seachDate != null) {
			if (searchTitle != null) {
				if (searchStatus != null && seachDate != null) {
					movies = MovieService.findSelectAllMovie(searchTitle, seachDate, searchStatus);
				} else if (searchStatus != null) {
					movies = MovieService.findSelectStatusAndTitleMovie(searchTitle, searchStatus);
				} else if (seachDate != null) {
					movies = MovieService.findSelectDateAndTitleMovie(searchTitle, seachDate);
				} else {
					movies = MovieService.findSelectTitleMovie(searchTitle);
				}
			} else if (searchStatus != null) {
				if (seachDate != null) {
					movies = MovieService.findSelectStatusAndDateMovie(seachDate, searchStatus);
				} else {
					movies = MovieService.findSelectStatusMovie(searchStatus);
				}
			} else if (seachDate != null) {
				movies = MovieService.findSelectDateMovie(seachDate);
			}
		} else {
			movies = MovieService.findAllMovies();
		}
		model.addAttribute("movies", movies);
		//		List<Movie> movies = MovieService.findAllMovies();
		//		model.addAttribute("movies", movies); // HTMLテンプレートに渡す

		return "data1";
	}

	// 映画情報を削除するエンドポイント
	@PostMapping("/deleteMovie")
	public String deleteMovie(@RequestParam(required = false) Integer movieId) {
		if (movieId == null) {
			throw new IllegalArgumentException("movieId must not be null");
		}

		MovieService.deleteMovieById(movieId);
		return "redirect:/data1"; // 削除後のリダイレクト先
	}

	// 新しい映画データを追加するエンドポイント
	@PostMapping("/addMovie")
	public String addMovie(@RequestParam("titleName") String titleName,
			@RequestParam("publicationDate") String publicationDate,
			@RequestParam("runningTime") Integer runningTime,
			@RequestParam("description") String description,
			@RequestParam("url") String url,
			@RequestParam("staff") String staff,
			@RequestParam(value = "imgPath", required = false) MultipartFile imgFile,
			@RequestParam(value = "tgl", defaultValue = "false") Boolean tgl) {

		LocalDate temp = LocalDate.parse(publicationDate);

		// デフォルトの画像パスを設定
		String uploadDir = new File("src/main/resources/static/images").getAbsolutePath();
		String imgPath = "default_image_path.jpg";

		if (imgFile != null && !imgFile.isEmpty()) {
			String fileName = imgFile.getOriginalFilename(); // アップロードされたファイル名を取得

			try {
				// 保存先ファイルのフルパスを作成
				File saveFile = new File(uploadDir, fileName);

				// 保存先フォルダが存在しない場合は作成
				if (!saveFile.getParentFile().exists()) {
					saveFile.getParentFile().mkdirs();
				}

				// ファイルを保存
				imgFile.transferTo(saveFile);

				// 保存された画像のパスを設定
				imgPath = "/images/" + fileName; // Webからアクセス可能な相対パス
			} catch (IOException e) {
				e.printStackTrace(); // エラー時のログ出力
			}
		}

		// サービスに保存パスを渡して登録
		MovieService.addMovie(titleName, temp, description, runningTime, tgl, imgPath, url, staff);

		return "redirect:/data1"; // 保存後に一覧ページにリダイレクト
	}

	//  data2.html
	@RequestMapping("/data2")
	public String data2(Model model, @RequestParam(required = false) LocalDate sDate,
			@RequestParam(required = false) Integer screenId, @RequestParam(required = false) String titleName,
			@RequestParam(required = false) LocalTime screeningTime,
			@RequestParam(required = false) LocalDate seachDate, @RequestParam(required = false) Integer searchScreen,
			@RequestParam(required = false) String searchTitle, HttpSession session) {
		//  管理者ログインの状態確認
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		String btnStatus = null;
		if (loggedInUserId != null) {
			btnStatus = "visible";
		}
		model.addAttribute("btnStatus", btnStatus);
		//  上映スケジュールの取得
		List<Object[]> screeningSchedules = null;
		if (searchTitle != null || searchScreen != null || seachDate != null) {
			if (searchTitle != null) {
				List<Object[]> searchMovieId = MovieService.findMovieId(searchTitle);
				Object[] searchMovieIdElement = searchMovieId.get(0);
				Integer searchMovieIdStr = (Integer) searchMovieIdElement[0];
				if (searchScreen != null && seachDate != null) {
					screeningSchedules = ScreeningScheduleService.findSelectAllScreeningSchedule(searchScreen,
							seachDate, searchMovieIdStr);
				} else if (searchScreen != null) {
					screeningSchedules = ScreeningScheduleService
							.findSelectScreenAndTitleScreeningSchedule(searchScreen, searchMovieIdStr);
				} else if (seachDate != null) {
					screeningSchedules = ScreeningScheduleService.findSelectDateAndTitleScreeningSchedule(seachDate,
							searchMovieIdStr);
				} else {
					screeningSchedules = ScreeningScheduleService.findSelectTitleScreeningSchedule(searchMovieIdStr);
				}
			} else if (searchScreen != null) {
				if (seachDate != null) {
					screeningSchedules = ScreeningScheduleService.findSelectScreenAndDateScreeningSchedule(searchScreen,
							seachDate);
				} else {
					screeningSchedules = ScreeningScheduleService.findSelectScreenScreeningSchedule(searchScreen);
				}
			} else if (seachDate != null) {
				screeningSchedules = ScreeningScheduleService.findSelectDateScreeningSchedule(seachDate);
			}
		} else {
			screeningSchedules = ScreeningScheduleService.findAllScreeningSchedule();
		}
		List<Object[]> screeningSchedules2 = ScreeningScheduleService.findAllScreeningSchedule();

		for (Object[] screeningSchedule : screeningSchedules) {
			LocalTime startTime = LocalTime.of(8, 0);
			LocalTime endTime = LocalTime.of(21, 59);
			List<String> updateTimes = new ArrayList<>();
			LocalDateTime screeningDate = (LocalDateTime) screeningSchedule[3];
			if (screeningSchedule[4] != null) {
				// 除外する時間範囲のリストを生成
				List<TimeRange> excludeRanges = new ArrayList<>();
				for (Object[] screeningSchedule2 : screeningSchedules2) {
					LocalDateTime scheduleStart = (LocalDateTime) screeningSchedule2[3];
					if (scheduleStart.toLocalDate().equals(screeningDate.toLocalDate())
							&& screeningSchedule2[2].equals(screeningSchedule[2])) {
						int durationMinutes = (int) screeningSchedule2[4] + 15 + (int) screeningSchedule[4];
						excludeRanges.add(new TimeRange(
								scheduleStart.toLocalTime().minusMinutes((int) screeningSchedule[4]), durationMinutes));
					}
				}
				for (LocalTime time = startTime; !time
						.isAfter(endTime.minusMinutes((int) screeningSchedule[4])); time = time.plusMinutes(1)) {
					if (isTimeExcluded(time, excludeRanges)) {
						continue;
					}
					updateTimes.add(time.toString());
				}
			}
			Object[] updatedScreeningSchedule = Arrays.copyOf(screeningSchedule, screeningSchedule.length + 1);
			updatedScreeningSchedule[screeningSchedule.length] = updateTimes;
			screeningSchedules.set(screeningSchedules.indexOf(screeningSchedule), updatedScreeningSchedule);
		}
		model.addAttribute("screeningSchedules", screeningSchedules);

		//  タイトル, スクリーンのプルダウン生成
		model.addAttribute("title", titleName);
		model.addAttribute("sDate", sDate);
		model.addAttribute("screen", screenId);
		model.addAttribute("screeningTimeStr", screeningTime);
		List<Object[]> movieTitles = MovieService.findMovieTitle();
		model.addAttribute("movieTitles", movieTitles);
		List<Object[]> screens = ScreenService.findAllScreen();
		model.addAttribute("screens", screens);
		//  上映時間のプルダウン生成
		if (titleName != null) {
			List<Object[]> movieId = MovieService.findMovieId(titleName);
			Object[] movieIdElement = movieId.get(0);
			Integer movieIdStr = (Integer) movieIdElement[0];
			model.addAttribute("movieId", movieIdStr);

			List<Object[]> movieRunningTime = MovieService.findRunningTime(titleName);
			Object[] movieRunningTimeElement = movieRunningTime.get(0);
			Integer runningTime = (Integer) movieRunningTimeElement[0];

			LocalTime startTime = LocalTime.of(8, 0);
			LocalTime endTime = LocalTime.of(21, 59);
			List<String> times = new ArrayList<>();
			if (sDate != null && screenId != null && runningTime != null) {
				// 除外する時間範囲のリストを生成
				List<TimeRange> excludeRanges = new ArrayList<>();
				for (Object[] screeningSchedule : screeningSchedules2) {
					LocalDateTime scheduleStart = (LocalDateTime) screeningSchedule[3];
					if (scheduleStart.toLocalDate().equals(sDate) && screeningSchedule[2].equals(screenId)) {
						int durationMinutes = (int) screeningSchedule[4] + 15 + runningTime;
						excludeRanges.add(
								new TimeRange(scheduleStart.toLocalTime().minusMinutes(runningTime), durationMinutes));
					}
				}
				for (LocalTime time = startTime; !time.isAfter(endTime.minusMinutes(runningTime)); time = time
						.plusMinutes(1)) {
					if (isTimeExcluded(time, excludeRanges)) {
						continue;
					}
					times.add(time.toString());
				}
			}
			model.addAttribute("times", times);

			if (screeningTime != null) {
				LocalTime endScreening = screeningTime.plusMinutes(runningTime);
				model.addAttribute("endScreening", endScreening);
			}
		}

		return "data2";
	}

	// 指定された時間が空いているかどうかを判定
	private boolean isTimeExcluded(LocalTime time, List<TimeRange> excludeRanges) {
		for (TimeRange range : excludeRanges) {
			if (range.overlaps(time)) {
				return true;
			}
		}
		return false;
	}

	private boolean canFitDuration(LocalTime startTime, int durationMinutes, LocalTime endTime) {
		LocalTime endTimeCandidate = startTime.plusMinutes(durationMinutes);
		return !endTimeCandidate.isAfter(endTime);
	}

	public class TimeRange {
		private LocalTime start;
		private LocalTime end;

		public TimeRange(LocalTime start, int durationMinutes) {
			this.start = start;
			this.end = start.plusMinutes(durationMinutes);
		}

		public boolean overlaps(LocalTime time) {
			return !time.isBefore(start) && !time.isAfter(end);
		}
	}

	//  上映スケジュール削除
	@RequestMapping("/ssDel")
	public String ssDel(@RequestParam(required = false) Integer delId) {
		ScreeningScheduleService.deleteScreeningDatetime(delId);
		return "redirect:/data2";
	}

	//  上映スケジュール追加
	@RequestMapping("/addSchedule")
	public String addSchedule(@RequestParam(required = false) LocalDate sDate,
			@RequestParam(required = false) Integer screenId, @RequestParam(required = false) String titleName,
			@RequestParam(required = false) LocalTime screeningTime, @RequestParam(required = false) Integer movieId) {
		LocalDateTime screeningDatetime = sDate.atTime(screeningTime);
		Screen screen = ScreenService.findScreenById(screenId);
		Movie movie = MovieService.findMovieById(movieId);
		ScreeningScheduleService.addScreeningSchedule(screeningDatetime, screen, movie);
		return "redirect:/data2";
	}

	//  上映スケジュール一括追加
	@RequestMapping("/addBlukSchedule")
	public String addBulkSchedule(@RequestParam(required = false) Integer month) {
		List<Object[]> screeningSchedules = ScreeningScheduleService
				.findMonthScreeningSchedule(LocalDate.now().getMonthValue());
		for (int i = LocalDate.now().getMonthValue() + 1; i < LocalDate.now().getMonthValue() + month + 1; i++) {
			int i2;
			int year;
			if (i > 12) {
				i2 = i % 12;
				year = LocalDate.now().getYear() + 1;
			} else {
				i2 = i;
				year = LocalDate.now().getYear();
			}

			for (Object[] screeningSchedule : screeningSchedules) {
				LocalDateTime scheduleDateTime = (LocalDateTime) screeningSchedule[3];
				LocalDate scheduleDate = scheduleDateTime.toLocalDate();
				YearMonth yearMonth = YearMonth.of(scheduleDate.getYear(), i2);
				if (scheduleDate.getDayOfMonth() <= yearMonth.lengthOfMonth()) {
					LocalDateTime updatedDateTime = scheduleDateTime.withYear(year).withMonth(i2);
					Screen screen = ScreenService.findScreenById((Integer) screeningSchedule[2]);
					Movie movie = MovieService.findMovieById((Integer) screeningSchedule[5]);
					ScreeningScheduleService.addScreeningSchedule(updatedDateTime, screen, movie);
				}
			}
		}
		return "redirect:/data2";
	}

	//  上映スケジュール更新
	@RequestMapping("/ssUpdate")
	public String ssUpdate(@RequestParam(required = false) LocalDate updateDay,
			@RequestParam(required = false) LocalTime updateTime, @RequestParam(required = false) Integer updateId) {
		LocalDateTime updateDatetime = updateDay.atTime(updateTime);
		ScreeningScheduleService.updateScreeningDatetime(updateDatetime, updateId);
		return "redirect:/data2";
	}

	// 管理者ログアウト（Securityなし仮）
	@RequestMapping("/mng_logout")
	public String mng_logout(HttpSession session) {
		session.invalidate();
		return "redirect:/mng_login";
	}

	//  ４次開発  //////////////////////////////////////////////////////////////////////////////////////////////

	//  店頭システムトップではloggedInMemberIdセッションは削除する 
	@RequestMapping("/memberAuth")
	public String memberAuth(Model model, @RequestParam(required = false) String next) {
		model.addAttribute("next", next);
		return "member_auth";
	}

	@RequestMapping("/memberSave")
	public String memberSave(HttpSession session, @RequestParam(required = false) UUID getMemberId,
			@RequestParam(required = false) String next) {
		session.setAttribute("loggedInMemberId", getMemberId);
		return "redirect:" + next;
	}

	// 物販システム  /////////////
	@RequestMapping("/shop")
	public String shop(Model model, HttpSession session) {
		// 会員認証状態を確認
		UUID loggedInMemberId = (UUID) session.getAttribute("loggedInMemberId");
		if (loggedInMemberId == null) {
			return "redirect:/systemtop";
		} else {
			List<Object[]> products = ProductService.findAllProduct();
			model.addAttribute("products", products);
			return "shop";
		}
	}

	@RequestMapping("/shopConf")
	public String shopConf(Model model) {
		return "shop_conf";
	}

	@RequestMapping("/buy")
	public String buy(@RequestBody Map<String, Object> orderData, HttpSession session) {
		UUID memberId = (UUID) session.getAttribute("loggedInMemberId");
		//		  UUID memberId = UUID.fromString("6d78b80b-8207-44a3-8ece-82737e26c74a");

		Member member = MemberService.findMemberById(memberId);
		LocalDateTime orderDatetime = LocalDateTime.now();
		SaleService.saveSale(orderDatetime, member);
		List<Object[]> order = SaleService.findSaleId(orderDatetime);
		Object[] orderElement = order.get(0);
		UUID orderId = (UUID) orderElement[0];
		for (Map.Entry<String, Object> entry : orderData.entrySet()) {
			String productName = entry.getKey();
			Map<String, Object> details = (Map<String, Object>) entry.getValue();
			int quantity = (int) details.get("quantity");
			Sale orderItem = SaleService.findSaleById(orderId);
			List<Object[]> product = ProductService.findProductId(productName);
			Object[] productElement = product.get(0);
			UUID productId = (UUID) productElement[0];
			Product productItem = ProductService.findProductById(productId);
			SaleDetailService.saveSaleDetail(quantity, productItem, orderItem);
		}
		//  メールアドレス取得
		List<Object[]> memberMail = MemberService.findMailaddress(memberId);
		Object[] mailElement = memberMail.get(0);
		String mailAddress = (String) mailElement[0];
		//  会員情報取得
		List<Object[]> memberList = MemberService.findReservationMember(memberId);
		Object[] memberElement = memberList.get(0);
		String memberName = (String) memberElement[0];

		//  メール送信
		String strOrderId = orderId.toString();
		String subject = "HALCINEMA | 購入明細をお届けします";
		EmailService.sendBoughtEmail(mailAddress, subject, memberName, strOrderId, orderData);
		return "redirect:/shopConp";
	}

	@RequestMapping("/shopConp")
	public String shopConp() {
		return "shop_conp";
	}

}
