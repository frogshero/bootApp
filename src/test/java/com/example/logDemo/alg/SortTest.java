package com.example.logDemo.alg;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

@Slf4j
public class SortTest {

  /**
   * 快排：设定基准值，把小于基准的放在左，大于的放右(互相对调)，基准放中间，基准左右的部分数组元素各自递归
   */
  @Test
  public void testQuick() {
//    int[] arr = {1}; //先测试最小数组
//    int[] arr = {9, 6};
    int[] arr = {9, 6, 1, 7, 2, 8, 3, 4, 5};
//    int[] arr = {9, 6, 1, 2, 8, 7, 8, 9};

    int[] arr2 = Arrays.copyOf(arr, arr.length);
    Arrays.sort(arr2);
    log.info(Arrays.toString(arr2));
    log.info(Arrays.toString(arr));

    myQSort(arr, 0, arr.length - 1);
    log.info(Arrays.toString(arr));
  }

  private void myQSort(int[] arr, int left, int right) {
    if (left >= right) return; //只有一个元素就退出
    int l = left;
    int r = right;

    int pivot = r;
    r--;

    //2个元素l=r不进入循环
    while (l < r) {
      while (arr[l] < arr[pivot] && l < r) {
        l++;
      }
      while (arr[r] > arr[pivot] && l < r) {
        r--;
      }
      //右边比基准值小和左边比基准值大的交换，直到左=右
      if (l < r) {
        swap(arr, l, r);
      }
    }

    //l=r时这个值再和p比较下,保证p在最终正确的位置
    if (arr[l] > arr[pivot]) {
      swap(arr, l, pivot);
    }
    log.info(Arrays.toString(arr));

    myQSort(arr, left, l - 1);
    myQSort(arr, l + 1, right);
  }

  private void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}
