var http = require("http");
var url = require("url");

http.createServer(function (req, rep) {
    var pathname = url.parse(req.url).pathname;
    console.log("url:" + req.url);
    console.log("pathname:" + pathname);

    rep.writeHead(200, {"Content-Type":"text/plain"});
    rep.write("Hello");
    rep.end();
}).listen(8888);
console.log("server started");
console.time("console log");
console.log(__filename);
console.log(__dirname);
console.timeEnd("console log");