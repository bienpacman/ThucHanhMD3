package controller;

import dao.DepartmentDao;
import dao.StaffDao;
import model.Department;
import model.Staff;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = "/staff")
public class StaffServlet extends HttpServlet {
    StaffDao staffDao = new StaffDao();
     DepartmentDao departmentDao = new DepartmentDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        RequestDispatcher dispatcher = null;
        switch (action) {
            case "create":
                create(req, resp);
                break;
            case "search":
                search(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;
            case "edit":
                edit(req, resp);
                break;
            default:
                show(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        RequestDispatcher dispatcher = null;
        switch (action) {
            case "create":
                int id = staffDao.getAll().size() + 1;
                String name = request.getParameter("name");
                LocalDate birth = LocalDate.parse(request.getParameter("birth"));
                String address = request.getParameter("address");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                int idPhongBan = Integer.parseInt(request.getParameter("department"));

                Staff st = new Staff(id, name, birth, address,email ,phone , departmentDao.findById(idPhongBan));
                staffDao.create(st);
                resp.sendRedirect("/staff");
                break;
            case "edit":
                int ide = Integer.parseInt(request.getParameter("id"));
                String namee = request.getParameter("name");
                LocalDate birthe = LocalDate.parse(request.getParameter("birth"));
                String addresse = request.getParameter("address");
                String emaile = request.getParameter("email");
                String phonee = request.getParameter("phone");
                int idPhongBann = Integer.parseInt(request.getParameter("class"));

                Staff ste = new Staff(ide, namee, birthe, addresse,emaile ,phonee , departmentDao.findById(idPhongBann));
                staffDao.edit(ide,ste);
                resp.sendRedirect("/staff");
                break;
        }
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("department", departmentDao.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/create.jsp");
        dispatcher.forward(req, resp);
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        req.setAttribute("staff", staffDao.getAllByName(search));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        dispatcher.forward(req, resp);
    }

    private void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("staff", staffDao.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        dispatcher.forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Staff> staff = staffDao.getAll();
        int id = Integer.parseInt(req.getParameter("id"));
        staffDao.delete(id);
        req.setAttribute("staff", staff);
        resp.sendRedirect("/staff");
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Staff staff = staffDao.findById(id);
        List<Department> departments = departmentDao.getAll();
        req.setAttribute("staff",staff );
        req.setAttribute("department",departments);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/edit.jsp");
        dispatcher.forward(req, resp);
    }

}
