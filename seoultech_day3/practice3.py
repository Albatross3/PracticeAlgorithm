# 37.5점/100점 풀이, 알고리즘 다시 짜볼 필요 있음
k=int(input())
food_times=list(map(int,input().split()))

N=len(food_times)
t=N #N부터 증가하면서 K가 될때까지 확인
n=0 #index 0부터 N-1까지 반복적으로 확인

for i in range(len(food_times)):
    food_times[i]=food_times[i]-1
    
if k < N:
  print(k+1)
else:
  while t<=k:
    # t일 때 대응 값이 n+1 [t: n+1]
    if food_times[n]!=0:
      food_times[n]=food_times[n]-1
      t=t+1
      
    
    # n이 전체 길이에 도달할 때 초기화
    if n==N-1:
      n=0
    else:
      n=n+1

  if n==0:
    print(N)
  else:
    print(n)  

