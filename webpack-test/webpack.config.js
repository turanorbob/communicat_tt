const resolve = require("path").resolve;
const HtmlWebpackPlugin = require("html-webpack-plugin");
const webpack = require("webpack"); // support es5
const url = require('url');
const publicPath = '';
const VueLoaderPlugin = require('vue-loader/lib/plugin');

module.exports = (options = {}) => ({
    mode: 'production',// development, production, none
    entry: {
        index: './src/index.js'
    },
    output: {
        path: resolve(__dirname, 'dist'),
        filename: options.dev ? '[name].js' : '[name].js?[chunkhash]',
        chunkFilename: "[id].js?[chunkhash]",
        publicPath: options.dev ? "/assets/" : publicPath
    },
    module: {
        rules: [
            {
                test: /\.vue$/,
                use: ['vue-loader']
            },
            {
                test: /\.js$/,
                use: ['babel-loader'],
                exclude: /node_modules/
            },
            {test: /\.css$/, use: ['vue-style-loader', 'css-loader', 'postcss-loader']},
            {
                test: /\.(gif|jpg|jpeg|png|svg|ttf|eot|woff|woff2|svgz)(\?.+)$/,
                use: [{
                    loader: "url-loader",
                    options: {
                        limit: 10000
                    }
                }]
            }
        ]
    },
    plugins: [
        new VueLoaderPlugin(),
        new HtmlWebpackPlugin({
            template: "src/index.html"
        })
    ],
    resolve: {
        alias: {
            '~': resolve(__dirname, 'src')
        },
        extensions: ['.js', '.vue', '.json', '.css']
    },
    devServer: {
        host: '127.0.0.1',
        port: 8010
    }
})