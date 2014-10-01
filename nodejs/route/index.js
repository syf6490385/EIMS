/**
 * Created by 孙艺峰 on 2014/9/28 0028.
 */
module.exports = function(app)
{
    app.get('/',function(req,res)
    {
        res.send("hello homepage");
    });
};