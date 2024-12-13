import java.io.*;

class CrcGen {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] data;
        int[] divisor;
        int[] div;
        int[] rem;
        int[] crc;
        int dataBits, divisorBits, totLength;

        System.out.println("Enter the number of data bits:");
        dataBits = Integer.parseInt(br.readLine());
        data = new int[dataBits];

        System.out.println("Enter data bits:");
        for (int i = 0; i < dataBits; i++) {
            data[i] = Integer.parseInt(br.readLine());
        }

        System.out.println("Enter the number of bits in divisor:");
        divisorBits = Integer.parseInt(br.readLine());
        divisor = new int[divisorBits];

        System.out.println("Enter divisor bits:");
        for (int i = 0; i < divisorBits; i++) {
            divisor[i] = Integer.parseInt(br.readLine());
        }

        totLength = dataBits + divisorBits - 1;
        div = new int[totLength];
        rem = new int[totLength];
        crc = new int[totLength];

        for (int i = 0; i < data.length; i++) {
            div[i] = data[i];
        }

        System.out.println("Dividend (after appending 0's):");
        for (int i = 0; i < div.length; i++) {
            System.out.print(div[i]);
        }
        System.out.println();

        for (int i = 0; i < div.length; i++) {
            rem[i] = div[i];
        }
        rem = divide(div, divisor, rem);

        for (int i = 0; i < div.length; i++) {
            crc[i] = (div[i] ^ rem[i]);
        }

        System.out.println("CRC code:");
        for (int i = 0; i < crc.length; i++) {
            System.out.print(crc[i]);
        }
        System.out.println();

        System.out.println("Enter the CRC code of " + totLength + " bits:");
        for (int i = 0; i < crc.length; i++) {
            crc[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < crc.length; i++) {
            rem[i] = crc[i];
        }
        rem = divide(crc, divisor, rem);

        boolean hasError = false;
        for (int i = 0; i < rem.length; i++) {
            if (rem[i] != 0) {
                hasError = true;
                break;
            }
        }

        if (hasError) {
            System.out.println("Error detected in the CRC code.");
        } else {
            System.out.println("No error detected in the CRC code.");
        }

        System.out.println("Thank you!");
    }

    static int[] divide(int div[], int divisor[], int rem[]) {
        int cur = 0;
        while (true) {
            for (int i = 0; i < divisor.length; i++) {
                rem[cur + i] = (rem[cur + i] ^ divisor[i]);
            }
            while (cur < rem.length && rem[cur] == 0) {
                cur++;
            }
            if ((rem.length - cur) < divisor.length) {
                break;
            }
        }
        return rem;
    }
}
