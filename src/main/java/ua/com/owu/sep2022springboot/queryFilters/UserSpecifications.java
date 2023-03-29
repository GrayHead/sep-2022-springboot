package ua.com.owu.sep2022springboot.queryFilters;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import ua.com.owu.sep2022springboot.models.User;

public class UserSpecifications {
    public static Specification<User> byName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<User> byAge(int age) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.gt(root.get("age"), age);
    }

    public static Specification<User> byId(int id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

//    public static Specification<User> byGendre(String genre) {
//        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("genre"), genre);
//    }


//    public static Specification<User> trasdasdladkgasdId(List<Genre> list) {
//        return new Specification<User>() {
//            @Override
//            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                return criteriaBuilder.in(root.get("genre")).value(list);
//            }
//        };
//    }


}
