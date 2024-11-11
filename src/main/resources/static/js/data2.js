function toggleUpdateField(button) {
    const row = button.closest("tr");
    const updateOpenField = row.querySelector(".updateOpenField");
    const beforeOpenField = row.querySelector(".beforeOpenField");
    const beforeClose = row.querySelector(".beforeClose");
    const afterClose = row.querySelector(".afterClose");
    const edit = row.querySelector(".edit");
    const save3 = row.querySelector(".save3");
    const updateTimeEdit = row.querySelector(".updateTimeEdit");

    if (updateOpenField.style.display === "" && edit.style.display === "") {
        updateOpenField.style.display = "none";
        edit.style.display = "block";
    }
    if (beforeOpenField.style.display === "" && save3.style.display === "") {
        beforeOpenField.style.display = "block";
        save3.style.display = "none";
    }
    edit.style.display = edit.style.display === "none" ? "block" : "none";
    save3.style.display = edit.style.display === "none" ? "block" : "none";
    updateOpenField.style.display = updateOpenField.style.display === "none" ? "block" : "none";
    beforeOpenField.style.display = updateOpenField.style.display === "none" ? "block" : "none";

    updateOpenField.addEventListener("change", function() {
        const runningTimeField = row.querySelector(".runningTimeField");
        afterClose.style.display = "block";
        beforeClose.style.display = "none";
        const selected = updateOpenField.value;
		updateTimeEdit.value = selected;
        const [hours, minutes] = selected.split(":");
        const openTime = new Date();
        openTime.setHours(parseInt(hours));
        openTime.setMinutes(parseInt(minutes));
        const runningTime = parseInt(runningTimeField.value);
        const closeTime = new Date(openTime.getTime() + runningTime * 60000);
        const closeHours = closeTime.getHours().toString().padStart(2, '0');
        const closeMinutes = closeTime.getMinutes().toString().padStart(2, '0');
        afterClose.textContent = `${closeHours}:${closeMinutes}`;
    });


    save3.addEventListener("click", function() {
        edit.style.display = "block";
        save3.style.display = "none";
        updateOpenField.style.display = "none";
        beforeOpenField.style.display = "block";
    });
}






//  送信制御
document.getElementById("titleName").addEventListener("change", function() {
    submitForm();
});

document.getElementById("screenId").addEventListener("change", function() {
    submitForm();
});

document.getElementById("date").addEventListener("change", function() {
    submitForm();
});

document.getElementById("screeningTime").addEventListener("change", function() {
    submitForm();
});


function submitForm() {
    const form = document.getElementById("screenForm");
    form.action = "/data2";
    form.method = "post";
    form.submit();
}
document.querySelector(".save").addEventListener("click", function(event) {
    event.preventDefault();
    const form = document.getElementById("screenForm");
    form.action = "/addSchedule";
    form.method = "post";
    form.submit();
});