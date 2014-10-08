/**到百度首页
 * Created by 孙艺峰 on 2014/9/28 0028.
 */

var userDao = require('../proxy').User;

exports.add = function(req,res){
    var name=req.query.name;
    var age=req.query.age;
    var pwd=req.query.pwd;
    var lv=req.query.lv;
    userDao.add(name,age,pwd,lv);
    res.send({result:'ok'});;
};

exports.list = function(req,res){
    userDao.list(function(err,docs)
    {
        res.send(docs);
    });
   // res.send('this is user list');
};

exports.del = function(req,res){
    var id=req.query.id;
    userDao.del(id);
    res.send({result:'ok'});;
};

exports.edit = function(req,res){
    var id=req.query.id;
    var newname=req.query.newname;
    var age=req.query.age;
    var pwd=req.query.pwd;
    var lv=req.query.lv;
    userDao.edit(id,newname,age,pwd,lv);
    res.send({result:'ok'});;
};

exports.login = function(req,res){
    var name=req.query.name;
    var pwd=req.query.pwd;
    userDao.login(name,pwd,function(err,docs){
        if (docs&&docs.length>0){
            res.send(docs[0]._id);
        } else {
            res.send('0');
        }
    });
};

exports.logout = function(req,res)
{
    res.send('this is 退出登录 page');
};

exports.sign = function(req,res)
{
    res.send('this is 签到 page');
};

exports.dayoff = function(req,res)
{
    res.send('this is 请假 page');
};

exports.reply = function(req,res)
{
    res.send('this is 回复条目 page');
};

exports.dailylog = function(req,res)
{
    res.send('this is 工作日志 page');
};

