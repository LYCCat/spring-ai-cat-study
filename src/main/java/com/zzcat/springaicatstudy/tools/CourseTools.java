package com.zzcat.springaicatstudy.tools;

import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.zzcat.springaicatstudy.entity.Course;
import com.zzcat.springaicatstudy.entity.CourseReservation;
import com.zzcat.springaicatstudy.entity.School;
import com.zzcat.springaicatstudy.entity.query.CourseQuery;
import com.zzcat.springaicatstudy.service.ICourseReservationService;
import com.zzcat.springaicatstudy.service.ICourseService;
import com.zzcat.springaicatstudy.service.ISchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CourseTools {
    private final ICourseService courseService;
    private  final ISchoolService schoolService;
    private final ICourseReservationService courseReservationService;

    @Tool(description="根据条件查询课程",name = "queryCourse")
    public List<Course>queryCourse(@ToolParam(required=false,description="课程查询条件")CourseQuery courseQuery){
        QueryChainWrapper<Course> wrapper = courseService.query();
        wrapper.eq(courseQuery.getType()!=null,"type",courseQuery.getType())
                .le(courseQuery.getEdu()!=null,"edu", courseQuery.getEdu());
       if(courseQuery.getSorts()!=null){
           for(CourseQuery.Sort sort: courseQuery.getSorts()){
              wrapper.orderBy(true,sort.getAsc(),sort.getField());
           }
       }
       return  wrapper.list();
    }

    @Tool(description = "查询所有校区")
    public List<School> querySchool(){
        return schoolService.list();
    }
    @Tool(description = "生成课程预约单，并返回生成的预约单号")
    public String generateCourseReservation(String courseName,String studentName,String contactInfo,String school,String remark){
        CourseReservation courseReservation = new CourseReservation();
        courseReservation.setCourse(courseName);
        courseReservation.setStudentName(studentName);
        courseReservation.setContactInfo(contactInfo);
        courseReservation.setSchool(school);
        courseReservation.setRemark(remark);
        courseReservationService.save(courseReservation);
        return String.valueOf(courseReservation.getId());
    }


    }
