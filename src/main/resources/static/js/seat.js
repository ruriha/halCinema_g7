document.addEventListener('DOMContentLoaded', (event) => {
    const table = document.getElementById('myTable');
    const selectedCell1NumberInput = document.getElementById('selectedCell1Number');
    const selectedCell1ContentInput = document.getElementById('selectedCell1Content');
    const selectedCell2NumberInput = document.getElementById('selectedCell2Number');
    const selectedCell2ContentInput = document.getElementById('selectedCell2Content');

    table.addEventListener('click', function(event) {
        if (event.target.tagName === 'TD' && !event.target.classList.contains('unselectable')) {
            const rowIndex = event.target.parentElement.rowIndex;
            const cellIndex = event.target.cellIndex;
            const cellNumber = rowIndex * table.rows[0].cells.length + cellIndex + 1;
            const cellContent = event.target.textContent.trim();

            // セルの内容が数字かどうかをチェック
            if (!isNaN(cellContent)) {
                const selectedCells = table.querySelectorAll('.selected');

                if (selectedCells.length < 2 || event.target.classList.contains('selected')) {
                    event.target.classList.toggle('selected');

                    const selectedCells = Array.from(table.querySelectorAll('.selected'));

                    // 選択されたセルのデータを更新
                    if (selectedCells.length > 0) {
                        const firstCell = selectedCells[0];
                        const firstRowIndex = firstCell.parentElement.rowIndex;
                        const firstCellIndex = firstCell.cellIndex;
                        const firstCellNumber = firstRowIndex * table.rows[0].cells.length + firstCellIndex + 1;
                        //文字追加の判定
                        if(firstCellNumber >= 74){
                            selectedCell1ContentInput.value = "G" + firstCell.textContent.trim();
                        }else if(firstCellNumber >= 62){
                            selectedCell1ContentInput.value = "F" + firstCell.textContent.trim();
                        }else if(firstCellNumber >= 50){
                            selectedCell1ContentInput.value = "E" + firstCell.textContent.trim();
                        }else if(firstCellNumber >= 38){
                            selectedCell1ContentInput.value = "D" + firstCell.textContent.trim();
                        }else if(firstCellNumber >= 26){
                            selectedCell1ContentInput.value = "C" + firstCell.textContent.trim();
                        }else if(firstCellNumber >= 14){
                            selectedCell1ContentInput.value = "B" + firstCell.textContent.trim();
                        }else if(firstCellNumber >= 1){
                            selectedCell1ContentInput.value = "A" + firstCell.textContent.trim();
                        }
                        selectedCell1NumberInput.value = firstCellNumber;
                    } else {
                        selectedCell1NumberInput.value = '';
                        selectedCell1ContentInput.value = '';
                    }

                    if (selectedCells.length > 1) {
                        const secondCell = selectedCells[1];
                        const secondRowIndex = secondCell.parentElement.rowIndex;
                        const secondCellIndex = secondCell.cellIndex;
                        const secondCellNumber = secondRowIndex * table.rows[0].cells.length + secondCellIndex + 1;
                        selectedCell2NumberInput.value = secondCellNumber;
                        //文字追加の判定
                        if(secondCellNumber >= 74){
                            selectedCell2ContentInput.value = "G" + secondCell.textContent.trim();
                        }else if(secondCellNumber >= 62){
                            selectedCell2ContentInput.value = "F" + secondCell.textContent.trim();
                        }else if(secondCellNumber >= 50){
                            selectedCell2ContentInput.value = "E" + secondCell.textContent.trim();
                        }else if(secondCellNumber >= 38){
                            selectedCell2ContentInput.value = "D" + secondCell.textContent.trim();
                        }else if(secondCellNumber >= 26){
                            selectedCell2ContentInput.value = "C" + secondCell.textContent.trim();
                        }else if(secondCellNumber >= 14){
                            selectedCell2ContentInput.value = "B" + secondCell.textContent.trim();
                        }else if(secondCellNumber >= 1){
                            selectedCell2ContentInput.value = "A" + secondCell.textContent.trim();
                        }
                    } else {
                        selectedCell2NumberInput.value = '';
                        selectedCell2ContentInput.value = '';
                    }
                }
            }
        }
    });
});



function generateContentLabel(cellNumber) {
    if (cellNumber >= 20) {
        return `B-${cellNumber}`;
    } else if (cellNumber >= 10) {
        return `A-${cellNumber}`;
    } else {
        return `${cellNumber}`;
    }
}



function goBack() {
    window.history.back();
}
