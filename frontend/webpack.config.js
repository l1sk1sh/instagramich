const webpack = require('webpack');
const path = require('path');

module.exports = {
    entry: './src/main/js/com/multiheaded/webapp/frontend/index.js',
    devtool: 'sourcemaps',
    cache: true,
    output: {
        path: path.resolve(__dirname, "../backend/src/main/resources/static/js"),
        filename: 'bundle.js',
        //TODO Versioning of JS
        //TODO building of CSS
    },
    devServer: {
        hot: true,
        inline: true,
        // host: '0.0.0.0', // for Docker
        publicPath: '/js/',
        contentBase: path.join(__dirname, '../backend/src/main/resources/static'),
        historyApiFallback: true,
        watchContentBase: true,
        compress: true,
        port: 9000
    },
    optimization: {
        removeAvailableModules: false,
        removeEmptyChunks: false,
        splitChunks: false,
    },
    module: {
        rules: [
            {
                test: path.join(__dirname, '.'),
                exclude: [
                    /(node_modules)/
                ],
                loader: 'babel-loader',
                query: {
                    cacheDirectory: true,
                    presets: ['react', 'env']
                }
            },
            {
                test: /(\.css$)|(\.less$)/,
                use: [
                    { loader: "style-loader" },
                    { loader: "css-loader?sourceMap" },
                    { loader: 'less-loader', options: { javascriptEnabled: true } }
                ],
            }
        ]
    },
    plugins: [
        new webpack.HotModuleReplacementPlugin()
    ]
};