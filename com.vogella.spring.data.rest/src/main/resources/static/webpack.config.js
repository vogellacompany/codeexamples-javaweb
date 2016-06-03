module.exports = {
	entry : './app.js',
	cache : true,
	debug : true,
	output : {
		path : __dirname,
		filename : './build/bundle.js'
	},
	module: {
        loaders: [
            {
                test: /\.js$/,
                loader: 'babel',
                exclude: /node_modules/,
                query: {
                    cacheDirectory: true,
                    presets: ['es2015', 'react']
                }
            }
        ]
    }
};