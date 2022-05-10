import random


N=int(input())
arr=[]
for _ in range(N):
  arr.append(random.randint(1,6))

dict={}
for i in range(N):
  dict[i+1]=arr[i]

#arr.sort()-> return x but sorted(arr)-> return o
sort_arr=sorted(arr)

print(dict)
print(sort_arr)

ans=[]
for i in range(N):
  for j in range(1,N+1):
    if dict[j]==sort_arr[i]:
      ans.append(j)
      dict[j]+=0.1
      break

print(ans)