package com.reemii.com.testforleetcode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View viewById = findViewById(R.id.start);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] data1 = {1, 2};
                int[] data2 = {3, 4};

                float ints = (float) findMedianSortedArrays(data1, data2);
//                Log.d("hhh", ints[0] + "," + ints[1]);
            }
        });

    }


    /**
     * There are two sorted arrays nums1 and nums2 of size m and n respectively.
     * <p>
     * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
     * <p>
     * Example 1:
     * nums1 = [1, 3]
     * nums2 = [2]
     * <p>
     * The median is 2.0
     * Example 2:
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     * <p>
     * The median is (2 + 3)/2 = 2.5
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double midValue = 0;
        int[] data = new int[nums1.length + nums2.length];

        for (int i = 0; i < nums1.length; i++) {
            data[i] = nums1[i];
        }

        for (int i = 0; i < nums2.length; i++) {
            data[i + nums1.length] = nums2[i];
        }

        Arrays.sort(data);

        if (data.length % 2 == 0) {
            midValue = (data[data.length / 2] + data[data.length / 2 - 1]) / 2.0;
        } else {
            midValue = data[data.length / 2];
        }

        return midValue;
    }

    /**
     * Given a string, find the length of the longest substring without repeating characters.
     * <p>
     * Examples:
     * <p>
     * Given "abcabcbb", the answer is "abc", which the length is 3.
     * <p>
     * Given "bbbbb", the answer is "b", with the length of 1.
     * <p>
     * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        String currentStr = "";
        String longStr = "";
        String tempStr;
        int i = 0;
        boolean hasSame;
        int pos = 0;
        while (i < s.length()) {
            tempStr = s.charAt(i) + "";
            hasSame = currentStr.contains(tempStr);
            if (hasSame) {
                if (longStr.length() < currentStr.length()) {
                    longStr = currentStr;
                }
                currentStr = s.charAt(pos + 1) + "";
                pos++;
                i = pos;
            } else {
                currentStr += tempStr;
            }
            i++;
        }
        if (longStr.length() < currentStr.length()) {
            longStr = currentStr;
        }
        return longStr.length();
    }

    /**
     * 做成了，寻找连续的最大字符串了
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1(String s) {
        String currentStr;
        String tempStr;
        int pos = 0;
        int strCount = 1;
        int maxCount = 1;
        int i = 0;
        while (i < s.length()) {
            i++;
            currentStr = s.substring(pos, i);
            if (i + strCount > s.length()) {
                strCount += s.length() - i;
                if (strCount > maxCount) {
                    maxCount = strCount;
                }
                break;
            }
            tempStr = s.substring(pos + strCount, i + strCount);

            if (currentStr.equals(tempStr)) {
                pos = i + strCount;
                i = pos;
                currentStr = "";
                tempStr = "";

                if (strCount > maxCount) {
                    maxCount = strCount;
                }
                strCount = 1;
            } else {
                strCount++;
            }
        }

        return maxCount;
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode headNode = new ListNode(0);
        ListNode nextNode = new ListNode(0);
        int temp = 0;
        int upCount = 0;
        int value1 = 0;
        int value2 = 0;
        ListNode tempNode;
        boolean isFirst = true;

        while (l1 != null || l2 != null) {
            tempNode = new ListNode(0);

            if (l1 != null) {
                value1 = l1.val;
                l1 = l1.next;
            } else {
                value1 = 0;
            }


            if (l2 != null) {
                value2 = l2.val;
                l2 = l2.next;
            } else {
                value2 = 0;
            }


            temp = value1 + value2 + upCount;
            if (temp >= 10) {
                upCount = 1;
            } else {
                upCount = 0;
            }
            temp = temp % 10;

            tempNode.val = temp;

            if (isFirst) {
                nextNode = tempNode;
                headNode = nextNode;
                isFirst = false;
            } else {
                nextNode.next = tempNode;
                nextNode = tempNode;
            }

        }
        if (upCount > 0) {
            tempNode = new ListNode(upCount);
            nextNode.next = tempNode;
            nextNode = tempNode;
        }
        return headNode;

    }


    /**
     * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
     * <p>
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     * <p>
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     *
     * @param l1
     * @param l2
     * @return
     */
    public int[] addTwoNumbers(int[] l1, int[] l2) {
        int[] resultList = new int[5];
        int[] longList;
        int[] shortList;

        if (l1.length > l2.length) {
            longList = l1;
            shortList = l2;
        } else {
            shortList = l1;
            longList = l2;
        }

        int temp = 0;
        int upCount = 0;
        int value1 = 0;
        int value2 = 0;

        for (int i = 0; i < longList.length; i++) {
            value1 = longList[i];
            if (l2.length > i) {
                value2 = shortList[i];
            } else {
                value2 = 0;
            }
            temp = value1 + value2 + upCount;
            if (temp >= 10) {
                upCount = 1;
            } else {
                upCount = 0;
            }
            temp = temp % 10;
            resultList[i] = (temp);
        }

        if (upCount > 0) {
            resultList[longList.length] = upCount;
        }

        return resultList;
    }


    /**
     * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
     * <p>
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     * <p>
     * Example:
     * Given nums = [2, 7, 11, 15], target = 9,
     * <p>
     * Because nums[0] + nums[1] = 2 + 7 = 9,
     * return [0, 1].
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] finalResult = new int[2];
        int temp1 = 0;
        int temp2 = 0;
        int value1 = 0;
        int value2 = 0;
        boolean isFinish = false;

        ArrayList<Integer> posList = new ArrayList<>();
        ArrayList<Integer> valueList = new ArrayList<>();
        // 进行数据过滤
        for (int i = 0; i < nums.length; i++) {
            posList.add(i);
            valueList.add(nums[i]);
        }


        for (int i = 0; i < posList.size(); i++) {
            temp1 = posList.get(i);
            for (int j = i + 1; j < posList.size(); j++) {
                temp2 = posList.get(j);
                value1 = valueList.get(temp1);
                value2 = valueList.get(temp2);
                if ((value1 + value2) == target) {
                    finalResult[0] = temp1;
                    finalResult[1] = temp2;
                    isFinish = true;
                    break;
                }
            }
            if (isFinish == true) {
                break;
            }
        }


        return finalResult;
    }
}
