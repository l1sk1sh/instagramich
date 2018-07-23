const path = require('path');

module.exports = {
    entry: './src/main/js/com/multiheaded/webapp/frontend/index.js',
    devtool: 'sourcemaps',
    cache: true,
    output: {
        path: __dirname,
        filename: '../backend/src/main/resources/static/js/bundle.js'
        //TODO Versioning of JS
        //TODO building of CSS
    },
    devServer: {
        contentBase: path.join(__dirname, '../backend/src/main/resources/static/'),
        compress: true,
        port: 9000
    },
    module: {
        rules: [
            {
                test: path.join(__dirname, '.'),
                exclude: [
                    /(node_modules)/,
                    /\.svg$/
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
            },
            {
                test: /\.svg$/,
                use: [{
                    loader: 'babel-loader'
                }, {
                    loader: 'react-svg-loader'
                }]
            },
        ]
    }
};