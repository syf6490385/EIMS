/**
 * Created by 孙艺峰 on 2014/9/28 0028.
 */

var DailyFunction = require('./controllers/daily');
var dayoffFunction = require('./controllers/dayoff');
var signFunction = require('./controllers/sign');
var userfunction = require('./controllers/user');


module.exports = function(app)
{
    // home page
    app.get('/',function(req,res)
    {
        res.send("hello homepage");
    });

    //dailylog
    app.get('/dailylog/add',DailyFunction.add);
    app.get('/dailylog/reply',DailyFunction.reply);
    app.get('/dailylog/list',DailyFunction.list);

    //dayoff
    app.get('/dayoff/add',dayoffFunction.add);
    app.get('/dayoff/reply',dayoffFunction.reply);
    app.get('/dayoff/list',dayoffFunction.list);

    //search
    app.get('/search',function(req,res){
        res.send("search");
    });

    //sign
    app.get('/sign/add',signFunction.add);
    app.get('/sign/list',signFunction.list);

    //user
    app.get('/user/add',userfunction.add);
    app.get('/user/del',userfunction.del);
    app.get('/user/edit',userfunction.edit);
    app.get('/user/list',userfunction.list);
    app.get('/user/login',userfunction.login);
    app.get('/user/logout',userfunction.logout);
    app.get('/user/sign',userfunction.sign);
    app.get('/user/dayoff',userfunction.dayoff);
    app.get('/user/dailylog',userfunction.dailylog);
    app.get('/user/relly',userfunction.reply);
};