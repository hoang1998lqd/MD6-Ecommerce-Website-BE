package com.example.md6be.service.impl;

import com.example.md6be.model.ImageURL;
import com.example.md6be.repository.ImageURLRepository;
import com.example.md6be.service.ImageURLGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ImageURLService implements ImageURLGet {
    @Autowired
    private ImageURLRepository imageURLRepository;
    @Override
    public List<String> findByProduct(Long id) {
        return imageURLRepository.findImageURLByProductId(id);
    }

    @Override
    public List<ImageURL> findAll() {
        return imageURLRepository.findAll();
    }

    @Override
    public Optional<ImageURL> findById(Long id) {
        return imageURLRepository.findById(id);
    }

    @Override
    public ImageURL save(ImageURL imageURL) {
        return imageURLRepository.save(imageURL);
    }

    @Override
    public void delete(Long id) {
    imageURLRepository.deleteById(id);
    }

    @Override
    public List<Long> findIdByProductId(Long id) {
        return imageURLRepository.findIdByProductId(id);
    }
}
