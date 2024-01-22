package servlet.WarData;

import entity.Wars;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapper.WarMapper;
import org.apache.ibatis.session.SqlSession;
import util.GetSqlSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author yiren
 * @Description
 * @create 2024/1/20
 */
@WebServlet("/GetWars")
public class getWar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String a = "\"";
        StringBuilder json = new StringBuilder();
        try (SqlSession sqlSession = GetSqlSession.createSqlSession()) {
            assert sqlSession != null;
            WarMapper mapper = sqlSession.getMapper(WarMapper.class);
            List<Wars> wars = mapper.queryAllWar();
            resp.setContentType("text/json");
            json.append("[");
            for(Wars war : wars){
                json.append("{");
                json.append(a).append("pictureUrl").append(a).append(":").append(a).append(war.getWarPic()).append(a).append(",");
                json.append(a).append("testContent").append(a).append(":").append(a).append(war.getWarSay()).append(a);
                json.append("},");
            }
            json.deleteCharAt(json.length() - 1);
            json.append("]");
            PrintWriter outputStream = resp.getWriter();
            outputStream.println(json.toString());
            outputStream.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
