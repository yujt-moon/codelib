/**
 * 常用的有用的js方法
 * Created by yujiangtao on 2018/1/20.
 */

/**
* 获取url中的参数的值
* @param name url中的参数名
* @returns {null}
* @constructor
*/
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURI(r[2]);
    }
    return null;
}

/**
 * 获取到指定日期的偏移量的日期
 * @param assignedDate 指定的日期
 * @param offset 偏移量
 */
function getNDayOffset(assignedDate, offset) {
    assignedDate.setDate(assignedDate.getDate() + offset);
    var genDate = new Date(assignedDate);
    return genDate;
}

/**
 * 格式化日期
 * @param date 日期
 * @param formatString 格式化字符串如（yyyy-MM-dd HH:mm:ss）
 */
function formatDate(date, formatString) {
    var year = date.getFullYear();
    var month = (date.getMonth() + 1) < 10 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1);
    var day = date.getDate();
    var hour = (date.getHours() < 10) ? "0" + date.getHours() : date.getHours();
    var minute = (date.getMinutes() < 10) ? "0" + date.getMinutes() : date.getMinutes();
    var second = (date.getSeconds() < 10) ? "0" + date.getSeconds() : date.getSeconds();
    formatString = formatString.replace("yyyy", year);
    formatString = formatString.replace("MM", month);
    formatString = formatString.replace("dd", day);
    formatString = formatString.replace("HH", hour);
    formatString = formatString.replace("mm", minute);
    formatString = formatString.replace("ss", second);
    return formatString;
}

// 测试formatDate
formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
