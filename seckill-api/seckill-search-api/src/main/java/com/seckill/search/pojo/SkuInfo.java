package com.seckill.search.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/*****
 * @Author: http://www.itheima.com
 * @Project: seckillproject
 * @Description: com.seckill.search.pojo.SkuInfo
 * 和索引库对应的JavaBean
 ****/
@Document(indexName = "goodsindex",type = "skuinfo")
public class SkuInfo {

    //Sku相关的数据
    //商品id，同时也是商品编号
    @Id //唯一标识符,ES中对应的_id
    private String id;

    /***
     * SKU名称
     * type =FieldType.Text:指定当前name属性所对应的域的类型为Text类型，该类型支持分词支持创建索引
     *       FiledType.Keyword:不分词
     * searchAnalyzer="ik_smart":搜索所使用的分词器
     * analyzer = "ik_smart":添加索引所使用的分词器
     */
    @Field(type =FieldType.Text ,searchAnalyzer = "ik_smart",analyzer = "ik_smart",store =false)
    private String name;

    //商品价格，单位为：元
    private Long price;

    //秒杀价
    private Long seckillPrice;

    //商品图片
    private String image;

    //更新时间
    private Date updateTime;

    //类目ID
    private String category1Id;

    //类目ID
    private String category2Id;

    //类目ID
    private String category3Id;

    //类目名称
    @Field(type = FieldType.Keyword)
    private String category1Name;

    //类目名称
    @Field(type = FieldType.Keyword)
    private String category2Name;

    //类目名称
    @Field(type = FieldType.Keyword)
    private String category3Name;

    //品牌名称
    @Field(type = FieldType.Keyword)
    private String brandName;

    //开始时间，用于做搜索
    @Field(type = FieldType.Keyword)
    private String bgtime;

    //品牌ID
    private String brandId;

    private Date seckillBegin;//秒杀开始时间

    private Date seckillEnd;//秒杀结束时间

    private Integer status; //秒杀状态，1普通商品，2秒杀

    //规格
    private String spec;

    //比例
    private Integer points;

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(Long seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCategory1Id() {
        return category1Id;
    }

    public void setCategory1Id(String category1Id) {
        this.category1Id = category1Id;
    }

    public String getCategory2Id() {
        return category2Id;
    }

    public void setCategory2Id(String category2Id) {
        this.category2Id = category2Id;
    }

    public String getCategory3Id() {
        return category3Id;
    }

    public void setCategory3Id(String category3Id) {
        this.category3Id = category3Id;
    }

    public String getCategory1Name() {
        return category1Name;
    }

    public void setCategory1Name(String category1Name) {
        this.category1Name = category1Name;
    }

    public String getCategory2Name() {
        return category2Name;
    }

    public void setCategory2Name(String category2Name) {
        this.category2Name = category2Name;
    }

    public String getCategory3Name() {
        return category3Name;
    }

    public void setCategory3Name(String category3Name) {
        this.category3Name = category3Name;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBgtime() {
        return bgtime;
    }

    public void setBgtime(String bgtime) {
        this.bgtime = bgtime;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public Date getSeckillBegin() {
        return seckillBegin;
    }

    public void setSeckillBegin(Date seckillBegin) {
        this.seckillBegin = seckillBegin;
    }

    public Date getSeckillEnd() {
        return seckillEnd;
    }

    public void setSeckillEnd(Date seckillEnd) {
        this.seckillEnd = seckillEnd;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
}
