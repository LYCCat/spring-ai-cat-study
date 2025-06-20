package com.zzcat.springaicatstudy.entity.query;
import lombok.Data;
import org.springframework.ai.tool.annotation.ToolParam;


import java.util.List;

@Data
public class CourseQuery {
    @ToolParam(required=false,description="课程类型：编程，设计，自媒体，其他")
    private  String type;
    @ToolParam(required=false,description="学历要求：0-无，1-初中，2-高中，3-大专，4-本科以上")
    private  Integer edu;
    @ToolParam(required=false,description="排序方式")
    private List<Sort> sorts;

    @Data
    public  static  class Sort{
        @ToolParam(required=false,description="排序字段:price或者duration")
        private String  field;
        @ToolParam(required=false,description="是否升序:true/false")
        private Boolean asc;
    }

}
