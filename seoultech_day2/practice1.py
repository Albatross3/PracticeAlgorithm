S=list(input())
A=[]
for x in S:
  A.append(int(x))


for i in range(len(A)):
  if i==0:
    if (A[0]+A[1]) < (A[0]*A[1]):
      result=A[0]*A[1]
    else:
      result=A[0]+A[1]
  elif i==1:
    pass
  else:
    if (result+A[i]) < (result*A[i]):
      result= result*A[i]
    else:
      result= result+A[i]

print(result)

# 다른 풀이
# 숫자가 0 또는 1이면 +, 나머지는 ×
    


