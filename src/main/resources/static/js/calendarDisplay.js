$(function () {
    $('.back_month').click(function () {
        let setval = null;
        const val = $("#moonth").val();
        if (val == 1) {
            setval = 12;
        } else {
            setval = val - 1;
        }
        $("#moonth").val(setval);
    })

    $('.next_month').click(function () {
        let setval = null;
        const val = $("#moonth").val();
        if (val == 12) {
            setval = 1;
        } else {
            setval = (parseInt(val) + 1);
        }
        $("#moonth").val(setval);
    })

    $(".day td").click(function () {
        let day = $(this).val();
        $(".table_contents").show();
    })

})