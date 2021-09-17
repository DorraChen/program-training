1.现实场景描述:

开发客服系统, 用户发起了工单时会根据条件打标签,标签有4种(后续可能会增加),设置条件如下:
- 发起过: 单笔订单发起工单 ≥ 2 笔;
- 重复: 当日同一个售后原因的工单 ≥ X 笔;
- 重大: 单笔工单售后金额（含积分换算）≥ Y 元;
- 反复: 同一个客户 ≤ Z 天发起工单的售后原因相同;

客服人员可以对工单进行编辑, 编辑如果修改了金额和类型或者原因什么的,需要按照条件重新设置标签;

大致的逻辑是针对每种标签逐一去判断符不符合条件,首先判断原先有无标签,再判断是否满足条件,对现有标签进行增删;

这里看做是两种逻辑条件的组合, 首先我想到单个标签伪代码应该是这么写:

```java
List<String> labels; // 原有标签 labels
boolean existLabel;  // 原有标签是否存在 L 标签
boolean isMatch;     // 是否满足标签设置的条件 condition
if(existLabel && !isMatch) {
	labels.remove(L);
} else if (!existLabel && isMatch) {
	labels.add(L);
}
return labels;
```

