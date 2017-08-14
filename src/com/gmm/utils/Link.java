package com.gmm.utils;

public class Link { // 用户唯一关注的是此类
    // 使用内部类的最大好处是可以和外部类进行私有操作的互相访问
    private class Node { // 处理节点关系
        private LinkItem data; // 要保存的数据
        private Node next; // 下一个节点

        public Node(LinkItem data) {
            this.data = data;
        }

        public void addNode(Node newNode) { // 增加节点
            if (this.next == null) { // 当前节点之后没有节点
                this.next = newNode; // 保存新节点
            } else { // 当前节点之后有节点了
                this.next.addNode(newNode); // 向下继续判断
            }
        }

        public boolean containsNode(LinkItem data) { // 查找数据
            if (data.compare(this.data)) { // 与当前节点数据吻合
                return true;
            } else { // 与当前节点数据不吻合
                if (this.next != null) { // 还有下一个节点
                    return this.next.containsNode(data);
                } else { // 没有后续节点
                    return false; // 查找不到
                }
            }
        }

        // 传入两个参数：上一个节点，另外一个表示要删除的数据
        public void removeNode(Node previous, LinkItem data) {
            if (this.data.compare(data)) { // 当前节点的数据吻合删除条件
                previous.next = this.next; // 空出当前节点
            } else {
                this.next.removeNode(this, data); // 向后继续删除
            }
        }

        public void toArrayNode() {
            Link.this.retData[Link.this.foot++] = this.data;
            if (this.next != null) {
                this.next.toArrayNode();
            }
        }

        public LinkItem getNode(int index) {
            if (Link.this.foot++ == index) { // 当前索引为查找数值
                return this.data;
            } else {
                return this.next.getNode(index);
            }
        }
    }

    private Node root; // 根节点，第一个保存元素
    private int count = 0; // 统计个数
    private int foot = 0; // 操作返回数组的脚标
    private LinkItem[] retData; // 返回数组
    private boolean changeFlag = true;

    // changeFlag == true：数据被更改了，则需要重新遍历
    // changeFlag == false：数据没有更改，不需要重新遍历
    public boolean add(LinkItem data) { // 增加数据
        if (data == null) { // 如果保存的是一个空数据
            return false; // 增加失败
        }
        // 将数据封装为节点，目的：节点有next可以处理关系
        Node newNode = new Node(data);
        // 链表的关键就在于根节点
        if (this.root == null) { // 现在没有根节点
            this.root = newNode; // 第一个作为根节点
        } else { // 根节点有了，新的节点要保留在合适的位置
            this.root.addNode(newNode); // Node类负责处理
        }
        this.count++; // 保存数据量增加
        this.changeFlag = true; // 被修改了
        return true; // 增加成功
    }

    public boolean addAll(LinkItem data[]) { // 一组数据
        for (int x = 0; x < data.length; x++) {
            if (!this.add(data[x])) { // 保存不成功
                return false;
            }
        }
        return true;
    }

    public int size() {
        return this.count;
    }

    public boolean isProductty() {
        return this.count == 0;
    }

    public boolean contains(LinkItem data) { // 查找数据
        // 根节点没有数据，查找的也没有数据
        if (this.root == null || data == null) {
            return false; // 不需要进行查找了
        }
        return this.root.containsNode(data); // 交给Node类处理
    }

    public void remove(LinkItem data) { // 要删除的节点
        if (!this.contains(data)) { // 要删除的数据不存在
            return; // 直接返回被调用处，下面代码不执行了
        }
        if (data.equals(this.root.data)) { // 要删除的是根节点
            this.root = this.root.next; // 根节点的下一个
        } else { // 要删除的不是根节点
            this.root.next.removeNode(this.root, data);
        }
        this.changeFlag = true; // 被修改了
        this.count--; // 修改个数
    }

    public LinkItem[] toArray() {
        if (this.count == 0) {
            return null; // 没有数据
        }
        this.foot = 0; // 清零
        if (this.changeFlag == true) { // 内容被修改了，需要重新取
            this.retData = new LinkItem[this.count]; // 开辟数组大小
            this.root.toArrayNode();
        }
        return this.retData;
    }

    public LinkItem get(int index) {
        if (index > this.count) { // 超过个数
            return null; // 返回null
        }
        this.foot = 0; // 操作foot来定义脚标
        return this.root.getNode(index);
    }

    public void clear() {
        this.root = null;
        this.count = 0;
    }
}
