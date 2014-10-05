/**
 * Created by haihu on 2014/10/5.
 */

var mongoose = require('mongoose');
var config = require('../config');

mongoose.connect(config.db, function (err) {
    if (err) {
        console.error('connect to %s error: ', config.db, err.message);
        process.exit(1);
    }
});

// models
require('./dailylog');
require('./dayoff');
require('./sign');
require('./user');

exports.DailyLog = mongoose.model('DailyLog');
exports.User = mongoose.model('User');
exports.Dayoff = mongoose.model('Dayoff');
exports.Sign = mongoose.model('Sign');

