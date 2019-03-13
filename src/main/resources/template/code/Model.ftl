package ${package}.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ${model} implements Serializable {//地点详情
    private ${PK} id;//id
    <#list properties?keys as key>
        private ${properties[key]} ${key};
    </#list>

}

