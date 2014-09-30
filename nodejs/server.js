/**
 * Created by 孙艺峰 on 2014/9/28 0028.
 */
var http=require('http');
var express=require('express');
var routes=require('./route/index');
var user=require('./route/user');
var search=require('./route/search');
var dailylog=require('./route/dailylog');
var dayoff=require('./route/dayoff');
var sign=require('./route/sign');
var app=express();
sign(app);
dayoff(app);
dailylog(app);
routes(app);
user(app);
search(app);
app.listen(80,function()
{
    console.log('api server is start!');
});
//这句话相当于把start函数变成了public函数,可以供外部调用
//外部可以使用require文件为server变量后调用 server.start();