/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Jesus Dicent
 */
public class Checkers {

    public static boolean esUnRNCValido(String pRNC) {

        int vnTotal = 0;

        int[] digitoMult = {7, 9, 8, 6, 5, 4, 3, 2};

        String vcRNC = pRNC.replace("-", "").replace(" ", "");

        String vDigito = vcRNC.substring(8, 1);

        if (vcRNC.length() == 9) {
            if (!"145".contains(vcRNC.substring(0, 1))) {
                return false;
            }
        }

        for (int vDig = 1; vDig <= 8; vDig++) {

            int vCalculo = Integer.parseInt(vcRNC.substring(vDig - 1, vDig)) * digitoMult[vDig - 1];

            vnTotal += vCalculo;

        }
        boolean thirdPart = (11 - (vnTotal % 11)) == Integer.parseInt(vDigito);

        return vnTotal % 11 == 0 && "1".equals(vDigito)
                || vnTotal % 11 == 1 && "1".equals(vDigito)
                || thirdPart;

    }

    public static boolean validaCedula(String pCedula) {
        int vnTotal = 0;
        String vcCedula = pCedula.replace("-", "");
        int pLongCed = vcCedula.trim().length();
        int[] digitoMult = {1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1};

        if (pLongCed < 11 || pLongCed > 11) {
            return false;
        }
        try {

            for (int vDig = 0; vDig < pLongCed; vDig++) {
                String sub = vcCedula.substring(vDig, vDig+1);
                int vCalculo = Integer.parseInt(sub) * digitoMult[vDig];
                if (vCalculo < 10) {
                    vnTotal += vCalculo;
                } else {
                    vnTotal += Integer.parseInt(String.valueOf(vCalculo).substring(0, 1)) + Integer.parseInt(String.valueOf(vCalculo).substring(1, 2));
                }
            }
        } catch (NumberFormatException e) {
            throw e;
        }

        return vnTotal % 10 == 0;
    }
}
