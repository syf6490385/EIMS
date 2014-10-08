/**
 * Created by 孙艺峰 on 2014/9/29 0029.
 */
var mongoose=require('mongoose');
var Schema=mongoose.Schema;

var DailylogScheme=new Schema({
    content:String,
    selfrate:Number,//1-5星 级
    reply:String,
    replyrate:Number,//1-5星 级
    username:String,
    userid:String,
    replyname:String,
    replyid:String,
    time:{type:Date,default:Date.now()},
    replytime:{type:Date}
});

mongoose.model('Dailylog',DailylogScheme);


