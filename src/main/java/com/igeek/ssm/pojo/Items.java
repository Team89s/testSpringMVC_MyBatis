package com.igeek.ssm.pojo;

import com.igeek.ssm.validate.ValidateGroup1;
import com.igeek.ssm.validate.ValidateGroup2;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class Items {
    private Integer id;

    @Size(min=2,max=6,message = "{items.name.length}",groups = ValidateGroup1.class)
    private String name;

    private Float price;

    private String pic;

    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "{items.createtime.notnull}",groups = ValidateGroup2.class)
    private Date createtime;

    private String detail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}