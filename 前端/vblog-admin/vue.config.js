let proxyObj = {};
// 以ws开始就以websocket形式来转发
proxyObj['/ws'] = {
    ws: true,
    target: "ws://localhost:8079",
};
proxyObj['/'] = {
    ws: false,
    target: 'http://localhost:8079',
    changeOrigin: true,
    pathRewrite: {
        '^/': ''
    }
}
module.exports = {
    devServer: {
        host: 'localhost',
        port: 8080,
        proxy: proxyObj
    }
}