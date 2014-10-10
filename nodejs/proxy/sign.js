/**
 * Created by haihu on 2014/10/5.
 */

var Sign = require('../models').Sign;
var userDao = require('./user');

exports.add = function(userid, name, callback){
    var sign = new Sign();
    sign.userid = userid;
    sign.username = name;
    sign.save(callback);
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