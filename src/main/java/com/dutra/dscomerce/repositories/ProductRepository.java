package com.dutra.dscomerce.repositories;

import com.dutra.dscomerce.dtos.ProductDto;
import com.dutra.dscomerce.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT obj FROM ProductEntity obj WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<ProductEntity> searchByName(Pageable pageable, String name);






    //N+1 problem - only to study. ManyToMany relation. Page do not work here.
    @Query("SELECT obj from ProductEntity obj JOIN FETCH obj.categories")
    List<ProductDto> findCategoriesList();

    //Pageable search.
    @Query("SELECT obj from ProductEntity obj JOIN FETCH obj.categories WHERE obj IN :products")
    List<ProductEntity> findCategoriesPage(List<ProductEntity> products);

    //NATIVE
//    select * from tb_product
//    inner join tb_product_category on tb_product.id = tb_product_category.product_id
//    inner join tb_category on tb_category.id = tb_product_category.category_id
//    where tb_product.id in (1,2,3,4,5,6)
}
