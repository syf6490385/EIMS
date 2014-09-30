var userfunction=require('../function/Userfunction');
module.exports = function(app)
{
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