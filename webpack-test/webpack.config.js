const path  = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const webpack = require("webpack"); // support es5

module.exports = {
    mode: 'production',// development, production, none
    entry: './src',
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: 'bundle.js'
    },
    module: {
        rules:[
            {test:/\.txt$/, use: 'raw-loader'},
            {test:/\.css$/, use: 'css-loader'},
            {test:/\.ts$/, use: 'ts-loader'}
        ]
    },
    plugins: [
        new webpack.ProgressPlugin(),
        new HtmlWebpackPlugin({template: './src/index.html'})
    ]
}