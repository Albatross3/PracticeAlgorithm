n=int(input())

# c=[]
# for i in range(n+1):
#   c.append(float("inf"))
# c[0]=0
# c[1]=0
#위 코드 대체 방법, 기본 리스트 만드는 법: 곱하기 연산
c=[0]*(n+1)


d=[5,3,2]
for j in range(2,n+1):
  x=[]
  for k in d:
    if j%k==0:
      x.append(c[j//k]+1)
  x.append(c[j-1]+1)
  c[j]=min(x)

print(c[n])