N,M=map(int,input().split())
riceCake= list(map(int,input().split()))

S=0
sum=0
while True:
  for height in riceCake:
    if height-S <0:
      sum+=0
    else:
      sum+=height-S
  if sum < M: break
  S=S+1
  sum=0

print(S-1)

## 더 효율적인 코드 고민해 볼 것
#이진 탐색으로 가능함