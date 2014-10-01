/**
 * Created by 孙艺峰 on 2014/9/29 0029.
 */
var DailyFunction=require('../function/DailyFunction');
module.exports=function(app)
{
    app.get('/dailylog/add',DailyFunction.add);
    app.get('/dailylog/reply',DailyFunction.reply);
    app.get('/dailylog/list',DailyFunction.list);
}