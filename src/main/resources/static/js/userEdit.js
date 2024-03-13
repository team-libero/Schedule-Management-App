// $(document).ready(function () {
//     if(ユーザ編集の場合){
//         $('input[name="userid"]').prop('disabled',true);
//         $('input[name="radio"]').prop('disabled',true);
//     }
// });


$(function () {

    // TODO ユーザ選択画面で登録を選択した場合、"ユーザ登録"にする

    if (insertFlg) {
        $("#title").text("ユーザ登録");
        $('input[name=userId]').prop('disabled', false);
        $('#insert').show();
        $('#edit').hide();

    } else {
        $("#title").text("ユーザ編集");
        $('input[name=userId]').prop('disabled', true);
        $('#insert').hide();
        $('#edit').show();
    }
    

    var radio = $('input[name="radio"]').val();

    if (authorityNo == '0') {
        // 権限が管理者の場合、管理者をチェック。ラジオボタン活性。
        $('input[name=radio]').val(['kanrisha']);
        $('input[name=radio]:eq(0)').prop('disabled', false);
        $('input[name=radio]:eq(1)').prop('disabled', false);
    } else {
        // 権限が一般の場合、一般をチェック。ラジオボタン非活性。
        $('input[name=radio]').val(['ippan']);
        $('input[name=radio]:eq(0)').prop('disabled', true);
        $('input[name=radio]:eq(1)').prop('disabled', true);
    }

    if (errorMessage != null && errorMessage != '') {
        // サーバ処理でユーザ編集処理に失敗した場合
        $('.edit_error').text(errorMessage);
    }
    //編集押下
    $('#edit').click(function () {

        var userId = $('input[name="userId"]').val();
        var sei = $('input[name="sei"]').val();
        var mei = $('input[name="mei"]').val();
        var address = $('input[name="address"]').val();
        var password1 = $('input[name="password1"]').val();
        var password2 = $('input[name="password2"]').val();
        // var radio = $('input[name="radio"]').val();
        // var inputArray = new Array(userId, sei, mei, password1, password2);

        if (sei == "") {
            $('.edit_error').text("姓が未入力です")
            $('.edit_error').show();
            return;
        } else if (!(sei.length >= 1 && sei.length <= 10)) {
            $('.edit_error').text("姓は1~10桁で入力してください。")
            $('.edit_error').show();
            return;
        } else if (mei == "") {
            $('.edit_error').text("名が未入力です")
            $('.edit_error').show();
            return;
        } else if (!(mei.length >= 1 && mei.length <= 10)) {
            $('.edit_error').text("名は1~10桁で入力してください。")
            $('.edit_error').show();
            return;
        } else if (!address.match(/.+@.+\..+/) || address.length > 255) {
            //メールアドレス形式エラー
            $('.edit_error').text("メールアドレスの形式に誤りがあります")
            $('.edit_error').show();
            return;
        } else if (password1 == "") {
            $('.edit_error').text("パスワードが未入力です")
            $('.edit_error').show();
            return;
        } else if (!(password1.length >= 4 && password1.length <= 20)) {
            $('.edit_error').text("パスワードは4~20桁で入力してください。")
            $('.edit_error').show();
            return;
        } else if (!password1.match(/([a-zA-Z])/) || !password1.match(/([0-9])/)) {
            //パスワード形式エラー（数字と英字が最低1文字入っていない場合）
            $('.edit_error').text("パスワードには数字と英字を最低1文字は入れてください。")
            $('.edit_error').show();
            return;
        }  else if (password2 == "") {
            $('.edit_error').text("パスワード(確認)が未入力です")
            $('.edit_error').show();
            return;
        }else if (password1 != password2) {
            //パスワードエラー（パスワードとパスワード（確認用）が異なる場合）
            $('.edit_error').text("パスワードに誤りがあります")
            $('.edit_error').show();
            return;
        } else {
            // $('#form').attr('action', '../html/calendar.html');
            $('#form').submit();
        }
    })

})