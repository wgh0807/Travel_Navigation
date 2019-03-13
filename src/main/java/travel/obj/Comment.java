package travel.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {//评论
    int id;//id
    String user_name;//用户名
    String user_level;//用户等级
    String comment_info;//评论
    String like_num;//点赞数
    String place_name;//地点名
    String place_level;//地点等级
    String comment_time;//评论时间
}
