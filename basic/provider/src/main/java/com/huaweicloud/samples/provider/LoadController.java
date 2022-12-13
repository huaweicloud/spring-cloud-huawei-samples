package com.huaweicloud.samples.provider;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider/benchmark")
public class LoadController {
  private static final Logger LOGGER = LoggerFactory.getLogger(LoadController.class);

  public static String RSA_ALGORITHM = "RSA";

  /**
   * 密钥长度，DSA算法的默认密钥长度是1024
   * 密钥长度必须是64的倍数，在512到65536位之间
   * */
  private static final int KEY_SIZE = 1024;

  @GetMapping("/health")
  public boolean health() {
    return true;
  }

  @PostMapping("/load")
  public String load(@RequestParam(name = "type") int type, @RequestParam(name = "time") int time,
      @RequestBody String input) {
    if (time <= 0) {
      return input;
    }

    if (type == 0) {
      try {
        Thread.sleep(time);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    if (type == 1) {
      executeConsuming(time);
    }

    return input;
  }

  private void executeConsuming(int time) {
    long begin = System.currentTimeMillis();
    while (System.currentTimeMillis() - begin < time) {
      try {
        String password = "1234abcd5678";
        KeyStore keys = createKeys();
        byte[] publicKey = getPublicKey(keys);
        byte[] privateKey = getPrivateKey(keys);
        byte[] encryptByPublicKey = encryptByPublicKey(password.getBytes(), publicKey);
        byte[] decryptByPrivateKey = decryptByPrivateKey(encryptByPublicKey, privateKey);
        if (!password.equals(new String(decryptByPrivateKey))) {
          throw new IllegalArgumentException("wrong");
        }
      } catch (Exception e) {
        LOGGER.error("", e);
      }
    }
    LOGGER.info("execute consuming token {}", System.currentTimeMillis() - begin);
  }

  public static KeyStore createKeys() throws NoSuchAlgorithmException {
    //KeyPairGenerator用于生成公钥和私钥对。密钥对生成器是使用 getInstance 工厂方法
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
    keyPairGenerator.initialize(KEY_SIZE);
    KeyPair keyPair = keyPairGenerator.generateKeyPair();
    RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
    RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
    return new KeyStore(publicKey, privateKey);
  }

  private static byte[] getPrivateKey(KeyStore keyStore) {
    return ((RSAPrivateKey) keyStore.privateKey).getEncoded();
  }

  private static byte[] getPublicKey(KeyStore keyStore) {
    return ((RSAPublicKey) keyStore.publicKey).getEncoded();
  }

  private static byte[] encryptByPublicKey(byte[] data, byte[] key) throws NoSuchAlgorithmException,
      InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException,
      InvalidKeyException {
    //实例化密钥工厂
    KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
    //初始化公钥,根据给定的编码密钥创建一个新的 X509EncodedKeySpec。
    X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(key);
    PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
    //数据加密
    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    return cipher.doFinal(data);
  }

  public static byte[] decryptByPrivateKey(byte[] data, byte[] key) throws Exception {
    //取得私钥
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
    KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
    //生成私钥
    PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
    //数据解密
    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    return cipher.doFinal(data);
  }

  public static class KeyStore {
    private final Object publicKey;

    private final Object privateKey;

    public KeyStore(Object publicKey, Object privateKey) {
      this.publicKey = publicKey;
      this.privateKey = privateKey;
    }
  }
}
