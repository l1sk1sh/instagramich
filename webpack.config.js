const path = require('path');

module.exports = {
    entry: './src/main/js/index.js',
    devtool: 'sourcemaps',
    cache: true,
    output: {
        path: __dirname,
        filename: './src/main/resources/static/js/bundle.js' //TODO Versioning of JS
        //TODO building of CSS
    },
    module: {
        rules: [
            { //TODO Remove redundant rules
                test: path.join(__dirname, '.'),
                exclude: [
                    /(node_modules)/,
                    /\.svg$/,
                    'src/main/js/assets'
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