package mapper;

import entity.Wars;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * @author yiren
 * @Description
 * @create 2024/1/20
 */
public interface WarMapper {
    @Select("select * from war where warPic = #{warPic} and warSay = #{warSay}")
    public Wars selectWarByInfo(@Param("warPic") String warPic, @Param("warSay") String warSay);
    @Select("select * from war")
    public List<Wars> queryAllWar();
    @Insert("insert into war values(#{warPic},#{warSay})")
    public boolean registerWar(@Param("warPic") String warPic, @Param("warSay") String warSay);
}
