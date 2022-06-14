/**
 * 校验所用的js,存放常用的js
 * Created by yujiangtao on 2018/1/22.
 */

/**
 * 校验身份证号
 * @param idCard 身份证
 */
function validIDCard(idCard) {
    // 将小写字母转换为大写字母
    idCard = idCard.toUpperCase();
    //身份证正则表达式
    var reg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
    if (!reg.test(idCard) || idCard == "" || idCard == null) {
        return false;
    }else {
        //简单校验身份证信息
        var chenArray = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2);//身份证每位所乘的系数
        var lastArray = new Array(1,0,'X',9,8,7,6,5,4,3,2);//身份证最后一位对应的余数校验
        var idCardArray = idCard.split("");
        var remainder = 0;
        var count = 0;//存储身份证每位数字乘以系数之和
        //求身份证各位乘以系数之和
        for (var i = 0; i < 17; i++) {
            count += chenArray[i] * idCardArray[i];
        }
        remainder = count % 11;//求余数
        if(idCardArray[17].toUpperCase() != lastArray[remainder]){
            return false;
        }
    }
    return true;
}
