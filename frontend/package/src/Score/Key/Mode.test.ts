import Mode from './Mode';

test('toString', () => {
  expect(Mode.toString(Mode.AEOLIAN)).toBe('aeo');
  expect(Mode.toString(Mode.DORIAN)).toBe('dor');
  expect(Mode.toString(Mode.IONIAN)).toBe('maj');
  expect(Mode.toString(Mode.MIXOLYDIAN)).toBe('mix');
  expect(Mode.toString(Mode.PHRYRGIAN)).toBe('phr');
});

test('fromString', () => {
  expect(Mode.fromString('aeo')).toBe(Mode.AEOLIAN);
  expect(Mode.fromString('dor')).toBe(Mode.DORIAN);
  expect(Mode.fromString('maj')).toBe(Mode.IONIAN);
  expect(Mode.fromString('mix')).toBe(Mode.MIXOLYDIAN);
  expect(Mode.fromString('phr')).toBe(Mode.PHRYRGIAN);
});

