package com.example.demo.JDBC.repository;

import com.example.demo.JDBC.entity.Hexa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HexaRepository extends JpaRepository<Hexa, String> {
    // 这里可以自定义查询方法
}
