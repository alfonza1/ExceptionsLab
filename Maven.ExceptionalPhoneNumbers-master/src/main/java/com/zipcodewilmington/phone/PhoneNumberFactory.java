package com.zipcodewilmington.phone;

import com.zipcodewilmington.exceptions.InvalidPhoneNumberFormatException;
import com.zipcodewilmington.tools.RandomNumberFactory;

import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by leon on 5/1/17.
 */
public final class PhoneNumberFactory {
    private static final Logger logger = Logger.getGlobal();

    private PhoneNumberFactory() {
        /** This constructor is private
         *  This class is uninstantiable */
    }

    /**
     * @param phoneNumberCount - number of PhoneNumber objects to instantiate
     * @return array of randomly generated PhoneNumber objects
     */ //TODO - Implement logic
    public static PhoneNumber[] createRandomPhoneNumberArray(int phoneNumberCount) throws InvalidPhoneNumberFormatException {

        PhoneNumber[] numbers = new PhoneNumber[phoneNumberCount];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = createRandomPhoneNumber();
        }
        return numbers;
    }

    /**
     * @return an instance of PhoneNumber with randomly generated phone number value
     */ //TODO - Implement logic

    public static PhoneNumber createRandomPhoneNumber() throws InvalidPhoneNumberFormatException {

        int areaCode = RandomNumberFactory.createInteger(100, 1000);
        int centralOfficeCode = RandomNumberFactory.createInteger(100, 1000);
        int phoneLineCode = RandomNumberFactory.createInteger(1000, 10000);


        return createPhoneNumberSafely(areaCode, centralOfficeCode, phoneLineCode);
    }



    /**
     * @param areaCode          - 3 digit code
     * @param centralOfficeCode - 3 digit code
     * @param phoneLineCode     - 4 digit code
     * @return a new phone number object
     */ //TODO - if input is valid, return respective PhoneNumber object, else return null
    public static PhoneNumber createPhoneNumberSafely(int areaCode, int centralOfficeCode, int phoneLineCode){

        String num = "";

        if (String.valueOf(areaCode).length() == 3 && String.valueOf(centralOfficeCode).length() == 3 && String.valueOf(phoneLineCode).length() == 4){
            num = String.format("(%03d)-%03d-%04d", areaCode, centralOfficeCode, phoneLineCode);
        }
        try {
            return createPhoneNumber(num);
        } catch (InvalidPhoneNumberFormatException e) {
            logger.warning(areaCode + "-" + centralOfficeCode  + "-" + phoneLineCode   + "" + " is not a valid phone number");
            return null;
        }
    }




    /**
     * @param phoneNumberString - some String corresponding to a phone number whose format is `(###)-###-####`
     * @return a new phone number object
     * @throws InvalidPhoneNumberFormatException - thrown if phoneNumberString does not match acceptable format
     */ // TODO - Add throws statement to method signature

    public static PhoneNumber createPhoneNumber(String phoneNumberString) throws InvalidPhoneNumberFormatException {
        logger.info("Attempting to create a new PhoneNumber object with a value of " + phoneNumberString);
        return new PhoneNumber(phoneNumberString);
    }


    public static void main(String[] args) throws InvalidPhoneNumberFormatException {

       PhoneNumber pn = createPhoneNumberSafely(444,444,4444);

        System.out.println(pn);


        PhoneNumber[] numbers = createRandomPhoneNumberArray(2);
        for (PhoneNumber number : numbers) {
            System.out.print(number + " " );
        }

        PhoneNumber phoneNumber = PhoneNumberFactory.createPhoneNumberSafely(111,111,11);
        System.out.println(phoneNumber);


    } }

