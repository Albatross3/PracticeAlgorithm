N=int(input())

house_cor=[]

for i in range(N):
  cor=int(input())
  house_cor.append(cor)


#find max & min
tmp_max=house_cor[0]
tmp_min=house_cor[0]

for i in range(1,N):
  if house_cor[i]>tmp_max:
    tmp_max=house_cor[i]
  
  if house_cor[i]<tmp_min:
    tmp_min=house_cor[i]

print(tmp_max-tmp_min)

# answer
# 최대 최소 같이 찾아야함