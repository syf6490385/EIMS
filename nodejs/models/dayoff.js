/**
 * Created by 孙艺峰 on 2014/9/30 0030.
 */
var mongoose=require('mongoose');
var Schema=mongoose.Schema;
var userDao=require('./User');
var DayoffSchema=new Schema({
    userid:String,
    username:String,
    time:{type:Date,default:Date.now()},
    length:Number,
    dayofftime:Number,   //24小时制
    content:String,
    reply:String,
    replyid:String,
    replyname:String,
    replytime:{type:Date},
    result:Number //0未批复 1许可 2不许可
});
mongoose.model('Dayoff',DayoffSchema);
var Dayoff=mongoose.model('Dayoff');
exports.add=function(userid,content,length,dayofftime)
{
    var dayoff=new Dayoff();
    dayoff.userid=userid;
    dayoff.content=content;
    dayoff.length=length;
    dayoff.result=0;
    dayoff.dayofftime=dayofftime;
    userDao.findByid(userid,function(err,docs)
    {
        dayoff.username=docs[0].name;
        console.log(dayoff.username);
        dayoff.save(function(err)
        {
            if(!err)
            {
                console.log('请假保存成功');
            }
            else
            {
                console.log(err);
            }
        });
    });
}

exports.reply=function(dayoffid,userid,content,result)
{
    userDao.findByid(userid,function(err,docs)
    {
        var update={$set:{replyid:userid,reply:content,result:result,replyname:docs[0].name,replytime:Date.now()}};
        var options={upsert:true};
        Dayoff.update({_id:dayoffid},update,options,function(error){
            if(error) {
                console.log("回复请假失败");
            } else {
                console.log('回复请假成功');
            }
        });
    });
}
exports.list=function(userid,callback)
{
    var conditions={userid:userid};
    Dayoff.find(conditions,callback);
}