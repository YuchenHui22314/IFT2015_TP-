/**
 * Definition for a binary tree node.*/
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
         return buildBST(nums,0,nums.length-1);
    }
    public TreeNode buildBST(int[] nums,int l,int r){
        if(l>r) return null;
        int mid = (l+r)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBST(nums,l,mid-1);
        root.right = buildBST(nums,mid+1,r);
        return root;     
    }
}
int main(int argc, char const *argv[])
{
    return Solution.sortedArrayToBST([1,2,3,4,5,6]);
}