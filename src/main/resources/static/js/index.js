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
        button.textContent = 'back -';
    } else {
        const allItems = document.querySelectorAll('.news_list_item');
        // Re-hide the extra items
        for (let i = 5; i < allItems.length; i++) {
            allItems[i].classList.add('hidden');
        }
        button.textContent = 'more +';
    }
});



document.addEventListener('DOMContentLoaded', (event) => {
    const loginBtn = document.getElementById('loginBtn');
    const loginPopup = document.getElementById('loginPopup');
    const closeBtn = document.querySelector('.close');

    loginBtn.addEventListener('click', () => {
        loginPopup.style.display = 'block';
    });

    closeBtn.addEventListener('click', () => {
        loginPopup.style.display = 'none';
    });

    window.addEventListener('click', (event) => {
        if (event.target == loginPopup) {
            loginPopup.style.display = 'none';
        }
    });
});