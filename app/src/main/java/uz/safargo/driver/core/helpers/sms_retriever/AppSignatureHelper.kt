package uz.safargo.driver.core.helpers.sms_retriever



import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.os.Build
import android.util.Base64
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*


class AppSignatureHelper(context: Context) : ContextWrapper(context) {

    companion object {
        private const val HASH_TYPE = "SHA-256"
        const val NUM_HASHED_BYTES = 9
        const val NUM_BASE64_CHAR = 11
    }

    fun getAppSignature(): String {
        val appSignatures = getAppSignatures()
        return if (appSignatures.isNotEmpty()) {
            appSignatures.first()
        } else {
            "NA"
        }
    }

    @SuppressLint("PackageManagerGetSignatures")
    fun getAppSignatures(): ArrayList<String> {
        val appCodes = ArrayList<String>()
        try {
            val packageName = packageName
            val packageManager = packageManager


            val signatures: Array<Signature> = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

                packageManager.getPackageInfo(
                    packageName,
                    PackageManager.GET_SIGNING_CERTIFICATES
                ).signingInfo.apkContentsSigners
            } else {

                @Suppress("DEPRECATION")
                packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES).signatures
            }

            for (signature in signatures) {
                val hash = hash(packageName, signature.toCharsString())
                if (hash != null) {
                    appCodes.add(hash)
                }
            }
        } catch (_: PackageManager.NameNotFoundException) {
        }
        return appCodes
    }


    private fun hash(packageName: String, signature: String): String? {
        val appInfo = "$packageName $signature"
        return try {
            val messageDigest = MessageDigest.getInstance(HASH_TYPE)
            messageDigest.update(appInfo.toByteArray(StandardCharsets.UTF_8))
            val hashSignature: ByteArray = messageDigest.digest()

            val truncatedHashSignature = Arrays.copyOfRange(hashSignature, 0, NUM_HASHED_BYTES)
            var base64Hash = Base64.encodeToString(truncatedHashSignature, Base64.NO_PADDING or Base64.NO_WRAP)
            base64Hash = base64Hash.substring(0, NUM_BASE64_CHAR)

            base64Hash
        } catch (e: NoSuchAlgorithmException) {
            null
        }
    }

}




