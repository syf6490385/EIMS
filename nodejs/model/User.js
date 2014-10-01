/**
 * Created by 孙艺峰 on 2014/9/28 0028.
 */
var mongoose=require('mongoose');
var Schema=mongoose.Schema;
mongoose.connect('mongodb://127.0.0.1/employee');
var UserScheme=new Schema({
    name:String,
    age:Number,
    pwd:String,
    lv:Number//级别1 普通员工 级别2 负责人 级别3 领导
});
mongoose.model('User',UserScheme);
var User=mongoose.model('User');
exports.findByid=function(id,callback)
{
    var conditions={_id:id};
    User.find(conditions,callback);
}
exports.add=function(name,age,pwd,lv)
{
    var newUser=new User();
    newUser.name=name;
    newUser.age=age;
    newUser.pwd=pwd;
    newUser.lv=lv;
    newUser.save(function(err)
    {
        if(!err)
        {
            console.log('save user!');
        }
        else
        {
            console.log('save user failed!');
        }
    }
    )
}
exports.list=function(callback)
{
     User.find({},callback);
}
exports.edit=function(id,newname,age,pwd,lv,callback)
{
    var update={$set:{name:newname,pwd:pwd,age:age,lv:lv}};
    var options={upsert:true};
    User.update({_id:id},update,options,function(error){
        if(error) {
            console.log("update failed"+error);
        } else {
            console.log('update ok!');
        }
    });
}
exports.del=function(id)
{
    var conditions={_id:id};
    User.remove(conditions,function(err) {
        if (err) {
            console.log("delete failed!" + err);
        } else {
            console.log('delete ok!');
        }
    });
}
exports.login=function(name,pwd,callback)
{
    var conditions={name:name,pwd:pwd};
    User.find(conditions,callback);
}