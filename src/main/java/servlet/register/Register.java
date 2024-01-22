package servlet.register;

import mapper.UserMapper;
import entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import util.GetSqlSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 * @author yiren
 * @Description
 * @create 2024/1/18
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Register.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        Users users = new Users(account, password);
        try (SqlSession sqlSession = GetSqlSession.createSqlSession()) {
            /* 3、执行SQL语句 */
            assert sqlSession != null;
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            boolean isSuccess = mapper.registerUser(users.getAccount(), users.getPassword());
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            if (isSuccess) {
                sqlSession.commit();
                resp.sendRedirect("/TTFweb_war_exploded/");
                logger.info("success");
            } else {
                writer.write("error");
            }
        }
    }

    public Register() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
