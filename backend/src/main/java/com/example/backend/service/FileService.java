package com.example.backend.service;

import com.azure.core.http.rest.PagedIterable;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class FileService {

    BlobServiceClient blobServiceClient;
    BlobContainerClient blobContainerClient;

    public void upload(MultipartFile[] multipartFiles)
            throws IOException {
        for (MultipartFile multipartFile : multipartFiles){
            BlobClient blob = blobContainerClient
                    .getBlobClient(multipartFile.getOriginalFilename());
            blob.upload(multipartFile.getInputStream(),
                    multipartFile.getSize(), true);
        }
    }


    public byte[] getFile(String fileName) {
        BlobClient blob = blobContainerClient.getBlobClient(fileName);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        blob.downloadStream(outputStream);
        return outputStream.toByteArray();
    }

    public List<String> listBlobs() {
        PagedIterable<BlobItem> items = blobContainerClient.listBlobs();
        return items.stream().map(BlobItem::getName).toList();
    }

    public void deleteBlob(String blobName) {
        BlobClient blob = blobContainerClient.getBlobClient(blobName);
        blob.delete();
    }

}
