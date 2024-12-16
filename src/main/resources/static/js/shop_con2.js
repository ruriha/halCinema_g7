document.addEventListener('DOMContentLoaded', () => {
// sessionStorageから注文データを取得
const orderData = JSON.parse(sessionStorage.getItem('orderData')) || {};

// 表のtbodyにデータを追加
const tableBody = document.querySelector('table').getElementsByTagName('tbody')[0];
let totalAmount = 0;

Object.keys(orderData).forEach(title => {
    const item = orderData[title];
    const row = document.createElement('tr');
    
    // 商品名セル
    const titleCell = document.createElement('td');
    titleCell.textContent = title;
    row.appendChild(titleCell);
    
    // 個数セル
    const quantityCell = document.createElement('td');
    quantityCell.textContent = item.quantity;
    row.appendChild(quantityCell);
    
    // 価格セル
    const priceCell = document.createElement('td');
    priceCell.textContent = item.total;
    row.appendChild(priceCell);

    tableBody.appendChild(row);

    // 合計金額を計算
    totalAmount += item.total;
});

// 合計金額を表示
document.getElementById('kane').textContent = `合計：${totalAmount}円`;
});