$(document).ready(function () {
    $('input[name="userid"]').val('');
    $('input[type="password"]').val('');
});


$(function () {
    $('#send_mail').click(function () {
        alert("登録されているメールアドレスに、パスワード再設定メールを送信しました。");
    })

})