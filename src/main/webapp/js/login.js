
$(document).ready(function() {
	$('#login').on('submit', function(e) {
		e.preventDefault()
		var data = {
			email: $('#email').val(),
			password: $('#password').val()
		}

		$.ajax({
			method: "POST",
			url: "http://localhost:8080/CRMProject/api/login",
			data: data,
		})
			.done(function(msg) {
				console.log(msg.data)
				if (msg.data === true) {
					alert("Success");
					location.replace("http://localhost:8080/CRMProject")
				} else {
					alert("Login Faild");
					location.reload();
				}
			});
	})
})