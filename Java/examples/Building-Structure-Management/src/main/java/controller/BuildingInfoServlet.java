package controller;

import entity.BuildingInfo;
import service.BuildingInfoService;
import service.impl.BuildingInfoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Building")
public class BuildingInfoServlet extends HttpServlet {

    private BuildingInfoService buildingInfoService=new BuildingInfoServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.setCharacterEncoding("UTF-8");
       String path=req.getContextPath();
        try {
            String method=req.getParameter("method");
            switch (method){
                case "list":
                    req.setAttribute("list",this.buildingInfoService.list());
                    req.getRequestDispatcher("house_list.jsp").forward(req,resp);
                    break;
                case "search":
                    String key=req.getParameter("key");
                    String value=req.getParameter("value");
                    req.setAttribute("list",this.buildingInfoService.search(key,value));
                    req.getRequestDispatcher("house_list.jsp").forward(req,resp);
                    break;
                case "save":
                    String location=req.getParameter("location");
                    String buildingname=req.getParameter("buildingname");
                    String structure=req.getParameter("structure");
                    String term=req.getParameter("term");
                    String type=req.getParameter("type");
                    this.buildingInfoService.save(new BuildingInfo(buildingname,location,type,structure,term));
                    resp.sendRedirect(path+"/Building?method=list");
                    break;

                case "update":
                    String idStr=req.getParameter("id");
                    Integer id=Integer.parseInt(idStr);
                     location=req.getParameter("location");
                     buildingname=req.getParameter("buildingname");
                     structure=req.getParameter("structure");
                     term=req.getParameter("term");
                     type=req.getParameter("type");
                     this.buildingInfoService.update(new BuildingInfo(id,buildingname,location,type,structure,term));
                    resp.sendRedirect(path+"/Building?method=list");
                    break;
                case "delete":
                    idStr=req.getParameter("id");
                     id=Integer.parseInt(idStr);
                     this.buildingInfoService.delete(id);
                    resp.sendRedirect(path+"/Building?method=list");;
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
