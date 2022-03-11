public class Encryptor
{
    /** A two-dimensional array of single-character strings, instantiated in the constructor */
    private String[][] letterBlock;

    /** The number of rows of letterBlock, set by the constructor */
    private int numRows;

    /** The number of columns of letterBlock, set by the constructor */
    private int numCols;

    /** Constructor*/
    public Encryptor(int r, int c)
    {
        letterBlock = new String[r][c];
        numRows = r;
        numCols = c;
    }

    public String[][] getLetterBlock()
    {
        return letterBlock;
    }

    /** Places a string into letterBlock in row-major order.
     *
     *   @param str  the string to be processed
     *
     *   Postcondition:
     *     if str.length() < numRows * numCols, "A" in each unfilled cell
     *     if str.length() > numRows * numCols, trailing characters are ignored
     */
    public void fillBlock(String str)
    {
        int count = 0;
        for (int row = 0; row < letterBlock.length; row++)
        {
            for (int col = 0; col < letterBlock[0].length; col++)
            {
                if (count == str.length())
                {
                    letterBlock[row][col] = "A";
                }
                else
                {
                    letterBlock[row][col] = str.substring(count, count + 1);
                    count++;
                }
            }
        }
    }

    public void fillBlockDeCrypt(String str)
    {
        int count = 0;
        for (int col = 0; col < letterBlock[0].length; col++)
        {
            for (int row = 0; row < letterBlock.length; row++)
            {
                letterBlock[row][col] = str.substring(count, count + 1);
                count++;
            }
        }
    }

    /** Extracts encrypted string from letterBlock in column-major order.
     *
     *   Precondition: letterBlock has been filled
     *
     *   @return the encrypted string from letterBlock
     */
    public String encryptBlock()
    {
        String encryptStr = "";
        for (int col = 0; col < letterBlock[0].length; col++)
        {
            for (int row = 0; row < letterBlock.length; row++)
            {
                encryptStr += letterBlock[row][col];
            }
        }
        return encryptStr;
    }

    public String decryptBlock()
    {
        String decryptStr = "";
        for (int row = 0; row < letterBlock.length; row++)
        {
            for (int col = 0; col < letterBlock[0].length; col++)
            {
                decryptStr += letterBlock[row][col];
            }
        }
        return decryptStr;
    }

    /** Encrypts a message.
     *
     *  @param message the string to be encrypted
     *
     *  @return the encrypted message; if message is the empty string, returns the empty string
     */
    public String encryptMessage(String message)
    {
        int filled = 0;
        String encyrptMessaged = "";
        while (message.length() > filled)
        {
           fillBlock(message.substring(filled));
           encyrptMessaged += encryptBlock();
           filled += numCols * numRows;
        }
        return encyrptMessaged;
    }

    /**  Decrypts an encrypted message. All filler 'A's that may have been
     *   added during encryption will be removed, so this assumes that the
     *   original message (BEFORE it was encrypted) did NOT end in a capital A!
     *
     *   NOTE! When you are decrypting an encrypted message,
     *         be sure that you have initialized your Encryptor object
     *         with the same row/column used to encrypted the message! (i.e.
     *         the “encryption key” that is necessary for successful decryption)
     *         This is outlined in the precondition below.
     *
     *   Precondition: the Encryptor object being used for decryption has been
     *                 initialized with the same number of rows and columns
     *                 as was used for the Encryptor object used for encryption.
     *
     *   @param encryptedMessage  the encrypted message to decrypt
     *
     *   @return  the decrypted, original message (which had been encrypted)
     *
     *   TIP: You are encouraged to create other helper methods as you see fit
     *        (e.g. a method to decrypt each section of the decrypted message,
     *         similar to how encryptBlock was used)
     */
    public String decryptMessage(String encryptedMessage)
    {
        int filled = 0;
        String decyrptMessaged = "";
        while (encryptedMessage.length() > filled)
        {
            fillBlockDeCrypt(encryptedMessage.substring(filled));
            decyrptMessaged += decryptBlock();
            filled += numCols * numRows;
        }

        int count = 0;
        while (decyrptMessaged.substring(decyrptMessaged.length() - count - 1, decyrptMessaged.length() - count).equals("A"))
        {
            count++;
        }

        decyrptMessaged = decyrptMessaged.substring(0, decyrptMessaged.length() - count);
        return decyrptMessaged;
    }
}
