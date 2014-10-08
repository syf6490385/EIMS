/**
 * Created by haihu on 2014/10/5.
 */

var Dailylog = require('../models').Dailylog;
var userDao=require('./user');

exports.add = function(userid,content,selfrate)
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
exports.reply = function(dailylogid,userid,reply,rate)
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
exports.list = function(year,month,userid,callback)
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