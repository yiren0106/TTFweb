package servlet.login;

import entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import util.GetSqlSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * @author yiren
 * @Description
 * @create 2024/1/18
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Login.class.getName());

    public Login() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        try (SqlSession sqlSession = GetSqlSession.createSqlSession()) {
            assert sqlSession != null;
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<Users> users = mapper.queryAllUsers();
            response.setStatus(400);
            for(Users us : users){
                if(Objects.equals(us.getAccount(), account) && Objects.equals(us.getPassword(), password)){
                    logger.info("Logging Success");
                    response.setStatus(200);
                }
            }
        }
    }
}
