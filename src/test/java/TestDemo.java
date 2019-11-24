import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @author byJiang
 * @title TestDemo
 * @date 2019/11/24
 **/
public class TestDemo {
    public static void main(String[] args) {
//        String saltTest = BCrypt.gensalt();
//        System.out.println(saltTest);
        String salt = "$2a$10$YhSUMEN9eD2Ov6uMybeeq.";//BCrypt.gensalt();
        String result = BCrypt.hashpw("12345", salt);
        System.out.println("salt:" + salt + ", result:" + result); //wifGZRwXcqpnpbe9SnSQ4bvErfvKoS2

    }
}
