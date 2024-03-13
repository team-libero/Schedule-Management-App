$(function () {
    $('#modal_open').click(function () {
        $(this).blur();
        if ($("#modal-overlay")[0]) return false;
        $("body").append('<div id="modal-overlay"></div>');

        $("#modal-overlay").fadeIn("slow");
        $("#modal-content").fadeIn("slow");
    })

    $("#modal-cancel").unbind().click(function () {
        $("#modal-content,#modal-overlay").fadeOut("slow", function () {
            $("#modal-overlay").remove();
        });

    });

    $('#submit').click(function () {

        $('.error').hide();
        var title = $('input[name="titleName"]').val();
        var from_date = $('input[name="from_date"]').val();
        var from_time = $('input[name="from_time"]').val();
        var to_date = $('input[name="to_date"]').val();
        var to_time = $('input[name="to_time"]').val();
        var address = $('input[name="address"]').val();
        var memo = $('textarea[name="memo"]').val();
        var kbn = $('input[name="calendarType"]').val();

        let sucFlg = false;
        //エラーの場合
        if (title == "") {
            $('.error_title').text("タイトルを入力してください。");
            $('.title_box').parent().find('.error_title').show();
            $('input[name="titleName"]').addClass("error_tbox");
            sucFlg = true;
        }
        if (title.length > 100) {
            $('.error_title').text("タイトルは100文字以内で入力してください。");
            $('.title_box').parent().find('.error_title').show();
            $('input[name="titleName"]').addClass("error_tbox");
            sucFlg = true;
        }
        if (from_date == "" || from_time == "") {
            $('.error_from').text("スケジュール開始情報は全て選択してください。");
            $('.date_box').parent().find('.error_from').show();
            $('input[name="from_date"]').addClass("error_leftbox");
            $('input[name="from_time"]').addClass("error_rightbox");
            sucFlg = true;
        }
        if (to_date != "" && to_time == "") {
            $('.error_to').text("スケジュール終了情報は全て選択、もしくは全て未選択にしてください。");
            $('.date_box').parent().find('.error_to').show();
            $('input[name="to_date"]').addClass("error_datebox");
            $('input[name="to_time"]').addClass("error_timebox");
            sucFlg = true;
        }
        if (to_date == "" && to_time != "") {
            $('.error_to').text("スケジュール終了情報は全て選択、もしくは全て未選択にしてください。");
            $('.date_box').parent().find('.error_to').show();
            $('input[name="to_date"]').addClass("error_datebox");
            $('input[name="to_time"]').addClass("error_timebox");
            sucFlg = true;
        }
        if (to_date != "") {
            if ((from_date > to_date) || (from_date == to_date && from_time > to_time)) {
                $('.error_to').text("スケジュール終了情報はスケジュール開始日時以降にしてください。");
                $('.date_box').parent().find('.error_to').show();
                $('input[name="to_date"]').addClass("error_datebox");
                $('input[name="to_time"]').addClass("error_timebox");
                sucFlg = true;
            }
        }
        if (address.length > 100) {
            $('.error_address').text("場所は100文字以内で入力してください。");
            $('.place_box').parent().find('.error_address').show();
            $('input[name="address"]').addClass("error_placebox");
            sucFlg = true;
        }
        if (memo.length > 1000) {
            $('.error_memo').text("説明は1000文字以内で入力してください。");
            $('.contents_box').parent().find('.error_memo').show();
            $('input[name="memo"]').addClass("error_contentsbox");
            sucFlg = true;
        }

        var fdate = document.getElementById('from_date').value;
        var ftime = document.getElementById('from_time').value;
        document.getElementById('fromDateTime').value = fdate + ' ' + ftime;
        var tdate = document.getElementById('to_date').value;
        var ttime = document.getElementById('to_time').value;
        document.getElementById('toDateTime').value = tdate + ' ' + ttime;

        if (sucFlg == false) {
            $('#form').submit();
        } else {
            return false;
        }
    })

    $("#kbn_box").click(function () {
        if (document.getElementById("kojin").checked === true){
            // disabled属性を設定
            document.getElementById("announce").setAttribute("disabled", true);
        }else{
            // disabled属性を削除
            document.getElementById("announce").removeAttribute("disabled");
        }
    });
    
})