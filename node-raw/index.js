const http = require('http')

http.createServer((req, res) => {
    res.setHeader('Server', 'node-raw')
    if (req.url == '/json') {
        let m = {message: 'Hello World!'}
        res.end(JSON.stringify(m))
    } else {
        res.end('Hello World!')
    }
}).listen(process.env.PORT || 8000)
