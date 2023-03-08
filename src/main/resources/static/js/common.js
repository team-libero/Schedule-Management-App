$(document).ready(function () {
    //前回選択時の表示モードが存在する場合は、継承（selected_typ）
    // 個人モード=>0   グループモード=>1   アカウント=>2
    // 初期表示は個人モードで表示
    $('.def_dis').addClass('selected_typ');
});

$(function () {
    $('.disp_mode').click(function () {
        $('.selected_typ').removeClass('selected_typ');
        $(this).addClass('selected_typ');  
    })

})