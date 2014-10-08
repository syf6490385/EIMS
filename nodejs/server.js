/**
 * Created by 孙艺峰 on 2014/9/28 0028.
 */
var http=require('http');
var express=require('express');
var config = require('./config');
var routes = require('./routes');

var app=express();

// all environments
app.set('port', process.env.PORT || config.port);

routes(app);

app.listen(app.get('port'),function()
{
    console.log("Server listening on port %d...", config.port);
});
//这句话相当于把start函数变成了public函数,可以供外部调用
//外部可以使用require文件为server变量后调用 server.start();