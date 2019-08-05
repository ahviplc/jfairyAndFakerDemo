package com.lc.demoapp.jfairyApp;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.company.Company;
import io.codearte.jfairy.producer.person.Person;

import java.util.Locale;

import static io.codearte.jfairy.producer.person.PersonProperties.*;

public class jfairyApp {
    public static void main(String[] args) {
        Fairy fairy = Fairy.create();
        Person person = fairy.person();

        System.out.println(person.getUsername());
// Chloe Barker
        System.out.println(person.getEmail());
// barker@yahoo.com
        System.out.println(person.getTelephoneNumber());
// 690-950-802

        Person adultMale = fairy.person(male(), minAge(21));
        System.out.println(adultMale.isMale());
// true
        System.out.println(adultMale.getDateOfBirth());
// at least 21 years earlier

//        Fairy enFairy = Fairy.create();
//// Locale.ENGLISH is default
//        Fairy plFairy = Fairy.create(Locale.forLanguageTag("pl"));
//// Polish version


//        Fairy fairy = Fairy.create();
        Company company = fairy.company();
        System.out.println(company.getName());
// Robuten Associates
        System.out.println(company.getUrl());
// http://www.robuteniaassociates.com

        Person salesman = fairy.person(withCompany(company));
        System.out.println(salesman.getFullName());
// Juan Camacho
        System.out.println(salesman.getCompanyEmail());
// juan.camacho@robuteniaassociates.com


// 输出样例
//        chiggins
//        higgins@gmail.com
//        649-832-5081
//        true
//        1970-06-11T09:58:27.484+08:00
//        Erntogra
//        http://www.erntogra.biz
//        Bella Coffey
//        bella.coffey@erntogra.biz
    }
}
