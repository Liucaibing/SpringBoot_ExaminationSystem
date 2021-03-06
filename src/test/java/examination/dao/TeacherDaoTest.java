package examination.dao;

import examination.ExaminationApplication;
import examination.entity.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExaminationApplication.class)
public class TeacherDaoTest {

    @Autowired
    TeacherDao teacherDao;

    @Test
    public void findByLogin() throws Exception {
        Teacher teacher = teacherDao.findByLogin("201536550000", "0000");
        System.out.println(teacher);
    }

    @Test
    public void add() throws Exception {
        teacherDao.add("123", "顺");
    }

    @Test
    public void findById() throws Exception {
        Teacher teacher = teacherDao.findById(2);
        System.out.println(teacher);
    }

    @Test
    public void update() throws Exception {
        Teacher teacher = teacherDao.findById(4);
        teacher.setAccount("今晚吃鸡");
        teacherDao.update(teacher);
    }

    @Test
    public void deleteBatch() throws Exception {
        System.out.println(teacherDao.deleteByList(Arrays.asList(14L)));
    }
}