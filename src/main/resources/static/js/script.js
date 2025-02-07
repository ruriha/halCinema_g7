document.addEventListener('DOMContentLoaded', function () {
    const yearSelect = document.getElementById('year');
    const monthSelect = document.getElementById('month');
    const daySelect = document.getElementById('day');
    const prefectureSelect = document.getElementById('prefecture');

    // 年の生成 (1900年～現在年まで)
    const currentYear = new Date().getFullYear();
    for (let year = 1900; year <= currentYear; year++) {
        const option = document.createElement('option');
        option.value = year;
        option.textContent = year + '年';
        yearSelect.appendChild(option);
    }

    // 月の生成 (1月～12月)
    for (let month = 1; month <= 12; month++) {
        const option = document.createElement('option');
        option.value = month;
        option.textContent = month + '月';
        monthSelect.appendChild(option);
    }

    // 日の生成 (1日～31日)
    function populateDays(year, month) {
        daySelect.innerHTML = '<option value="">日</option>'; // 初期化
        const daysInMonth = new Date(year, month, 0).getDate(); // 月の日数を取得
        for (let day = 1; day <= daysInMonth; day++) {
            const option = document.createElement('option');
            option.value = day;
            option.textContent = day + '日';
            daySelect.appendChild(option);
        }
    }

    // 年または月が選択されたとき、日にちを更新
    yearSelect.addEventListener('change', () => {
        const year = parseInt(yearSelect.value);
        const month = parseInt(monthSelect.value);
        if (!isNaN(year) && !isNaN(month)) {
            populateDays(year, month);
        }
    });

    monthSelect.addEventListener('change', () => {
        const year = parseInt(yearSelect.value);
        const month = parseInt(monthSelect.value);
        if (!isNaN(year) && !isNaN(month)) {
            populateDays(year, month);
        }
    });

    // 都道府県リストの生成
    const prefectures = [
        "北海道", "青森県", "岩手県", "宮城県", "秋田県", "山形県", "福島県", 
        "茨城県", "栃木県", "群馬県", "埼玉県", "千葉県", "東京都", "神奈川県",
        "新潟県", "富山県", "石川県", "福井県", "山梨県", "長野県", 
        "岐阜県", "静岡県", "愛知県", "三重県",
        "滋賀県", "京都府", "大阪府", "兵庫県", "奈良県", "和歌山県",
        "鳥取県", "島根県", "岡山県", "広島県", "山口県",
        "徳島県", "香川県", "愛媛県", "高知県",
        "福岡県", "佐賀県", "長崎県", "熊本県", "大分県", "宮崎県", "鹿児島県", "沖縄県"
    ];

    prefectures.forEach(prefecture => {
        const option = document.createElement('option');
        option.value = prefecture;
        option.textContent = prefecture;
        prefectureSelect.appendChild(option);
    });
});
