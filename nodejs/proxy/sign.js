/**
 * Created by haihu on 2014/10/5.
 */

var Sign = require('../models').Sign;
var userDao = require('./user');

exports.add = function(userid){
    userDao.findByid(userid,function(err,docs){
        var sign = new Sign();
        sign.userid = userid;
        sign.username = docs[0].name;
        sign.save(function(err){
            if(!err){
                console.log('签到保存正常,签到内容为'+sign);
            } else {
                console.log('签到保存失败');
            }
        });
    });
};

exports.list = function(userid,year,month,callback){
    var start = new Date(year,month,1);
    var end = new Date(year,month,31);
    var conditions = {
        userid: userid,
        time: {$gt: start, $lt: end}
    };
    Sign.find(conditions,callback);
};