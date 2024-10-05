import sys
input = sys.stdin.readline
def getInts(): return map(int, input().split())


for _ in range(int(input())):
    W, N = getInts()
    X, w = 0, W
    for _ in range(N):  # 규칙 3번
        x_i, w_i = getInts()
        if w - w_i < 0:  # 규칙 2번 - 비우고 다시오기
            X += x_i*2
            w = W - w_i
        elif w - w_i == 0:  # 규칙 1번
            X += x_i*2
            w = W
        else:
            w -= w_i
    if w != W: # 마지막 집감
        X += x_i*2

    print(X)
