package com.study.app;

import com.study.model.dto.PhoneDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.study.common.JDBCTemplate.close;
import static com.study.common.JDBCTemplate.getConnection;

public class DBManager {
    public void selectCode(int code) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/study/mapper/phone-query.xml"));
            String query = prop.getProperty("selectCode");

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, code);

            rset = pstmt.executeQuery();
            if(rset.next()) {
                System.out.println(rset.getString("phone_name") + " : " + rset.getDouble("phone_price"));
            } else {
                System.out.println("▷ 조회된 데이터가 없습니다.");
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }
    }

    public void selectName(String name) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Properties prop = new Properties();

        PhoneDTO phone = null;
        List<PhoneDTO> phoneList = null;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/study/mapper/phone-query.xml"));
            String query = prop.getProperty("selectName");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);

            rset = pstmt.executeQuery();
            phoneList = new ArrayList<>();
            while(rset.next()) {
                phone = new PhoneDTO();
                phone.setCode(rset.getInt("phone_code"));
                phone.setName(rset.getString("phone_name"));
                phone.setPrice(rset.getDouble("phone_price"));

                phoneList.add(phone);
            }
            if(!phoneList.isEmpty()) {
                for(PhoneDTO ph : phoneList) {
                    System.out.println(ph);
                }
            } else {
                System.out.println("▷ 조회된 데이터가 없습니다.");
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }
    }

    public void insert(PhoneDTO phone) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/study/mapper/phone-query.xml"));
            String query = prop.getProperty("insertPhone");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, phone.getName());
            pstmt.setDouble(2, phone.getPrice());

            result = pstmt.executeUpdate();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);
        }
        if(result > 0) System.out.println("▷ 데이터 추가 성공");
        else System.out.println("▷ 데이터 추가 실패");
    }

    public void update(PhoneDTO phone) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/study/mapper/phone-query.xml"));
            String query = prop.getProperty("updatePhone");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, phone.getName());
            pstmt.setDouble(2, phone.getPrice());
            pstmt.setInt(3, phone.getCode());

            result = pstmt.executeUpdate();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);
        }
        if(result > 0) System.out.println("▷ 데이터 수정 성공");
        else System.out.println("▷ 데이터 수정 실패");
    }

    public void delete(int code) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/study/mapper/phone-query.xml"));
            String query = prop.getProperty("deletePhone");

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, code);

            result = pstmt.executeUpdate();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);
        }
        if(result > 0) System.out.println("▷ 데이터 삭제 성공");
        else System.out.println("▷ 데이터 삭제 실패");
    }

}
