const save = document.getElementById("save")
save.style.display = "none"

document.getElementById("title").addEventListener("change", function() {
	const titileForm = document.getElementById("title")
	const dataForm = document.getElementById("data")
	const intForm = document.getElementById("int")
	const descriptionForm = document.getElementById("description")
	const imgForm = document.getElementById("img")
	const tglForm = document.getElementById("url")
	const urlForm = document.getElementById("staff")
	const staffForm = document.getElementById("tgl")


	if (titileForm.value != "" && dataForm.value != "" && intForm.value != "" && descriptionForm.value != "" && imgForm.value != "" && tglForm.value != "" && urlForm.value != "" && staffForm != "") {
		const save2 = document.getElementById("save2")
		const save = document.getElementById("save")



		save2.style.display = "none"
		save.style.display = "block"
	}
});
document.getElementById("data").addEventListener("change", function() {
	const titileForm = document.getElementById("title")
	const dataForm = document.getElementById("data")
	const intForm = document.getElementById("int")
	const descriptionForm = document.getElementById("description")
	const imgForm = document.getElementById("img")
	const tglForm = document.getElementById("url")
	const urlForm = document.getElementById("staff")
	const staffForm = document.getElementById("tgl")


	if (titileForm.value != "" && dataForm.value != "" && intForm.value != "" && descriptionForm.value != "" && imgForm.value != "" && tglForm.value != "" && urlForm.value != "" && staffForm != "") {
		const save2 = document.getElementById("save2")
		const save = document.getElementById("save")



		save2.style.display = "none"
		save.style.display = "block"
	}
});
document.getElementById("int").addEventListener("change", function() {
	const titileForm = document.getElementById("title")
	const dataForm = document.getElementById("data")
	const intForm = document.getElementById("int")
	const descriptionForm = document.getElementById("description")
	const imgForm = document.getElementById("img")
	const tglForm = document.getElementById("url")
	const urlForm = document.getElementById("staff")
	const staffForm = document.getElementById("tgl")


	if (titileForm.value != "" && dataForm.value != "" && intForm.value != "" && descriptionForm.value != "" && imgForm.value != "" && tglForm.value != "" && urlForm.value != "" && staffForm != "") {
		const save2 = document.getElementById("save2")
		const save = document.getElementById("save")



		save2.style.display = "none"
		save.style.display = "block"
	}
});
document.getElementById("description").addEventListener("change", function() {
	const titileForm = document.getElementById("title")
	const dataForm = document.getElementById("data")
	const intForm = document.getElementById("int")
	const descriptionForm = document.getElementById("description")
	const imgForm = document.getElementById("img")
	const tglForm = document.getElementById("url")
	const urlForm = document.getElementById("staff")
	const staffForm = document.getElementById("tgl")


	if (titileForm.value != "" && dataForm.value != "" && intForm.value != "" && descriptionForm.value != "" && imgForm.value != "" && tglForm.value != "" && urlForm.value != "" && staffForm != "") {
		const save2 = document.getElementById("save2")
		const save = document.getElementById("save")



		save2.style.display = "none"
		save.style.display = "block"
	}
});
document.getElementById("img").addEventListener("change", function() {
	const titileForm = document.getElementById("title")
	const dataForm = document.getElementById("data")
	const intForm = document.getElementById("int")
	const descriptionForm = document.getElementById("description")
	const imgForm = document.getElementById("img")
	const tglForm = document.getElementById("url")
	const urlForm = document.getElementById("staff")
	const staffForm = document.getElementById("tgl")


	if (titileForm.value != "" && dataForm.value != "" && intForm.value != "" && descriptionForm.value != "" && imgForm.value != "" && tglForm.value != "" && urlForm.value != "" && staffForm != "") {
		const save2 = document.getElementById("save2")
		const save = document.getElementById("save")



		save2.style.display = "none"
		save.style.display = "block"
	}
});
document.getElementById("url").addEventListener("change", function() {
	const titileForm = document.getElementById("title")
	const dataForm = document.getElementById("data")
	const intForm = document.getElementById("int")
	const descriptionForm = document.getElementById("description")
	const imgForm = document.getElementById("img")
	const tglForm = document.getElementById("url")
	const urlForm = document.getElementById("staff")
	const staffForm = document.getElementById("tgl")


	if (titileForm.value != "" && dataForm.value != "" && intForm.value != "" && descriptionForm.value != "" && imgForm.value != "" && tglForm.value != "" && urlForm.value != "" && staffForm != "") {
		const save2 = document.getElementById("save2")
		const save = document.getElementById("save")



		save2.style.display = "none"
		save.style.display = "block"
	}
});
document.getElementById("staff").addEventListener("change", function() {
	const titileForm = document.getElementById("title")
	const dataForm = document.getElementById("data")
	const intForm = document.getElementById("int")
	const descriptionForm = document.getElementById("description")
	const imgForm = document.getElementById("img")
	const tglForm = document.getElementById("url")
	const urlForm = document.getElementById("staff")
	const staffForm = document.getElementById("tgl")


	if (titileForm.value != "" && dataForm.value != "" && intForm.value != "" && descriptionForm.value != "" && imgForm.value != "" && tglForm.value != "" && urlForm.value != "" && staffForm != "") {
		const save2 = document.getElementById("save2")
		const save = document.getElementById("save")



		save2.style.display = "none"
		save.style.display = "block"
	}
});
document.getElementById("tgl").addEventListener("change", function() {
	const titileForm = document.getElementById("title")
	const dataForm = document.getElementById("data")
	const intForm = document.getElementById("int")
	const descriptionForm = document.getElementById("description")
	const imgForm = document.getElementById("img")
	const tglForm = document.getElementById("url")
	const urlForm = document.getElementById("staff")
	const staffForm = document.getElementById("tgl")


	if (titileForm.value != "" && dataForm.value != "" && intForm.value != "" && descriptionForm.value != "" && imgForm.value != "" && tglForm.value != "" && urlForm.value != "" && staffForm != "") {
		const save2 = document.getElementById("save2")
		const save = document.getElementById("save")



		save2.style.display = "none"
		save.style.display = "block"
	}
});

//////////////////////////////

function upBtn(button) {
    const row = button.closest("tr");
    const updateOpenField = row.querySelectorAll(".movieTxt");
    const beforeOpenField = row.querySelectorAll(".none-input");
    const edit = row.querySelectorAll(".edit");
    const save3 = row.querySelectorAll(".save3");
    const isTglElements = row.querySelectorAll(".isTgl");

    
    beforeOpenField.forEach(input => {
		if (input.style.display === ""){
			input.style.display ="none";
		}
	});
	
	    
    save3.forEach(save => {
		if (save.style.display === ""){
			save.style.display ="none";
		}
	});
	
		
	    
    edit.forEach(edit1 => {
		if (edit1.style.display === ""){
			edit1.style.display ="block";
		}
	});
	
	beforeOpenField.forEach(input => {
		input.style.display = input.style.display === "none" ? "block":"none";
	});
    
	
	updateOpenField.forEach(input => {
		input.style.display = input.style.display === "none" ? "block":"none";
	});
	
	
	
	
	edit.forEach(edit1 => {
		edit1.style.display = edit1.style.display === "none" ? "block":"none";
	});
	
	
	save3.forEach(save => {
		save.style.display = save.style.display === "none" ? "block":"none";
	});
	
    Array.from(isTglElements).forEach(element => {
        element.disabled = false;
    });
    
    
}



//function submitUpdate() {
//    const form = document.getElementById("updateForm");
//    form.action = "/update";
//    form.method = "post";
//    form.submit();
//}







