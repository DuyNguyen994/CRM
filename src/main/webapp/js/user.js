$(document).ready(function(){
	$("#content-modal").hide()
	$(".btn-update").on("click", function() {		
		$(".overlay").on("click", function() {
			$("#content-modal").hide()
		});
		$(".cancel-btn").on("click", function() {
			$("#content-modal").hide()
		});
		$("#content-modal").show()

		var This = $(this)
		$.ajax({
			method: "POST",
			url: "http://localhost:8080/CRMProject/api/user/user",
			data: { id: $(this).attr('idUpdate') }
		})
			.done(function(res) {
				
				$("#fullName").val(res.data.fullName),
					$("#email").val(res.data.email),
					$("#pwd").val(res.data.pwd),
					$("#phone").val(res.data.phone),
					$("#idRole").val(res.data.idRole)

				/*Submit*/

				$("#form-update").on("submit", function(e) {
					e.preventDefault()
					var dataUpdate = {
						id: This.attr('idUpdate'),
						fullName: $("#fullName").val(),
						email: $("#email").val(),
						pwd: $("#pwd").val(),
						phone: $("#phone").val(),
						idRole: $("#idRole").val()
					}
					$.ajax({
						method: "POST",
						url: "http://localhost:8080/CRMProject/api/user/update",
						data: dataUpdate,
						success: function() {
							alert("Update Success")
							location.reload()
						}
					})
						.done(function(msg) {

						});
				});

			});

	});
})

