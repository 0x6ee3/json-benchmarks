const { send } = require('micro')
const { router, get } = require('microrouter')

 
const helloJson = (req, res) => {
  res.setHeader('Server', 'node-micro')
  let m = {message: 'Hello World!'}
  return m
}

const helloPlain = (req, res) => {
  res.setHeader('Server', 'node-micro')
  return 'Hello World!'
}

module.exports = router(
  get('/json', helloJson),
  get('/*', helloPlain)
)

