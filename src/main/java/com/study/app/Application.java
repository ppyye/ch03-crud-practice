package com.study.app;

import com.study.model.dto.PhoneDTO;

import java.util.Scanner;


public class Application {
    DBManager dbManager = new DBManager();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Application app = new Application();
        while(true) {
            System.out.println("==== phone db manager ====");
            System.out.println("1. 데이터 조회");
            System.out.println("2. 데이터 추가");
            System.out.println("3. 데이터 수정");
            System.out.println("4. 데이터 삭제");
            System.out.println("0. 프로그램 종료");
            System.out.println("==========================");
            System.out.print("> ");

            int choice = sc.nextInt();

            switch(choice) {
                case 1: app.selectDB(); break;
                case 2: app.insertDB(); break;
                case 3: app.updateDB(); break;
                case 4: app.deleteDB(); break;
                case 0:
                    System.out.println("▷ terminating ... ");
                    return;
                default:
                    System.out.println("▷ 올바른 숫자를 입력하여 주시기 바랍니다.");
            }
        }
    }


    public void selectDB() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("==== 데이터 조회 ====");
            System.out.println("1. 코드로 검색");
            System.out.println("2. 이름으로 검색");
            System.out.print("> ");
            int choice = sc.nextInt();

            switch(choice) {
                case 1:
                    System.out.print("검색할 코드 입력 : ");
                    dbManager.selectCode(sc.nextInt());
                    return;
                case 2:
                    System.out.print("검색할 이름 입력 : ");
                    sc.nextLine();
                    dbManager.selectName(sc.nextLine());
                    return;
                default:
                    System.out.println("▷ 올바른 숫자를 입력하여 주시기 바랍니다.");
            }
        }
    }

    public void insertDB() {
        Scanner sc = new Scanner(System.in);
        System.out.println("==== 데이터 추가 ====");
        System.out.print("추가할 모델명 입력 : ");
        String name = sc.nextLine();
        System.out.print("추가할 모델 가격 입력 : ");
        double price = sc.nextDouble();

        PhoneDTO phone = new PhoneDTO();
        phone.setName(name);
        phone.setPrice(price);

        dbManager.insert(phone);
    }

    public void updateDB() {
        Scanner sc = new Scanner(System.in);
        System.out.println("==== 데이터 수정 ====");
        System.out.print("수정할 모델 코드 입력 : ");
        int code = sc.nextInt();
        System.out.print("추가할 모델명 입력 : ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("추가할 모델 가격 입력 : ");
        double price = sc.nextDouble();

        PhoneDTO phone = new PhoneDTO();
        phone.setName(name);
        phone.setPrice(price);
        phone.setCode(code);

        dbManager.update(phone);
    }

    public void deleteDB() {
        Scanner sc = new Scanner(System.in);
        System.out.println("==== 데이터 삭제 ====");
        System.out.print("삭제할 모델 코드 입력 : ");
        int code = sc.nextInt();

        dbManager.delete(code);
    }
}
