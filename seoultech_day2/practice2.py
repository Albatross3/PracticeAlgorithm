N=int(input())
N_list=list(map(int,input().split()))

M=int(input())
M_list=list(map(int,input().split()))

# N_list sort (ascending)
N_list.sort()

# define binary search
def binarySearch(data,target):
  start=0
  end=len(data)-1

  while start<= end:
    mid=(start+end)//2

    if data[mid]==target:
      return "yes"
    elif data[mid] < target:
      start=mid+1
    else:
      end=mid-1
  return "no"

for m in M_list:
  print(binarySearch(N_list,m), end=" ")

