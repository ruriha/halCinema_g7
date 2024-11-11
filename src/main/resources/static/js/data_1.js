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
    event.stopPropagation(); // イベントの伝播を止める
 
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
            <input type="checkbox" class="cell-input" ${isChecked ? 'checked' : ''}>
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
 
    // 保存後にチェックボックスを無効化する
    let checkbox = row.querySelector('input[type="checkbox"]');
    if (checkbox) {
      checkbox.disabled = true;
    }
  }
 
  // 編集ボタンの処理
  function editRow(event) {
    event.stopPropagation(); // イベントの伝播を止める
 
    let button = event.target.closest('button'); // クリックされたボタンを取得
    let row = button.closest('tr');
    row.querySelectorAll('td').forEach((cell, index) => {
      if (index == 0) {
        let content = cell.innerText;
        cell.innerHTML = `<input type="text" value="${content}" class="cell-input">`;
      } else if (index == 1) {
        let content = cell.innerText;
        cell.innerHTML = `<input type="date" value="${content}" class="cell-input">`;
      } else if (index == 2) {
        let content = cell.innerText;
        cell.innerHTML = `<input type="number" value="${content}" class="cell-input">`;
      } else if (index == 3) {
        let content = cell.innerText;
        cell.innerHTML = `<input type="text" value="${content}" class="cell-input">`;
      } else if (index == 4) {
        let content = cell.innerText.trim();
        cell.dataset.originalContent = content; // 元の内容を保存
        cell.innerHTML = `<input type="file" class="cell-input">`;
      } else if (index == 5) {
        let isChecked = cell.querySelector('input[type="checkbox"]').checked;
        cell.innerHTML = `
          <label class="switch">
            <input type="checkbox" class="cell-input" ${isChecked ? 'checked' : ''}>
            <span class="slider round"></span>
          </label>
        `;
      }
    });
 
    clearButtonContent(button);
    let saveIcon = document.createElement('i');
    saveIcon.classList.add('fas', 'fa-save');
    button.appendChild(saveIcon);
    button.classList.add('save');
    button.classList.remove('edit');
    button.removeEventListener('click', editRow);
    button.addEventListener('click', saveRow);
 
    // 編集時にチェックボックスを有効化する
    let checkbox = row.querySelector('input[type="checkbox"]');
    if (checkbox) {
      checkbox.disabled = false;
      checkbox.nextElementSibling.classList.add('active');
    }
  }
 
  // 新規追加ボタンの処理
  function addRow() {
    let table = document.querySelector('table');
    let rowCount = table.rows.length;
    let newRow = table.insertRow(rowCount);
 
    let titleCell = newRow.insertCell(0);
    titleCell.innerHTML = `<input type="text" class="cell-input">`;
    let dayCell = newRow.insertCell(1);
    dayCell.innerHTML = `<input type="date" class="cell-input">`;
    let timeCell = newRow.insertCell(2);
    timeCell.innerHTML = `<input type="number" class="cell-input">`;
    let textCell = newRow.insertCell(3);
    textCell.innerHTML = `<input type="text" class="cell-input">`;
    let imgCell = newRow.insertCell(4);
    imgCell.innerHTML = `<input type="file" class="cell-input">`;
    let checkCell = newRow.insertCell(5);
    checkCell.innerHTML = `
      <label class="switch">
        <input type="checkbox" class="cell-input">
        <span class="slider round"></span>
      </label>
    `; // 公開状態のトグルボタン
    let btnCell = newRow.insertCell(6);
    btnCell.innerHTML = `
      <div class="btn_g">
        <button class="save"><i class="fas fa-save"></i></button>
        <button class="del"><i class="fas fa-trash-alt"></i></button>
      </div>
    `;
    btnCell.querySelector('.save').addEventListener('click', saveRow);
 
    // 新しい削除ボタンにイベントリスナーを設定
    btnCell.querySelector('.del').addEventListener('click', deleteRow);
  }
 
    // セルを挿入し、各入力フィールドを追加
  //   for (let i = 0; i < 6; i++) {
  //     let newCell = newRow.insertCell(i);
  //     if (i == 0) {
  //       newCell.innerHTML = `<input type="text" class="cell-input">`; // タイトルの入力フィールド
  //     } else if (i == 1) {
  //       newCell.innerHTML = `<input type="date" class="cell-input">`; // 公開日の入力フィールド
  //     } else if (i == 2) {
  //       newCell.innerHTML = `<input type="number" class="cell-input">`; // 上映時間の入力フィールド
  //     } else if (i == 3) {
  //       newCell.innerHTML = `<input type="text" class="cell-input">`; // 作品詳細の入力フィールド
  //     } else if (i == 4) {
  //       newCell.innerHTML = `<input type="file" class="cell-input">`; // 商品画像の入力フィールド
  //     } else if (i == 5) {
  //       newCell.innerHTML = `
  //         <label class="switch">
  //           <input type="checkbox" class="cell-input">
  //           <span class="slider round"></span>
  //         </label>
  //       `; // 公開状態のトグルボタン
  //     }
  //   }
 
  //   // 新しいセルに保存ボタンと削除ボタンを追加
  //   let btnCell = newRow.insertCell(6);
  //   let saveBtn = document.createElement('button');
  //   saveBtn.innerHTML = '<i class="fas fa-save"></i>';
  //   saveBtn.classList.add('save'); // .save-btn クラスを追加
  //   saveBtn.addEventListener('click', saveRow);
  //   btnCell.appendChild(saveBtn);
 
  //   let delBtn = document.createElement('button');
  //   delBtn.innerHTML = '<i class="fas fa-trash-alt"></i>';
  //   delBtn.classList.add('del','del_2'); // .del-btn クラスを追加
  //   delBtn.addEventListener('click', deleteRow);
  //   btnCell.appendChild(delBtn);
  // }
  // 削除ボタンの処理
  function deleteRow(event) {
    let button = event.target.closest('button'); // クリックされたボタンを取得
    let row = button.closest('tr');
    row.remove();
  }
 
  // ボタン内の内容をクリアする関数
  function clearButtonContent(button) {
    while (button.firstChild) {
      button.removeChild(button.firstChild);
    }
  }

document.addEventListener("DOMContentLoaded", function() {
    document.querySelectorAll(".del").forEach(button => {
        button.addEventListener("click", function() {
            const row = this.closest("tr");
            const movieId = row.querySelector(".title").getAttribute("data-id");

            if (confirm("本当に削除しますか？")) {
                fetch(`/movies/${movieId}`, {
                    method: "DELETE"
                }).then(response => {
                    if (response.ok) {
                        row.remove();  // 行を削除
                        alert("削除しました。");
                    } else {
                        alert("削除に失敗しました。");
                    }
                });
            }
        });
    });
});
