/**
 * Created by 孙艺峰 on 2014/9/30 0030.
 */
var mongoose=require('mongoose');
var Schema=mongoose.Schema;
var userDao=require('./User');
var moment=require('./moment');
var SignSchema=new Schema({
    userid:String,
    username:String,
    time:{type:Date,default:Date.now()}
});
mongoose.model('Sign',SignSchema);
var Sign=mongoose.model('Sign');
exports.add=function(userid)
{
    userDao.findByid(userid,function(err,docs)
    {
        var sign=new Sign();
        sign.userid=userid;
        sign.username=docs[0].name;
        sign.save(function(err)
            {
                if(!err)
                {
                    console.log('签到保存正常,签到内容为'+sign);
                }
                else
                {
                    console.log('签到保存失败');
                }
            }
          );
    });

}
exports.list=function(userid,year,month,callback)
{
    var start=new Date(year,month,1);
    var end=new Date(year,month,31);
    var conditions=
    {
        userid: userid,
        time: {$gt: start, $lt: end}
    };
    Sign.find(conditions,callback);

}