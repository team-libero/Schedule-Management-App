const week = ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'];
const today = new Date();
// 月末だとずれる可能性があるため、1日固定で取得
var showDate = new Date(today.getFullYear(), today.getMonth(), 1);

// 初期表示
window.onload = function () {
    showProcess(today, calendar);
};
// 前の月表示
function back_month() {
    showDate.setMonth(showDate.getMonth() - 1);
    showProcess(showDate);
}

// 次の月表示
function next_month() {
    showDate.setMonth(showDate.getMonth() + 1);
    showProcess(showDate);
}

// セレクトボックスから月を選択
function selectMonthChange() {
    let selectYear = document.getElementById("select_year").value;
    let selectMonth = document.getElementById("select_month").value;
    showDate.setFullYear(selectYear);
    showDate.setMonth(selectMonth - 1);

    showProcess(showDate);

}
// セレクトボックスから年を選択
function selectYearChange() {
    let selectYear = document.getElementById("select_year").value;
    let selectMonth = document.getElementById("select_month").value;
    showDate.setFullYear(selectYear);
    showDate.setMonth(selectMonth - 1);

    showProcess(showDate);
}

// カレンダー表示
function showProcess(date) {
    var year = date.getFullYear();
    var month = date.getMonth();
    selectYear(year);
    selectMonth(month);

    var calendar = createProcess(year, month);
    document.querySelector('#calendar').innerHTML = calendar;
}

// カレンダー作成
function createProcess(year, month) {
    // 曜日
    var calendar = "<table class='calendar' id='calendar'><tbody><tr>";
    for (var i = 0; i < week.length; i++) {
        if (week[i] == "SUN") {
            calendar += "<th style='color: red;'>" + week[i] + "</th>";
        } else if (week[i] == "SAT") {
            calendar += "<th style='color: blue;'>" + week[i] + "</th>";
        } else {
            calendar += "<th>" + week[i] + "</th>";
        }
    }
    calendar += "</tr>";

    var count = 0;
    var startDayOfWeek = new Date(year, month, 1).getDay();
    var endDate = new Date(year, month + 1, 0).getDate();
    var lastMonthEndDate = new Date(year, month, 0).getDate();
    var row = Math.ceil((startDayOfWeek + endDate) / week.length);

    // 1行ずつ設定
    for (var i = 0; i < row; i++) {
        calendar += "<tr class='day'>";
        // 1colum単位で設定
        for (var j = 0; j < week.length; j++) {
            if (i == 0 && j < startDayOfWeek) {
                // 1行目の設定
                if ((lastMonthEndDate - startDayOfWeek + j + 1) >= 7) {
                    // 先月
                    calendar += "<td>" + "</td>";
                } else {
                    //　当月
                    calendar += "<td>" + (lastMonthEndDate - startDayOfWeek + j + 1) + "</td>";
                }
            } else if (count >= endDate) {
                // 最終行で最終日以降、翌月の日付を設定
                calendar += "<td>" + "</td>";
            } else {
                // 当月の日付を曜日に照らし合わせて設定
                count++;
                calendar += "<td>" + count + "</td>";
            }
        }
        calendar += "</tr>";
    }
    return calendar += "</tbody></table>";
}

function selectMonth(month) {
    var select = document.getElementById("select_month");
    if (select.length == 0) {
        for (var i = 1; i <= 12; i++) {
            // optionタグを作成する
            var option = document.createElement("option");
            // optionタグのテキストを設定する
            option.text = i + "月";
            // optionタグのvalueを設定する
            option.value = i;
            if (i == month + 1) {
                option.selected = true;
            }

            // selectタグの子要素にoptionタグを追加する
            select.appendChild(option);
        }
    } else {
        select.options[month].selected = true;
    }

}

function selectYear(year) {
    var select = document.getElementById("select_year");
    const thisYear = today.getFullYear();
    // セレクトボックスの最大値(デフォルト：今年 + 1)
    var lastYear = thisYear + 1;
    if (year >= lastYear) {
        // 選択した年 + 1の年数を表示
        lastYear = year + 1;
    }
    // セレクトボックスの最小値(デフォルト：今年 - 4)
    var firstYear = thisYear - 4;
    if (year <= firstYear) {
        firstYear = year
    }

    if (select.hasChildNodes()) {
        // セレクトボックス初期化
        while (select.childNodes.length > 0) {
            select.removeChild(select.firstChild)
        }
    }

    for (var i = lastYear; i >= firstYear; i--) {
        // optionタグを作成する
        var option = document.createElement("option");
        // optionタグのテキストを設定する
        option.text = i + "年";
        // optionタグのvalueを設定する
        option.value = i;
        if (i == year) {
            option.selected = true;
        }

        // selectタグの子要素にoptionタグを追加する
        select.appendChild(option);

    }
}