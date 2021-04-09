const express = require('express');
const bodyParser = require('body-parser');

/**
 * Server configuration
 */

const app = express();

app.use(express.static('./front/'))
app.use(bodyParser.json());

/**
 * Routes
 */
let client;

app.get('/stream', (req, res) => {
    res.set({
        'Content-Type': 'text/event-stream',
        'Connection': 'keep-alive',
        'Cache-Control': 'no-cache'
    });
    res.status(200);

    client = res;
});

app.post('/alerts', (req, res) => {
    res.status(200).json({"message": "ok"});
    const alert = JSON.stringify(req.body);
    client.write(`data: ${alert}\n\n`);
});

/**
 * Start listening
 */

app.listen(3000);
