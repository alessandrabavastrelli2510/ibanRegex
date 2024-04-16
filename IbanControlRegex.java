package com.exercise.java;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IbanControlRegex {
    public static void main(String[] args) {

        /*String regexIban = "^([A-Z]{2})([0-9]{2})([A-Z])([0-9]{4})([0-9]{5})([0-9]{12})$";
        String iban = "IT05B777895874321456554568";

        Pattern pattern = Pattern.compile(regexIban); //creazione pattern
        Matcher matcher = pattern.matcher(iban);
        boolean matchFound = matcher.find();


        if (matchFound){
            System.out.println("Iban corretto.");
            System.out.println(matcher.group(6));
        } else {
            System.out.println("Iban NON valido.");
        }*/

        Random random = new Random();

        String [] nationLista = {"IT", "FR", "UK", "US", "ES"};

        List <String> ibanLista = new ArrayList<>();
        String iban2;

        for (int i = 0; i < 20; i++){
            String nazioneScelta =nationLista[random.nextInt(0, 5)];
            iban2 = generaIban(nazioneScelta);

            ibanLista.add(iban2);

            if (i % 3 == 0){

                System.out.print("\n" + ibanLista.get(i) + "\t\t");
            } else {
                System.out.print( ibanLista.get(i) + "\t\t");
            }
        }

        //System.out.println(ibanLista);

        /* Dato un array di IBAN, creare una lista contenente le sole nazioni e una lista
         contenente i soli numeri di conto corrente (gruppo 6) */

        String regexIban = "^([A-Z]{2})([0-9]{2})([A-Z])([0-9]{5})([0-9]{5})([0-9]{12})$";
        //boolean matchFound = matcher.find();


        String[] arrayIBAN = {"IT01A0123456789123456789101",
                "IT01A3423456799234567123001",
                "FR02B0123456789890123567891",
                "UK01A0123456789889012365001",
                "ES01A0123456789889902341681",
                "IT01K0123456789112563009131",
                "FR39P1299981473481943100241"};

        List <String> nazioniLista = new ArrayList<>();
        List <String> contoCorrenteLista = new ArrayList<>();
        Pattern pattern = Pattern.compile(regexIban); //creazione pattern
        Map <String, Integer> mappaNazioniCC = new TreeMap<>();

        for (String iban : arrayIBAN){
            Matcher matcher = pattern.matcher(iban);
            boolean matchFound = matcher.find();
            nazioniLista.add(matcher.group(1));
            contoCorrenteLista.add(matcher.group(6));
        }

        System.out.println(nazioniLista);
        System.out.println(contoCorrenteLista);

        for (int i = 0; i< nazioniLista.size(); i++){

            if (!mappaNazioniCC.containsKey(nazioniLista.get(i))){
                mappaNazioniCC.put(nazioniLista.get(i), 1);
            } else {
                int valorePrec = mappaNazioniCC.get(nazioniLista.get(i));
                mappaNazioniCC.put(nazioniLista.get(i), valorePrec++);

            }
        }
        System.out.println(mappaNazioniCC);

    }

    public static String generaIban (String nazione){

        Random random = new Random();

        int codiceControlloInternazionale = random.nextInt(10,99);
        char codiceControlloNazionale = (char) ('A' + random.nextInt(26));
        int codiceABI = random.nextInt(10000,99999);
        int codiceCAB = random.nextInt(10000,99999);
        long codiceContoCorrente = random.nextLong(100000000000L,999999999999L);


        return nazione + codiceControlloInternazionale + codiceControlloNazionale + codiceABI
                + codiceCAB + codiceContoCorrente;
    }
}
