document.addEventListener("DOMContentLoaded", function() {
    // localStorage から保存したデータを取得
    const name = localStorage.getItem('name');
    const kana = localStorage.getItem('kana');
    const birthdate = localStorage.getItem('birthdate');
    const phone = localStorage.getItem('phone');
    const gender = localStorage.getItem('gender');
    const postal = localStorage.getItem('postal');
    const address = localStorage.getItem('address');

    // それぞれの確認用フィールドに表示
//    document.getElementById('confirm-name').textContent = name || '未入力';
//    document.getElementById('confirm-kana').textContent = kana || '未入力';
//    document.getElementById('confirm-birthdate').textContent = birthdate || '未入力';
//    document.getElementById('confirm-phone').textContent = phone || '未入力';
//    document.getElementById('confirm-gender').textContent = gender === 'male' ? '男性' : gender === 'female' ? '女性' : '回答しない';
//    document.getElementById('confirm-postal').textContent = postal || '未入力';
//    document.getElementById('confirm-address').textContent = address || '未入力';

    // 修正するボタンの動作
    document.getElementById('back-to-form').addEventListener('click', function() {
        // 入力フォームに戻る
        window.location.href = 'index.html'; // index.html は元のフォームページに置き換えてください
    });

    // 登録を確定するボタンの動作
//    document.getElementById('confirm-registration').addEventListener('click', function() {
//        // 確認画面で登録確定処理（例：サーバーへの送信など）
//        alert('登録が確定しました！');
//        localStorage.clear(); // 登録が完了したら localStorage をクリア
//        window.location.href = 'card.html'; // サンクスページに遷移（thankyou.html は登録完了後のページ）
//    });
});
