import ScaleValue from './ScaleValue';

test('raise', () => {
  expect(ScaleValue.raise(ScaleValue.C)).toBe(ScaleValue.D);
  expect(ScaleValue.raise(ScaleValue.D)).toBe(ScaleValue.E);
  expect(ScaleValue.raise(ScaleValue.E)).toBe(ScaleValue.F);
  expect(ScaleValue.raise(ScaleValue.F)).toBe(ScaleValue.G);
  expect(ScaleValue.raise(ScaleValue.G)).toBe(ScaleValue.A);
  expect(ScaleValue.raise(ScaleValue.A)).toBe(ScaleValue.B);
  expect(ScaleValue.raise(ScaleValue.B)).toBe(ScaleValue.B);
});

test('lower', () => {
  expect(ScaleValue.lower(ScaleValue.C)).toBe(ScaleValue.C);
  expect(ScaleValue.lower(ScaleValue.D)).toBe(ScaleValue.C);
  expect(ScaleValue.lower(ScaleValue.E)).toBe(ScaleValue.D);
  expect(ScaleValue.lower(ScaleValue.F)).toBe(ScaleValue.E);
  expect(ScaleValue.lower(ScaleValue.G)).toBe(ScaleValue.F);
  expect(ScaleValue.lower(ScaleValue.A)).toBe(ScaleValue.G);
  expect(ScaleValue.lower(ScaleValue.B)).toBe(ScaleValue.A);
});

test('toString', () => {
  expect(ScaleValue.toString(ScaleValue.C)).toBe('C');
  expect(ScaleValue.toString(ScaleValue.D)).toBe('D');
  expect(ScaleValue.toString(ScaleValue.E)).toBe('E');
  expect(ScaleValue.toString(ScaleValue.F)).toBe('F');
  expect(ScaleValue.toString(ScaleValue.G)).toBe('G');
  expect(ScaleValue.toString(ScaleValue.A)).toBe('A');
  expect(ScaleValue.toString(ScaleValue.B)).toBe('B');
});

test('fromString', () => {
  expect(ScaleValue.fromString('C')).toBe(ScaleValue.C);
  expect(ScaleValue.fromString('D')).toBe(ScaleValue.D);
  expect(ScaleValue.fromString('E')).toBe(ScaleValue.E);
  expect(ScaleValue.fromString('F')).toBe(ScaleValue.F);
  expect(ScaleValue.fromString('G')).toBe(ScaleValue.G);
  expect(ScaleValue.fromString('A')).toBe(ScaleValue.A);
  expect(ScaleValue.fromString('B')).toBe(ScaleValue.B);
});

