# 다시 풀어볼 것 
N=int(input())
scary_list=list(map(int,input().split()))

# scary_list sort, O(Nlog(N))
scary_list.sort()

ans=0
group_count=0
for i in scary_list:
  group_count+=1
  if group_count >= i:
    ans+=1
    group_count=0

print(ans)

## 2번쨰 풀이, O(N)

# N=int(input())
# scary_list=list(map(int,input().split()))


# #O(N)의 시간복잡도
# count_list=[]
# for i in range(N+1):
#   count_list.append(0)

# for i in scary_list:
#   count_list[i]+=1
# print(count_list)



# # O(N^2)의 시간복잡도
# # count_list=[]
# # for i in range(N+1):
# #   count_list.append(scary_list.count(i))

# # print(count_list)

# result=0
# remainder=0
# for i in range(1,N+1):
#   if remainder!=0:
#     result+= (remainder+count_list[i])//i
#     reaminder= (remainder+count_list[i])%i
#   else:
#     result+= count_list[i]//i
#     remainder=count_list[i] % i

# print(result)

