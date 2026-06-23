import express from 'express';

const app = express();
app.get('/health', (_req, res) => res.json({ service: 'typescript', status: 'ok' }));
app.listen(process.env.PORT || 8085, () => console.log('typescript service listening'));
