package dao;

import connect_MySQL.Connect_MySQL;
import model.Department;
import model.Staff;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StaffDao implements CRUD<Staff> {
    DepartmentDao departmentDao = new DepartmentDao();

    @Override
    public List<Staff> getAll() {
        String sql = "select * from nhanvien";
        List<Staff> staff = new ArrayList<>();
        try (Connection connection = Connect_MySQL.getConnect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("idnv");
                String name = resultSet.getString("tennv");
                Date date = resultSet.getDate("ngaysinh");
                LocalDate dateOfBirth = date.toLocalDate();
                String address = resultSet.getString("diachi");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("sdt");
                Department department = departmentDao.findById(resultSet.getInt("idpb"));
                staff.add(new Staff(id, name, dateOfBirth, address, email, phoneNumber, department));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return staff;
    }


    @Override
    public List<Staff> getAllByName(String name) {
        String sql = "select * from nhanvien where tennv like concat('%',?,'%')";
        List<Staff> staff = new ArrayList<>();
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("idnv");
                String nameS = resultSet.getString("tennv");
                Date date = resultSet.getDate("ngaysinh");
                LocalDate dateOfBirth = date.toLocalDate();
                String address = resultSet.getString("diachi");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("sdt");
                Department department = departmentDao.findById(resultSet.getInt("idpb"));
                staff.add(new Staff(id, nameS, dateOfBirth, address, email, phoneNumber, department
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return staff;
    }

    @Override
    public boolean create(Staff staff) {
        String sql = "insert into nhanvien value (?,?,?,?,?,?,?)";
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, staff.getId());
            preparedStatement.setString(2, staff.getName());
            preparedStatement.setString(3, String.valueOf(staff.getDateOfBirth()));
            preparedStatement.setString(4, staff.getAddress());
            preparedStatement.setString(5, staff.getEmail());
            preparedStatement.setString(6, staff.getPhoneNumber());
            preparedStatement.setInt(7, staff.getDepartment().getId());
            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean edit(int id, Staff staff) {

        String sql = "UPDATE student SET name = ?,dateOfBirth = ?, " +
                "address = ?,email = ?,phoneNumber = ?, idClass=? WHERE (idStudent = ?)";
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(7, staff.getId());
            preparedStatement.setString(1, staff.getName());
            preparedStatement.setString(2, String.valueOf(staff.getDateOfBirth()));
            preparedStatement.setString(3, staff.getAddress());
            preparedStatement.setString(4, staff.getEmail());
            preparedStatement.setString(5, staff.getPhoneNumber());
            preparedStatement.setInt(6, staff.getDepartment().getId());
            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from nhanvien WHERE idnv = ?";
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public Staff findById(int id) {
        String sql = "select * from nhanvien where idnv = " + id;
        try (Connection connection = Connect_MySQL.getConnect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            resultSet.next();
            int idS = resultSet.getInt("idnv");
            String name = resultSet.getString("nhanvien");
            LocalDate dateOfBirth = LocalDate.parse(resultSet.getString("ngaysinh"));
            String address = resultSet.getString("diachi");
            String email = resultSet.getString("email");
            String phoneNumber = resultSet.getString("sdt");
           Department  department = departmentDao.findById(resultSet.getInt("idpb"));

            return new Staff(idS, name, dateOfBirth, address, email, phoneNumber, department);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
