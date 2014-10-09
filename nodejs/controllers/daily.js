/**
 * Created by 孙艺峰 on 2014/9/29 0029.
 */

var dailyDao = require('../proxy').Dailylog;

exports.add = function (req,res){
    var userid = req.query.userid;
    var content = req.query.content;
    var selfrate = req.query.selfrate;
    dailyDao.add(userid,content,selfrate,function(err)
    {
        if(!err){
            console.log('save dailylog!');
            res.send('add');
        } else {
            console.log('save dailylog failed!');
        }
    });
};

exports.reply =function (req,res){
    var dailylogid = req.query.dailylogid;
    var userid = req.query.userid;
    var rate = req.query.rate;
    var reply = req.query.reply;
    dailyDao.reply(dailylogid,userid,reply,rate);
    res.send('reply');
};

exports.list = function (req,res){
    var year = req.query.year;
    var month = parseInt(req.query.month);
    var userid = req.query.userid;
    month = month - 1;
    dailyDao.list(year, month, userid, function(err,docs){
        res.send(docs);
    });
};
