num_arr = list(map(int, input().split()))

i=0
num_increase=1
increaseArr=[]
while num_arr[i]!=-1:
  if num_arr[i]<num_arr[i+1]:
    num_increase+=1
  else:
    if num_increase!=1:
      increaseArr.append(num_increase)
      num_increase=1
    if num_arr[i+1]==-1:
      increaseArr.append(1)
  i+=1

i=0
num_decrease=1
decreaseArr=[]
while num_arr[i]!=-1:
  if num_arr[i]>num_arr[i+1]:
    num_decrease+=1
    if num_arr[i+1]==-1:
      decreaseArr.append(num_decrease-1)
  else:
    if num_decrease!=1:
      decreaseArr.append(num_decrease)
      num_decrease=1
  i+=1

ans=[]

if max(increaseArr) <=2:
  ans.append(1)
else:
  ans.append(max(increaseArr))

print(increaseArr)

if max(decreaseArr) <= 2:
  ans.append(1)
else:
  ans.append(max(decreaseArr))

for i in range(2):
  print(ans[i],end=" ")
