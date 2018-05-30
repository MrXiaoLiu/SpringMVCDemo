package smp.xiaotao.text.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import smp.xiaotao.text.dao.DeveloperDAO;
import smp.xiaotao.text.model.CommonModel;
import smp.xiaotao.text.model.DeveloperModel;

import java.util.List;


@Controller
@RequestMapping("/developer")
public class DeveloperController {

    private DeveloperDAO developerDAO;

    @Autowired
    public DeveloperController(DeveloperDAO developerDAO) {
        this.developerDAO = developerDAO;
    }

    @RequestMapping(value = "/api/hello", method = RequestMethod.GET)
    public String hello() {

        return "hello";
    }


    @RequestMapping(value = "/api/developers", method = RequestMethod.GET)
    @ResponseBody//通过@ResponseBody返回JSON数据，通过@ResquestBody解析Json
    public CommonModel getAllDevelopers() {

        List<DeveloperModel> modelList = developerDAO.getAllDevelopers();
        CommonModel commonModel = new CommonModel();
        if (modelList.size() > 0) {
            commonModel.setSuccess();
            commonModel.setData(modelList);
        } else {
            commonModel.setFail();
        }
        return commonModel;
    }


    @RequestMapping(value = "/api/developer", method = RequestMethod.GET)
    @ResponseBody
    public CommonModel getDeveloper(String id) {

        DeveloperModel developerModel = developerDAO.getDeveloper(id);

        CommonModel commonModel = new CommonModel();
        if (developerModel != null) {
            commonModel.setSuccess();
            commonModel.setData(developerModel);
        } else {
            commonModel.setFail();
        }
        return commonModel;
    }


    @RequestMapping(value = "/api/add", method = RequestMethod.GET)
    @ResponseBody
    public CommonModel addDeveloper(int id,String user_id, String content, String date) {

        DeveloperModel developerModel = new DeveloperModel();
        developerModel.setId(id);
        developerModel.setUser_id(user_id);
        developerModel.setContent(content);
        developerModel.setDate(date);
        CommonModel commonModel = new CommonModel();
        if (developerDAO.addDeveloper(developerModel)) {
            commonModel.setSuccess();
        } else {
            commonModel.setFail();
        }
        return commonModel;
    }


    //Spring MVC不支持put,delete 方法实现传参，这里用到了 PathVariable
    @RequestMapping(value = "/api/update", method = RequestMethod.GET)
    @ResponseBody
    public CommonModel updateDeveloper(String id, String content) {

        CommonModel commonModel = new CommonModel();
        if (developerDAO.updateDeveloper(id,content)){
            commonModel.setSuccess();
        }else {
            commonModel.setFail();
        }
        return commonModel;
    }



    @RequestMapping(value = "/api/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public CommonModel deteleDeveloper(@PathVariable("id") String id){

        CommonModel commonModel = new CommonModel();
        if (developerDAO.deleteDeveloper(id)){
            commonModel.setSuccess();
        }else {
            commonModel.setFail();
        }
        return commonModel;

    }

}
