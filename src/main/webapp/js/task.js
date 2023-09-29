
$('#content-modal').hide()
$(document).ready(function() {
	$('#form-add').on('submit', function(e) {
		e.preventDefault()
		var start = $('#startDate').val()
		var end = $('#endDate').val()
		var dataAdd = {
			nameJob: $('#nameJob').val(),
			startDate: start,
			endDate: end,
			idProject: $('#idProject').val(),
			idUser: $('#idUser').val(),
			idStatus: 1
		}

		if (new Date(start) < new Date(end)) {
			$.ajax({
				method: "POST",
				url: "http://localhost:8080/CRMProject/api/task/add",
				data: dataAdd
			})
				.done(function(res) {
					if (res.data === true) {
						alert("Add Sucess " + $('#nameJob').val())
						location.reload()
					} else {
						alert("Add Faild \n" + "Người này đã có trong dự án ")

					}
				});
		} else {
			alert('Ngày kết thúc không hợp lệ')
		}

	}),
		$('.btn-Delete').on('click', function(e) {
			var This = $(this)
			var idDelete = { id: $(this).attr('idDelete') }

			$.ajax({
				method: "POST",
				url: "http://localhost:8080/CRMProject/api/task/delete",
				data: idDelete
			})
				.done(function(msg) {
					if (msg.data == true) {
						This.closest('tr').remove()
					}
				});
		}),

		$('.btn-Delete').on('click', function(e) {
			var This = $(this)
			var idDelete = { id: $(this).attr('idDelete') }

			$.ajax({
				method: "POST",
				url: "http://localhost:8080/CRMProject/api/task/delete",
				data: idDelete
			})
				.done(function(msg) {
					if (msg.data == true) {
						This.closest('tr').remove()
					}
				});
		}),

		$('.btn-Update').on('click', function() {
			$(".overlay").on("click", function() {
				$("#content-modal").hide()
			});
			$(".cancel-btn").on("click", function() {
				$("#content-modal").hide()
			});
			$("#content-modal").show()

			var This = $(this)
			var idUpdate = { id: $(this).attr('idUpdate') }
			var id = $(this).attr('idUpdate')

			$.ajax({
				method: "POST",
				url: "http://localhost:8080/CRMProject/api/task/task",
				data: idUpdate
			})
				.done(function(msg) {
					$('#nameJob').val(msg.data.name)
					$('#startDate').val(msg.data.startDate)
					$('#endDate').val(msg.data.endDate)
					$('#idProject').val(msg.data.idProject)
					$('#idUser').val(msg.data.user.id)
				});
			$("#form-update").on("submit", function(e) {
				e.preventDefault()

				var start = $('#startDate').val()
				var end = $('#endDate').val()
				var dataUpdate = {
					idJob: id,
					nameJob: $('#nameJob').val(),
					startDate: start,
					endDate: end,
					idProject: $('#idProject').val(),
					idUser: $('#idUser').val(),
					idStatus: 1
				}

				if (new Date(start) < new Date(end)) {
					$.ajax({
						method: "POST",
						url: "http://localhost:8080/CRMProject/api/task/update",
						data: dataUpdate,
					})
						.done(function(msg) {
							if (msg.data == true) {
								alert("Update Success")
								location.reload()
							} else {
								alert("Save Fail!!!")
							}
						});
				} else {
					$('form h3').show()
					alert("Ngày kết thúc Không hợp lệ");
				}
			});
		});
}
)