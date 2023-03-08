$(function () {

    // 新規登録
    $('#register').click(function () {
        $('#form').attr('action', '../html/userEdit.html');
        $('#form').submit();
    })

    // 編集
    $('#edit').click(function () {
        var radio = $('input[name="radio"]:checked').val();
        if(!radio){
            // ユーザ未選択の場合
            alert("ユーザを選択してください。");
        } else {
            $('#form').attr('action', '../html/userEdit.html');
            $('#form').submit();
        }
    })

    // 削除
    $('#delete').click(function () {
        var radio = $('input[name="radio"]:checked').val();
        if(!radio){
            // ユーザ未選択の場合
            alert("ユーザを選択してください。");
        } else {
            let confirmFlg = window.confirm("選択したユーザを削除します。");
            if(confirmFlg){
                $('#form').attr('action', '../html/userSelect.html');
                $('#form').submit();
            }
        }    
    })
})