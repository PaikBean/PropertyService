package com.propertyservice.propertyservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
@Slf4j
@RequiredArgsConstructor
public class EntityExceptionService {

    /**
     * @param findEntityFunction  = '() -> departmentRepository.findByDepartmentName(departmentName)'
     * @param notFoundMessage   = "부서명이 존재 하지 않습니다"
     */
    public <T> T findEntityById(Supplier<Optional<T>> findEntityFunction, String notFoundMessage) {
        return findEntityFunction.get().orElseThrow(
                () -> new EntityNotFoundException(notFoundMessage));
    }

    // 객체를 반환하지 않고, 조건에 따라 처리만 수행하는 메서드
    public <T> void validateEntityExists(Supplier<Optional<T>> findEntityFunction, String notFoundMessage) {
         findEntityFunction.get().orElseThrow(
                () -> new EntityNotFoundException(notFoundMessage));
    }

    public <T> T findEntityByIdNotExistsThenNull(Supplier<Optional<T>> findEntityFunction) {
        return findEntityFunction.get().orElse(null);
    }


//    // 제네릭 메소드: 엔티티와 레포지토리 타입을 파라미터로 받아서 처리
//    public <T> T findByIdOrThrow(Long id, JpaRepository<T, Long> repository, String errorMessage) {
//        return repository.findById(id)
//                .orElseThrow(() -> new RuntimeException(errorMessage));
//    }
//
//    // void 반환 메소드: 엔티티 존재 여부를 검사
//    public <T> void validateEntityExists(Long id, JpaRepository<T, Long> repository, String errorMessage) {
//        if (!repository.existsById(id)) {
//            throw new RuntimeException(errorMessage);
//        }
//    }
}
