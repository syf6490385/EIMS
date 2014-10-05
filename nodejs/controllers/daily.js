/**
 * Created by 孙艺峰 on 2014/9/29 0029.
 */
var dailyDao=require('../models/dailylog');
function add(req,res)
{
    var userid=req.query.userid;
    var content=req.query.content;
    var selfrate=req.query.selfrate;
    dailyDao.add(userid,content,selfrate);
    res.send('add');
}
function reply(req,res)
{
    var dailylogid=req.query.dailylogid;
    var userid=req.query.userid;
    var rate=req.query.rate;
    var reply=req.query.reply;
    dailyDao.reply(dailylogid,userid,reply,rate);
    res.send('reply');
}
function list(req,res)
{
    var year=req.query.year;
    var month=req.query.month;
    var userid=req.query.userid;
    var m=parseInt(month);
    m=m-1
    dailyDao.list(year,m,userid,function(err,docs)
    {
        res.send(docs);
    });
}
exports.add=add;
exports.reply=reply;
exports.list=list;