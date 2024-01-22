package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author yiren
 * @Description
 * @create 2024/1/19
 */
public class GetSqlSession {
    public static SqlSession createSqlSession() {
        SqlSessionFactory sqlSessionFactory = null;
        InputStream inputStream = null;
        SqlSession sqlSession = null;
        try {
            String resource = "mybatis-config.xml";
            inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            return sqlSession;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
