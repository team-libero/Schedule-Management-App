// $(document).ready(function () {
//     if(ユーザ編集の場合){
//         $('input[name="userid"]').prop('disabled',true);
//         $('input[name="radio"]').prop('disabled',true);
//     }
// });


$(function () {

    //編集押下
    $('#edit').click(function () {

        var userId = $('input[name="userid"]').val();
        var sei = $('input[name="sei"]').val();
        var mei = $('input[name="mei"]').val();
        var address = $('input[name="address"]').val();
        var password1 = $('input[name="password1"]').val();
        var password2 = $('input[name="password2"]').val();
        var radio = $('input[name="radio"]').val();
        var inputArray = new Array(userId,sei,mei,address,password1,password2,radio);

        if (inputArray.includes("")||inputArray.includes(null)) {
            //未入力項目がある場合
            $('.edit_error').show();
            return;
        }else if(password1 != password2){
            //パスワードエラー
            $('.pass_error').show();
        } else {
            $('#form').attr('action', '../html/calendar.html');
            $('#form').submit();
        }
    })

})