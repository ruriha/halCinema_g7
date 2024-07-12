$(document).ready(function () {
  $(".content-button1").click(function () {
    $(".more-detailFl1").toggle(500);
  });
});

$(document).ready(function () {
  $(".content-button2").click(function () {
    $(".more-detailFl2").toggle(500);
  });
});

$(document).ready(function () {
  $(".content-button3").click(function () {
    $(".more-detailFl3").toggle(500);
  });
});

$(document).ready(function () {
  $(".content-button4").click(function () {
    $(".more-detailFl4").toggle(500);
  });
});

$(document).ready(function () {
  $(".content-button5").click(function () {
    $(".more-detailFl5").toggle(500);
  });
});

$(document).ready(function () {
  $(".content-button6").click(function () {
    $(".more-detailFl6").toggle(500);
  });
});

$(document).ready(function () {
  $(".content-button7").click(function () {
    $(".more-detailFl7").toggle(500);
  });
});

$(document).ready(function () {
  $(".content-button8").click(function () {
    $(".more-detailFl8").toggle(500);
  });
});

$(".comingShow1").hover(
  function () {
    $(".nowShow1").css("opacity", 0.3);
  },
  function () {
    $(".nowShow1").css("opacity", 1);
  }
);

$(".nowShow2").hover(
  function () {
    $(".comingShow2").css("opacity", 0.3);
  },
  function () {
    $(".comingShow2").css("opacity", 1);
  }
);

$(".comingShow1").click(function () {
  $("html, body").animate(
    {
      scrollTop: $("#choiceBoxFl2").offset().top,
    },
    1000
  );
});

$(".nowShow2").click(function () {
  $("html, body").animate(
    {
      scrollTop: $("#choiceBoxFl1").offset().top,
    },
    1000
  );
});

//カレンダー１
// document.addEventListener('DOMContentLoaded', function() {
//   const scrollLeftButton = document.getElementById('scroll-left1');
//   const scrollRightButton = document.getElementById('scroll-right1');
//   const calendarContainer = document.querySelector('.calendar-container');

//   const scrollAmount = 140; // スクロールする量を設定

//   scrollLeftButton.addEventListener('click', function() {
//       calendarContainer.scrollBy({
//           left: -scrollAmount,
//           behavior: 'smooth'
//       });
//   });

//   scrollRightButton.addEventListener('click', function() {
//       calendarContainer.scrollBy({
//           left: scrollAmount,
//           behavior: 'smooth'
//       });
//   });
// });


// カレンダー１
document.addEventListener('DOMContentLoaded', function() {
  const scrollLeftButton = document.getElementById('scroll-left1');
  const scrollRightButton = document.getElementById('scroll-right1');
  const calendarContainer = document.querySelector('.calendar-container1'); // IDまたはクラスを明確に区別
  const days = Array.from(document.querySelectorAll('.calendar1 .day')); // カレンダー1専用のセレクタ

  let selectedIndex = 0;
  let scrollInterval;
  let isScrolling = false;

  days[selectedIndex].classList.add('select');

  function updateSelection() {
    days.forEach(day => day.classList.remove('selected'));
    days[selectedIndex].classList.add('selected');
  }

  updateSelection();

  scrollRightButton.addEventListener('click', function() {
    if (isScrolling) return;
    isScrolling = true;
    selectedIndex = (selectedIndex + 1) % days.length;
    updateSelection();
    days[selectedIndex].scrollBy({ behavior: 'smooth', block: 'nearest' }); // 正しい関数名に修正
    setTimeout(() => isScrolling = false, 100);
  });

  scrollLeftButton.addEventListener('click', function() {
    if (isScrolling) return;
    selectedIndex = (selectedIndex - 1 + days.length) % days.length;
    updateSelection();
    days[selectedIndex].scrollBy({ behavior: 'smooth', block: 'nearest' }); // 正しい関数名に修正
    setTimeout(() => isScrolling = false, 100);
  });

  scrollRightButton.addEventListener('mousedown', () => {
    scrollInterval = setInterval(() => calendarContainer.scrollBy({ left: 50, behavior: 'smooth' }), 200);
  });

  scrollLeftButton.addEventListener('mousedown', () => {
    scrollInterval = setInterval(() => calendarContainer.scrollBy({ left: -50, behavior: 'smooth' }), 200);
  });

  [scrollLeftButton, scrollRightButton].forEach(button => {
    button.addEventListener('mouseup', () => clearInterval(scrollInterval));
  });
});

