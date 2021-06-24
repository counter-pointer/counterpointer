import Accidental from './Accidental';

test('raise', () => {
  expect(Accidental.raise(Accidental.NAT)).toBe(Accidental.SHARP);
  expect(Accidental.raise(Accidental.SHARP)).toBe(Accidental.DBLSHARP);
  expect(Accidental.raise(Accidental.DBLSHARP)).toBe(Accidental.DBLSHARP);
  expect(Accidental.raise(Accidental.FLAT)).toBe(Accidental.NAT);
  expect(Accidental.raise(Accidental.DBLFLAT)).toBe(Accidental.FLAT);
})

test('lower', () => {
  expect(Accidental.lower(Accidental.NAT)).toBe(Accidental.FLAT);
  expect(Accidental.lower(Accidental.SHARP)).toBe(Accidental.NAT);
  expect(Accidental.lower(Accidental.DBLSHARP)).toBe(Accidental.SHARP);
  expect(Accidental.lower(Accidental.FLAT)).toBe(Accidental.DBLFLAT);
  expect(Accidental.lower(Accidental.DBLFLAT)).toBe(Accidental.DBLFLAT);
})


test('toString', () => {
  expect(Accidental.toString(Accidental.NAT)).toBe('');
  expect(Accidental.toString(Accidental.SHARP)).toBe('#');
  expect(Accidental.toString(Accidental.DBLSHARP)).toBe('##');
  expect(Accidental.toString(Accidental.FLAT)).toBe('b');
  expect(Accidental.toString(Accidental.DBLFLAT)).toBe('bb');
});

test('fromString', () => {
  expect(Accidental.fromString('')).toBe(Accidental.NAT);
  expect(Accidental.fromString('#')).toBe(Accidental.SHARP);
  expect(Accidental.fromString('##')).toBe(Accidental.DBLSHARP);
  expect(Accidental.fromString('b')).toBe(Accidental.FLAT);
  expect(Accidental.fromString('bb')).toBe(Accidental.DBLFLAT);
});

