/**
 * Created by 孙艺峰 on 2014/9/30 0030.
 */

var dayoffDao = require('../proxy').Dayoff;
var userDao = require('../proxy').User;

exports.add = function(req,res){
    var userid = req.query.userid;
    var content = req.query.content;
    var length = req.query.length;
    var dayofftime = req.query.dayofftime;
    userDao.findByid(userid, function(err,docs){
        var username = docs[0].name;
        console.log(username);
        dayoffDao.add(userid, username, content, length, dayofftime, function(err)
        {
            if(!err){
                console.log('请假保存成功');
                res.send('add');
            } else {
                console.log(err);
            }
        });
    });
};

exports.reply = function(req,res){
    var dayoffid = req.query.dayoffid;
    var content = req.query.content;
    var result = req.query.result;
    var userid = req.query.userid;
    dayoffDao.reply(dayoffid,userid,content,result);
    res.send('reply');
};

exports.list = function (req,res){
    var userid = req.query.userid;
    dayoffDao.list(userid,function(err,docs)
    {
        res.send(docs);
    })
};
