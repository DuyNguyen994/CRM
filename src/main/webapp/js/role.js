$(document).ready(function() {

	$('#role-add').on('submit', function(event) {
		event.preventDefault();
		var data = {
			name: $('#name-role').val(),
			desc: $('#desc-role').val()
		}

		$.ajax({
			method: "POST",
			url: "http://localhost:8080/CRMProject/api/role/add",
			data: data
		})
			.done(function(res) {
				alert("Data Saved!");
			});
	})
},
	$('.delete-btn').on('click', function() {
		var This = $(this)
		var dataDelete = {
			id: $(this).attr('idDelete')
		}
		$.ajax({
			method: "POST",
			url: "http://localhost:8080/CRMProject/api/role/delete",
			data: dataDelete,
		})
			.done(function(res) {
				if (res.data == true) {
					This.closest('tr').remove()
				}
			});

	}),
	$("#content-modal").hide(),
	$('.update-btn').on('click', function() {
		$(".overlay").on("click", function() {
			$("#content-modal").hide()
		});
		$(".cancel-btn").on("click", function() {
			$("#content-modal").hide()
		});
		$("#content-modal").show()


		var This = $(this)
		var idGet = {
			id: $(this).attr('idUpdate')
		}
		$.ajax({
			method: "POST",
			url: "http://localhost:8080/CRMProject/api/role/role",
			data: idGet,
		})
			.done(function(res) {
				console.log(res.data.name)
				$("#name").val(res.data.name)
				$("#desc").val(res.data.description)
			});

		$("#form-update").on("submit", function(e) {
			e.preventDefault()
			var dataUpdate = {
				id: idGet.id,
				name: $("#name").val(),
				desc: $("#desc").val()
			}
			$.ajax({
				method: "POST",
				url: "http://localhost:8080/CRMProject/api/role/update",
				data: dataUpdate,
				success: function() {
					alert("Update Success")
					location.reload()
				}
			})
				.done(function(msg) {

				});
		});
	})
)