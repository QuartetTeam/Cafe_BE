package quartet.cafe.presentation.common.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import quartet.cafe.infrastructure.s3.S3Uploader;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final S3Uploader s3Uploader;

    @Operation(summary = "파일 업로드", description = "S3에 파일을 업로드합니다.")
    @ApiResponse(responseCode = "200", description = "업로드 성공")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) {
        String imageUrl = s3Uploader.upload(file, "test-dir");
        return ResponseEntity.ok(imageUrl);
    }
}
