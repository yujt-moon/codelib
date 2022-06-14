/**
 * Created by yujiangtao on 2018/2/9.
 */
var result = $.extend({type:"what"}, {name:"hehe", age:16}, {sex:"male", name:"haha"});
console.log(result);

//------------------------------------------------
// 合并到jQuery全局对象中去
$.extend({
    sayHello : function (msg) {
        alert(msg);
    }
});

$.sayHello("Hello everyone!");
//-------------------------------------------------

//--------------------------------------------------------
// 合并到jquery实例对象中去
$.fn.extend({
    phoneCall : function (toWho) {
        alert("You will call who, when you feel lonely!");
    }
});

$.fn.phoneCall();
//---------------------------------------------------------

//---------------------------------------------------------
// 在jquery全局对象中扩展一个net命名空间。
$.extend({net:{}});

$.extend($.net, {
    href : function() {
        alert(window.location.href);
    }
});

$.net.href();
//---------------------------------------------------------

//---------------------------------------------------------
// Jquery的extend方法还有一个重载原型：
// extend(boolean,dest,src1,src2,src3...)
// 第一个参数boolean代表是否进行深度拷贝
var deepResult=$.extend( true, {},
    { name: "John", location: {city: "Boston",county:"USA"} },
    { last: "Resig", location: {state: "MA",county:"China"} } );
console.log(deepResult);

var shallowResult=$.extend( false, {},
    { name: "John", location:{city: "Boston",county:"USA"} },
    { last: "Resig", location: {state: "MA",county:"China"} }
);
console.log(shallowResult);

//---------------------------------------------------------