package com.moon.arithmetic.linkedlist;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author yujiangtao
 * @date 2020/9/27 上午11:52
 */
public class PalindromeLinkedListTest {

    @Test
    public void testCreate() {
        PalindromeLinkedList palindrome = new PalindromeLinkedList("abcdefgfedcba");
        palindrome.printAllNodes();
    }

    @Test
    public void testIsPalindrome() {
        PalindromeLinkedList palindrome = new PalindromeLinkedList("abcdefgfedcba");
        Assert.assertTrue(palindrome.isPalindrome());

        PalindromeLinkedList palindrome2 = new PalindromeLinkedList("abcdefggfedcba");
        Assert.assertTrue(palindrome2.isPalindrome());

        PalindromeLinkedList palindrome3 = new PalindromeLinkedList("abcdefgkfedcba");
        Assert.assertFalse(palindrome3.isPalindrome());

        PalindromeLinkedList palindrome4 = new PalindromeLinkedList("abcdefgfejcba");
        Assert.assertFalse(palindrome4.isPalindrome());

        PalindromeLinkedList palindrome5 = new PalindromeLinkedList("a");
        Assert.assertTrue(palindrome5.isPalindrome());
    }

    @Test
    public void testReverseLinkedList() {
        PalindromeLinkedList palindrome = new PalindromeLinkedList("abcdefgh");
        PalindromeLinkedList.Node node = palindrome.reverseLinkedList(palindrome.getHead());
    }
}
