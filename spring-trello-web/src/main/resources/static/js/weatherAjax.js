$.ajax({
    url: "http://localhost/api/weather",
    type: "GET",
    success: function (data) {
        $(".weather").append($(
            "<li><i class=\"fas fa-cloud\"></i><span>" + data['temperature'] + "&#8451; - " + data['status'] + "</span></li>"
        ))
    }
})