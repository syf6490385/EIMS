/**
 * Created by haihu on 2014/10/5.
 */
var User =  require('../models').User;

exports.findByid=function(id,callback){
    var conditions={_id:id};
    User.find(conditions,callback);
};

exports.add = function(name, age, pwd, lv, callback){
    var newUser = new User();
    newUser.name = name;
    newUser.age = age;
    newUser.pwd = pwd;
    newUser.lv = lv;
    newUser.save(callback);
};

exports.list=function(callback){
    User.find({},callback);
};

exports.edit = function(id,newname,age,pwd,lv,callback){
    var update = {$set:{name:newname,pwd:pwd,age:age,lv:lv}};
    var options = {upsert:true};
    User.update({_id:id},update,options,callback);
};

exports.del = function(id){
    var conditions = {_id:id};
    User.remove(conditions,function(err) {
        if (err) {
            console.log("delete failed!" + err);
        } else {
            console.log('delete ok!');
        }
    });
};

exports.login = function(name,pwd,callback){
    var conditions = {name:name,pwd:pwd};
    User.find(conditions,callback);
}