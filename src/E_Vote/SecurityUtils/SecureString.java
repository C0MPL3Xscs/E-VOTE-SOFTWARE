//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
//::                                                                         ::
//::     Antonio Manuel Rodrigues Manso                                      ::
//::                                                                         ::
//::     I N S T I T U T O    P O L I T E C N I C O   D E   T O M A R        ::
//::     Escola Superior de Tecnologia de Tomar                              ::
//::     e-mail: manso@ipt.pt                                                ::
//::     url   : http://orion.ipt.pt/~manso                                  ::
//::                                                                         ::
//::     This software was build with the purpose of investigate and         ::
//::     learning.                                                           ::
//::                                                                         ::
//::                                                               (c)2022   ::
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//////////////////////////////////////////////////////////////////////////////
package E_Vote.SecurityUtils;

import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Created on 28/12/2022, 11:04:35
 *
 * @author IPT - computer
 * @version 1.0
 */
public class SecureString implements CharSequence {

    private final int[] chars;
    private final int[] pad;

    public SecureString(final CharSequence original) {
        this(0, original.length(), original);
    }

    public SecureString(final int start, final int end, final CharSequence original) {
        final int length = end - start;
        pad = new int[length];
        chars = new int[length];
        scramble(start, length, original);
    }

    @Override
    public char charAt(final int i) {
        return (char) (pad[i] ^ chars[i]);
    }

    @Override
    public int length() {
        return chars.length;
    }

    @Override
    public CharSequence subSequence(final int start, final int end) {
        return new SecureString(start, end, this);
    }

    /**
     * Convert array back to String but not using toString().See toString() docs
     * below.
     *
     * @return
     */
    public String asString() {
        final char[] value = new char[chars.length];
        for (int i = 0; i < value.length; i++) {
            value[i] = charAt(i);
        }
        return new String(value);
    }

    /**
     * Manually clear the underlying array holding the characters
     */
    public void clear() {
        Arrays.fill(chars, 0);
        Arrays.fill(pad, 0);
    }

    /**
     * Protect against using this class in log statements.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Secure:XXXXX";
    }

    /**
     * Randomly pad the characters to not store the real character in memory.
     *
     * @param start start of the {@code CharSequence}
     * @param length length of the {@code CharSequence}
     * @param characters the {@code CharSequence} to scramble
     */
    private void scramble(final int start, final int length, final CharSequence characters) {
        final SecureRandom random = new SecureRandom();
        for (int i = start; i < length; i++) {
            final char charAt = characters.charAt(i);
            pad[i] = random.nextInt();
            chars[i] = pad[i] ^ charAt;
        }
    }

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    private static final long serialVersionUID = 202212281104L;
    //:::::::::::::::::::::::::::  Copyright(c) M@nso  2022  :::::::::::::::::::
    ///////////////////////////////////////////////////////////////////////////
}
