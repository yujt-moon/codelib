## 解题步骤
找零钱
1. 确定状态
   1. 最后一步（最优策略中使用的最后一枚硬币ak）
   2. 化成子问题（最少的硬币拼更小的面值27-ak）
2. 转移方程
   1. f[x] = min{f[x-2]+1, f[x-5]+1, f[x-7]+1}
3. 初始条件和边界情况
   1. f[0] = 0，如果不能拼出Y，f[Y]=正无穷
4. 计算顺序
   1. f[0], f[1], f[2], ...

[动态规划](https://www.bilibili.com/video/BV1xb411e7ww?spm_id_from=333.337.search-card.all.click&vd_source=a90ad52da3413fbdf07d4bf807ffc6d1)