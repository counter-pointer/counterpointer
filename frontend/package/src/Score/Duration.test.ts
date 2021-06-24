import Duration from "./Duration"



test("toABC", () => {
  expect(Duration.toABC(Duration.QUARTER)).toBe("2");
  expect(Duration.toABC(Duration.HALF)).toBe("4");
  expect(Duration.toABC(Duration.WHOLE)).toBe("8");
})