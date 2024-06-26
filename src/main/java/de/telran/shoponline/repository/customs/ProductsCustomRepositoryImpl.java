package de.telran.shoponline.repository.customs;

import de.telran.shoponline.entity.Categories;
import de.telran.shoponline.entity.Products;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

public class ProductsCustomRepositoryImpl implements ProductsCustomRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Products> findProductsByFilter(Categories category, Double minPrice, Double maxPrice,
                                               Boolean isDiscount, String sort) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Products> query = cb.createQuery(Products.class); //SELECT
        Root<Products> root = query.from(Products.class); //FROM

        // WHERE
        List<Predicate> predicates = new ArrayList<>();
        if(category !=null ) {
            predicates.add(cb.equal(root.get("category"), category)); //category = 1
        }
        Predicate filterPrice = null;
        if(minPrice != null && maxPrice != null && minPrice < maxPrice) {
            filterPrice = cb.between(root.get("price"), minPrice, maxPrice);
        } else if(minPrice != null) {//>minPrice
            filterPrice = cb.gt(root.get("price"), minPrice); // price > 1
        } else if(maxPrice != null) {//<maxPrice
            filterPrice = cb.lt(root.get("price"), maxPrice); // price < 10
        }
        if(filterPrice != null) {
            predicates.add(filterPrice);
        }
        if(isDiscount) {
            predicates.add(cb.gt(root.get("discountPrice"), 0));
        }

        // сортировка SORT
        Order sortOrder = null;
        if(sort!=null) {
            String[] sortArr = sort.split(",");
            if(sortArr.length==2) {
                if(sortArr[1].equalsIgnoreCase("DESC")) {
                    sortOrder = cb.desc(root.get(sortArr[0]));
                } else {
                    sortOrder = cb.asc(root.get(sortArr[0]));
                }
            } else { //если не передали тип сортировки
                sortOrder = cb.asc(root.get(sortArr[0]));
            }
        }
        if(sortOrder == null){ //сортировка по умолчанию
            sortOrder = cb.asc(root.get("name"));
        }
        // Нужно сделать ExceptionHandler на org.springframework.dao.InvalidDataAccessApiUsageException,
        // или org.hibernate.query.sqm.PathElementException, если не правильно передали поле для сортировки

        query.select(root)
                .where(cb.and(predicates.toArray(new Predicate[predicates.size()])))
                .orderBy(sortOrder);
        return entityManager.createQuery(query).getResultList();
    }
}
