/**
 * Created by 孙艺峰 on 2014/9/30 0030.
 */
var dayoffFunction=require('../function/DayoffFunction')
module.exports=function(app)
{
    app.get('/dayoff/add',dayoffFunction.add);
    app.get('/dayoff/reply',dayoffFunction.reply);
    app.get('/dayoff/list',dayoffFunction.list);
}