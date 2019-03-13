package ${package}.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ${package}.obj.${model};
import ${package}.service.${model}Service;
import ${package}.util.Pagination;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("${model?lower_case}")
public class ${model}Controller extends BaseController {

    private ${model}Service ${model?lower_case}Service;

    @Autowired
    public void set${model}Service(${model}Service ${model?lower_case}Service) {
        this.${model?lower_case}Service = ${model?lower_case}Service;
    }

    @RequestMapping("index")

    public void index() throws IOException {
        PrintWriter out = response.getWriter();
        out.println("index");
    }

    @RequestMapping("create")
    private String create(${model} ${model?lower_case}) {
        ${model?lower_case}Service.create(${model?lower_case});
        return "redirect:/${model?lower_case}/queryAll";
    }

    @RequestMapping("delete/{id}")
    private String delete(@PathVariable("id") ${PK} id) {
        ${model?lower_case}Service.delete(id);
        return "redirect:/${model?lower_case}/queryAll";
    }

    @RequestMapping("modify")
    private String modify(${model} ${model?lower_case}) {
        ${model?lower_case}Service.modify(${model?lower_case});
        return "redirect:/${model?lower_case}/queryAll";
    }

    @RequestMapping("queryAll")
    private String queryAll() {
        session.setAttribute("list", ${model?lower_case}Service.queryAll());
        return "redirect:/${model?lower_case}/list.jsp";
    }

    @RequestMapping("queryById/{id}")
    private String queryById(@PathVariable("id") ${PK} id) {
        session.setAttribute("${model?lower_case}", ${model?lower_case}Service.queryOne(id));
        return "redirect:/${model?lower_case}/edit.jsp";
    }

}
