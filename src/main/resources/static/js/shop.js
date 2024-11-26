document.addEventListener('DOMContentLoaded', () => {
  const items = document.querySelectorAll('.gaiwaku');


  const itemData = {};

  items.forEach(item => {
    const title = item.querySelector('.title')?.textContent.trim();
    const price = item.querySelector('.nedan')?.textContent.trim();
    const quantityInput = item.querySelector('.text');

    if (title && price && quantityInput) {
      if (itemData[title] !== undefined) {
        itemData[title].push({ title, price, quantityInput });
      } else {
        console.warn(`未定義のタイトル：${title}`);
      }
    } else {
      console.warn('必要なデータが取得できませんでした。', { title, price, quantityInput });
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

});


document.querySelector('#kakunin').addEventListener('click', () => {
  const orderData = {};

  // 各商品のデータを取得し、オブジェクトに保存
  document.querySelectorAll('.gaiwaku').forEach(item => {
    const title = item.querySelector('.title')?.textContent.trim();
    const priceText = item.querySelector('.nedan')?.textContent.replace('¥', '').trim();
    const quantity = parseInt(item.querySelector('.text')?.value, 10) || 0;
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
//  window.location.href = 'shop_conf.html';
});