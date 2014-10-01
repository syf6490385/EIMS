/**
 * Created by 孙艺峰 on 2014/9/30 0030.
 */
var dayoffDao=require('../model/Dayoff');
function add(req,res)
{
    var userid=req.query.userid;
    var content=req.query.content;
    var length=req.query.length;
    var dayofftime=req.query.dayofftime;
    dayoffDao.add(userid,content,length,dayofftime);
    res.send('add');
}
function reply(req,res)
{
    var dayoffid=req.query.dayoffid;
    var content=req.query.content;
    var result=req.query.result;
    var userid=req.query.userid;
    dayoffDao.reply(dayoffid,userid,content,result);
    res.send('reply');
}
function list(req,res)
{
    var userid=req.query.userid;
    dayoffDao.list(userid,function(err,docs)
    {
        res.send(docs);
    })
}
exports.add=add;
exports.reply=reply;
exports.list=list;