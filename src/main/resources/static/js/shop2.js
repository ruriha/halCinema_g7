document.addEventListener('DOMContentLoaded', () => {
  const items = document.querySelectorAll('.product-card');

  const itemData = {};

  items.forEach(item => {
      const title = item.querySelector('.product-title')?.textContent.trim();
      const priceText = item.querySelector('.product-price')?.textContent.trim();
      const price = priceText?.replace('¥', '').trim();
      const quantityInput = item.querySelector('.quantity-input');

      if (title && price && quantityInput) {
          if (itemData[title] !== undefined) {
              itemData[title].push({ title, price, quantityInput });
          } else {
              itemData[title] = [{ title, price, quantityInput }];
          }
      }

      const plusButton = item.querySelector('.plus');
      const minusButton = item.querySelector('.minus');

      plusButton.addEventListener('click', () => {
          let currentValue = parseInt(quantityInput.value, 10);
          quantityInput.value = currentValue + 1;
      });

      minusButton.addEventListener('click', () => {
          let currentValue = parseInt(quantityInput.value, 10);
          if (currentValue > 0) {
              quantityInput.value = currentValue - 1;
          }
      });
  });

  // 注文内容確認ボタンのイベントリスナー
  document.querySelector('#kakunin')?.addEventListener('click', () => {
      const orderData = {};

      // 各商品のデータを取得し、オブジェクトに保存
      document.querySelectorAll('.product-card').forEach(item => {
          const title = item.querySelector('.product-title')?.textContent.trim();
          const priceText = item.querySelector('.product-price')?.textContent.replace('¥', '').replace(',', '').trim();
          const quantity = parseInt(item.querySelector('.quantity-input')?.value, 10) || 0;
          const price = parseInt(priceText, 10) || 0;
          
          if (quantity > 0) {
              orderData[title] = {
                  quantity: quantity,
                  price: price,
                  total: price * quantity
              };
          }
      });

      // sessionStorageにデータを保存
      sessionStorage.setItem('orderData', JSON.stringify(orderData));

      // shop_conf.htmlへ遷移
      window.location.href = 'shop_conf.html';
  });
});