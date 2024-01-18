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
        $('input[name=userid]').prop('disabled', false);
        $('#insert').show();
        $('#edit').hide();

    } else {
        $("#title").text("ユーザ編集");
        $('input[name=userid]').prop('disabled', true);
        $('#insert').hide();
        $('#edit').show();
    }
    

    var authority = userInfo["authorityNo"];
    var radio = $('input[name="radio"]').val();

    if (authority == '0') {
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

    //編集押下
    $('#edit').click(function () {

        var userId = $('input[name="userid"]').val();
        var sei = $('input[name="sei"]').val();
        var mei = $('input[name="mei"]').val();
        var address = $('input[name="address"]').val();
        var password1 = $('input[name="password1"]').val();
        var password2 = $('input[name="password2"]').val();
        // var radio = $('input[name="radio"]').val();
        var inputArray = new Array(userId, sei, mei, address, password1, password2, radio);

        if (inputArray.includes("") || inputArray.includes(null)) {
            //未入力項目がある場合
            $('.edit_error').show();
            return;
        } else if (!address.match(/.+@.+\..+/)) {
            //メールアドレス形式エラー
            $('.pass_error').show();
            return;
        } else if (!password1.match(/([a-zA-Z])/) && password.match(/([0-9])/)) {
            //パスワード形式エラー（数字と英字が最低1文字入っていない場合）
            $('.pass_error').show();
            return;
        } else if (password1 != password2) {
            //パスワードエラー（パスワードとパスワード（確認用）が異なる場合）
            $('.pass_error').show();
        } else {
            $('#form').attr('action', '../html/calendar.html');
            $('#form').submit();
        }
    })

})