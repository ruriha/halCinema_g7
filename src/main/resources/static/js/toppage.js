//スライド
document.addEventListener('DOMContentLoaded', () => {
    const slides = document.querySelectorAll('.slide');
    const slideWrapper = document.querySelector('.slide-wrapper');
    let currentIndex = 0;

    const showSlide = (index) => {
        const offset = -index * 100;
        slideWrapper.style.transform = `translateX(${offset}%)`;
    };

    document.getElementById('next').addEventListener('click', () => {
        currentIndex = (currentIndex + 1) % slides.length;
        showSlide(currentIndex);
    });

    document.getElementById('prev').addEventListener('click', () => {
        currentIndex = (currentIndex - 1 + slides.length) % slides.length;
        showSlide(currentIndex);
    });
});


//NEWSの追加表示
document.getElementById('more').addEventListener('click', () => {
    const hiddenItems = document.querySelectorAll('.hidden');
    const button = document.getElementById('more');

    //more・lessの切り替え
    if (hiddenItems.length > 0) {
        hiddenItems.forEach(item => item.classList.remove('hidden'));
        button.textContent = 'close';
    } else {
        const allItems = document.querySelectorAll('.news_list_item');
        // Re-hide the extra items
        for (let i = 5; i < allItems.length; i++) {
            allItems[i].classList.add('hidden');
        }
        button.textContent = 'open';
    }
});
  
  // //カレンダー１
  document.addEventListener('DOMContentLoaded', function() {
    const scrollLeftButton = document.getElementById('scroll-left1');
    const scrollRightButton = document.getElementById('scroll-right1');
    const calendarContainer = document.querySelector('.calendar-container');
  
    const scrollAmount = 140; // スクロールする量を設定
    let scrollInterval; // スクロールを制御するためのインターバルを保存する変数
  
    scrollLeftButton.addEventListener('mousedown', function() {
        // 既存のインターバルがあればクリアする
        if (scrollInterval) clearInterval(scrollInterval);
        // 新しいインターバルを設定する
        scrollInterval = setInterval(function() {
            calendarContainer.scrollBy({
                left: -scrollAmount,
                behavior: 'smooth'
            });
        }, 300); // 100ミリ秒ごとにスクロール
    });
  
    scrollRightButton.addEventListener('mousedown', function() {
        // 既存のインターバルがあればクリアする
        if (scrollInterval) clearInterval(scrollInterval);
        // 新しいインターバルを設定する
        scrollInterval = setInterval(function() {
            calendarContainer.scrollBy({
                left: scrollAmount,
                behavior: 'smooth'
            });
        }, 300); // 100ミリ秒ごとにスクロール
    });
  
    // ボタンからマウスが離れたときにスクロールを停止する
    scrollLeftButton.addEventListener('mouseup', function() {
        clearInterval(scrollInterval);
    });
    scrollRightButton.addEventListener('mouseup', function() {
        clearInterval(scrollInterval);
    });
  });
  
  //カレンダー２
  document.addEventListener('DOMContentLoaded', function() {
    const scrollLeftButton = document.getElementById('scroll-left2');
    const scrollRightButton = document.getElementById('scroll-right2');
    const calendarContainer = document.querySelector('.calendar-container');
  
    const scrollAmount = 200; // スクロールする量を設定
  
    scrollLeftButton.addEventListener('click', function() {
        calendarContainer.scrollBy({
            left: -scrollAmount,
            behavior: 'smooth'
        });
    });
  
    scrollRightButton.addEventListener('click', function() {
        calendarContainer.scrollBy({
            left: scrollAmount,
            behavior: 'smooth'
        });
    });
  });

// 作品一覧
document.querySelectorAll('.dropdown-content a').forEach(anchor => {
    anchor.addEventListener('click', function(e) {
        e.preventDefault();
        const targetId = this.getAttribute('href').substring(1);
        const targetElement = document.getElementById(targetId);
        targetElement.scrollIntoView({ behavior: 'smooth' });
    });
});


