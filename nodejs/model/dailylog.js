/**
 * Created by 孙艺峰 on 2014/9/29 0029.
 */
var mongoose=require('mongoose');
var Schema=mongoose.Schema;
//mongoose.connect('mongodb://127.0.0.1/employee');
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
mongoose.model('dailylog',DailylogScheme);
var Dailylog=mongoose.model('dailylog');
var userDao=require('./User');
exports.add=function(userid,content,selfrate)
{
    var newDailylog=new Dailylog();
    newDailylog.userid=userid;
    newDailylog.content=content;
    newDailylog.selfrate=selfrate;
    newDailylog.save(function(err)
        {
            if(!err)
            {
                console.log('save dailylog!');
            }
            else
            {
                console.log('save dailylog failed!');
            }
        }
    )
}
exports.reply=function(dailylogid,userid,reply,rate)
{
   userDao.findByid(userid,function(err,docs){
       var update={$set:{replyid:userid,reply:reply,replyrate:rate,replyname:docs[0].name,replytime:Date.now()}};
       var options={upsert:true};
       Dailylog.update({_id:dailylogid},update,options,function(err){
           if(!err)
           {
               console.log('回复日志成功');
           }
           else
           {
               console.log('回复日志失败:'+err);
           }
       });
   });
}
exports.list=function(year,month,userid,callback)
{
    var start=new Date(year,month,1);
    var end=new Date(year,month,31);
    var conditions=
    {
        userid: userid,
        time: {$gt: start, $lt: end}
    };
    Dailylog.find(conditions,callback);
}