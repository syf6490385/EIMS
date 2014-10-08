/**
 * Created by haihu on 2014/10/5.
 */

var pkg = require('./package.json');

var config = {
    debug: true,
    name: 'EIMS',
    description: 'EIMS is a simple employee system',
    version: pkg.version,

    db: 'mongodb://127.0.0.1/EIMS',
    db_name: 'EIMS',
    port: 3000
};


module.exports = config;