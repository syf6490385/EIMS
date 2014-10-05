/**
 * Created by 孙艺峰 on 2014/9/30 0030.
 */
var signDao=require('../models/Sign');
function add(req,res)
{
    var userid=req.query.userid;
    signDao.add(userid);
    res.send('add');
}
function list(req,res)
{
    var year=req.query.year;
    var month=req.query.month;
    var userid=req.query.userid;
    var m=parseInt(month);
    m=m-1;
    console.log('month is'+m);
    signDao.list(userid,year,m,function(err,docs)
    {
        res.send(docs);
    }
    );
}
exports.add=add;
exports.list=list;