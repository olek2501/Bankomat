import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Main {
    public static void main(String[] args) throws IOException {
        // BANKOMAT

        boolean bankomat = true;
        String pin;
        long karta = 1349194191124356L;
        long konto = 1231483109701709L;
        boolean blik = true;
        int jezyk = 0;
        int i = 0;
        long iLong = 0L;
        String waluta = "";
        boolean czyKarta = false;
        Konta baza_kont = new Konta();
        long spr_konta;
        String str_konta;
        int spr_id;
        int money = 0;
        int a = 0;
        int req_pin = 0;
        int wpl_konto;
        long nr_wpl_konta;
        String req_str_pin;
        ArrayList akceptowalne = new ArrayList();
        String i_str;
        String kod_blik_str;


        // Wpłata
        String nr_kontastr = "";
        boolean pytaj = true;
        int kwota_wpl = 0;
        int wplacone = 0;
        String nominal = "";
        ArrayList<String> nominaly = new ArrayList<String>();
        int reszta = 0;
        File pot_wpl = new File("potwierdzenie_wplaty.txt");
        File pot_wpl_eng = new File("deposit_confirmation.txt");
        FileWriter pisz_wpl = new FileWriter(pot_wpl);
        FileWriter pisz_wpl_eng = new FileWriter(pot_wpl_eng);
        File raport_wpl = new File("raport_wplaty.txt");
        File raport_wpl_eng = new File("deposit_report.txt");
        FileWriter pisz_rap_wpl = new FileWriter(raport_wpl);
        FileWriter pisz_rap_wpl_eng = new FileWriter(raport_wpl_eng);
        int money_wpl;


        // BLIK
        int kod_blik;
        int kwota_blik;
        boolean druk_blik = false;
        File pot_blik = new File("potwierdzenie_blik.txt");
        FileWriter pisz_blik = new FileWriter(pot_blik);
        File pot_blik_eng = new File("blik_withdrawal_confirmation.txt");
        FileWriter pisz_blik_eng = new FileWriter(pot_blik_eng);
        int max = 999999;
        int min = 100000;


        // Normalna wypłata
        int kwota_wyplata = 0;
        File pot_wyp = new File("potwierdzenie_wyplaty.txt");
        FileWriter pisz_wyp = new FileWriter(pot_wyp);
        File pot_wyp_eng = new File("withdrawal_confirmation.txt");
        FileWriter pisz_wyp_eng = new FileWriter(pot_wyp_eng);

        //Stan konta
        File stan_konta = new File("stan_konta.txt");
        FileWriter pisz_stan = new FileWriter(stan_konta);
        File stan_konta_eng = new File("account_balance.txt");
        FileWriter pisz_stan_eng = new FileWriter(stan_konta_eng);


        Scanner czytaj = new Scanner(System.in);
        while(bankomat) {
            kwota_blik = 0;
            kwota_wyplata = 0;
            wplacone = 0;
            reszta = 0;
            a = 0;
            nominaly.clear();
            akceptowalne.clear();




            System.out.println("1 - Skorzystaj z bankomatu; Inny - Utwórz konto // 1 - Use the ATM; Other - Create account");
            i = parseInt(czytaj.nextLine());
            if (i == 1) {
                System.out.println("Wybierz język / Select a Language 1 - PL; 2 - ANG");
                jezyk = parseInt(czytaj.nextLine());
                if (jezyk == 1) {
                    // POLSKI
                    System.out.println("Transakcja z kartą czy bez karty? 1 - Tak; Inny - Nie;");
                    i = parseInt(czytaj.nextLine());
                    if (i == 1) {
                        czyKarta = true;
                    } else {
                        czyKarta = false;
                    }
                    System.out.println("Wybierz walutę 1 - PLN; 2 - EUR; Inny - USD;");
                    i = parseInt(czytaj.nextLine());
                    if (i == 1) {
                        waluta = "PLN";
                    } else if (i == 2) {
                        waluta = "EUR";
                    } else {
                        waluta = "USD";
                    }
                    pytaj = true;
                    while (pytaj == true) {
                        System.out.println("Podaj numer konta:");
                        nr_kontastr = czytaj.nextLine();
                        while (a<=999999) {
                            spr_konta = (long) baza_kont.tablica_kont[a];
                            str_konta = String.valueOf(spr_konta);

                            if (Objects.equals(nr_kontastr, str_konta)) {
                                System.out.println("Konto wybrane " + nr_kontastr + ".");
                                pytaj = false;
                                break;
                            } else {
                                a++;
                            }
                        }

                    }
                    konto = baza_kont.tablica_kont[a];
                    // Działa i elo 320
                    karta = baza_kont.numery_kart.get(konto);
                    req_pin = baza_kont.numery_pin.get(konto);
                    money = baza_kont.numery_money.get(konto);
                    while (true) {
                        System.out.println("Podaj PIN:");
                        pin = czytaj.nextLine();
                        req_str_pin = String.valueOf(req_pin);
                        if (Objects.equals(pin, req_str_pin)) {
                            break;
                        }
                    }
                    if (req_str_pin.equals(pin)) {
                        System.out.println("ZALOGOWANY");
                        System.out.println("1 - Wpłać/2 - Wypłać/Inny - Sprawdź stan konta.");
                        i = parseInt(czytaj.nextLine());
                        if (i == 1) {
                            System.out.println("Wpłać");
                            a = 0;
                            pytaj = true;
                            while (pytaj == true) {
                                System.out.println("Podaj numer konta:");
                                nr_kontastr = czytaj.next();
                                while (true) {
                                    spr_konta = (long) baza_kont.tablica_kont[a];
                                    str_konta = String.valueOf(spr_konta);

                                    if (Objects.equals(nr_kontastr, str_konta)) {
                                        System.out.println("Konto wybrane " + nr_kontastr + ".");
                                        pytaj = false;
                                        break;
                                    } else {
                                        a++;
                                    }
                                }

                            }
                            nr_wpl_konta = parseLong(nr_kontastr);
                            money_wpl = baza_kont.numery_money.get(nr_wpl_konta);
                            System.out.println("Wpisz kwotę wpłaty (numer):");
                            czytaj.nextLine();
                            kwota_wpl = parseInt(czytaj.nextLine());
                            System.out.println("Wybrano kwotę " + kwota_wpl +" "+waluta+"!");
                            System.out.println("Proszę, uważać, aby banknoty nie były pogięte!");
                            System.out.println("Wsadź " + kwota_wpl +" "+waluta+" do bankomatu, aby wpłacić tą kwotę.");
                            if(waluta=="PLN"){
                                akceptowalne.add(10);
                                akceptowalne.add(20);
                                akceptowalne.add(50);
                                akceptowalne.add(100);
                                akceptowalne.add(200);
                                akceptowalne.add(500);
                            } else if (waluta=="EUR") {
                                akceptowalne.add(5);
                                akceptowalne.add(10);
                                akceptowalne.add(20);
                                akceptowalne.add(50);
                                akceptowalne.add(100);
                                akceptowalne.add(200);
                                akceptowalne.add(500);
                            } else if (waluta=="USD") {
                                akceptowalne.add(1);
                                akceptowalne.add(2);
                                akceptowalne.add(5);
                                akceptowalne.add(10);
                                akceptowalne.add(20);
                                akceptowalne.add(50);
                                akceptowalne.add(100);
                            }
                            while (wplacone < kwota_wpl) {
                                // Zrób banknoty
                                if(waluta=="PLN"){
                                    System.out.println("Wprowadź banknot do bankomatu (numer). 10zł/20zł/50zł/100zł/200zł/500zł");
                                } else if (waluta=="EUR") {
                                    System.out.println("Wprowadź banknot do bankomatu (numer). €5/€10/€20/€50/€100/€200/€500");
                                } else if (waluta=="USD") {
                                    System.out.println("Wprowadź banknot do bankomatu (numer). $1/$2/$5/$10/$20/$50/$100");
                                }

                                nominal = czytaj.nextLine();
                                if(akceptowalne.contains(parseInt(nominal))){
                                    wplacone += parseInt(nominal);
                                    nominaly.add(nominal);
                                }else{
                                    System.out.println("Ten banknot nie istnieje!");
                                }
                            }
                            if (kwota_wpl < wplacone) {
                                reszta = wplacone - kwota_wpl;
                                System.out.println("Wypłacam resztę, wynoszącą " + reszta +" "+ waluta + "...");
                                wplacone -= reszta;
                                System.out.println("Wypłacono.");
                            }
                            if(waluta=="PLN"){
                                money_wpl += kwota_wpl;
                            } else if (waluta=="EUR") {
                                money_wpl += kwota_wpl*5;
                            } else if (waluta=="USD") {
                                money_wpl += kwota_wpl*5;
                            }
                            baza_kont.numery_money.remove(nr_wpl_konta);
                            baza_kont.numery_money.put(nr_wpl_konta, money_wpl);
                            System.out.println("Drukowanie potwierdzenia...");
                            pisz_wpl.write("Potwierdzenie wpłaty. \n");
                            pisz_wpl.write("Dokonano wpłatę na konto o numerze " + nr_kontastr + " , wynoszącą " + kwota_wpl + " "+ waluta +".\n");
                            pisz_wpl.write("Reszta wynosiła " + reszta + ".\n");
                            pisz_wpl.write("Wpłata przeszła pomyślnie. -Bank AtlantusGold\n\n");
                            System.out.println("Potwierdzenie wydrukowane.");
                            // Sporządzenie raportu
                            pisz_rap_wpl.write("Raport wpłaty.\n");
                            pisz_rap_wpl.write("Ten dokument jest prywatną własnością Banku AtlantusGold. Odczyt tylko dla pracowników o wyższej pozycji.");
                            pisz_rap_wpl.write("Wpłata była o kwocie " + kwota_wpl + " "+ waluta + " na konto o numerze " + nr_kontastr + ".\n");
                            pisz_rap_wpl.write("Reszta wydana klientowi (gdyby klient wpłacił za dużo) wynosiła " + reszta + " "+ waluta + ".\n");
                            pisz_rap_wpl.write("Wpłacił on banknoty o nominałach:\n");
                            pisz_rap_wpl.write(String.valueOf(nominaly));
                            pisz_rap_wpl.write("\n");
                            pisz_rap_wpl.write("Raport sprzorządzony autoryzowanym Bankomatem Banku AtlantusGold.\n");


                            System.out.println("Zakończyć działanie na bankomacie?? 1 - Tak; Inny - Nie;");
                            i = parseInt(czytaj.nextLine());
                            if(i==1){
                                bankomat = false;
                            }

                        } else if (i == 2) {
                            System.out.println("Wypłać");
                            System.out.println("BLIK - 1 /Normalnie - Inny");
                            i = parseInt(czytaj.nextLine());
                            if (i == 1) {
                                //BLIK
                                if(waluta=="PLN"){
                                    while(true){
                                        System.out.println("Podaj kwotę (numer):");
                                        i = parseInt(czytaj.nextLine());
                                        if(money>=i){
                                            break;
                                        }else{
                                            System.out.println("Za mało środków na koncie.");
                                        }
                                    }
                                } else if (waluta=="EUR") {
                                    while(true){
                                        System.out.println("Podaj kwotę (numer):");
                                        i = parseInt(czytaj.nextLine());
                                        if(money/5>=i){
                                            break;
                                        }else{
                                            System.out.println("Za mało środków na koncie.");
                                        }
                                    }
                                } else if (waluta=="USD") {
                                    while(true){
                                        System.out.println("Podaj kwotę (numer):");
                                        i = parseInt(czytaj.nextLine());
                                        if(money/5>=i){
                                            break;
                                        }else{
                                            System.out.println("Za mało środków na koncie.");
                                        }
                                    }
                                }


                                kwota_blik = i;
                                kod_blik = (int) ((Math.random() * (max - min)) + min);
                                while(true){
                                    System.out.println("Wygenerowany kod BLIK: "+kod_blik+". Przepisz ten kod, aby wypłacić pieniądze.");
                                    i_str = czytaj.nextLine();
                                    kod_blik_str = String.valueOf(kod_blik);
                                    if(Objects.equals(i_str, kod_blik_str)){
                                        break;
                                    }
                                }
                                if(waluta=="PLN"){
                                    money -= i;
                                    baza_kont.numery_money.remove(konto);
                                    baza_kont.numery_money.put(konto, money);
                                }else{
                                    money -= i*5;
                                    baza_kont.numery_money.remove(konto);
                                    baza_kont.numery_money.put(konto, money);
                                }
                                System.out.println("Wydrukować potwierdzenie? Tak - 1; Nie - Inny;");
                                i = parseInt(czytaj.nextLine());
                                if (i == 1) {
                                    pisz_blik.write("Potwierdzenie płatności BLIK. \n");
                                    pisz_blik.write("Dnia nastąpiło wypłacanie pieniędzy o kwocie " + kwota_blik + " "+ waluta + " z bankomatu \n");
                                    pisz_blik.write("firmy AtlantusGold, metodą BLIK z konta klienta " + konto + ".\n");

                                }

                                System.out.println("Zakończyć działanie na bankomacie?? 1 - Tak; Inny - Nie;");
                                i = parseInt(czytaj.nextLine());
                                if(i==1){
                                    bankomat = false;
                                }
                            } else {
                                //NORMALNIE
                                if(waluta=="PLN"){
                                    while(true){
                                        System.out.println("Wybierz kwotę wypłacenia: 10 " + waluta + ", 20 " + waluta + ", 50 " + waluta + ", 100 " + waluta + ", 200 " + waluta + ", 500 " + waluta + ", 1000 " + waluta + " lub inne.");
                                        i = parseInt(czytaj.nextLine());
                                        if(money>=i){
                                            break;
                                        }else{
                                            System.out.println("Za mało środków.");
                                        }
                                    }
                                } else if (waluta=="EUR") {
                                    while(true){
                                        System.out.println("Wybierz kwotę wypłacenia: 10 " + waluta + ", 20 " + waluta + ", 50 " + waluta + ", 100 " + waluta + ", 200 " + waluta + ", 500 " + waluta + ", 1000 " + waluta + " lub inne.");
                                        i = parseInt(czytaj.nextLine());
                                        if(money*5>=i){
                                            break;
                                        }else{
                                            System.out.println("Za mało środków.");
                                        }
                                    }
                                } else if (waluta=="USD") {
                                    while(true){
                                        System.out.println("Wybierz kwotę wypłacenia: 10 " + waluta + ", 20 " + waluta + ", 50 " + waluta + ", 100 " + waluta + ", 200 " + waluta + ", 500 " + waluta + ", 1000 " + waluta + " lub inne.");
                                        i = parseInt(czytaj.nextLine());
                                        if(money*5>=i){
                                            break;
                                        }else{
                                            System.out.println("Za mało środków.");
                                        }
                                    }
                                }


                                kwota_wyplata = i;
                                if(waluta=="PLN"){
                                    money -= i;
                                    baza_kont.numery_money.remove(konto);
                                    baza_kont.numery_money.put(konto, money);
                                }else{
                                    money -= i*5;
                                    baza_kont.numery_money.remove(konto);
                                    baza_kont.numery_money.put(konto, money);
                                }
                                System.out.println("Wypłacanie...");
                                System.out.println("Wydrukować potwierdzenie?? Tak - 1; Nie - Inny;");
                                i = parseInt(czytaj.nextLine());
                                if (i == 1) {
                                    pisz_wyp.write("Potwierdzenie wypłaty pieniędzy. \n");
                                    pisz_wyp.write("Dnia nastąpiło wypłacanie pieniędzy o kwocie " + kwota_wyplata +" "+ waluta + " z bankomatu \n");
                                    pisz_wyp.write("firmy AtlantusGold, metodą normalną z konta klienta " + konto + ".\n");
                                }

                                System.out.println("Zakończyć działanie na bankomacie?? 1 - Tak; Inny - Nie;");
                                i = parseInt(czytaj.nextLine());
                                if(i==1){
                                    bankomat = false;
                                }
                            }
                        } else {
                            System.out.println("Sprawdzeniu stanu konta.. Drukowanie..");
                            if(waluta=="PLN"){
                                System.out.println("Stan konta wynosi "+money+" "+waluta+".");
                            }else{
                                System.out.println("Stan konta wynosi "+money/5+" "+waluta+".");
                            }

                            pisz_stan.write("Stan konta klienta " + nr_kontastr + ":\n");
                            if(waluta=="PLN"){
                                pisz_stan.write(money+" "+waluta+"\n");
                            }else{
                                pisz_stan.write(money/5+" "+waluta+"\n");
                            }


                            System.out.println("Zakończyć działanie na bankomacie?? 1 - Tak; Inny - Nie;");
                            i = parseInt(czytaj.nextLine());
                            if(i==1){
                                bankomat = false;
                            }
                        }
                    }
                } else {
                    // ENGLISH
                    System.out.println("Purchase with card or without card? 1 - Yes; Other - No;");
                    i = parseInt(czytaj.nextLine());
                    if (i == 1) {
                        czyKarta = true;
                    } else {
                        czyKarta = false;
                    }
                    System.out.println("Select currency: 1 - PLN; 2 - EUR; Other - USD;");
                    i = parseInt(czytaj.nextLine());
                    if (i == 1) {
                        waluta = "PLN";
                    } else if (i == 2) {
                        waluta = "EUR";
                    } else {
                        waluta = "USD";
                    }
                    pytaj = true;
                    while (pytaj == true) {
                        System.out.println("Input account number:");
                        nr_kontastr = czytaj.nextLine();
                        while (a<=999999) {
                            spr_konta = (long) baza_kont.tablica_kont[a];
                            str_konta = String.valueOf(spr_konta);

                            if (Objects.equals(nr_kontastr, str_konta)) {
                                System.out.println("Account selected: " + nr_kontastr + ".");
                                pytaj = false;
                                break;
                            } else {
                                a++;
                            }
                        }

                    }
                    konto = baza_kont.tablica_kont[a];
                    // Działa i elo 320
                    karta = baza_kont.numery_kart.get(konto);
                    req_pin = baza_kont.numery_pin.get(konto);
                    money = baza_kont.numery_money.get(konto);
                    while (true) {
                        System.out.println("Insert PIN:");
                        pin = czytaj.nextLine();
                        req_str_pin = String.valueOf(req_pin);
                        if (Objects.equals(pin, req_str_pin)) {
                            break;
                        }
                    }
                    if (req_str_pin.equals(pin)) {
                        System.out.println("LOGGED-IN");
                        System.out.println("1 - Deposit/2 - Withdraw/Other - Check account balance.");
                        i = parseInt(czytaj.nextLine());
                        if (i == 1) {
                            System.out.println("Deposit");
                            a = 0;
                            pytaj = true;
                            while (pytaj == true) {
                                System.out.println("Input account number for deposit::");
                                nr_kontastr = czytaj.next();
                                while (true) {
                                    spr_konta = (long) baza_kont.tablica_kont[a];
                                    str_konta = String.valueOf(spr_konta);

                                    if (Objects.equals(nr_kontastr, str_konta)) {
                                        System.out.println("Account selected: " + nr_kontastr + ".");
                                        pytaj = false;
                                        break;
                                    } else {
                                        a++;
                                    }
                                }

                            }
                            nr_wpl_konta = parseLong(nr_kontastr);
                            money_wpl = baza_kont.numery_money.get(nr_wpl_konta);
                            System.out.println("Input deposit money ammount (number):");
                            czytaj.nextLine();
                            kwota_wpl = parseInt(czytaj.nextLine());
                            System.out.println("Selected ammount: " + kwota_wpl +" "+waluta+"!");
                            System.out.println("Please, watch out for the money bills to not be ");
                            System.out.println("Insert " + kwota_wpl +" "+waluta+" into ATM, to deposit that ammount.");
                            if(waluta=="PLN"){
                                akceptowalne.add(10);
                                akceptowalne.add(20);
                                akceptowalne.add(50);
                                akceptowalne.add(100);
                                akceptowalne.add(200);
                                akceptowalne.add(500);
                            } else if (waluta=="EUR") {
                                akceptowalne.add(5);
                                akceptowalne.add(10);
                                akceptowalne.add(20);
                                akceptowalne.add(50);
                                akceptowalne.add(100);
                                akceptowalne.add(200);
                                akceptowalne.add(500);
                            } else if (waluta=="USD") {
                                akceptowalne.add(1);
                                akceptowalne.add(2);
                                akceptowalne.add(5);
                                akceptowalne.add(10);
                                akceptowalne.add(20);
                                akceptowalne.add(50);
                                akceptowalne.add(100);
                            }
                            while (wplacone < kwota_wpl) {
                                // Zrób banknoty
                                if(waluta=="PLN"){
                                    System.out.println("Insert money bill into ATM (number). 10zł/20zł/50zł/100zł/200zł/500zł");
                                } else if (waluta=="EUR") {
                                    System.out.println("Insert money bill into ATM (number). €5/€10/€20/€50/€100/€200/€500");
                                } else if (waluta=="USD") {
                                    System.out.println("Insert money bill into ATM (number). $1/$2/$5/$10/$20/$50/$100");
                                }

                                nominal = czytaj.nextLine();
                                if(akceptowalne.contains(parseInt(nominal))){
                                    wplacone += parseInt(nominal);
                                    nominaly.add(nominal);
                                }else{
                                    System.out.println("This bill doesn't exist!");
                                }
                            }
                            if (kwota_wpl < wplacone) {
                                reszta = wplacone - kwota_wpl;
                                System.out.println("Returning change... : " + reszta +" "+ waluta + "...");
                                wplacone -= reszta;
                                System.out.println("Change has been returned.");
                            }
                            if(waluta=="PLN"){
                                money_wpl += kwota_wpl;
                            } else if (waluta=="EUR") {
                                money_wpl += kwota_wpl*5;
                            } else if (waluta=="USD") {
                                money_wpl += kwota_wpl*5;
                            }
                            baza_kont.numery_money.remove(nr_wpl_konta);
                            baza_kont.numery_money.put(nr_wpl_konta, money_wpl);
                            System.out.println("Printing confirmation...");
                            pisz_wpl_eng.write("Deposit confirmation. \n");
                            pisz_wpl_eng.write("A deposit was made on the account number " + nr_kontastr + " , the ammount deposited was " + kwota_wpl + " "+ waluta +".\n");
                            pisz_wpl_eng.write("Change was equal to " + reszta + ".\n");
                            pisz_wpl_eng.write("Deposit has finished successfully. -Bank AtlantusGold\n\n");
                            System.out.println("Confirmation has been printed.");
                            // Sporządzenie raportu
                            pisz_rap_wpl_eng.write("Deposit Report.\n");
                            pisz_rap_wpl_eng.write("This document is a private property of Bank AtlantusGold. Read-able only by employees of high positions.");
                            pisz_rap_wpl_eng.write("The deposited ammount was " + kwota_wpl + " "+ waluta + " to the account number " + nr_kontastr + ".\n");
                            pisz_rap_wpl_eng.write("Change returned to the client (if the client inserted too much money) equalled " + reszta + " "+ waluta + ".\n");
                            pisz_rap_wpl_eng.write("Client inserted money bills of denominations:\n");
                            pisz_rap_wpl_eng.write(String.valueOf(nominaly));
                            pisz_rap_wpl_eng.write("\n");
                            pisz_rap_wpl_eng.write("Report has been prepared by an authorized ATM of Bank AtlantusGold.\n");


                            System.out.println("Finish the operations on the ATM?? 1 - Yes; Other - No;");
                            i = parseInt(czytaj.nextLine());
                            if(i==1){
                                bankomat = false;
                            }

                        } else if (i == 2) {
                            System.out.println("Withdraw");
                            System.out.println("BLIK - 1 /Normal - Other");
                            i = parseInt(czytaj.nextLine());
                            if (i == 1) {
                                //BLIK
                                if(waluta=="PLN"){
                                    while(true){
                                        System.out.println("Input ammount (number):");
                                        i = parseInt(czytaj.nextLine());
                                        if(money>=i){
                                            break;
                                        }else{
                                            System.out.println("Too low account balance.");
                                        }
                                    }
                                } else if (waluta=="EUR") {
                                    while(true){
                                        System.out.println("Input ammount (number):");
                                        i = parseInt(czytaj.nextLine());
                                        if(money/5>=i){
                                            break;
                                        }else{
                                            System.out.println("Too low account balance.");
                                        }
                                    }
                                } else if (waluta=="USD") {
                                    while(true){
                                        System.out.println("Input ammount (number):");
                                        i = parseInt(czytaj.nextLine());
                                        if(money/5>=i){
                                            break;
                                        }else{
                                            System.out.println("Too low account balance.");
                                        }
                                    }
                                }


                                kwota_blik = i;
                                kod_blik = (int) ((Math.random() * (max - min)) + min);
                                while(true){
                                    System.out.println("Generated BLIK code: "+kod_blik+". Copy and write this code, in order to withdraw money.");
                                    i_str = czytaj.nextLine();
                                    kod_blik_str = String.valueOf(kod_blik);
                                    if(Objects.equals(i_str, kod_blik_str)){
                                        break;
                                    }
                                }
                                if(waluta=="PLN"){
                                    money -= i;
                                    baza_kont.numery_money.remove(konto);
                                    baza_kont.numery_money.put(konto, money);
                                }else{
                                    money -= i*5;
                                    baza_kont.numery_money.remove(konto);
                                    baza_kont.numery_money.put(konto, money);
                                }
                                System.out.println("Print out confirmation? Yes - 1; No - Other;");
                                i = parseInt(czytaj.nextLine());
                                if (i == 1) {
                                    pisz_blik_eng.write("BLIK transaction confirmation. \n");
                                    pisz_blik_eng.write("Today there was money withdrawn, equalled to " + kwota_blik + " "+ waluta + " by an ATM \n");
                                    pisz_blik_eng.write("of the AtlantusGold, with the BLIK method from the clients account number " + konto + ".\n");

                                }

                                System.out.println("Finish the operations on the ATM?? 1 - Yes; Other - No;");
                                i = parseInt(czytaj.nextLine());
                                if(i==1){
                                    bankomat = false;
                                }
                            } else {
                                //NORMALNIE
                                if(waluta=="PLN"){
                                    while(true){
                                        System.out.println("Select ammount to withdraw: 10 " + waluta + ", 20 " + waluta + ", 50 " + waluta + ", 100 " + waluta + ", 200 " + waluta + ", 500 " + waluta + ", 1000 " + waluta + " or OTHER.");
                                        i = parseInt(czytaj.nextLine());
                                        if(money>=i){
                                            break;
                                        }else{
                                            System.out.println("Too low account balance.");
                                        }
                                    }
                                } else if (waluta=="EUR") {
                                    while(true){
                                        System.out.println("Select ammount to withdraw: 10 " + waluta + ", 20 " + waluta + ", 50 " + waluta + ", 100 " + waluta + ", 200 " + waluta + ", 500 " + waluta + ", 1000 " + waluta + " or OTHER.");
                                        i = parseInt(czytaj.nextLine());
                                        if(money*5>=i){
                                            break;
                                        }else{
                                            System.out.println("Too low account balance.");
                                        }
                                    }
                                } else if (waluta=="USD") {
                                    while(true){
                                        System.out.println("Select ammount to withdraw: 10 " + waluta + ", 20 " + waluta + ", 50 " + waluta + ", 100 " + waluta + ", 200 " + waluta + ", 500 " + waluta + ", 1000 " + waluta + " or OTHER.");
                                        i = parseInt(czytaj.nextLine());
                                        if(money*5>=i){
                                            break;
                                        }else{
                                            System.out.println("Too low account balance.");
                                        }
                                    }
                                }


                                kwota_wyplata = i;
                                if(waluta=="PLN"){
                                    money -= i;
                                    baza_kont.numery_money.remove(konto);
                                    baza_kont.numery_money.put(konto, money);
                                }else{
                                    money -= i*5;
                                    baza_kont.numery_money.remove(konto);
                                    baza_kont.numery_money.put(konto, money);
                                }
                                System.out.println("Withdrawing...");
                                System.out.println("Print out confirmation?? Yes - 1; No - Other;");
                                i = parseInt(czytaj.nextLine());
                                if (i == 1) {
                                    pisz_wyp_eng.write("Money withdrawal confirmation. \n");
                                    pisz_wyp_eng.write("Today money was withdrawn equalling to " + kwota_wyplata +" "+ waluta + " from the ATM of \n");
                                    pisz_wyp_eng.write("AtlantusGold, normal method from the clients account number " + konto + ".\n");
                                }

                                System.out.println("Finish the operations on the ATM?? 1 - Yes; Other - No;");
                                i = parseInt(czytaj.nextLine());
                                if(i==1){
                                    bankomat = false;
                                }
                            }
                        } else {
                            System.out.println("Checking account balance.. Printing..");
                            if(waluta=="PLN"){
                                System.out.println("Account balance equalls to "+money+" "+waluta+".");
                            }else{
                                System.out.println("Account balance equalls to "+money/5+" "+waluta+".");
                            }

                            pisz_stan_eng.write("Account balance equalls to " + nr_kontastr + ":\n");
                            if(waluta=="PLN"){
                                pisz_stan_eng.write(money+" "+waluta+"\n");
                            }else{
                                pisz_stan_eng.write(money/5+" "+waluta+"\n");
                            }


                            System.out.println("Finish the operations on the ATM?? 1 - Yes; Other - No;");
                            i = parseInt(czytaj.nextLine());
                            if(i==1){
                                bankomat = false;
                            }
                        }
                    }
                }
            } else {
                baza_kont.stworzKonto();
            }
        }


        pisz_blik.close();
        pisz_wyp.close();
        pisz_wpl.close();
        pisz_rap_wpl.close();
        pisz_stan.close();
        pisz_rap_wpl_eng.close();
        pisz_wpl_eng.close();
        pisz_blik_eng.close();
        pisz_wyp_eng.close();
        pisz_stan_eng.close();
        czytaj.close();

    }
}