const cluster = require('cluster')
const cpus = require('os').cpus().length

if (cluster.isMaster) {
    for (let i = 0; i < cpus; i++) {
        cluster.fork()
    }
} else {
    const http = require('http')

    http.createServer((req, res) => {
        res.setHeader('Server', 'node-raw')
        if (req.url == '/json') {
            let m = {message: 'Hello World!'}
            res.end(JSON.stringify(m))
        } else {
            res.end('Hello World!')
        }
    }).listen(process.env.PORT || 8080)
}
