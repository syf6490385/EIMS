/**
 * Created by 孙艺峰 on 2014/9/30 0030.
 */

var signDao = require('../proxy').Sign;
var userDao = require('../proxy').User;

exports.add = function(req,res){
    var userid = req.query.userid;
    userDao.findByid(userid, function(err,docs){
        if(err){
            console.error("err");
        } else {
            var name = docs[0].name;
            signDao.add(userid, name, function(err){
                if(!err){
                    console.log('签到保存正常');
                    res.send('add');
                } else {
                    console.log('签到保存失败');
                }
            });
        }
    });
};

exports.list = function(req,res){
    var year = req.query.year;
    var month = parseInt(req.query.month);
    var userid = req.query.userid;
    month = month - 1;
    console.log('month is' + month);
    signDao.list(userid, year, month, function(err,docs){
        res.send(docs);
    });
};
