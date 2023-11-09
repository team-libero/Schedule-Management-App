$(document).ready(function () {
    $('input[name="userid"]').val('');
    $('input[type="password"]').val('');
});


$(function () {

    if (errorMessage != null && errorMessage != '') {
        // サーバ処理でユーザテーブルから情報を取得できなかった場合
        $('.login_error').text(errorMessage);
            $('.login_error').show();
            $('input[name="userid"]').addClass("error_tbox");
            $('input[type="password"]').addClass("error_tbox");
    }

    $('#login_btn').click(function () {

        // 現在の表示をクリア
        $('.login_error').hide();
        $('input[name="userid"]').removeClass("error_tbox");
        $('input[type="password"]').removeClass("error_tbox");

        var userid = $('input[name="userid"]').val();
        var password = $('input[name="password"]').val();

        if (userid == "") {
            //ユーザIDが未入力の場合
            $('.login_error').text("ユーザIDが未入力です。")
            $('.login_error').show();
            $('input[name="userid"]').addClass("error_tbox");
        } else if (password == "") {
            //パスワードが未入力の場合
            $('.login_error').text("パスワードが未入力です。");
            $('.login_error').show();
            $('input[type="password"]').addClass("error_tbox");
        } else if (isHanEisu(userid) !== true || userid.length >= 21 || isHanEisu(password) !== true || !(password.length >= 8 && password.length <= 20) ) {
            // 以下のいずれかに該当する場合
            // ユーザIDが半角英数字でない
            // ユーザIDが21桁以上
            // パスワードが半角英数字でない
            // パスワードが8～20桁でない
            $('.login_error').text("ユーザIDまたはパスワードに誤りがあります。");
            $('.login_error').show();
            $('input[name="userid"]').addClass("error_tbox");
            $('input[type="password"]').addClass("error_tbox");

        } else {
            // 入力チェックOKの場合 
            $('#form').submit();
        }
    })

    $('#send_mail').click(function () {
        alert("登録されているメールアドレスに、パスワード再設定メールを送信しました。");
    })

})

function isHanEisu(str){
    str = (str==null)?"":str;
    if(str.match(/^[A-Za-z0-9]*$/)){
      return true;
    }else{
      return false;
    }
  }