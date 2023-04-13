package com.app.spring.security.jwt.service;

import com.app.spring.security.jwt.constant.AppConstant;
import com.app.spring.security.jwt.models.Category;
import com.app.spring.security.jwt.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("No data!"));
    }
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    public Map<String, Object> findAllWithPaging(int page, String sortBy, String name) {
        Pageable pagingSort = PageRequest.of(page-1, AppConstant.globalPageSize, Sort.by(sortBy).descending());
        Page<Category> categories;
        if (name == null || name.isEmpty()){
            categories = categoryRepository.findAll(pagingSort);
        }else {
            categories = categoryRepository.findByNameContaining(name, pagingSort);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("categories", categories.get().toList());
        response.put("currentPage", categories.getNumber() + 1);
        response.put("totalItems", categories.getTotalElements());
        response.put("totalPages", categories.getTotalPages());
        response.put("pageSize", categories.getSize());
        return response;
    }
}
