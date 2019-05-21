var fs = require("fs");
var hello = require("./hello");

var data = fs.readFileSync('input.txt');

console.log(data.toString());
console.log('game over');

hello.say("test");