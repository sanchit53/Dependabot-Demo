const express = require('express');
const app = express();
app.get('/health', (_req, res) => res.json({ service: 'node', status: 'ok' }));
app.listen(process.env.PORT || 8082, () => console.log('node service listening'));
