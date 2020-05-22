import { MyMatchPipe } from './my-match.pipe';

describe('MyMatchPipe', () => {
  it('create an instance', () => {
    const pipe = new MyMatchPipe();
    expect(pipe).toBeTruthy();
  });
});
