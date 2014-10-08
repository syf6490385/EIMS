/**
 * Created by 孙艺峰 on 2014/9/28 0028.
 */

var Daily = require('./controllers/daily');
var Dayoff = require('./controllers/dayoff');
var Sign = require('./controllers/sign');
var User = require('./controllers/user');

module.exports = function(app)
{
    // home page
    app.get('/',function(req,res)
    {
        res.send("hello homepage");
    });

    //dailylog
    app.get('/dailylog/add',Daily.add);
    app.get('/dailylog/reply',Daily.reply);
    app.get('/dailylog/list',Daily.list);

    //dayoff
    app.get('/dayoff/add',Dayoff.add);
    app.get('/dayoff/reply',Dayoff.reply);
    app.get('/dayoff/list',Dayoff.list);

    //search
    app.get('/search',function(req,res){
        res.send("search");
    });

    //sign
    app.get('/sign/add',Sign.add);
    app.get('/sign/list',Sign.list);

    //user
    app.get('/user/add',User.add);
    app.get('/user/del',User.del);
    app.get('/user/edit',User.edit);
    app.get('/user/list',User.list);
    app.get('/user/login',User.login);
    app.get('/user/logout',User.logout);
    app.get('/user/sign',User.sign);
    app.get('/user/dayoff',User.dayoff);
    app.get('/user/dailylog',User.dailylog);
    app.get('/user/relly',User.reply);
};