import Note from "abceditor/lib/Score/Note"

export const getSuggestions = (score, species) => {
  return fetch('/api/suggest?' + new URLSearchParams({'species': species}), {
    method: 'POST',
    body: JSON.stringify(score)
  }).then(r => {
    if (!r.ok) throw new Error('Something went wrong.');
    return r.json();
  }).then(data => {
    return data.map(Note.fromJSON);
  })
}

export const checkScore = (score, species, beatsPerBar=4) => {
  return fetch('/api/check?' + new URLSearchParams({'species': species}), {
    method: 'POST',
    body: JSON.stringify(score)
  }).then(r => {
    if (!r.ok) throw new Error('Something went wrong.');
    return r.json();
  }).then(data => data.map(violation => ({
    measure: Math.floor(violation.beat / beatsPerBar),
    beat: Math.floor(violation.beat % beatsPerBar),
    name: violation.rule.name,
    description: violation.rule.description,
  })));
}

export const generateScore = (score, species) => {
  return fetch('/api/generate?' + new URLSearchParams({'species': species}), {
    method: 'POST',
    body: JSON.stringify(score)
  }).then(r => {
    if (!r.ok) throw new Error('Something went wrong.');
    return r.json();
  });
}