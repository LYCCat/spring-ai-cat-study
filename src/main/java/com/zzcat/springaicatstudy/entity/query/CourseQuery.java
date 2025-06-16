package com.zzcat.springaicatstudy.entity.query;

import lombok.Data;
import org.apache.ibatis.ognl.BooleanExpression;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;

import java.util.List;

@Data
public class CourseQuery {
    private  String type;
    private  Integer edu;
    private List<Sort> sorts;

    public  static  class Sort{
        private String  field;
        private Boolean asc;
    }

}
