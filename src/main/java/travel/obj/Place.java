package travel.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place implements Serializable {//地点详情
    private int id;//id
    private String city;//所在城市
    private String name;//地点名
    private String cover_pic;//封面图片
    private String desc;//描述
    private String detail_pic;//详情图片
    private String tel;//联系电话
    private String web;//网站
    private String tourtime;//推荐旅行时长
    private String traffic;//交通
    private String price;//价格
    private String open_time;//开放时间
    private String location;//位置
    private String comment_num;//评论数
    private String longitude;//经度
    private String latitude;//纬度
    private int state;//营运状态，0-正常，1-关闭，2-暂时关闭


    public Place(String city, String name, String cover_pic, String desc, String detail_pic, String tel, String web, String tourtime, String traffic, String price, String open_time, String location, String comment_num, String longitude, String latitude, int state) {
        this.city = city;
        this.name = name;
        this.cover_pic = cover_pic;
        this.desc = desc;
        this.detail_pic = detail_pic;
        this.tel = tel;
        this.web = web;
        this.tourtime = tourtime;
        this.traffic = traffic;
        this.price = price;
        this.open_time = open_time;
        this.location = location;
        this.comment_num = comment_num;
        this.longitude = longitude;
        this.latitude = latitude;
        this.state = state;
    }

    public Place(String city, String name, String cover_pic, String desc, String detail_pic, String tel, String web, String tourtime, String traffic, String price, String open_time, String location, String longitude, String latitude, int state) {
        this.city = city;
        this.name = name;
        this.cover_pic = cover_pic;
        this.desc = desc;
        this.detail_pic = detail_pic;
        this.tel = tel;
        this.web = web;
        this.tourtime = tourtime;
        this.traffic = traffic;
        this.price = price;
        this.open_time = open_time;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
        this.state = state;
    }
}

