const path = require("path");
const express = require("express");
const webpack = require("webpack");

var host="0.0.0.0";
var port=10000;
var app = express();

app.get("/", function (req, res) {
    //res.send("hello");
    res.sendfile(path.join(__dirname, "static", "index.html"));
});

app.listen(port, host, function (err) {
    if(err){
        console.log(err);
        return;
    }
    console.log("server is running, please visit http://" + host + ":" + port);
});
