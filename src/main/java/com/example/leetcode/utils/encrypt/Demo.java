package com.example.leetcode.utils.encrypt;

import com.example.leetcode.utils.Util;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

/**
 * @Auther: Rxh
 * @Date: 2019/12/31 17:20
 * @Description: 主流加密算法demo
 * <p>
 * https://juejin.im/post/5e09741f518825497837833b
 */
public class Demo {

    public static void main(String[] args) {
        Util.invokeThenExit((o) -> {
            try {
                //base64
                String demo = "123456";
                byte[] bytes = Base64Demo.base64Encode(demo);
                System.out.println(Arrays.toString(bytes));
                System.out.println(Base64Demo.base64Decode(bytes));

                byte[] md5Encrypt = MD5Demo.md5Encrypt(demo);
                System.out.println(Arrays.toString(md5Encrypt));
                System.out.println(new BigInteger(md5Encrypt).toString(16));

                //RSA Encrypt
                RSADemo rsaDemo = new RSADemo();
                rsaDemo.generateKeys(512);

                String privateEncrypt = rsaDemo.privateEncrypt(demo);
                System.out.println(privateEncrypt);
                System.out.println(rsaDemo.publicDecrypt(privateEncrypt));
//
                String publicEncrypt = rsaDemo.publicEncrypt(demo);
                System.out.println(publicEncrypt);
                System.out.println(rsaDemo.privateDecrypt(publicEncrypt));

                System.out.println(Arrays.toString(SHADemo.encryptSHA(demo.getBytes())));

                String initMacKey = HMACDemo.initMacKey();
                System.out.println(initMacKey);
                System.out.println(Arrays.toString(HMACDemo.encryptHMAC(demo.getBytes(), initMacKey)));

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * HMAC(Hash Message Authentication Code，散列消息鉴别码，基于密钥的Hash算法的认证协议。
     * 消息鉴别码实现鉴别的原理是，用公开函数和密钥产生一个固定长度的值作为认证标识，用这个标识鉴别
     * 消息的完整性。使用一个密钥生成一个固定大小的小数据块，即MAC，并将其加入到消息中，然后传输。
     * 接收方利用与发送方共享的密钥进行鉴别认证等
     *
     */
    private static class HMACDemo{
        /**
         * 初始化HMAC密钥
         *
         * @return
         * @throws Exception
         */
        public static String initMacKey() throws Exception {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");

            SecretKey secretKey = keyGenerator.generateKey();
            return Base64Demo.base64Decode(secretKey.getEncoded());
        }

        /**
         * HMAC加密
         *
         * @param data
         * @param key
         * @return
         * @throws Exception
         */
        public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

            SecretKey secretKey = new SecretKeySpec(Base64Demo.base64Encode(key), "HmacMD5");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);

            return mac.doFinal(data);

        }
    }

    /**
     * SHA(Secure Hash Algorithm，安全散列算法），数字签名等密码学应用中重要的工具，被广泛地应用于电子商务
     * 等信息安全领域。虽然，SHA与MD5通过碰撞法都被破解了， 但是SHA仍然是公认的安全加密算法，较之MD5更为安全
     */
    private static class SHADemo{
        public static byte[] encryptSHA(byte[] data) throws Exception {

            MessageDigest sha = MessageDigest.getInstance("sha");
            sha.update(data);

            return sha.digest();

        }
    }

    /**
     * MD5 -- message-digest algorithm 5 （信息-摘要算法）缩写，广泛用于加密和解密技术，常用于文件校验。
     * 校验？不管文件多大，经过MD5后都能生成唯一的MD5值。好比现在的ISO校验，都是MD5校验。怎么用？当然是把ISO
     * 经过MD5后产生MD5的值。一般下载linux-ISO的朋友都见过下载链接旁边放着MD5的串。就是用来验证文件是否一致的。
     *
     */
    private static class MD5Demo{
        public static byte[] md5Encrypt(String string) throws Exception {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            md5.update(string.getBytes());

            return md5.digest();
        }
    }
    /**
     * 按照RFC2045的定义，Base64被定义为：Base64内容传送编码被设计用来把任意序列的8位字节描述为一种不易被人直接识别的形式。
     * （The Base64 Content-Transfer-Encoding is designed to represent arbitrary sequences of octets in a form that need not be humanly readable.） 
     * 常见于邮件、http加密，截取http信息，你就会发现登录操作的用户名、密码字段通过BASE64加密的。
     *
     */
    private static class Base64Demo{

        public static byte[] base64Encode(String string) throws Exception {
            return (new BASE64Decoder()).decodeBuffer(string);
        }

        public static String base64Decode(byte[] bytes) throws Exception {
            return (new BASE64Encoder()).encode(bytes);
        }
    }

    private static class RSADemo {
        public static final String RSA = "RSA";
        public static final String CHARSET = "utf-8";

        //密钥字符串（经过base64编码）
        public static String publicKeyStr, privateKeyStr;

        public void generateKeys(int keySize) {
            try {
                //创建KeyPairGenerator对象
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
                keyPairGenerator.initialize(keySize);
                //生成秘钥对
                KeyPair keyPair = keyPairGenerator.genKeyPair();
                PublicKey PK = keyPair.getPublic();
                PrivateKey SK = keyPair.getPrivate();

                publicKeyStr = Base64.encodeBase64URLSafeString(PK.getEncoded());
                privateKeyStr = Base64.encodeBase64URLSafeString(SK.getEncoded());
                System.out.println("publicKeyStr: " + publicKeyStr);
                System.out.println("privateKeyStr: " + privateKeyStr);

            } catch (NoSuchAlgorithmException e) {
                System.out.println("生成秘钥失败");
                e.printStackTrace();
            }
        }

        /**
         * 得到公钥
         *
         * @throws Exception
         */
        public RSAPublicKey getPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
            //通过X509编码的Key指令获得公钥对象
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyStr));
            RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
            return key;
        }

        /**
         * 得到私钥
         *
         * @throws Exception
         */
        public RSAPrivateKey getPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
            //通过PKCS#8编码的Key指令获得私钥对象
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyStr));
            RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
            return key;
        }

        /**
         * 公钥加密 对应 私钥解密
         *
         * @param data
         * @return
         */
        public String publicEncrypt(String data) {
            try {
                RSAPublicKey publicKey = getPublicKey();
                Cipher cipher = Cipher.getInstance(RSA);
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), publicKey.getModulus().bitLength()));
            } catch (Exception e) {
                throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
            }
        }

        /**
         * 私钥解密
         *
         * @param data
         * @return
         */
        public String privateDecrypt(String data) {
            try {
                RSAPrivateKey privateKey = getPrivateKey();
                Cipher cipher = Cipher.getInstance(RSA);
                cipher.init(Cipher.DECRYPT_MODE, privateKey);
                return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), privateKey.getModulus().bitLength()), CHARSET);
            } catch (Exception e) {
                throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
            }
        }


        /**
         * 私钥加密 对应公钥解密
         *
         * @param data
         * @return
         */

        public String privateEncrypt(String data) {
            try {
                RSAPrivateKey privateKey = getPrivateKey();
                Cipher cipher = Cipher.getInstance(RSA);
                cipher.init(Cipher.ENCRYPT_MODE, privateKey);
                return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), privateKey.getModulus().bitLength()));
            } catch (Exception e) {
                throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
            }
        }

        /**
         * 公钥解密
         *
         * @param data
         * @return
         */

        public String publicDecrypt(String data) {
            try {
                RSAPublicKey publicKey = getPublicKey();
                Cipher cipher = Cipher.getInstance(RSA);
                cipher.init(Cipher.DECRYPT_MODE, publicKey);
                return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), publicKey.getModulus().bitLength()), CHARSET);
            } catch (Exception e) {
                throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
            }
        }

        private byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize) {
            int maxBlock = 0;
            if (opmode == Cipher.DECRYPT_MODE) {
                maxBlock = keySize / 8;
            } else {
                maxBlock = keySize / 8 - 11;
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] buff;
            int i = 0;
            try {
                while (datas.length > offSet) {
                    if (datas.length - offSet > maxBlock) {
                        buff = cipher.doFinal(datas, offSet, maxBlock);
                    } else {
                        buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                    }
                    out.write(buff, 0, buff.length);
                    i++;
                    offSet = i * maxBlock;
                }
            } catch (Exception e) {
                throw new RuntimeException("加解密阀值为[" + maxBlock + "]的数据时发生异常", e);
            }
            byte[] resultDatas = out.toByteArray();
            Util.closeCloseable(out);
            return resultDatas;
        }
    }
}
