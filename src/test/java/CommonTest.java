import com.fr.general.Encrypt;
import com.fr.general.Decrypt;
import org.junit.Assert;
import org.junit.Test;

public class CommonTest {
    @Test
    public void encrypt() {
        String abc = Encrypt.encrypt("abc");
        String decrypt2 = Decrypt.decrypt(abc);
        String decrypt1 = Decrypt.decrypt("6b1cdf00cae924");
        Assert.assertEquals("abc", decrypt1);
        Assert.assertEquals("abc", decrypt2);
    }

    @Test
    public void decrypt() {

    }
}
