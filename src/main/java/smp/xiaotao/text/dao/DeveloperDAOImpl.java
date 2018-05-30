package smp.xiaotao.text.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import smp.xiaotao.text.model.DeveloperModel;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository("developerDAOImpl")
public class DeveloperDAOImpl implements DeveloperDAO {

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<DeveloperModel> getAllDevelopers() {
        String sql = "select * from developer";

        System.out.println("查询的执行语句=" + sql);
        return query(sql);

    }

    @Override
    public DeveloperModel getDeveloper(String developerId) {

        String sql = "select * from developer where id =" + developerId;
        List<DeveloperModel> developerModels = query(sql);
        if (developerModels.size() > 0) {
            return developerModels.get(0);
        } else {
            return null;
        }
    }


    private List<DeveloperModel> query(String sql) {
        final List<DeveloperModel> modelList = new ArrayList<>();

        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

                System.out.println("查询的数据结果=" + resultSet.toString());
                int id = resultSet.getInt(1);
                int article_id = resultSet.getInt(2);
                String user_id = resultSet.getString(3);
                String content = resultSet.getString(4);
                String date = resultSet.getString(5);
                DeveloperModel model = new DeveloperModel();
                model.setId(id);
                model.setArticle_id(article_id);
                model.setUser_id(user_id);
                model.setContent(content);
                model.setDate(date);
                modelList.add(model);

            }
        });
        return modelList;
    }

    @Override
    public boolean addDeveloper(DeveloperModel developerModel) {
        String sql = "INSERT INTO developer(id,user_id,content,date) VALUES (" +
                "'" + developerModel.getId() + "'," +
                "'" + developerModel.getUser_id() + "'," +
                "'" + developerModel.getContent() + "'," +
                "'" + developerModel.getDate() + "');";

        System.out.println("增加数据的执行语句=" + sql);
        try {
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateDeveloper(String id, String content) {

        String sql = "UPDATE developer set content='" + content + "' WHERE id = " + id;

        System.out.println("更新数据的执行语句=" + sql);
        try {
            jdbcTemplate.update(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteDeveloper(String id) {
        String sql = "DELETE FROM developer WHERE id =" + id;

        System.out.println("删除数据的执行语句=" + sql);
        try {
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
