document.addEventListener('DOMContentLoaded', (event) => {
  // 既存の編集ボタンにイベントリスナーを設定
  document.querySelectorAll('.edit').forEach(button => {
    button.addEventListener('click', editRow);
  });

  // 新規追加ボタンにイベントリスナーを設定
  document.getElementById('new_b').addEventListener('click', addRow);
});

// 保存ボタンの処理
function saveRow(event) {
  let button = event.target.closest('button'); // クリックされたボタンを取得
  let row = button.closest('tr');
  let inputs = row.querySelectorAll('input[type="text"], input[type="date"], input[type="number"], input[type="file"], input[type="checkbox"], select');

  // 入力のバリデーションチェック
  let isValid = true;
  inputs.forEach(input => {
    if ((input.type === 'text' || input.type === 'date' || input.type === 'number' || input.tagName === 'SELECT') && input.value.trim() === '') {
      isValid = false;
      input.classList.add('error'); // エラースタイルを追加
    } else if (input.type === 'file' && input.files.length === 0) {
      // 何もしない
    } else {
      input.classList.remove('error'); // エラースタイルを解除
    }
  });

  // 入力が完了していない場合は保存処理をスキップ
  if (!isValid) {
    alert('未入力の項目があります。');
    return;
  }

  // 入力内容をセルに反映
  inputs.forEach(input => {
    let cell = input.parentElement;
    if (input.type === 'text' || input.type === 'date' || input.type === 'number' || input.tagName === 'SELECT') {
      cell.innerText = input.value;
    } else if (input.type === 'file') {
      let file = input.files[0];
      if (file) {
        cell.innerText = file.name; // ファイル名を表示
      } else {
        let originalContent = cell.dataset.originalContent || '';
        cell.innerText = originalContent; // 元の内容に戻す
      }
    } else if (input.type === 'checkbox') {
      let isChecked = input.checked;
      cell.innerHTML = `
        <label class="switch">
          <input type="checkbox" class="cell-input" ${isChecked ? 'checked' : ''} disabled>
          <span class="slider round"></span>
        </label>
      `;
    }
  });

  // ボタンを「編集」に戻す
  clearButtonContent(button);
  let editIcon = document.createElement('i');
  editIcon.classList.add('fas', 'fa-edit');
  button.appendChild(editIcon);
  button.classList.add('edit');
  button.classList.remove('save');
  button.removeEventListener('click', saveRow);
  button.addEventListener('click', editRow);
}

// 編集ボタンの処理
function editRow(event) {
  let button = event.target.closest('button'); // クリックされたボタンを取得
  let row = button.closest('tr');
  row.querySelectorAll('td').forEach((cell, index) => {
    if (index === 0) { // タイトル
      let currentText = cell.innerText.trim();
      let options = ['タイトル１', 'タイトル２', 'タイトル３'];
      let selectHTML = '<select class="cell-input">';
      options.forEach(option => {
        selectHTML += `<option ${option === currentText ? 'selected' : ''}>${option}</option>`;
      });
      selectHTML += '</select>';
      cell.innerHTML = selectHTML;
    } else if (index === 1) { // スクリーン
      let currentText = cell.innerText.trim();
      let options = ['1', '2', '3'];
      let selectHTML = '<select class="cell-input">';
      options.forEach(option => {
        selectHTML += `<option ${option === currentText ? 'selected' : ''}>${option}</option>`;
      });
      selectHTML += '</select>';
      cell.innerHTML = selectHTML;
    } else if (index === 2) { // 上映日
      let currentDate = cell.innerText.trim();
      cell.innerHTML = `<input type="date" class="cell-input" value="${currentDate}">`;
    } else if (index === 3) { // 上映開始時間
      let currentText = cell.innerText.trim();
      let options = ['13:00', '15:00', '18:00'];
      let selectHTML = '<select class="cell-input">';
      options.forEach(option => {
        selectHTML += `<option ${option === currentText ? 'selected' : ''}>${option}</option>`;
      });
      selectHTML += '</select>';
      cell.innerHTML = selectHTML;
    } else if (index === 4) { // 上映終了時間
      let currentText = cell.innerText.trim();
      cell.innerHTML = `<input type="text" class="cell-input" value="${currentText}">`;
    } else if (index === 5) { // 編集/削除
      // 何もしない
    }
  });

  // ボタンを「保存」に変更
  clearButtonContent(button);
  let saveIcon = document.createElement('i');
  saveIcon.classList.add('fas', 'fa-save');
  button.appendChild(saveIcon);
  button.classList.add('save');
  button.classList.remove('edit');
  button.removeEventListener('click', editRow);
  button.addEventListener('click', saveRow);
}

// 行の追加
function addRow() {
  let table = document.querySelector('table');
  let newRow = table.insertRow();

  let titleCell = newRow.insertCell(0);
  titleCell.innerHTML = `
    <select class="cell-input">
      <option>タイトル１</option>
      <option>タイトル２</option>
      <option>タイトル３</option>
    </select>
  `;

  let screenCell = newRow.insertCell(1);
  screenCell.innerHTML = `
    <select class="cell-input">
      <option>1</option>
      <option>2</option>
      <option>3</option>
    </select>
  `;

  let dayCell = newRow.insertCell(2);
  dayCell.innerHTML = `<input type="date" class="cell-input">`;

  let openTimeCell = newRow.insertCell(3);
  openTimeCell.innerHTML = `
    <select class="cell-input">
      <option>13:00</option>
      <option>15:00</option>
      <option>18:00</option>
    </select>
  `;

  let closeTimeCell = newRow.insertCell(4);
  closeTimeCell.innerHTML = `<input type="text" class="cell-input">`;

  let btnCell = newRow.insertCell(5);
  btnCell.innerHTML = `
    <div class="btn_g">
      <button class="save"><i class="fas fa-save"></i></button>
      <button class="del"><i class="fas fa-trash-alt"></i></button>
    </div>
  `;

  // 新しい保存ボタンにイベントリスナーを設定
  btnCell.querySelector('.save').addEventListener('click', saveRow);

  // 新しい削除ボタンにイベントリスナーを設定
  btnCell.querySelector('.del').addEventListener('click', deleteRow);
}

// 行の削除
function deleteRow(event) {
  let button = event.target.closest('button'); // クリックされたボタンを取得
  let row = button.closest('tr');
  row.remove();
}

// ボタンの内容をクリア
function clearButtonContent(button) {
  while (button.firstChild) {
    button.removeChild(button.firstChild);
  }
}
