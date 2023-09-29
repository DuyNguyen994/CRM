
$('form h3').hide()
$('#content-modal').hide()
$(document).ready(function() {
	$('#form-add').on('submit', function(e) {
		e.preventDefault()
		var start = $('#startDate').val()
		var end = $('#endDate').val()

		var dataAdd = {
			name: $('#nameProject').val(),
			startDate: start,
			endDate: end,
		}

		if (new Date(start) < new Date(end)) {
			$.ajax({
				method: "POST",
				url: "http://localhost:8080/CRMProject/api/groupwork/add",
				data: dataAdd
			})
				.done(function(msg) {
					alert("Data Saved");
					location.reload();
				});

		} else {
			$('form h3').show()
			alert("Save Faild");
		}

	})
	$('.btn-Delete').on('click', function() {
		var This = $(this)
		var idDelete = { id: $(this).attr('idDelete') }

		$.ajax({
			method: "POST",
			url: "http://localhost:8080/CRMProject/api/groupwork/delete",
			data: idDelete
		})
			.done(function(msg) {
				This.closest('tr').remove()
			});

	})
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
			url: "http://localhost:8080/CRMProject/api/groupwork/groupwork",
			data: idUpdate
		})
			.done(function(msg) {
				$('#nameProject').val(msg.data.name)
				$('#startDate').val(msg.data.startDate)
				$('#endDate').val(msg.data.endDate)
			});
		$("#form-update").on("submit", function(e) {
			e.preventDefault()

			var start = $('#startDate').val()
			var end = $('#endDate').val()

			var dataUpdate = {
				id: id,
				name: $('#nameProject').val(),
				startDate: start,
				endDate: end,
			}
			if (new Date(start) < new Date(end)) {
				$.ajax({
					method: "POST",
					url: "http://localhost:8080/CRMProject/api/groupwork/update",
					data: dataUpdate,
					success: function() {
						alert("Update Sucess")
						location.reload()
					}
				})
					.done(function(msg) {
					});
			} else {
				$('form h3').show()
				alert("Ngày kết thúc Không hợp lệ!!!!");
			}
		});
	});
})