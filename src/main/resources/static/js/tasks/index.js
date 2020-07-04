function updateTask(taskId) {
		// your code that does something with param

		$.ajax({
			type : "POST",
			url : "/tasks/complete_task/" + taskId,
			data : {
				id : taskId
			},

			success : function(data) {
				location.reload();
				$("#alertSuccess").show(); //or fadeIn
				setTimeout(function() {
					$("#alertSuccess").hide(); //or fadeOut
				}, 5000);
			}
		});

	};

	function redirectEdit(id) {
		window.location.href = "/tasks/" + id + "/edit";
	}

	function redirectCreate() {
		window.location.href = "/tasks/new";
	}
	var listIDDelete = [];
	function getListIdDelete(id) {

		if ($("#cb_delete" + id).is(':checked')) {
			listIDDelete.push(id);
		} else {
			//uncheck - clear input

			const index = listIDDelete.indexOf(id);
			if (index > -1) {
				listIDDelete.splice(index, 1);
			}
		}

		console.log(listIDDelete);
	}

	function deleteListId() {
		if (listIDDelete.length > 0) {
			var r = confirm("Are you sure! Delete task");
			if (r == true) {
				$.ajax({
					type : "post",
					url : "/tasks/delete/" + listIDDelete,
					data : {
						listId : listIDDelete
					},

					success : function(data) {
						listIDDelete = [];
						location.reload()

					}
				});
			}
		}
		};