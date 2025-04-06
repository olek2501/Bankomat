import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Konta {
    // Magazyn kont
    ArrayList konta = new ArrayList();
    long tablica_kont[] = new long[9999999];
    int i = 0;
    HashMap<Long, Long> numery_kart = new HashMap<Long, Long>();
    HashMap<Long, Integer> numery_pin = new HashMap<Long, Integer>();
    HashMap<Long, Integer> numery_money = new HashMap<Long, Integer>();



    public void stworzKonto() {
        int kod_pin;
        String str_pin;
        long nr_konta;
        long nr_karty;
        long max = 9999999999999999L;
        long min = 1000000000000000L;
        int money = 50000;
        int odpowiedz;
        Scanner odp = new Scanner(System.in);
        System.out.println("Chciałbyś utwórz konto?? 1 - Tak; Inny - Nie // Do you want to create an account?? 1 - Yes; Other - No");
        odpowiedz = parseInt(odp.nextLine());
        if(odpowiedz==1){
            //Generacja Numeru Konta

            nr_konta = (long) ((Math.random() * (max - min)) + min);
            nr_karty = (long) ((Math.random() * (max - min)) + min);

            while(true){
                while(true){
                    System.out.println("Podaj kod PIN do swojego konta, aby go ustawić: // Insert PIN code, in order to add it to your account.");
                    kod_pin = parseInt(odp.nextLine());
                    str_pin = String.valueOf(kod_pin);
                    if(str_pin.length()==4){
                        break;
                    }else{
                        System.out.println("Zła długość kodu pin. Poprawna to 4 cyfry. // Wrong length. PIN code should be 4 digits.");
                    }
                }

                System.out.println("Powtórz kod PIN, aby go ustawić: // Repeat the PIN code in order to confirm it.");
                odpowiedz = parseInt(odp.nextLine());
                if(kod_pin==odpowiedz){
                    System.out.println("Kod PIN ustawiony! // PIN code has been set!");
                    break;
                }else{
                    System.out.println("Kod PIN się nie zgadza. // PIN code is incorrect.");
                }
            }

            konta.add(nr_konta);
            konta.add(nr_karty);
            konta.add(kod_pin);

            numery_kart.put(nr_konta, nr_karty);
            numery_pin.put(nr_konta, kod_pin);
            numery_money.put(nr_konta, money);

            i = konta.size()/4;
            tablica_kont[i] = nr_konta;

            System.out.println("Konto utworzone // Account created, numer konta // account number: "+nr_konta+"; numer karty // card number: "+nr_karty+"; o PINie // with the PIN code: "+kod_pin+".");



        }
    }




}
