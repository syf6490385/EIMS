/**
 * Created by 孙艺峰 on 2014/9/30 0030.
 */
var signFunction=require('../function/SignFunction')
module.exports=function(app)
{
    app.get('/sign/add',signFunction.add);
    app.get('/sign/list',signFunction.list);
}