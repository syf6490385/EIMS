/**
 * Created by haihu on 2014/10/5.
 */

var Dayoff = require('../models').Dayoff;
var userDao = require('./user');

exports.add = function(userid,content,length,dayofftime)
{
    var dayoff = new Dayoff();
    dayoff.userid = userid;
    dayoff.content = content;
    dayoff.length = length;
    dayoff.result = 0;
    dayoff.dayofftime = dayofftime;
    userDao.findByid(userid,function(err,docs)
    {
        dayoff.username = docs[0].name;
        console.log(dayoff.username);
        dayoff.save(function(err)
        {
            if(!err){
                console.log('请假保存成功');
            } else {
                console.log(err);
            }
        });
    });
};

exports.reply = function(dayoffid, userid, content, result)
{
    userDao.findByid(userid,function(err,docs){
        var update = {$set:{replyid:userid,reply:content,result:result,replyname:docs[0].name,replytime:Date.now()}};
        var options = {upsert:true};
        Dayoff.update({_id:dayoffid},update,options,function(error){
            if(error) {
                console.log("回复请假失败");
            } else {
                console.log('回复请假成功');
            }
        });
    });
};

exports.list = function(userid,callback)
{
    var conditions = {userid:userid};
    Dayoff.find(conditions,callback);
};