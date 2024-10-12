package uz.safargo.driver.features.auth.data.models

import uz.safargo.driver.core.constants.AppConstants
import uz.safargo.driver.features.auth.domain.entities.UploadFileEntity

data class UploadFileResponse(
    val data: List<UploadFileData>? = null
)

data class UploadFileData(
    val id: String? = null,
    val type: String? = null,
    val name: String? = null,
    val size: String? = null,
    val key: String? = null,
    val hash: String? = null,
    val info: Info? = null,
    val url: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val v: String? = null
)

data class Info(
    val etag: String? = null,
    val versionId: Any? = null
)


fun UploadFileResponse.toEntity(): UploadFileEntity {
    return UploadFileEntity(
        url = "${AppConstants.cdnUrl}/files/${data?.firstOrNull()?.key}"
    )
}