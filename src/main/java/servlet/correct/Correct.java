package servlet.correct;

import entity.Wars;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import mapper.WarMapper;
import org.apache.ibatis.session.SqlSession;
import util.GetSqlSession;

import java.io.*;

/**
 * @author yiren
 * @Description
 * @create 2024/1/20
 */
@MultipartConfig
@WebServlet("/Correct")
public class Correct extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置编码格式
        req.setCharacterEncoding("UTF-8");
        // 用于获取请求中的文件
        Part warImg = req.getPart("warImg");
        // 获得文件名
        String pictureName = warImg.getSubmittedFileName();
        // 获得文件输入流
        InputStream inputStream = warImg.getInputStream();
        // 完整传输路径
//        String filePath = "D:\\ideaWorkFile\\TTFweb\\src\\main\\webapp\\resource";
        String filePath = req.getServletContext().getRealPath("userImages");
        // 根据路径创建文件夹
        File uploadDir = new File(filePath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        // 获得文件输出流
        FileOutputStream outputStream = new FileOutputStream(filePath + File.separator + pictureName);
        // 输出文件
        int read = 0;
        final byte[] bytes = new byte[1024];
        while ((read = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }
        inputStream.close();
        outputStream.close();
        // 构造Wars对象
        String warSay = req.getParameter("warSay");
        Wars wars = new Wars(filePath + File.separator + pictureName, warSay);
        // 构造session
        try (SqlSession sqlSession = GetSqlSession.createSqlSession()) {
            assert sqlSession != null;
            WarMapper mapper = sqlSession.getMapper(WarMapper.class);
            boolean isSuccess = mapper.registerWar(wars.getWarPic(), wars.getWarSay());
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            if (isSuccess) {
                sqlSession.commit();
                resp.sendRedirect("/TTFweb_war_exploded/");
            } else {
                writer.write("error");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
