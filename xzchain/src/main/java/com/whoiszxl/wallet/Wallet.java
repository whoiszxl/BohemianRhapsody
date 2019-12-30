package com.whoiszxl.wallet;

import com.whoiszxl.utils.AddressUtils;
import com.whoiszxl.utils.Base58Check;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.security.*;

/**
 * @description: 钱包管理类
 * @author: whoiszxl
 * @create: 2019-11-29
 **/
@Data
@Slf4j
@AllArgsConstructor
public class Wallet implements Serializable {

    private static final long serialVersionUID = 1651257570288656126L;

    /**
     * 校验码长度
     */
    private static final int ADDRESS_CHECKSUM_LEN = 4;
    /**
     * 私钥
     */
    private BCECPrivateKey privateKey;
    /**
     * 公钥
     */
    private byte[] publicKey;

    public Wallet() {
        initWallet();
    }

    /**
     * 初始化钱包
     */
    private void initWallet() {
        try {
            KeyPair keyPair = newECKeyPair();
            BCECPrivateKey privateKey = (BCECPrivateKey) keyPair.getPrivate();
            BCECPublicKey publicKey = (BCECPublicKey) keyPair.getPublic();
            byte[] publicKeyBytes = publicKey.getQ().getEncoded(false);
            this.setPublicKey(publicKeyBytes);
            this.setPrivateKey(privateKey);
        }catch (Exception e) {
            log.error("【初始化钱包失败】", e);
        }
    }


    /**
     * 创建密钥对
     * @return
     * @throws Exception
     */
    private KeyPair newECKeyPair() throws Exception {
        //注册BC Provider
        Security.addProvider(new BouncyCastleProvider());
        // 创建椭圆曲线算法的密钥对生成器，算法为 ECDSA
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", BouncyCastleProvider.PROVIDER_NAME);
        // 椭圆曲线（EC）域参数设定
        // bitcoin 为什么会选择 secp256k1，详见：https://bitcointalk.org/index.php?topic=151120.0
        ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256k1");
        keyPairGenerator.initialize(ecSpec, new SecureRandom());
        return keyPairGenerator.generateKeyPair();
    }


    /**
     * 获取钱包地址
     * @return
     */
    public String getAddress() {
        try {
            //1. 获取 ripemdHashedKey
            byte[] ripemdHashedKey = AddressUtils.ripeMD160Hash(this.getPublicKey());

            // 2. 添加版本 0x00
            ByteArrayOutputStream addrStream = new ByteArrayOutputStream();
            addrStream.write((byte) 0);
            addrStream.write(ripemdHashedKey);
            byte[] versionedPayload = addrStream.toByteArray();

            // 3. 计算校验码
            byte[] checksum = AddressUtils.checksum(versionedPayload);

            // 4. 得到 version + paylod + checksum 的组合
            addrStream.write(checksum);
            byte[] binaryAddress = addrStream.toByteArray();

            // 5. 执行Base58转换处理
            return Base58Check.rawBytesToBase58(binaryAddress);

        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Fail to get wallet address ! ");
    }
}
