const osuCalc = require('osu-sr-calculator');
const express = require('express')
const app = express()
const port =  process.env.PORT || 3000;

app.get('/:id', (req, res) => {
    const starRating = osuCalc.calculateStarRating(req.params.id).then((data) => 
    res.status(200).send(data)).catch((error) => res.status(404));
})

app.listen(port, () => console.log(`Server locale in ascolto sulla porta:${port}`))