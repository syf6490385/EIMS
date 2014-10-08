/**
 * Created by 孙艺峰 on 2014/9/30 0030.
 */
var mongoose=require('mongoose');
var Schema=mongoose.Schema;

var DayoffSchema=new Schema({
    userid:String,
    username:String,
    time:{type:Date,default:Date.now()},
    length:Number,
    dayofftime:Number,   //24小时制
    content:String,
    reply:String,
    replyid:String,
    replyname:String,
    replytime:{type:Date},
    result:Number //0未批复 1许可 2不许可
});
mongoose.model('Dayoff',DayoffSchema);
