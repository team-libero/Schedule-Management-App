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
        var titile = $('input[name="titile"]').val();
        var from_date = $('input[name="from_date"]').val();
        var from_time = $('input[name="from_time"]').val();
        var to_date = $('input[name="to_date"]').val();
        var to_time = $('input[name="to_time"]').val();

        var from = new Date(from_date + from_time);
        var to = new Date(to_date + to_time);

        let sucFlg = false;
        //エラーの場合
        if (titile == "") {
            $('.title_box').parent().find('.error_title').show();
            $('input[name="titile"]').addClass("error_tbox");
            sucFlg = true;
        }
        if (from_date == "" || from_time == "") {
            $('.date_box').parent().find('.error_from').show();
            $('input[name="from_date"]').addClass("error_leftbox");
            $('input[name="from_time"]').addClass("error_rightbox");
            sucFlg = true;
        }
        if (to_date != "" && to_time == "") {
            $('.date_box').parent().find('.error_to').show();
            $('input[name="to_date"]').addClass("error_datebox");
            $('input[name="to_time"]').addClass("error_timebox");
            sucFlg = true;
        }
        if (to_date == "" && to_time != "") {
            $('.date_box').parent().find('.error_to').show();
            $('input[name="to_date"]').addClass("error_datebox");
            $('input[name="to_time"]').addClass("error_timebox");
            sucFlg = true;
        }
        if (to_date != "") {
            if (from.getTime() > to.getTime()) {
                $('.date_box').parent().find('.error_to').show();
                $('input[name="to_date"]').addClass("error_datebox");
                $('input[name="to_time"]').addClass("error_timebox");
                sucFlg = true;
            }
        }
        if (sucFlg == false) {
            $('#form').attr('action', '../html/scheduleDetail.html');
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