package travel.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import travel.obj.Place;
import travel.service.PlaceService;
import travel.util.Pagination;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    public Map getPlace(int page_num) {
        Map data = new HashMap();
        Pagination<Place> pagination = placeService.queryAll("selectAll", null, page_num);

        data.put("currentList", pagination.getList());
        data.put("currentPages", pagination.getCurrentPages());
        data.put("totalPages", pagination.getTotalPages());
        data.put("totalRows", pagination.getTotalRows());

        return data;
    }


}
