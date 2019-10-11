<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <script src="<c:url value="/res/jquery/jquery-3.4.1.js"/>"></script>
    <script defer src="<c:url value="/res/js/main.js"/>"></script>

    <link href="<c:url value="/res/bootstrap/css/bootstrap.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/res/bootstrap/css/bootstrap-grid.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/res/bootstrap/css/bootstrap-reboot.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<br/>
<div class="container">
    <div class="row">
        <div class="col-md-8" id="main-div">
            <table class="table table-bordered">
                <thead  id="main-table">
                <tr>
                    <th>Elements</th>
                    <th>Wight</th>
                    <th>Code</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
        <div class="col-md-4">
            <input type="text" id="lab2-in" value="programming languages" class="form-control">
            <input value="dsfsdf" type="button" id="lab2"  class="btn btn-success">
        </div>
    </div>
</div>
<script defer>
    $('#lab2').click(function (ev) {
        let code = $("#lab2-in").val();
        console.log(code);
        $.get("/coren/get", {code: code}, function (json) {
            $("tbody tr").remove();
            for (let newStr of json) {
                $('tbody').append("<tr><td>" + newStr.symbols + "</td><td>" + newStr.wight + "</td><td>" + newStr.code + "</td></tr>");
            }
            $.get("/coren/getCode",{code:code},function (newCode) {
                $("p").remove();
                $("table").after("<p>"+$("#lab2-in").val()+"</p>").after("<p>"+newCode+"</p>");
                console.log(newCode);
            })
        })
    });
</script>
</body>
</html>