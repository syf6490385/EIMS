/**到百度首页
 * Created by 孙艺峰 on 2014/9/28 0028.
 */
var userDao=require('../models/User');
function list(req,res)
{
    userDao.list(function(err,docs)
    {
        res.send(docs);
    });
   // res.send('this is user list');
}
function add(req,res)
{
    var name=req.query.name;
    var age=req.query.age;
    var pwd=req.query.pwd;
    var lv=req.query.lv;
    userDao.add(name,age,pwd,lv);
    res.send({result:'ok'});;
}
function del(req,res)
{
    var id=req.query.id;
    userDao.del(id);
    res.send({result:'ok'});;
}
function edit(req,res)
{
    var id=req.query.id;
    var newname=req.query.newname;
    var age=req.query.age;
    var pwd=req.query.pwd;
    var lv=req.query.lv;
    userDao.edit(id,newname,age,pwd,lv);
    res.send({result:'ok'});;
}
function login(req,res)
{
    var name=req.query.name;
    var pwd=req.query.pwd;
    userDao.login(name,pwd,function(err,docs){
        if(docs&&docs.length>0)
        {
        res.send(docs[0]._id);
        }
        else
        {
            res.send('0');
        }}
        );
}
function logout(req,res)
{
    res.send('this is 退出登录 page');
}
function sign(req,res)
{
    res.send('this is 签到 page');
}
function dayoff(req,res)
{
    res.send('this is 请假 page');
}
function reply(req,res)
{
    res.send('this is 回复条目 page');
}
function dailylog(req,res)
{
    res.send('this is 工作日志 page');
}
exports.list=list;
exports.login=login;
exports.logout=logout;
exports.sign=sign;
exports.add=add;
exports.del=del;
exports.edit=edit;
exports.dayoff=dayoff;
exports.reply=reply;
exports.dailylog=dailylog;