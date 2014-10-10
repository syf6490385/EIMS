/**
 * Created by 孙艺峰 on 2014/9/30 0030.
 */
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

//var moment = require('../utility/moment');
var SignSchema = new Schema({
    userid:     String,
    username:   String,
    time:{type:Date,default:Date.now()}
});
mongoose.model('Sign',SignSchema);
