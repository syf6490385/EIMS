/**
 * Created by 孙艺峰 on 2014/9/28 0028.
 */
var mongoose=require('mongoose');
var Schema=mongoose.Schema;

var UserScheme=new Schema({
    name:String,
    age:Number,
    pwd:String,
    lv:Number//级别1 普通员工 级别2 负责人 级别3 领导
});
mongoose.model('User',UserScheme);
