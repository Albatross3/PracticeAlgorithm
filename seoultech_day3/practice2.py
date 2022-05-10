##첫 번째 방법
#target값 찾은 후 target값 기준으로 좌,우에서 target값 존재하는 index 찾기
#O(logn)+... , 최악의 경우(같은 숫자 반복시) O(n)을 더해야 함


N,x=map(int,input().split())
array1=list(map(int,input().split()))

# 재귀호출시 반드시 return을 붙여주어야 한다
def findTargetIndex(array,target,start,end):
  mid=(start+end)//2

  #start와 end가 역전되는 순간 target값이 array안에 존재 x
  if start>end:
    return 0

  if array[mid] < target:
    return findTargetIndex(array,target,mid+1,end)
  elif array[mid] > target:
    return findTargetIndex(array,target,start,mid-1)
  else:
    return mid   # 중앙의 위치와 target값 같으면 index 리턴

index=findTargetIndex(array1,x,0,len(array1)-1)
print(index)

#mid에서 왼쪽 검사
def leftIndex(array,targetIndex):

  while True:
    targetIndex=targetIndex-1
    if array[targetIndex] != x:
      leftIndex=targetIndex+1
      break
  return leftIndex

#mid에서 오른쪽 검사
def rightIndex(array,targetIndex):

  while True:
    targetIndex=targetIndex+1
    if array[targetIndex] != x:
      rightIndex=targetIndex-1
      break
  return rightIndex

if index!=0:
  leftIndex=leftIndex(array1,index)
  rightIndex=rightIndex(array1,index)

  print(rightIndex-leftIndex+1)
else:
  print(-1)

##두 번째 방법
#이진 탐색으로 최선의 왼쪽 index,오른쪽 index 구하는 방법
#O(logn), 두 번째 방법이 확실한 해결책