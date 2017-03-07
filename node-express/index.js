const cluster = require('cluster')
const cpus = require('os').cpus().length

if (cluster.isMaster) {
    for (let i = 0; i < cpus; i++) {
        cluster.fork()
    }
} else {
    const app = require('express')()

    app.set('x-powered-by', false)
    app.set('etag', false)

    app.use((req, res, next) => {
        res.set('Server', 'Express')
        next()
    })

    app.get('/', (req, res) => {
        res.send('Hello World!')
    })

    app.get('/json', (req, res) => {
        let msg = { message: 'Hello World!' }
        res.json(msg)
    })

    app.listen(process.env.PORT || 8000)
}
