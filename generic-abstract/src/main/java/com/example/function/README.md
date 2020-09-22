题目一：
```java
    public String cleanNames(List<String> listOfNames) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < listOfNames.size(); i++) {
            if (listOfNames.get(i).length() > 1) {
                result.append(capitalizeString(listOfNames.get(i))).append(",");
            }
        }
        return result.substring(0, result.length() - 1).toString();
    }
 
    public String capitalizeString(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
    }
}

```

试着对这段代码进行改写，使它变得更简洁

题目二：
写一个求俩数和的add函数，写完后试着想想，能否写成每次只接收一个参数，类似从 add(3,5) 变成 add(3)(5)

题目三：
使用lambda表达式写一个递归的斐波那契函数

注：斐波那契数列，即兔子繁殖问题，1、1、2、3、5、8、13、21、34......



=======================================================================================

解析:

第一题:

实际上就是结合函数式编程改写代码, 遍历集合将每个元素的首字母大写,并用逗号拼接.

(其实优化前的代码没有考虑异常情况, 如果result的length为0会抛出异常, 如{"a", "b", "c", "d"}, 改成lambda之后不会抛出异常) 优化前后详见代码.

问: 还能再优化吗?



第二题:

参考一下js是如何优化的:

```js
// 柯里化
function curry(fn) {
    if(fn.length<=1) return fn
    const generator = (...args) => {
        return fn.length === args.length 
            ? fn(...args) : (...args2) => {return generator(...args,...args2)}
    }
    return generator
}
let add = (a,b,c,d)=>a+b+c+d
const curriedAdd = curry(add)
let result = curriedAdd(1)(2)(3)(4)
console.log(result)
```

虽然不是很了解js, 但是这段代码一看就能明白是什么含义. 这里就是将多参函数优化成了单参函数.即将add(1,2,3,4)优化成了add(1)(2)(3)(4). 这个概念就叫做柯里化.

回到java中, 也是有这种概念的. 这个题目的主旨是柯里化和复合函数.

代码分了三步优化, v1中是最原始的, 优化前的; v2是支持两个参数的, v3是支持3个参数的.





第三题:

斐波那契函数的定义是: F[n]=F[n-1]+F[n-2] (n>=2,F[0]=0,F[1]=1)