// カレンダー２
document.addEventListener('DOMContentLoaded', function() {
  const scrollLeftButton2 = document.getElementById('scroll-left2');
  const scrollRightButton2 = document.getElementById('scroll-right2');
  const calendarContainer2 = document.querySelector('.calendar-container2'); // カレンダー2専用のコンテナ
  const days2 = Array.from(document.querySelectorAll('.calendar2 .day')); // カレンダー2専用のセレクタ

  let selectedIndex2 = 0;
  let scrollInterval2;
  let isScrolling2 = false;

  days2[selectedIndex2].classList.add('select');

  function updateSelection() {
    days2.forEach(day => day.classList.remove('selected'));
    days2[selectedIndex2].classList.add('selected');
  }

  updateSelection();

  scrollRightButton2.addEventListener('click', function() {
    if (isScrolling2) return;
    isScrolling2 = true;
    selectedIndex2 = (selectedIndex2 + 1) % days2.length;
    updateSelection();
    days2[selectedIndex2].scrollBy({ behavior: 'smooth', block: 'nearest' });
    setTimeout(() => isScrolling2 = false, 100);
  });

  scrollLeftButton2.addEventListener('click', function() {
    if (isScrolling2) return;
    selectedIndex2 = (selectedIndex2 - 1 + days2.length) % days2.length;
    updateSelection();
    days2[selectedIndex2].scrollBy({ behavior: 'smooth', block: 'nearest' });
    setTimeout(() => isScrolling2 = false, 100);
  });

  scrollRightButton2.addEventListener('mousedown', () => {
    scrollInterval2 = setInterval(() => calendarContainer2.scrollBy({ left: 50, behavior: 'smooth' }), 200);
  });

  scrollLeftButton2.addEventListener('mousedown', () => {
    scrollInterval2 = setInterval(() => calendarContainer2.scrollBy({ left: -50, behavior: 'smooth' }), 200);
  });

  [scrollLeftButton2, scrollRightButton2].forEach(button => {
    button.addEventListener('mouseup', () => clearInterval(scrollInterval2));
  });
});

// カレンダー３
document.addEventListener('DOMContentLoaded', function() {
  const scrollLeftButton3 = document.getElementById('scroll-left3');
  const scrollRightButton3 = document.getElementById('scroll-right3');
  const calendarContainer3 = document.querySelector('.calendar-container3'); // カレンダー3専用のコンテナ
  const days3 = Array.from(document.querySelectorAll('.calendar3 .day')); // カレンダー3専用のセレクタ

  let selectedIndex3 = 0;
  let scrollInterval3;
  let isScrolling3 = false;

  days3[selectedIndex3].classList.add('select');

  function updateSelection() {
    days3.forEach(day => day.classList.remove('selected'));
    days3[selectedIndex3].classList.add('selected');
  }

  updateSelection();

  scrollRightButton3.addEventListener('click', function() {
    if (isScrolling3) return;
    isScrolling3 = true;
    selectedIndex3 = (selectedIndex3 + 1) % days3.length;
    updateSelection();
    days3[selectedIndex3].scrollBy({ behavior: 'smooth', block: 'nearest' });
    setTimeout(() => isScrolling3 = false, 100);
  });

  scrollLeftButton3.addEventListener('click', function() {
    if (isScrolling3) return;
    selectedIndex3 = (selectedIndex3 - 1 + days3.length) % days3.length;
    updateSelection();
    days3[selectedIndex3].scrollBy({ behavior: 'smooth', block: 'nearest' });
    setTimeout(() => isScrolling3 = false, 100);
  });

  scrollRightButton3.addEventListener('mousedown', () => {
    scrollInterval3 = setInterval(() => calendarContainer3.scrollBy({ left: 50, behavior: 'smooth' }), 200);
  });

  scrollLeftButton3.addEventListener('mousedown', () => {
    scrollInterval3 = setInterval(() => calendarContainer3.scrollBy({ left: -50, behavior: 'smooth' }), 200);
  });

  [scrollLeftButton3, scrollRightButton3].forEach(button => {
    button.addEventListener('mouseup', () => clearInterval(scrollInterval3));
  });
});

// カレンダー４
document.addEventListener('DOMContentLoaded', function() {
  const scrollLeftButton4 = document.getElementById('scroll-left4');
  const scrollRightButton4 = document.getElementById('scroll-right4');
  const calendarContainer4 = document.querySelector('.calendar-container4'); // カレンダー4専用のコンテナ
  const days4 = Array.from(document.querySelectorAll('.calendar4 .day')); // カレンダー4専用のセレクタ

  let selectedIndex4 = 0;
  let scrollInterval4;
  let isScrolling4 = false;

  days4[selectedIndex4].classList.add('select');

  function updateSelection() {
    days4.forEach(day => day.classList.remove('selected'));
    days4[selectedIndex4].classList.add('selected');
  }

  updateSelection();

  scrollRightButton4.addEventListener('click', function() {
    if (isScrolling4) return;
    isScrolling4 = true;
    selectedIndex4 = (selectedIndex4 + 1) % days4.length;
    updateSelection();
    days4[selectedIndex4].scrollBy({ behavior: 'smooth', block: 'nearest' });
    setTimeout(() => isScrolling4 = false, 100);
  });

  scrollLeftButton4.addEventListener('click', function() {
    if (isScrolling4) return;
    selectedIndex4 = (selectedIndex4 - 1 + days4.length) % days4.length;
    updateSelection();
    days4[selectedIndex4].scrollBy({ behavior: 'smooth', block: 'nearest' });
    setTimeout(() => isScrolling4 = false, 100);
  });

  scrollRightButton4.addEventListener('mousedown', () => {
    scrollInterval4 = setInterval(() => calendarContainer4.scrollBy({ left: 50, behavior: 'smooth' }), 200);
  });

  scrollLeftButton4.addEventListener('mousedown', () => {
    scrollInterval4 = setInterval(() => calendarContainer4.scrollBy({ left: -50, behavior: 'smooth' }), 200);
  });

  [scrollLeftButton4, scrollRightButton4].forEach(button => {
    button.addEventListener('mouseup', () => clearInterval(scrollInterval4));
  });
});


