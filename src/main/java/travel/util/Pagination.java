package travel.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination<T extends Serializable> implements Serializable {

    private List<T> list;//当前页数查询结果集合
    private String sqlId;//对应的sql id
    private int pageSize;//当前页数
    private int totalRows;//总条数
    private int totalPages;//总页数
    private int currentPages;//当前页数

}
