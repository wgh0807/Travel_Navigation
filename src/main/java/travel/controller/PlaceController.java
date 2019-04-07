package travel.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import travel.obj.Place;
import travel.service.PlaceService;
import travel.util.Pagination;
import travel.util.PlaceUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("place")
public class PlaceController extends BaseController {

    private PlaceService placeService;

    @Autowired
    public void setPlaceService(PlaceService placeService) {
        this.placeService = placeService;
    }

    @RequestMapping("index")
    public void index() throws IOException {
        PrintWriter out = response.getWriter();
        out.println("index");
    }

    @RequestMapping("seltest2")
    @ResponseBody
    public Map<String,List> seltest2() throws IOException {
        Map<String, List> placeMap = new HashMap<>();

        List<Place> placeList = placeService.queryAll("selectAll");
        placeMap.put("placeList", placeList);
        for (Place p : placeList) {
            System.out.println(p.getName());
        }
        return placeMap;
    }

    @RequestMapping("pagetest")
    @ResponseBody
    public Map<String,Object> pageTest() throws IOException {
        Map<String, Object> data = new HashMap<>();
        Pagination<Place> pagination =  placeService.queryAll("selectAll",null,2);

        data.put("currentList", pagination.getList());
        data.put("currentPages", pagination.getCurrentPages());
        data.put("totalPages", pagination.getTotalPages());
        data.put("totalRows", pagination.getTotalRows());

        return data;
    }

    @RequestMapping("placelist/{page_num}")
    @ResponseBody
    public Map getPlace(@PathVariable("page_num") Integer page_num) {
//        System.out.println(page_num);
        Map data = new HashMap();
        Pagination<Place> pagination = placeService.queryAll("selectAll", null, page_num);

        if (pagination.getCurrentPages() <= 0 || pagination.getCurrentPages() > pagination.getTotalPages()) {
            data.put("state","fail");
            return data;
        }

        data.put("state","success");
        data.put("currentList", pagination.getList());
        data.put("currentPages", pagination.getCurrentPages());
        data.put("totalPages", pagination.getTotalPages());
        data.put("totalRows", pagination.getTotalRows());

        return data;
    }

    @RequestMapping("create")
    @ResponseBody
    public Map createPlace(Place place) {
        Map data = new HashMap();
        int result = placeService.create(place);
        if (result == 0) {
            data.put("result","false");
        }else {
            data.put("result", "success");
        }
        return data;
    }

    @RequestMapping("routePlan")
    @ResponseBody
    public Map formatPlan(Integer place1,Integer place2,Integer place3,Integer place4,Integer place5){
        Map data = new HashMap();
        Set<Place> places = new HashSet<>();

//        判断对象
        List<Integer> idList = new LinkedList();
        if (place5 != null) {
            idList.add(place1);
            idList.add(place2);
            idList.add(place3);
            idList.add(place4);
            idList.add(place5);
        } else if (place4 != null) {
            idList.add(place1);
            idList.add(place2);
            idList.add(place3);
            idList.add(place4);
        } else if (place3 != null) {
            idList.add(place1);
            idList.add(place2);
            idList.add(place3);
        } else if (place2 != null) {
            idList.add(place1);
            idList.add(place2);
        } else if (place1 != null) {
            idList.add(place1);
        } else {
            data.put("result", "fail");
            return data;
        }

//        找到对象并且去重
        for (Integer id:idList) {
            try {
                places.add(placeService.queryOne(id));
                System.out.println(placeService.queryOne(id));
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("地点信息不存在");
            }
        }

//        todo：获取规划结果,未完成
        List<Place> list =  PlaceUtil.getPlanList(places);

//        上述算法未完成，采取临时方法
        list.addAll(places);

        //将结果放入 Map 中，以json形式返回前端
        data.put("result", "success");
        int i = 1;
        for (Place p:list) {
            data.put(i++, p);
        }

        return data;
    }

}
