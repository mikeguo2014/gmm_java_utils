package com.gmm.utils;

// 商品表：名称、价格、描述。

class ProductGroup {
    private int pgid;
    private String title;
    private String note;
    private Link products = new Link();

    public ProductGroup() {
    }

    public ProductGroup(int pgid, String title, String note) {
        this.pgid = pgid;
        this.title = title;
        this.note = note;
    }

    public void setProducts(Link products) {
        this.products = products;
    }

    public Link getProducts() {
        return this.products;
    }

    public String getProductGroupInfo() {
        return "商品组编号：" + this.pgid + "，名称：" + this.title + "，描述：" + this.note;
    }
}

class LinkItem {
    private int pid;
    private String title;
    private double price;
    private String note;
    private ProductGroup group;

    public LinkItem() {
    }

    public LinkItem(int pid, String title, double price, String note) {
        this.pid = pid;
        this.title = title;
        this.price = price;
        this.note = note;
    }

    public boolean compare(LinkItem product) {
        if (this == product) {
            return true;
        }
        if (product == null) {
            return false;
        }
        if (this.pid == product.pid && this.title.equals(product.title)
                && this.price == product.price
                && this.note.equals(product.note)) {
            return true;
        }
        return false;
    }

    public void setGroup(ProductGroup group) {
        this.group = group;
    }

    public ProductGroup getGroup() {
        return this.group;
    }

    public String getProductInfo() {
        return "商品编号：" + this.pid + "，名称：" + this.title + "，价格：" + this.price
                + "，描述：" + this.note;
    }
}

public class LinkDemo1 {
    public static void main(String args[]) {
        // 一层设置关系
        ProductGroup group = new ProductGroup(1, "生活用品", "你懂的。。。。");
        group.getProducts().add(new LinkItem(10, "毛巾", 1.0, "你懂的。"));
        group.getProducts().add(new LinkItem(11, "香皂", 1.5, "你懂的。"));
        group.getProducts().add(new LinkItem(12, "牙刷", 0.3, "你懂的。"));
        System.out.println(group.getProductGroupInfo());
        LinkItem prod[] = group.getProducts().toArray();
        for (int x = 0; x < prod.length; x++) {
            System.out.println(prod[x].getProductInfo());
        }
    }
}
