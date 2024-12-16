document.getElementById('payment-form').addEventListener('submit', function(event) {
    event.preventDefault(); // フォームの送信を防ぐ（テスト目的）

    // カード番号のバリデーション（16桁であることを確認）
    const cardNumber = document.getElementById('card-number').value.replace(/\s+/g, '');
    if (cardNumber.length !== 16 || isNaN(cardNumber)) {
        alert('カード番号は16桁の数字で入力してください。');
        return;
    }

    // カード名義のバリデーション
    const cardName = document.getElementById('card-name').value;
    if (cardName.trim() === '') {
        alert('カード名義を入力してください。');
        return;
    }

    // 有効期限のバリデーション（未来の日付であるかを確認）
    const expiryMonth = document.getElementById('expiry-month').value;
    const expiryYear = document.getElementById('expiry-year').value;
    const currentDate = new Date();
    const expiryDate = new Date(expiryYear, expiryMonth - 1); // 月は0始まりなので-1

    if (expiryDate < currentDate) {
        alert('有効期限が切れています。');
        return;
    }

    // セキュリティコードのバリデーション（3桁の数字であることを確認）
    const securityCode = document.getElementById('security-code').value;
    if (securityCode.length !== 3 || isNaN(securityCode)) {
        alert('セキュリティコードは3桁の数字で入力してください。');
        return;
    }

    // すべてのバリデーションが通った場合、成功メッセージを表示
    alert('決済が完了しました！');
});
