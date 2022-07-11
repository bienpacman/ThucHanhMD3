package dao;

import connect_MySQL.Connect_MySQL;
import model.Department;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao implements CRUD<Department> {
    @Override
    public List<Department> getAll() {
        String sql = "select * from phongban";
        List<Department> departments = new ArrayList<>();
        try (Connection connection = Connect_MySQL.getConnect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String Name  = resultSet.getString(2);
                departments.add(new Department(id, Name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return departments;
    }

    @Override
    public List<Department> getAllByName(String name) {
        return null;
    }

    @Override
    public boolean create(Department department) {
        return false;
    }

    @Override
    public boolean edit(int id, Department department) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Department findById(int id) {
        String sql = "select * from phongban where idpb = ?";
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            int idD = resultSet.getInt(1);
            String DepartmentName = resultSet.getString(2);
            Department department = new Department(idD, DepartmentName);
            return department;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
