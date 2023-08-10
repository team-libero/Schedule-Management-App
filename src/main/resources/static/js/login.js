$(document).ready(function () {
    $('input[name="userid"]').val('');
    $('input[type="password"]').val('');
});


$(function () {
/*
    $('#login_btn').click(function () {

        var userid = $('input[name="userid"]').val();
        var password = $('input[name="password"]').val();
        if (userid != "" && password != "") {
            $('#form').attr('action', '../html/calendarDisplay.html');
            $('#form').submit();
        } else {
            //ログインエラーの場合
            $('.login_error').show();
            $('input[name="userid"]').addClass("error_tbox");
            $('input[type="password"]').addClass("error_tbox");
        }
    })
*/
    $('#send_mail').click(function () {
        alert("登録されているメールアドレスに、パスワード再設定メールを送信しました。");
    })

})