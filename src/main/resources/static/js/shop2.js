// document.addEventListener('DOMContentLoaded', () => {
//   const items = document.querySelectorAll('.product-card');

//   const itemData = {};

//   items.forEach(item => {
//       const title = item.querySelector('.product-title')?.textContent.trim();
//       const priceText = item.querySelector('.product-price')?.textContent.trim();
//       const price = priceText?.replace('¥', '').trim();
//       const quantityInput = item.querySelector('.quantity-input');

//       if (title && price && quantityInput) {
//           if (itemData[title] !== undefined) {
//               itemData[title].push({ title, price, quantityInput });
//           } else {
//               itemData[title] = [{ title, price, quantityInput }];
//           }
//       }

//       const plusButton = item.querySelector('.plus');
//       const minusButton = item.querySelector('.minus');

//       plusButton.addEventListener('click', () => {
//           let currentValue = parseInt(quantityInput.value, 10);
//           quantityInput.value = currentValue + 1;
//       });

//       minusButton.addEventListener('click', () => {
//           let currentValue = parseInt(quantityInput.value, 10);
//           if (currentValue > 0) {
//               quantityInput.value = currentValue - 1;
//           }
//       });
//   });

//   // 注文内容確認ボタンのイベントリスナー
//   document.querySelector('#kakunin')?.addEventListener('click', () => {
//       const orderData = {};

//       // 各商品のデータを取得し、オブジェクトに保存
//       document.querySelectorAll('.product-card').forEach(item => {
//           const title = item.querySelector('.product-title')?.textContent.trim();
//           const priceText = item.querySelector('.product-price')?.textContent.replace('¥', '').replace(',', '').trim();
//           const quantity = parseInt(item.querySelector('.quantity-input')?.value, 10) || 0;
//           const price = parseInt(priceText, 10) || 0;
          
//           if (quantity > 0) {
//               orderData[title] = {
//                   quantity: quantity,
//                   price: price,
//                   total: price * quantity
//               };
//           }
//       });

//       // sessionStorageにデータを保存
//       sessionStorage.setItem('orderData', JSON.stringify(orderData));

//       // shop_conf.htmlへ遷移
//       window.location.href = 'shop_conf.html';
//   });
// });

// shop.js
document.addEventListener('DOMContentLoaded', () => {
    const items = document.querySelectorAll('.product-card');
    
    // セッションストレージから既存の商品データを取得
    let storedOrderData = JSON.parse(sessionStorage.getItem('orderData')) || {};

    // 商品カードの初期化と数量の復元
    items.forEach(item => {
        const title = item.querySelector('.product-title')?.textContent.trim();
        const priceText = item.querySelector('.product-price')?.textContent.trim();
        const price = parseInt(priceText?.replace('¥', '').replace(',', '').trim(), 10);
        const quantityInput = item.querySelector('.quantity-input');

        // 保存されていた数量があれば復元
        if (storedOrderData[title]) {
            quantityInput.value = storedOrderData[title].quantity;
        }

        const plusButton = item.querySelector('.plus');
        const minusButton = item.querySelector('.minus');

        // プラスボタンのイベントリスナー
        plusButton.addEventListener('click', () => {
            let currentValue = parseInt(quantityInput.value, 10);
            quantityInput.value = currentValue + 1;
            updateOrderData(title, price, currentValue + 1);
        });

        // マイナスボタンのイベントリスナー
        minusButton.addEventListener('click', () => {
            let currentValue = parseInt(quantityInput.value, 10);
            if (currentValue > 0) {
                quantityInput.value = currentValue - 1;
                updateOrderData(title, price, currentValue - 1);
            }
        });
    });

    // 注文データの更新関数
    function updateOrderData(title, price, quantity) {
        let orderData = JSON.parse(sessionStorage.getItem('orderData')) || {};
        
        if (quantity > 0) {
            orderData[title] = {
                quantity: quantity,
                price: price,
                total: price * quantity
            };
        } else {
            // 数量が0の場合は項目を削除
            delete orderData[title];
        }

        sessionStorage.setItem('orderData', JSON.stringify(orderData));
    }

    // カテゴリー切り替え時のデータ保持
    const categoryButtons = document.querySelectorAll('.category-btn');
    categoryButtons.forEach(button => {
        button.addEventListener('click', () => {
            // 現在のページの商品データを保存してから画面遷移
            const currentOrderData = {};
            items.forEach(item => {
                const title = item.querySelector('.product-title')?.textContent.trim();
                const priceText = item.querySelector('.product-price')?.textContent.replace('¥', '').replace(',', '').trim();
                const quantity = parseInt(item.querySelector('.quantity-input')?.value, 10) || 0;
                const price = parseInt(priceText, 10) || 0;
                
                if (quantity > 0) {
                    currentOrderData[title] = {
                        quantity: quantity,
                        price: price,
                        total: price * quantity
                    };
                }
            });
            
            // 既存のデータとマージ
            const mergedOrderData = { ...storedOrderData, ...currentOrderData };
            sessionStorage.setItem('orderData', JSON.stringify(mergedOrderData));
        });
    });

    // 注文内容確認ボタンのイベントリスナー
    document.querySelector('#kakunin')?.addEventListener('click', () => {
        // 現在のページの商品データを最終確認
        const finalOrderData = JSON.parse(sessionStorage.getItem('orderData')) || {};
        
        // カート内に商品があるか確認
        if (Object.keys(finalOrderData).length === 0) {
            alert('商品が選択されていません。');
            return;
        }

        // 確認ページへ遷移
        window.location.href = 'shop_conf.html';
    });
});